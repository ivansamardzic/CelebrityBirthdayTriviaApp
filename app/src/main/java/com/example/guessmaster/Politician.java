package com.example.guessmaster;

/*
Samardzic, Ivan - 20296563
April 6, 2023
The following code was adapted from the provided code for GuessMaster 1.0/2.0
All non-commented lines are self-explanatory, and/or used from the previous assignment.
*/

public class Politician extends Person{
    private String party;

    public Politician(String name,
                      Date birthDate,
                      String gender,
                      String party,
                      double difficulty) {
        super(name, birthDate, gender, difficulty);
        this.party = party;
    }

    public Politician(Politician politician) {
        super(politician);
        this.party = politician.party;
    }

    public String entityType() {
        return "This entity is a politician!";
    }

    public String toString() {
        return super.toString() + "Party: " + party + "\n";
    }

    public Politician clone() {
        return new Politician(this);
    }
}


