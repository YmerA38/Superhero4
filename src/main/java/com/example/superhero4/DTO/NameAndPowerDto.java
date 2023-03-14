package com.example.superhero4.DTO;

import com.example.superhero4.model.Superpower;

import java.util.ArrayList;

public class NameAndPowerDto {
    String sName;
    String rName;
    ArrayList<Superpower> listP;

    public NameAndPowerDto(String sName, String rName, ArrayList<Superpower> listP) {
        this.sName = sName;
        this.rName = rName;
        this.listP = listP;
    }
    public void addPowerToList(Superpower superpower){
        this.listP.add(superpower);
    }
    @Override
    public String toString(){
        return "Hero: " + sName + ", Name: " + rName + " Powers: " + listP;
    }


    public String getsName() {
        return sName;
    }

    public String getrName() {
        return rName;
    }

    public ArrayList<Superpower> getListP() {
        return listP;
    }
}
