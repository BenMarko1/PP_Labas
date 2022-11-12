package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;



public class BattleMenu {
    List<Droids> droids = chooseDroid();

    //базові дроїди
    private List<Droids> chooseDroid() {
        List<Droids> droid = new ArrayList<>();
        droid.add(new Droids("Tangiro", 110, 40, 01));
        droid.add(new Droids("Zenicu", 95, 35, 02));
        droid.add(new Droids("Mydzan", 150, 50, 03));
        droid.add(new Droids("Inosyke", 100, 35, 04));
        droid.add(new Droids("Nedzyko", 70, 30, 05));
        droid.add(new Droids("Tomoe", 120, 45, 06));
        return droid;
    }

    List<Katana> katans = chooseKatana();

    //лист базових катан
    private List<Katana> chooseKatana() {
        List<Katana> item = new ArrayList<>();
        item.add(new Katana("Fire katana", 25, 001));
        item.add(new Katana("Water katana", 20, 002));
        item.add(new Katana("Solar katana", 30, 003));
        item.add(new Katana("Katana of the earth", 20, 004));
        return item;
    }

    List<Droids> team1 = new ArrayList<>();
    List<Droids> team2 = new ArrayList<>();


    /*------------------Батл меню------------------*/
    public void battle() throws InterruptedException {
        System.out.println();
        Scanner s = new Scanner(System.in);
        int num;

        System.out.print("""
                Menu:\s
                1. Create new droid(1 vs 1)
                2. Choose droids(1 vs 1)
                3. Team fight
                4. Print from file
                """);

        num = s.nextInt();

        if(num == 1){

            createForPvP();

        }else if(num == 2){
            chooseForPvp();

        }else if(num == 3){
            teams();
            teamFight();
        }else{
            PrintResult file = new PrintResult();
            file.PrintFight();
        }

    }

    /*------------------Бій дроїдів------------------*/
    //створення пвп нових дроїдів
    public void createForPvP() throws InterruptedException {
        Droids droid1 = createNewDroid();
        Droids droid2 = createNewDroid();
        Katana item1 = null, item2 = null;

        System.out.println("""
                Would you like choose katana for droids?\s
                1. Yes
                2. No""");
        Scanner s1 = new Scanner(System.in);
        int num2 = s1.nextInt();
        if (num2 == 1) {
            //обрали битися з катанами

            printListKatana();

            katanaForDroid(item1, item2);

            ByteArrayOutputStream buffer = StartPrintToFile();//запис в файл

            Battlefield arena = new Battlefield(droid1, droid2, item1, item2);
            Droids winner = Battlefield.startFight();
            System.out.println("The winner is " + winner.getName());

            EndPrintToFile(buffer);
        } else {
            //обрали битися без катан
            ByteArrayOutputStream buffer = StartPrintToFile();

            Battlefield arena = new Battlefield(droid1, droid2, null, null);
            Droids winner = arena.startFight();
            System.out.println("The winner is " + winner.getName());

            EndPrintToFile(buffer);
        }
    }

    //обираємо катану для дроїда по айді
    private void katanaForDroid(Katana item1, Katana item2){
        printListKatana();
        System.out.println("Please enter id of item for");
        item1 = choseKatanaForDroid();
        item2 = choseKatanaForDroid();
        katans.remove(item1);
        katans.remove(item2);
    }

    //створюємо бій 1 на 1 з існуючих дроїдів
    public void chooseForPvp() throws InterruptedException {
        printListDroid();
        Droids droid1 = selectDroid();
        Droids droid2 = selectDroid();



        System.out.println("Would you like choose katana for droids? \n"+"1. Yes\n"+"2. No");
        Scanner s2 = new Scanner(System.in);
        int num2 = s2.nextInt();
        Katana item1 = null, item2 = null;


        ByteArrayOutputStream buffer = StartPrintToFile();

        Battlefield arena;
        if(num2 == 1){
            katanaForDroid(item1, item2);
            arena = new Battlefield(droid1, droid2, item1, item2);
            Droids winner = arena.startFight();
            System.out.println("The winner is " + winner.getName());

        }
        else{

            arena = new Battlefield(droid1, droid2, item1, item2);
            Droids winner = arena.startFight();
            System.out.println("The winner is " + winner.getName());

        }
        EndPrintToFile(buffer);

    }

    /*------------------Командний бій------------------*/
    public void teamFight() throws InterruptedException{
        int score1 = 0, score2 = 0;
        Random random = new Random();
        ByteArrayOutputStream buffer;

        int j = 0;
        do{
            int i = random.nextInt(team1.size());
            Droids droid1 = team1.get(i);
            int k = random.nextInt(team2.size());
            Droids droid2 = team2.get(k);

            buffer = StartPrintToFile();

            Battlefield battle = new Battlefield(droid1, droid2, null, null);
            Droids winner = battle.startFight();
            System.out.println("Winner is " + winner.getName());

            if (winner == droid1){
                score1++;

            }else {
                score2++;
            }
            team1.remove(droid1);
            team2.remove(droid2);
            j++;
        }while(j < 3);

        if (score1 > score2){
            System.out.println("First team is winner");
        } else {
            System.out.println("Second team is winner");
        }
        EndPrintToFile(buffer);
    }



    //---------------------------------Teams----------------------------------

    public void teams(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Would you like auto fight team or create new team?\n"+"1. Auto team\n"+"2. Create new team");
        int num3 = scan.nextInt();
        if(num3 == 1){
            chooseDroidsForTeam(team1, team2);
        }
        else {
            createNewTeam(team1, team2);
        }
    }
    //створення нової команди для бою
    public void createNewTeam(List<Droids> Team, List<Droids> Team1){
        System.out.println("Create 3 droids for first team");
        int i = 0;
        while(i < 3){
            Droids newDroid = createNewDroid();
            Team.add(newDroid);
            i++;
        }
        int j = 0;
        while(j < 3){
            Droids newDroid = createNewDroid();
            Team1.add(newDroid);
            j++;
        }
    }

    //автоматичний бій
    public void chooseDroidsForTeam(List<Droids> Team, List<Droids> Team1){
        printListDroid();
        //заповнення обох команд
        int i = 0;
        System.out.println("\nChoose droids for team 1:");
        while(i < 3){
            Droids newDroid = selectDroid();
            Team.add(newDroid);
            droids.remove(newDroid);
            i++;
        }
        System.out.println("\nChoose droids for team 2:");
        int j = 0;
        while(j < 3){
            Droids newDroid = selectDroid();
            Team1.add(newDroid);
            droids.remove(newDroid);
            j++;
        }

    }

    /*------------------Вибір дроїда------------------*/
    //Вибір дроїда через id
    private Droids selectDroid() {
        Scanner in = new Scanner(System.in);

        System.out.print(" Enter id droid: ");
        return findDroid(in.nextInt());
    }

    //Пошук обраного дроїда
    public Droids findDroid(int id) {

        int idDroid;

        for (Droids droid : droids) {
            idDroid = droid.getId();
            if (idDroid == id) {
                return droid;
            }
        }
        return null;
    }

    //Створення нового дроїда
    public Droids createNewDroid() {
        Scanner in = new Scanner(System.in);

        System.out.print("\nEnter name droid: ");
        String name = in.nextLine();
        System.out.print("\nEnter damage droid: ");
        int damage = in.nextInt();
        System.out.print("\nEnter health droid: ");
        int health = in.nextInt();
        System.out.println("\nEnter droid id: ");
        int id = in.nextInt();

        Droids newDroid = new Droids(name, damage, health, id);

        return newDroid;
    }

    /*------------------Вибір катани------------------*/
    private Katana choseKatanaForDroid () {
        Scanner in = new Scanner(System.in);

        System.out.print(" Enter katana id: ");
        return findKatana(in.nextInt());
    }
    //пошук обраної катани
    public Katana findKatana ( int id){

        int katanaId;

        for (Katana katana : katans) {
            katanaId = katana.getId();
            if (katanaId == id) {
                return katana;
            }
        }
        return null;
    }




    /*------------------Друк в файл------------------*/

    public ByteArrayOutputStream StartPrintToFile(){
        // для запису консолі у текстовий документ
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        OutputStream teeStream = new TeeOutputStream(System.out, buffer);
        System.setOut(new PrintStream(teeStream));

        return buffer;
    }
    public void EndPrintToFile(ByteArrayOutputStream buffer){
        // Збереження буферу в текстовий документ
        try(OutputStream fileStream = new FileOutputStream("result.txt")){
            buffer.writeTo(fileStream);
            System.out.println(" \n\n The Game is successfully written in file! ");
        } catch(IOException e){
            System.out.println("error");
        }
    }

    /*------------------Друк списку------------------*/

    public void printListDroid(){
        for(int i = 0; i < droids.size();i++){
            System.out.println(droids.get(i)+" ");
        }
    }
        public void printListKatana () {
            for (int i = 0; i < katans.size(); i++) {
                System.out.println(katans.get(i) + " ");
            }
        }

        public class TeeOutputStream extends PrintStream {
            private final ByteArrayOutputStream second;

            public TeeOutputStream(OutputStream main, ByteArrayOutputStream second) {
                super(main);
                this.second = second;
            }

                /**
                 * Closes the main stream.
                 * The second stream is just flushed but <b>not</b> closed.
                 * @see java.io.PrintStream#close()
                 */
                @Override
                public void close() {
                    // just for documentation
                    super.close();
                }

                @Override
                public void flush() {
                    super.flush();
                    try {
                        second.flush();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

                @Override
                public void write(byte[] buf, int off, int len) {
                    super.write(buf, off, len);
                    second.write(buf, off, len);
                }

                @Override
                public void write(int b) {
                    super.write(b);
                    second.write(b);
                }

                @Override
                public void write(byte[] b) throws IOException {
                    super.write(b);
                    second.write(b);
                }
        }

    //друк результату в текстовий файл
    public class PrintResult {
        public static String FILE = "result.txt";
        public void PrintFight() {

            try { // open file
                BufferedReader reader = new BufferedReader(new FileReader(FILE));

                while (true) {

                    String line = reader.readLine();
                    if (line != null) {
                        System.out.println(line);
                    } else break;
                }

                reader.close();
            } catch (IOException e) {
                System.out.println("Cant open: " + FILE);
            }
        }
    }
}

