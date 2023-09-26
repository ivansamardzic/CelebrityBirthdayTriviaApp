package com.example.guessmaster;

/*
Samardzic, Ivan - 20296563
April 6, 2023
The following code was adapted from the provided code for GuessMaster 1.0/2.0
All non-commented lines are self-explanatory, and/or used from the previous assignment.
*/

public class Country extends Entity{
    private String capital;

    public Country(String name, Date birthDate) {
        super(name, birthDate);
    }

    public Country(String name,
                   Date birthDate,
                   String capital,
                   double difficulty) {
        super(name, birthDate, difficulty);
        this.capital = capital;
    }

    public Country(Country country) {
        super(country);
        this.capital = country.capital;
    }

    public String entityType() {
        return "This entity is a country!";
    }

    public String toString() {
        return super.toString() + "Capital:" + capital +"\n";
    }

//	public String welcomeMessage() {
//		return "Welcome! Let’s start the game! "+entityType();
//	}

//	public String toString() {
//		return aaa;
//	}

    public Country clone() {
        return new Country(this);
    }

}


