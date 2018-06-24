import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.sql.*;

public class SupplierManagerDatabase
{
	private SupplierCollection suppliers;
	private OfficerCollection officers;
	private LocationCollection locations;
	Connection conn;

	public SupplierManagerDatabase() {
		officers = new OfficerCollection();
		suppliers = new SupplierCollection();
		locations = new LocationCollection();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/wisebridedb?user=bloom&password=bloom&serverTimezone=UTC&useSSL=false");
			readFromDB(conn);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void addSupplier(Supplier supplier) {
		if(insertToDB(supplier)) {
			suppliers.addSupplier(supplier);
		}
	}

	boolean insertToDB(Supplier supplier) {
		PreparedStatement stmt = null;
		try {
			if(supplier instanceof WeddingVenue) {
				WeddingVenue weddingvenue = (WeddingVenue)supplier;
				stmt = conn.prepareStatement("INSERT INTO suppliers ( name, location, assignedto, type ) VALUES ( ?, ?, ?, ? );");
				stmt.setString(1, weddingvenue.getName());
				stmt.setString(2, weddingvenue.getLocation());
				stmt.setString(3, weddingvenue.getAssignedTo());
				stmt.setString(4, "weddingvenue");
				stmt.executeUpdate();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			return(false);
		}
		finally {
			try { if (stmt != null) stmt.close(); } catch (Exception e) {};
		}
		return(true);
	}

	public SupplierCollection getSupplierCollection() {
		return(suppliers);
	}

	public OfficerCollection getOfficerCollection() {
		return(officers);
	}

	public LocationCollection getLocationCollection() {
		return(locations);
	}

	public Officer getOfficerById(String id) {
		return(officers.getOfficerById(id));
	}

	public ArrayList<Object> search(String s) {
		ArrayList<Object> ao = new ArrayList<Object>();
		ArrayList<Officer> ps = officers.search(s);
		ArrayList<Supplier> as = suppliers.search(s);
		ArrayList<Location> ls = locations.search(s);
		ao.addAll(ps);
		ao.addAll(as);
		ao.addAll(ls);
		return(ao);
	}

	public boolean readFromDB(Connection conn) {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM officers;");
			while(rs.next()) {
				System.out.println(rs.getString("id"));
				String[] str = {
					rs.getString("id"),
					rs.getString("firstname"),
					rs.getString("lastname"),
					rs.getString("contactnumber"),
					rs.getString("department"),
					"officer"
				};
				createObject(str);
			}
			rs = stmt.executeQuery("SELECT * FROM locations;");
			while(rs.next()) {
				System.out.println(rs.getString("id"));
				String[] str = {
					rs.getString("id"),
					rs.getString("name"),
					rs.getString("city"),
					rs.getString("country"),
					rs.getString("address"),
					"location"
				};
				createObject(str);
			}
			rs = stmt.executeQuery("SELECT * FROM suppliers;");
			while(rs.next()) {
				String[] str = {
					rs.getString("name"),
					rs.getString("location"),
					rs.getString("assignedto"),
					rs.getString("type")
				};
				createObject(str);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try { if(stmt!=null) {stmt=null;}} catch(Exception e) {};
			try { if(rs!=null) {rs=null;}} catch(Exception e) {}
		}
		return(true);
	}

	public boolean readFromFile(String filename) {
		boolean f = false;
		try {
			FileReader file = new FileReader(filename);
			BufferedReader fbr = new BufferedReader(file);
			String fileLine;
			while((fileLine = fbr.readLine()) != null) {
				createObject(fileLine);
			}
			f = true;
			fbr.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return(f);
	}

	public void createObject(String... details) {
		int len = details.length - 1;
		String d = details[len];
		if("officer".equals(d)) {
			Officer p = new Officer(details[0], details[1], details[2], details[3], details[4]);
			officers.addOfficer(p);
		}
		else if("location".equals(d)) {
			Location l = new Location(details[0], details[1], details[2],details[3], details[4]);
			locations.addLocation(l);
		}
		else if("caterer".equals(d)) {
			Caterer b = new Caterer(details[0], details[1], details[2]);
			suppliers.addSupplier(b);
		}
		else if("photographyservices".equals(d)) {
			PhotographyServices s = new PhotographyServices(details[0], details[1],details[2]);
			suppliers.addSupplier(s);
		}
		else if("weddingvenue".equals(d)) {
			WeddingVenue t = new WeddingVenue(details[0], details[1], details[2]);
			suppliers.addSupplier(t);
		}
	}

	public boolean writeToFile(String filename) {
		boolean f = false;
		try {
			FileWriter fWriter = new FileWriter(filename);
			BufferedWriter fbw = new BufferedWriter(fWriter);
			fbw.write(getCollectionContent());
			fbw.close();
			f=true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return(f);
	}

	public String getCollectionContent() {
		ArrayList<Officer> p = officers.getAllOfficers();
		ArrayList<Location> l = locations.getAllLocations();
		ArrayList<Supplier> a = suppliers.getAllSuppliers();
		StringBuffer sb = new StringBuffer("");
		for(int i=0; i<p.size(); i++) {
			Officer officer = p.get(i);
			sb.append(officer.getDetails() + "\n");
		}
		for(int i=0; i<l.size(); i++) {
			Location location = l.get(i);
			sb.append(location.getDetails() + "\n");
		}
		for( int i=0; i<a.size(); i++) {
			Supplier supplier = a.get(i);
			sb.append(supplier.getDetails());
			if(i < a.size() -1) {
				sb.append("\n");
			}
		}
		return(sb.toString());
	}
}
