import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

public class AllSuppliersServlet extends HttpServlet
{
	SupplierCollection suppliers;

	public void init() throws ServletException {
		SupplierManagerDatabase sup = new SupplierManagerDatabase();
		suppliers = sup.getSupplierCollection();
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/xml");
		PrintWriter out = res.getWriter();
		out.print(exportToXML());
	}

	public String exportToXML() {
		StringBuilder sb = new StringBuilder("<suppliers>");ArrayList<Supplier> supplier = suppliers.getAllSuppliers();
		for(Supplier a: supplier) {
			if(a instanceof WeddingVenue) {
				WeddingVenue tb = (WeddingVenue)a;
				sb.append("<weddingvenue><name>");
				sb.append(tb.getName() + "</name><location>");
				sb.append(tb.getLocation() + "</location><assignedto>");
				sb.append(tb.getAssignedTo() + "</assignedto></weddingvenue>");
			}
			else if(a instanceof Caterer) {
				Caterer b = (Caterer)a;
				sb.append("<caterer><name>");
				sb.append(b.getName() + "</name><location>");
				sb.append(b.getLocation() + "</location><assignedto>");
				sb.append(b.getAssignedTo() + "</assignedto></caterer>");
			}
			else if(a instanceof PhotographyServices) {
				PhotographyServices sl = (PhotographyServices)a;
				sb.append("<photographyservices><name>");
				sb.append(sl.getName() + "</name><location>");
				sb.append(sl.getLocation() + "</location><assignedto>");
				sb.append(sl.getAssignedTo() + "</assignedto></photographyservices>");
			}
		}
		sb.append("</suppliers>");
		return(sb.toString());
	}

	public void destroy() {
		suppliers = null;
	}
}
