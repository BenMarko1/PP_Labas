package org.example;

import javax.xml.namespace.QName;

public class Customer {
    private int id;

    private String surname;
    private String name;
    private String patronymic;

    private String address;

    private int numberOfCard;
    private int balanceOfMoney;

    public void setId(int id) {
        this.id = id;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setNumberOfCard(int numberOfCard) {
        this.numberOfCard = numberOfCard;
    }

    public void setBalanceOfMoney(int balanceOfMoney) {
        this.balanceOfMoney = balanceOfMoney;
    }

    public int getId() {
        return id;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getAddress() {
        return address;
    }

    public int getNumberOfCard() {
        return numberOfCard;
    }

    public int getBalanceOfMoney() {
        return balanceOfMoney;
    }
    public String toString(){
        return "Id: " + id + " Full name: " + surname+" "+name+" "+patronymic + "\nAdress: " + address +
                "\nBank info: "+numberOfCard + balanceOfMoney;
    }
}
