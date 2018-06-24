public class Supplier
{
	private String name;

	public void setName(String n) {
		name = n;
	}

	public String getName() {
		return(name);
	}

	public String toString() {
		return("<name>" + name + "</name>");
	}

	public String getDetails() {
		return(name + "@");
	}
}
