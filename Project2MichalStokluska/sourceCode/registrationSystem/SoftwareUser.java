package registrationSystem;
import java.io.Serializable;

public class SoftwareUser implements Serializable{
	private String id;
	private String password;
	private int organisationType;
	private String organisationName;
	private String randomAttribute1;
	private String randomAttribute2;
	private String randomAttribute3;
	private String randomAttribute4;

	public SoftwareUser(String idIn, String passwordIn) {
		this.id = idIn;
		this.password = passwordIn;
	}

	public void setId(String idIn) {
		this.id = idIn;
	}

	public void setPassword(String passwordIn) {
		this.password = passwordIn;
	}

	public void setOrganisationType(int orgIn) {
		this.organisationType = orgIn;
	}
	public void setOrganisationName(String orgIn) {
		organisationName = orgIn;
	}

	public String getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public int getOrgType() {
		return organisationType;
	}
	public String getOrganisationName() {
		return organisationName;
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
	public String toString()
	{
		return "{" +  id + " , " + password +"}";
	}

	public boolean equals(SoftwareUser rIn)
	{
	return id == rIn.id && password == rIn.password;
	}
}