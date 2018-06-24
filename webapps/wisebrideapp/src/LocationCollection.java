import java.util.ArrayList;

public class LocationCollection
{
	ArrayList<Location> locations;

	public LocationCollection() {
		locations = new ArrayList<Location>();
	}

	public void addLocation(Location location) {
		locations.add(location);
	}

	public void removeLocation(Location location) {
		locations.remove(location);
	}

	public ArrayList<Location> getAllLocations() {
		return(locations);
	}

	public Location getLocationByIndex(int n) {
		return(locations.get(n));
	}

	public int getLocationCount() {
		return(locations.size());
	}

	public ArrayList<Location> search(String s) {
		Location l = null;
		ArrayList<Location> al = new ArrayList<Location>();
		String str = s.toLowerCase();
		for(int i=0; i<getLocationCount(); i++) {
			l = getLocationByIndex(i);
			if(matches(l, str)) {
				al.add(l);
			}
		}
		return(al);
	}

	public boolean matches(Location l, String str) {
		String id = l.getId().toLowerCase();
		String name = l.getName().toLowerCase();
		String city = l.getCity().toLowerCase();
		String country = l.getCountry().toLowerCase();
		String address = l.getAddress().toLowerCase();
		if(id.contains(str) || name.contains(str) || city.contains(str) || country.contains(str) || address.contains(str)) {
			return(true);
		}
		return(false);
	}
}
