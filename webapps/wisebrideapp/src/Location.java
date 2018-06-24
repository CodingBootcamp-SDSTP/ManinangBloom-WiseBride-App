public class Location
{
	private final String ID;
	private String name;
	private String city;
	private String country;
	private String address;

	public Location(String id, String n, String c, String ctr, String add) {
		ID = id;
		name = n;
		city = c;
		country = ctr;
		address = add;
	}

	public String getId() {
		return(ID);
	}

	public String getName() {
		return(name);
	}

	public void setName(String n) {
		name = n;
	}

	public String getCity() {
		return(city);
	}

	public void setCity(String c) {
		city = c;
	}

	public String getCountry() {
		return(country);
	}

	public void setCountry(String c) {
		country = c;
	}

	public String getAddress() {
		return(address);
	}

	public void setAddress(String a) {
		address = a;
	}

	public String toString() {
		return("<ID>" + ID + "</ID>" + "<name>" + name + "</name>" + "<city>" + city + "</city>" + "<country>" + country + "</country>" + "<address>" + address + "</address>");
	}

	public String getDetails() {
		return(ID + "@" + name + "@" + city + "@" + country + "@" + address + "@location");
	}
}
