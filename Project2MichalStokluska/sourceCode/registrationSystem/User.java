package registrationSystem;
import java.io.Serializable;

public class User implements Serializable{
	private String name, surname, randomAttribute1, randomAttribute2, randomAttribute3, randomAttribute4;
	private int age;
	
	
	public User (String nameIn, String surnameIn) {
		name = nameIn;
		surname = surnameIn;
	}
	public void setName(String nameIn) {
		name = nameIn;
	}
	public void setSurname(String surnameIn) {
		surname = surnameIn;
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
	public void setAge(int ageIn) {
		age = ageIn;
	}
	public String getName() {
		return name;
	}
	public String getSurname() {
		return surname;
	}
	public int getAge() {
		return age;
	}
	public boolean equals(User userIn)
	{
	return name == userIn.name && surname == userIn.surname;
	}
	public String toString()
	{
		CurrentSoftwareUser currentUser = new CurrentSoftwareUser();
		return "Name: " +  name + ", Surname: " + surname + ", " + currentUser.getRandomAttribute1() + ": " + randomAttribute1 + ", " + currentUser.getRandomAttribute2() + ": " + randomAttribute2 + ", " + currentUser.getRandomAttribute3() + ": " + randomAttribute3 + ", " + currentUser.getRandomAttribute4() + ": " + randomAttribute4 + "\n\n";
	}
}
