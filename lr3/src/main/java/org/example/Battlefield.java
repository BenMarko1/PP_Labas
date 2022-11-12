package org.example;


import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Battlefield{
    private static Droids firstDroid;
    private static Droids secondDroid;
    private final Katana item1;
    private final Katana item2;
    private static Droids attacker;
    private static Droids defender;
    private static int currentRound = 0;



    //конструктор
    public Battlefield(Droids firstDroid, Droids secondDroid, Katana item1, Katana item2) {
        this.firstDroid = firstDroid;
        this.secondDroid = secondDroid;
        this.item1 = item1;
        this.item2 = item2;
    }

    private void printInfoItem1(){
        System.out.println(attacker.getName() + " have boost damage:"
                + item1.getDamage() + "\n"
                +"Damage now: " + attacker.getDamage() +" + " + item1.getDamage() + ")\n");

    }

    private void printInfoItem2(){
        System.out.println(attacker.getName() + " have boost damage:"
                + item2.getDamage() + "Damage now: " + attacker.getDamage() +" + " + item2.getDamage());

    }

    public void useItem1(){
        attacker.addDamage(item1.getDamage());

        printInfoItem1();

    }

    public void useItem2(){
        attacker.addDamage(item2.getDamage());

        printInfoItem2();

    }


    private static void initFighters() {  // визначення хто атакує, а хто приймає удар
        Random random = new Random();
        if (random.nextBoolean()) {
            attacker = secondDroid;
            defender = firstDroid;
        } else {
            attacker = firstDroid;
            defender = secondDroid;
        }
    }
    public static Droids startFight() throws InterruptedException {

        System.out.println("\n\t\t  "+ firstDroid.getName() + " vs " + secondDroid.getName());
        do {
            prepareRound();
            int actualDamage = doFight(); // визначення актуальної атаки
            printRoundInfo(actualDamage);

            TimeUnit.SECONDS.sleep(2);
        } while (defender.isAlive());

        return defender;
    }

    private static void prepareRound() {
        initFighters();
        currentRound++;
        System.out.print("\n--------------Fighting--------------\t Round " + currentRound + "\n");
    }

    // функція повертає к-сть атаки, яку отримав defender
    private static int doFight() { return defender.getHit(attacker.getDamage()); }

    private static void printRoundInfo(int actualDamage) { // виведення інформації про раунд битви
        System.out.println(attacker.getName() + " -- making--damage -- " + defender.getName());
        System.out.println(defender.getName() + " get " + actualDamage + " damage ");
        System.out.println("\n| Name: " + defender.getName() + " \t " + defender.getHealth() + " HP ");
        System.out.println("| Name: " + attacker.getName() + " \t " + attacker.getHealth() + "  HP ");
    }

}
