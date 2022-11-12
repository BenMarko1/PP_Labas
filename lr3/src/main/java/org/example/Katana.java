package org.example;

import java.util.Scanner;


public class Katana{
    private final String name;
    private final int damage;
    private final int id;


    public Katana(String name, int damage, int id) {
        this.name = name;
        this.damage = damage;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString(){
        return "Name: "+ name+" damage: "+damage+" id:  "+id;
    }
}
