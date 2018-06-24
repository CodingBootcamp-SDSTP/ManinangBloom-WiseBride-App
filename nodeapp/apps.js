const http = require('http');
const mongojs = require('mongojs');
const StaticServer = require('node-static').Server;
const querystring = require('querystring');
const crypto = require('crypto');
const redis = require('redis');
let client = redis.createClient();
let file = new StaticServer('./public');
let db = mongojs('dbname');
let handlers = {};

handlers["/"] = function(req, res) {
	file.serve(req, res);
}

handlers["/suppliers"] = function(req, res) {
	console.log(req.method);
	let qs = querystring.parse(req.url);
	let id = qs['/suppliers?id'];
	doesSessionExist(id, function(reply) {
		if(reply == 1) {
			if(req.method == "POST") {
				console.log("POST request testing");
				var data = "";
				req.on('data', function(dd) {
					data += dd.toString();
				});
				req.on('end', function() {
					let details = JSON.parse(data);
					let content = "name=" + details["name"]
						+ "&location=" + details["location"]
						+ "&assignedto=" + details["assignedto"]
						+ "&type=" + details["type"];
					const options = {
						hostname: '127.0.0.1',
						method: "POST",
						port: 8080,
						path: '/wisebrideapp/addsupplier',
						header: {
							'Content-Type': 'application/x-www-form-urlencoded'
						}
					};
					const httpreq = http.request(options);
					console.log(content);
					httpreq.write(content);
					httpreq.end();
					httpreq.on('response', function(resData) {
						let storeData = "";
						resData.on('data', function(resData) {
							storeData += resData;
						});
						resData.on('end', function() {
						});
					});
				});
			}
			else if(req.method == "GET") {
				const options = {
					hostname: '127.0.0.1',
					port: 8080,
					path: '/wisebrideapp/suppliers'
				};
				const httpreq = http.request(options);
				httpreq.end();
				httpreq.on('response', function(resData) {
					console.log("---------------response event");
					let storeData = "";
					resData.on('data', function(resData) {
						console.log("---------------data event");
						storeData += resData;
					});
					resData.on('end', function() {
						res.writeHead(200, { "Content-Type" : "text/plain"});
						res.write(storeData);
						res.end();
					});
				});
			}
		}
		else {
			res.end();
		}
	});
}

handlers["/login"] = function(req, res) {
	let qs = querystring.parse(req.url);
	let un = qs['/login?username'];
	let pw = qs['password'];
	res.writeHead(200, { "Content-Type" : "text/plain" });
	db.users.findOne({ "username" : un, "password" : pw }, (err, docs) => {
		if(docs != null) {
			startSession(un, (id) => {
				res.write(id);
				res.end();
			});
		}
		else {
			res.end();
		}
	});
}

handlers["/logout"] = function(req, res) {
	let qs = querystring.parse(req.url);
	let id = qs['/suppliers?id'];
	res.writeHead(200, { "Content-Type" : "text/plain" });
	endSession(id, function() {
		console.log("Session ended: " + id);
		res.end();
	});
}

function startSession(name, callback) {
	var id = crypto.randomBytes(16).toString("hex");
	client.set(id, name, function() {
		callback(id)
	});
}

function doesSessionExist(sessionid, callback) {
	client.exists(sessionid, function(err,reply) {
		callback(reply)
	});
}

function endSession(sessionid, callback) {
	client.del(sessionid, function(err, reply) {
		callback()
	});
}

const server = http.createServer(function(req, res) {
	console.log(req.url);
	if(handlers[req.url]) {
		handlers[req.url](req, res);
	}
	else {
		if(req.url.includes("?")) {
			let url = req.url.split("?");
			handlers[url[0]](req, res);
		}
		else {
			res.writeHead(404);
			res.end();
		}
	}
});

server.listen(1505);
console.log("Node server is running..");
