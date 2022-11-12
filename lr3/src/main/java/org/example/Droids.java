package org.example;

import java.util.Random;

public class Droids {
    private String name;
    private int damage;
    private int health;
    private int id;
    private int actualDamage;

    // конструктори
    public Droids(){}
    public Droids(String name, int health, int damage, int id) {
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.id = id;
    }


    public int getActualDamage() {return actualDamage;}
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public int addDamage(int add){
        return actualDamage += add;
    }

    public int setActualDamage(int damage) {
        Random random = new Random();
        this.actualDamage = random.nextInt(damage);
        return actualDamage;
    }

    public boolean isAlive() {

        return health > 0;
    }

    public int getHit(int damage) {  // функція в якій визначається рандомним чином атака attacker
        // та знімається здоров'я в defender

        Random random = new Random();

        int actualDamage = random.nextInt(damage);
        this.health -= actualDamage;

        if (health < 0) {
            health = 0;
        }

        return actualDamage;
    }



    public String toString() {
        return    "\n| Name:  " + name            + "  |" +
                "\n| " + health + " - HP           " +
                "\n| " + damage + " - Damage       " +
                "\n| " + id     + "  - Id          " +
                "\n-------------------------------------";
    }
}
