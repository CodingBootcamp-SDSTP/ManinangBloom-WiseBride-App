public class Caterer extends Supplier implements SupplierWithLocation, AssignableSupplier, SpecialToString
{
	private String location;
	private String assignedTo;

	public Caterer(String n) {

		setName(n);
		location = "";
		assignedTo = "";
	}

	public Caterer(String n, String loc, String a) {
		assignedTo = a;
		location = loc;

		setName(n);
	}

	public void setAssignedTo(String n) {
		assignedTo = n;
	}

	public String getAssignedTo() {
		return(assignedTo);
	}

	public void setLocation(String loc) {
		location = loc;
	}

	public String getLocation() {
		return(location);
	}

	public String toString(SupplierManagerDatabase amd) {
		String officerDetails = "";
		Officer officer = amd.getOfficerById(assignedTo);
		if(officer != null) {
			officerDetails = "\nAssigned to: " + officer.toString();
		}
		return(toString() + ", Location: " + location + officerDetails);
	}

	@Override
	public String getDetails() {
		return(super.getDetails() + "@" + location + "@" + assignedTo + "@caterer");
	}
}
