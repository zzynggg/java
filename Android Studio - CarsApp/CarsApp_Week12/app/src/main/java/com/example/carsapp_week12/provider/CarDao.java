package com.example.carsapp_week12.provider;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CarDao {

    @Query("select * from carTable")
    LiveData<List<Car>> getAllCar();

    @Insert
    void addCar(Car car);

    @Query("select * from carTable where carMaker=:name")
    List<Car> getCar(String name);

    @Query("delete from carTable where carModel=:model")
    void deleteCar(String model);

    @Query("delete from carTable where carPrice=:price")
    int deleteCarPrice(int price);

    @Query("delete FROM carTable")
    void deleteAllCars();
}
