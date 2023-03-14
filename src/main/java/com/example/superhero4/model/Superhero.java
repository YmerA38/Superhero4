package com.example.superhero4.model;

import java.util.ArrayList;

public class Superhero {
   private String superheroName;
   private String realName;
   private String creationYear;
   private ArrayList<Superpower> powerList;

    public Superhero(String superheroName, String realName, String creationYear) {
        this.superheroName = superheroName;
        this.realName = realName;
        this.creationYear = creationYear;
    }



    public String getSuperheroName() {
        return superheroName;
    }

    public String getRealName() {
        return realName;
    }

    public String getCreationYear() {
        return creationYear;
    }
}
