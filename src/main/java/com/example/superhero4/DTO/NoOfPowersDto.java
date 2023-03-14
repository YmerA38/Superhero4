package com.example.superhero4.DTO;

import com.example.superhero4.model.Superpower;

public class NoOfPowersDto {
    private String superheroName;
    private String realName;
    private int numberOfPowers;

    public NoOfPowersDto(String superheroName, String realName, int numberOfPowers) {
        this.superheroName = superheroName;
        this.realName = realName;
        this.numberOfPowers = numberOfPowers;
    }


    @Override
    public String toString(){
        return "Hero: "+superheroName+"Name: "+realName+"Number of powers: "+numberOfPowers;
    }
}
