package registrationSystem;
//class to store current software users that are logging in and their club detais and random attribute of their own user lists.
public class CurrentSoftwareUser{
	private static String id;
	private static int organisationType;
	private static String organisationName;
	private static String randomAttribute1;
	private static String randomAttribute2;
	private static String randomAttribute3;
	private static String randomAttribute4;
	public CurrentSoftwareUser() {		
	}

	public void setId(String idIn) {
		this.id = idIn;
	}
	public String getId() {
		return id;
	}
	public void setTypeOfOrganisation(int orgIn) {
		organisationType = orgIn;
	}
	public void setOrganisationName(String orgIn) {
		organisationName = orgIn;
	}
	public String getOrganisationName() {
		return organisationName;
	}
	public int getOrganisationType() {
		return organisationType;
	}
	public String getRandomAttribute1() {
		return randomAttribute1;
	}
	public String getRandomAttribute2() {
		return randomAttribute2;
	}
	public String getRandomAttribute3() {
		return randomAttribute3;
	}
	public String getRandomAttribute4() {
		return randomAttribute4;
	}
	public void setRandomAttribute1(String rAIn) {
		randomAttribute1 = rAIn;
	}
	public void setRandomAttribute2(String rAIn) {
		randomAttribute2 = rAIn;
	}
	public void setRandomAttribute3(String rAIn) {
		randomAttribute3 = rAIn;
	}
	public void setRandomAttribute4(String rAIn) {
		randomAttribute4 = rAIn;
	}
}
