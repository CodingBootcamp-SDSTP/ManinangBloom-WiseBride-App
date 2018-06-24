import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

public class AllOfficersServlet extends HttpServlet
{
	OfficerCollection officers;

	public void init() throws ServletException {
		SupplierManagerDatabase sup = new SupplierManagerDatabase();
		officers = sup.getOfficerCollection();
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/xml");
		PrintWriter out = res.getWriter();
		out.print(exportToXML());
	}

	public String exportToXML() {
		StringBuilder sb = new StringBuilder("<officers>");ArrayList<Officer> officer = officers.getAllOfficers();
		for(Officer p: officer) {
			sb.append("<officer><officerid>");
			sb.append(p.getId());
			sb.append("</officerid><firstname>");
			sb.append(p.getFirstName());
			sb.append("</firstname><lastname>");
			sb.append(p.getLastName());
			sb.append("</lastname><contactnumber>");
			sb.append(p.getContactNumber());
			sb.append("</contactnumber><department>");
			sb.append(p.getDepartment());
			sb.append("</department></officer>");
		}
		sb.append("</officers>");
		return(sb.toString());
	}

	public void destroy() {
		officers = null;
	}
}
