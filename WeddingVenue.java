public class WeddingVenue extends Supplier implements SupplierWithLocation, AssignableSupplier, SpecialToString
{
	private String location;
	private String assignedTo;

	public WeddingVenue(String n) {

		setName(n);
		location = "";
		assignedTo = "";
	}

	public WeddingVenue(String n, String loc, String a) {
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
		return(super.getDetails() + "@" + location + "@" + assignedTo + "@weddingVenue");
	}
}
