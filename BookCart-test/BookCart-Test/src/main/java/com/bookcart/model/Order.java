package com.bookcart.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "OrderTransaction")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int transactionId;

    private int isbnNO;

    private double price;

    private int noOfCopies;

    public Order() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Order(int transactionId, String userId, int isbnNO, double price, int noOfCopies) {
        super();
        this.transactionId = transactionId;
        this.isbnNO = isbnNO;
        this.price = price;
        this.noOfCopies = noOfCopies;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getIsbnNO() {
        return isbnNO;
    }

    public void setIsbnNO(int isbnNO) {
        this.isbnNO = isbnNO;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNoOfCopies() {
        return noOfCopies;
    }

    public void setNoOfCopies(int noOfCopies) {
        this.noOfCopies = noOfCopies;
    }

}
