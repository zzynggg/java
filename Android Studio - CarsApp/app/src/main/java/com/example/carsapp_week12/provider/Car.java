package com.example.carsapp_week12.provider;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "carTable")
public class Car {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "carID")
    private int carID;

    @ColumnInfo(name = "carMaker")
    private String maker;

    @ColumnInfo(name = "carModel")
    private String model;

    @ColumnInfo(name = "carYear")
    private String year;

    @ColumnInfo(name = "carColor")
    private String color;

    @ColumnInfo(name = "carSeats")
    private String seats;

    @ColumnInfo(name = "carPrice")
    private String price;

    public Car(String maker, String model, String year, String color, String seats, String price) {
        this.maker = maker;
        this.model = model;
        this.year = year;
        this.color = color;
        this.seats = seats;
        this.price = price;
    }

    public int getCarID() {
        return carID;
    }

    public void setCarID(@NonNull int carID) {
        this.carID = carID;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
