import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

public class AllLocationsServlet extends HttpServlet
{
	LocationCollection locations;

	public void init() throws ServletException {
		SupplierManagerDatabase sup = new SupplierManagerDatabase();
		locations = sup.getLocationCollection();
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/xml");
		PrintWriter out = res.getWriter();
		out.print(exportToXML());
		System.out.println("output" + exportToXML());
	}

	public String exportToXML() {
		StringBuilder sb = new StringBuilder("<locations>");ArrayList<Location> location = locations.getAllLocations();
		for(Location l: location) {
			sb.append("<location><locationid>");
			sb.append(l.getId());
			sb.append("</locationid><locationname>");
			sb.append(l.getName());
			sb.append("</locationname><city>");
			sb.append(l.getCity());
			sb.append("</city><country>");
			sb.append(l.getCountry());
			sb.append("</country><address>");
			sb.append(l.getAddress());
			sb.append("</address></location>");
		}
		sb.append("</locations>");
		return(sb.toString());
	}

	public void destroy() {
		locations = null;
	}
}
