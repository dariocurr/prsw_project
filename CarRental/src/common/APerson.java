package common;

import java.util.Objects;

public abstract class APerson {

	protected String firstName;
	protected String lastName;
	protected int age;
	
	public APerson(String firstName, String lastName, int age) {
		Objects.requireNonNull(firstName);
		Objects.requireNonNull(lastName);
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}
	
	@Override
    public boolean equals(Object otherObject) {
        if (this == otherObject)
            return true;
        if (otherObject == null)
            return false;
        if (getClass() != otherObject.getClass())
            return false;
        APerson otherPerson = (APerson) otherObject;
        return this.firstName.equalsIgnoreCase(otherPerson.firstName) 
        		&& this.lastName.equals(otherPerson.lastName)
        		&& this.age == otherPerson.age;
	}
	
}
