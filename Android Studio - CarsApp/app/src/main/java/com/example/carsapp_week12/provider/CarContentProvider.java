package com.example.carsapp_week12.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CarContentProvider extends ContentProvider {
    // allow other application to share the same data
    // other application need to use content resolver to access the data
    CarDatabase db;
    private final String tableName = "carTable";

    public static final String CONTENT_AUTHORITY = "fit2081.app.ZIYING";
    public static final Uri CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    CarDao dao;
    DatabaseReference myRef;
    public CarContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int deletionCount;
        // using DAO
//        int deletionCount;
//        int cost = Integer.parseInt(uri.getLastPathSegment());
//        deletionCount = dao.deleteCarPrice(cost);
        deletionCount = db
                .getOpenHelper()
                .getWritableDatabase()
                .delete(tableName, selection, selectionArgs);
        return deletionCount;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // one row only
        Car car = new Car(values.getAsString("carMaker"),values.getAsString("carModel"), values.getAsString("carYear"),
                values.getAsString("carColor"),values.getAsString("carSeats"), values.getAsString("carPrice"));

        long rowId = db
                .getOpenHelper()
                .getWritableDatabase()
                .insert(tableName, 0, values);
        myRef.push().setValue(car);
        return ContentUris.withAppendedId(CONTENT_URI, rowId);
    }

    @Override
    public boolean onCreate() {
        db = CarDatabase.getDatabase(getContext());
        dao = db.carDao();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("carsApp/car");
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        builder.setTables(tableName);
        String query = builder.buildQuery(projection, selection, null, null, sortOrder, null);
        final Cursor cursor = db
                .getOpenHelper()
                .getReadableDatabase()
                .query(query, selectionArgs);
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        //all
        int updateCount;
        updateCount = db
                .getOpenHelper()
                .getWritableDatabase()
                .update(tableName, 0, values, selection, selectionArgs);
        return updateCount;

    }
}