import java.util.ArrayList;

class OfficerCollection
{
	ArrayList<Officer> officers;

	public OfficerCollection() {
		officers = new ArrayList<Officer>();
	}

	public void addOfficer(Officer officer) {
		officers.add(officer);
	}

	public void removeOfficer(Officer officer) {
		officers.remove(officer);
	}

	public ArrayList<Officer> getAllOfficers() {
		return(officers);
	}

	public Officer getOfficerByIndex(int n) {
		return(officers.get(n));
	}

	public int getOfficerCount() {
		return(officers.size());
	}

	public Officer getOfficerById(String id) {
		Officer officer = null;
		for(int i=0; i<getOfficerCount(); i++) {
			if(getOfficerByIndex(i).getId().equalsIgnoreCase(id)) {
				officer = getOfficerByIndex(i);
			}
		}
		return(officer);
	}

	public ArrayList<Officer> search(String s) {
		Officer p = null;
		ArrayList<Officer> pl = new ArrayList<Officer>();
		String str = s.toLowerCase();
		for(int i=0; i<getOfficerCount(); i++) {
			p = getOfficerByIndex(i);
			if(matches(p, str)) {
				pl.add(p);
			}
		}
		return(pl);
	}

	public boolean matches(Officer p, String str) {
		String id = p.getId().toLowerCase();
		String firstName = p.getFirstName().toLowerCase();
		String lastName = p.getLastName().toLowerCase();
		String contactNumber = p.getContactNumber();
		String department = p.getDepartment().toLowerCase();
		if(id.contains(str) || firstName.contains(str) || lastName.contains(str) || contactNumber.contains(str) || department.contains(str)) {
			return(true);
		}
		return(false);
	}
}
