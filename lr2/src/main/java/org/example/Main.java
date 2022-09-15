package org.example;

import java.util.Scanner;

public class Main {
    public static Customer[] setInfo(int count) {
        Scanner s1 = new Scanner(System.in);
        Customer []customer = new Customer[count];

        for (int i=0; i<count; i++){
            customer[i] = new Customer();
            System.out.printf("Введіть id: ");
            customer[i].setId(s1.nextInt());
            s1.nextLine();
            System.out.printf("Введіть Прізвище: ");
            customer[i].setSurname(s1.nextLine());
            System.out.printf("Введіть ваше ім'я: ");
            customer[i].setName(s1.nextLine());
            System.out.printf("Введіть ваше по батькові: ");
            customer[i].setPatronymic(s1.nextLine());

            System.out.printf("Введіть вашу адресу: ");
            customer[i].setAddress(s1.nextLine());

            System.out.printf("Введіть номер вашої карти: ");
            customer[i].setNumberOfCard(s1.nextInt());
            s1.nextLine();

            System.out.printf("Введіть ваш баланс: ");
            customer[i].setBalanceOfMoney(s1.nextInt());
            s1.nextLine();

        }
        return customer;

    }

    public static void findName(Customer[] customers) {
        Scanner s1 = new Scanner(System.in);
        System.out.println("ВВедіть ім'я для пошуку: ");
        String name = s1.nextLine();
        int count = 1;

        for (Customer customer : customers) {
            if (customer.getName().equals(name)){
                System.out.println(count+ ". "+customer);
                count++;
            }
        }
        System.out.println();
    }
    public static void cardNumber(Customer[] customers){
        Scanner s1 = new Scanner(System.in);

        System.out.println("Введіть інтервал для пошуку карти. ");

        System.out.printf("Введіть низ інтервалу: ");
        int a = s1.nextInt();
        s1.nextLine();

        System.out.printf("Введіть верх інтервалу: ");
        int b = s1.nextInt();
        s1.nextLine();
        System.out.println("Під заданий інтервал підходять карти покупців: ");
        for (Customer customer: customers){
            if(customer.getNumberOfCard() >= a && customer.getNumberOfCard() <= b){
                System.out.println(customer.getSurname()+" "+customer.getName()+" "+customer.getPatronymic());
                System.out.println("Карта користувача: "+customer.getNumberOfCard());
            }
        }
        System.out.println();
    }

    public static void lessBalance(Customer[] customers){
        for (Customer customer: customers){
            if (customer.getBalanceOfMoney() < 0 )
                System.out.println("Покупець: "+ customer.getSurname()+" "+customer.getName()+" " +
                        customer.getPatronymic() +" Має від'ємний баланс");
        }

    }


    public static void main(String[] args) {
        Customer[] customer = setInfo(5);

        findName(customer);
        cardNumber(customer);
        lessBalance(customer);
    }

}