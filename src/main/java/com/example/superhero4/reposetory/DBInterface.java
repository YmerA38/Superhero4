package com.example.superhero4.reposetory;

import com.example.superhero4.DTO.NameAndPowerDto;
import com.example.superhero4.DTO.NoOfPowersDto;
import com.example.superhero4.model.Superhero;

import java.util.ArrayList;
import java.util.List;

public interface DBInterface {
    //Read
    ArrayList allHeroes();
    Superhero hero(String name);
    ArrayList allNameNumPow();
    ArrayList<NameAndPowerDto> nameAndPower(String name);

    //delete
    boolean deleteHero(String heroName);


}
