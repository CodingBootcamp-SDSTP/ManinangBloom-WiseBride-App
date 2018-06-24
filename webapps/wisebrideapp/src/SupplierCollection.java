import java.util.ArrayList;

public class SupplierCollection
{
	private ArrayList<Supplier> suppliers;

	public SupplierCollection() {
		suppliers = new ArrayList<Supplier>();
	}

	public void addSupplier(Supplier supplier) {
		suppliers.add(supplier);
	}

	public void removeSupplier(Supplier supplier) {
		suppliers.remove(supplier);
	}

	public ArrayList<Supplier> getAllSuppliers() {
		return(suppliers);
	}

	public Supplier getSupplierByIndex(int n) {
		return(suppliers.get(n));
	}

	public int getSupplierCount() {
		return(suppliers.size());
	}

	public ArrayList<Supplier> search(String s) {
		Supplier a = null;
		ArrayList<Supplier> al = new ArrayList<Supplier>();
		String str = s.toLowerCase();
		for(int i=0; i<getSupplierCount(); i++) {
			a = getSupplierByIndex(i);
			if(matches(a, str)) {
				al.add(a);
			}
		}
		return(al);
	}

	public boolean matches(Supplier a, String str) {
		String name = a.getName().toLowerCase();
		if(name.contains(str)) {
			return(true);
		}
		return(false);
	}
}
