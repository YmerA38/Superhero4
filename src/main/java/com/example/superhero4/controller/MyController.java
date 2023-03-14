package com.example.superhero4.controller;

import com.example.superhero4.DTO.NameAndPowerDto;
import com.example.superhero4.DTO.NoOfPowersDto;
import com.example.superhero4.model.Superhero;
import com.example.superhero4.model.Superpower;
import com.example.superhero4.reposetory.DBInterface;
import com.example.superhero4.reposetory.Database;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@Controller
public class MyController {
   DBInterface d = new Database();


//Q1:
    @GetMapping( "/superhero/all")
    public ResponseEntity<List<Superhero>> superheroes(){
        List<Superhero> list = d.allHeroes();
        return new ResponseEntity<List<Superhero>>(list, HttpStatus.OK);
    }
    @GetMapping( "/superhero/{sName}")
    public ResponseEntity<Superhero> superheroe(@PathVariable String sName){
        Superhero hero = d.hero(sName);
        return new ResponseEntity<Superhero>(hero, HttpStatus.OK);
    }
//Q2:
    @GetMapping( "/superhero/noOfPow")
    public ResponseEntity<List<NoOfPowersDto>> noOfPowers(){
        List<NoOfPowersDto> list = d.allNameNumPow();
        return new ResponseEntity<List<NoOfPowersDto>>(list, HttpStatus.OK);
    }


//Q3:   En superhelt med et bestemt heroName eller en liste med alle superhelte, der indeholder:
//      heroName, realName, superkræfter (Superpower)
    @GetMapping( "/superhero/nameAndPower/{name}")
    public ResponseEntity<String> nameAndPower(@PathVariable String name){
    List<NameAndPowerDto> list = d.nameAndPower(name);
    String message = "";
    for(NameAndPowerDto n : list){
        message += "Hero: "+n.getsName()+"\nName: "+n.getrName()+"\nSuperpower: ";
        for(Superpower s : n.getListP()){
           message += s.getPower() +", ";
        }
    }
    return new ResponseEntity<String>(message, HttpStatus.OK);
}



//Q4:   En superhelt med et bestemt heroName eller en liste med alle superhelte,
//      der indeholder: heroName og by (City


    //Delete hero
    @GetMapping( "/superhero/delete/{hero}")
    public ResponseEntity<String> deleteHero(@PathVariable String hero){
        String message = "";
        boolean wasDeleted = d.deleteHero(hero);
        if(wasDeleted){
            message = hero + " has now been deleted";
        }else {
            message = hero + " was NOT deleted";
        }

        return new ResponseEntity<String>(message, HttpStatus.OK);
    }

}
