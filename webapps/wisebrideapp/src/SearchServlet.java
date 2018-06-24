import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.ArrayList;

public class SearchServlet extends HttpServlet
{
	SupplierCollection suppliers;
	SupplierManagerDatabase sup;

	public void init() throws ServletException {
		sup = new SupplierManagerDatabase();
		suppliers = sup.getSupplierCollection();
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/xml");
		PrintWriter out = res.getWriter();
		String str = req.getQueryString();
		String[] strs = str.split("=");
		out.print(search(strs));
	}

	public String search(String[] s) {
		StringBuilder sb = new StringBuilder("<Suppliers>\n");
		ArrayList<Supplier> supplier = suppliers.getAllSuppliers();
		String text = s[0];
		if("text".contains(text)) {
			String value = s[1];
			ArrayList<Object> o = sup.search(value);
			for(int i=0; i<o.size(); i++) {
				sb.append("<supplier>");
				sb.append(o.get(i));
				sb.append("</supplier>\n");
			}
		}
		sb.append("</suppliers>");
		return(sb.toString());
	}

	public void destroy() {
		suppliers = null;
	}
}
