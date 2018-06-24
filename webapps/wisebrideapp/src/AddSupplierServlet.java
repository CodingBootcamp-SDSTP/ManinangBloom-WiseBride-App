import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class AddSupplierServlet extends HttpServlet
{
	SupplierCollection coll;
	SupplierManagerDatabase sup;

	public void init() throws ServletException {
		sup = new SupplierManagerDatabase();
		coll = sup.getSupplierCollection();
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("POST request from nodejs");
		InputStreamReader isr = new InputStreamReader(req.getInputStream());
		BufferedReader br = new BufferedReader(isr);
		String line = br.readLine();
		String[] details = line.split("&");
		String[] name = details[0].split("=");
		String[] location = details[1].split("=");
		String[] assignedto = details[2].split("=");
		String[] type = details[3].split("=");
		if("weddingvenue".equals(type[1])) {
			WeddingVenue weddingvenue = new WeddingVenue(name[1], location[1], assignedto[1]);
			sup.addSupplier(weddingvenue);
		}
		res.setContentType("text/plain");
		PrintWriter out = res.getWriter();
		out.close();
	}

	public void destroy() {
		coll = null;
		sup = null;
	}
}
