package common;

public class Person implements IPerson {

	private String name;
	
	public Person(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
}
