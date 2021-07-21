package com.example.carsapp_week12.provider;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class CarRepository {

    private CarDao mCarDao;
    private LiveData<List<Car>> mAllCars;

    CarRepository(Application application) {
        CarDatabase db = CarDatabase.getDatabase(application);
        mCarDao = db.carDao();
        mAllCars = mCarDao.getAllCar();
    }

    LiveData<List<Car>> getAllCars() {
        return mAllCars;
    }

    void insert(Car car) {
        CarDatabase.databaseWriteExecutor.execute(() -> mCarDao.addCar(car));
    }

    // extra task
    void deleteCar(String model){CarDatabase.databaseWriteExecutor.execute(() -> mCarDao.deleteCar(model));}

    void deleteAll(){
        CarDatabase.databaseWriteExecutor.execute(()->{
            mCarDao.deleteAllCars();
        });
    }

}
