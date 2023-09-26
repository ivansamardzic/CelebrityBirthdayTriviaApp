package com.example.guessmaster;

/*
Samardzic, Ivan - 20296563
April 6, 2023
The following code was adapted from the provided code for GuessMaster 1.0/2.0
All non-commented lines are self-explanatory, and/or used from the previous assignment.
*/

public class Person extends Entity{
    private String gender;

    public Person(String name, Date birthDate) {
        super(name, birthDate);
    }

    public Person(String name,
                  Date birthDate,
                  String gender,
                  double difficulty) {
        super(name, birthDate, difficulty);
        this.gender = gender;
    }

    public Person(Person person) {
        super(person);
        this.gender = person.gender;
    }

    public String entityType() {
        return "This entity is a person!";
    }

//	public String welcomeMessage() {
//		return "Welcome! Let’s start the game! "+entityType();
//	}

    public String toString() {
        return super.toString() + "Gender: " + gender + "\n";
    }

    public Person clone() {
        return new Person(this);
    }
}


