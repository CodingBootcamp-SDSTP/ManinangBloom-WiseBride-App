class Officer
{
	private final String ID;
	private String firstName;
	private String lastName;
	private String contactNumber;
	private String department;

	public Officer(String id, String fn, String ln, String a, String dept) {
		ID = id;
		firstName = fn;
		lastName = ln;
		contactNumber = a;
		department = dept;
	}

	public String getId() {
		return(ID);
	}

	public void setFirstName(String fn) {
		firstName = fn;
	}

	public String getFirstName() {
		return(firstName);
	}

	public void setLastName(String ln) {
		lastName = ln;
	}

	public String getLastName() {
		return(lastName);
	}

	public void setContactNumber(String a) {
		contactNumber = a;
	}

	public String getContactNumber() {
		return(contactNumber);
	}

	public void setDepartment(String dept) {
		department = dept;
	}

	public String getDepartment() {
		return(department);
	}

	public String toString() {
		return("<ID>" + ID + "</ID>" + "<firstname>" + firstName + "</firstname>" + "<lastname>" + lastName + "</lastname>" + "<contactnumber>" + contactNumber + "</contactnumber>" + "<department>" + department + "</department>");
	}

	public String getDetails() {
		return(ID + "@" + firstName + "@" + lastName + "@" + contactNumber + "@" + department + "@officer");
	}
}
