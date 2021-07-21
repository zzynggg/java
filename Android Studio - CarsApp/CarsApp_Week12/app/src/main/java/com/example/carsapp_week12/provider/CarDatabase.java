package com.example.carsapp_week12.provider;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Car.class}, version = 1)
public abstract class CarDatabase extends RoomDatabase {
    // we don't create instance of db but can directly use the get db
    public static final String CAR_DATABASE_NAME = "car_database";

    public abstract CarDao carDao();

    // marking the instance as volatile to ensure atomic access to the variable
    private static volatile CarDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    // return the instance of database
    static CarDatabase getDatabase(final Context context) {
        // check the instance is created or not
        if (INSTANCE == null) {
            synchronized (CarDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CarDatabase.class, CAR_DATABASE_NAME)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
