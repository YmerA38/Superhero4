package com.example.superhero4.reposetory;

import com.example.superhero4.DTO.NameAndPowerDto;
import com.example.superhero4.DTO.NoOfPowersDto;
import com.example.superhero4.model.Superhero;
import com.example.superhero4.model.Superpower;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;

@Repository
public class Database implements DBInterface{
  /*  @Value("${spring.datasauce.url}")
    private String url;
    @Value("${spring.datasauce.uid}")
    private String uid;
    @Value("${spring.datasauce.psw}")
    private String psw; */
//CRUD
    //CREATE
    //public Database() {}

    public boolean addHero(Superhero hero){
        try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/superheroesdata","testuser", "Sivertsen13")) {
            String SQL = "insert into superheroesdata.superhero values (?,?,?)";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1,hero.getSuperheroName() );
            ps.setString(2,hero.getRealName() );
            ps.setString(3,hero.getCreationYear() );
            ps.executeQuery();
            return true;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }






    //READ
    @Override
    public ArrayList<Superhero> allHeroes(){
        ArrayList<Superhero> resultList = new ArrayList<>();
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/superheroesdata","testuser", "Sivertsen13")) {
            String SQL = "select * from superheroesdata.superhero";
            ResultSet rs = con.createStatement().executeQuery(SQL);

            while (rs.next()){
                resultList.add( new Superhero(rs.getString("superheroname"),rs.getString("realname"),rs.getString("creationyear")));
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }

    @Override
    public Superhero hero(String name){
        Superhero hero;
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/superheroesdata","testuser", "Sivertsen13")) {
            String SQL = "select * from superheroesdata.superhero where superheroName = ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1,name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
            hero = new Superhero(rs.getString("superheroname"),rs.getString("realname"),rs.getString("creationyear"));
            return hero;
            }
            return null;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
//Q2:
    @Override
    public ArrayList<NoOfPowersDto> allNameNumPow(){
        ArrayList<NoOfPowersDto> resultList = new ArrayList<>();
        try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/superheroesdata","testuser", "Sivertsen13")) {
           String SQL = "select  superhero.superheroName, superhero.realName, count(*) as powers from superheroesdata.superhero join " +
                   "superheroesdata.powerrelation on powerrelation.superheroName = superhero.superheroName group by superheroName";
           //select  superhero.superheroName, count(*) as numberOf from superheroesdata.superhero join superheroesdata.powerrelation on powerrelation.superheroName = superhero.superheroName  group by superheroName
           //select  superhero.superheroName, count(*) from superheroesdata.superhero join superheroesdata.powerrelation on powerrelation.superheroName = superhero.superheroName group by superheroName
           ResultSet rs = con.createStatement().executeQuery(SQL);
           while (rs.next()){
               resultList.add(new NoOfPowersDto(rs.getString("superheroName"),rs.getString("realName"),rs.getInt("powers")));
           }
           return resultList;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

//Q3:
    @Override
    public ArrayList<NameAndPowerDto> nameAndPower(String name){
        ArrayList<NameAndPowerDto> resultList = new ArrayList<>();
        try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/superheroesdata","testuser", "Sivertsen13")){
            String SQL = "select superhero.superheroName, superhero.realName, power from superheroesdata.superhero join " +
                    "(superheroesdata.powerrelation join superheroesdata.superpower on powerrelation.powerId = superpower.powerId) " +
                    "on powerrelation.superheroName = superhero.superheroName where superhero.superheroName = ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            if(name.isEmpty()) {
                ps.setString(1,name);
            }else{
                ps.setString(1,"*");
            }

            ResultSet rs = ps.executeQuery();
            String pastAlreadyName = "";
            NameAndPowerDto nameAndPowerDto = null;
            ArrayList<Superpower> powerList;
            Superpower superpower;
            while(rs.next()) {

                String sName = rs.getString("superheroName");
                superpower = new Superpower(rs.getString("power"));

                if (!pastAlreadyName.equals(sName)) {
                    powerList = new ArrayList<>();
                    powerList.add(superpower);
                    nameAndPowerDto = new NameAndPowerDto(sName, rs.getString("realName"), powerList);
                    resultList.add(nameAndPowerDto);
                    pastAlreadyName = sName;
                }else {
                    nameAndPowerDto.addPowerToList(superpower);
                }
            }


            return resultList;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }




    //UPDATE
    public boolean updateHero(String whatToSet,String entryToSet,String whereToSet){ //whereToSet is PK
        try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/superheroesdata","testuser", "Sivertsen13")) {
            String SQL = "update superheroesdata.superhero set ? = ? where superheroname = ? ";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1,whatToSet);
            ps.setString(2,entryToSet);
            ps.setString(3,whereToSet);// primary key PK
            ps.executeQuery();
            return true;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //DELETE
    @Override
    public boolean deleteHero(String heroName){
        try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/superheroesdata","testuser", "Sivertsen13")) {
            String SQL = "delete from superheroesdata.superhero where superheroname = ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1,heroName);
            ResultSet rs = ps.executeQuery();
            return true;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }







}
