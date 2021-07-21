package com.example.carsapp_week12;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.carsapp_week12.provider.Car;
import com.example.carsapp_week12.provider.CarViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String CAR_APP_WEEK_3 = "CarApp_Week3";
    EditText maker;
    String myMaker;
    EditText model;
    String myModel;
    EditText year;
    String myYear;
    EditText color;
    String myColor;
    EditText seats;
    String mySeats;
    EditText price;
    String myPrice;
    SharedPreferences sharedPreferences;
    ArrayList<String> myCarList = new ArrayList<>();
//    ArrayList<String> newMyCarList = new ArrayList<>();
    ArrayAdapter myCarAdapter;
    // week 6
//    Gson gson = new Gson();

    // Week 7
    CarViewModel mCarViewModel;
    RecyclerViewAdapter adapter;

    // week 8
    DatabaseReference myRef;

    // week 10
    int x;
    int y;

    // Week 11
    GestureDetector gestureDetector;
    ScaleGestureDetector scaleGestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);
        // Week 3: observe the life cycle
        Log.i(CAR_APP_WEEK_3, "onCreate");
        // Week 4: SMS data
        EditText makerr = findViewById(R.id.txtMaker);
        EditText modell = findViewById(R.id.txtModel);
        EditText yearr = findViewById(R.id.txtYear);
        EditText colorr = findViewById(R.id.txtColor);
        EditText seatss = findViewById(R.id.txtSeats);
        EditText pricee = findViewById(R.id.txtPrice);

        // request permission to access sms
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS, Manifest.permission.RECEIVE_SMS, Manifest.permission.READ_SMS}, 0);

        // create and instantiate the local broadcast receiver
        MyBroadCastReceiver myBroadCastReceiver = new MyBroadCastReceiver();
        IntentFilter intentFilter = new IntentFilter("SMS");
        // register the broadcast handler with the intent filter
        registerReceiver(myBroadCastReceiver, intentFilter);

        // week 5: List View
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewCars();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ListView myListView = findViewById(R.id.listView);
        if (savedInstanceState != null) {
            myCarList = savedInstanceState.getStringArrayList("CARLIST");
        }
        myCarAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, myCarList);
        myListView.setAdapter(myCarAdapter);

        // Week 7: recycler view
        adapter = new RecyclerViewAdapter();

        mCarViewModel = new ViewModelProvider(this).get(CarViewModel.class);
        mCarViewModel.getAllCars().observe(this, newData -> {
            adapter.setData(newData);
            adapter.notifyDataSetChanged();

        });

        // Week 8: firebase database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("carsApp/car");
//        myRef.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                Toast.makeText(getApplicationContext(), "Item added", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
//                Toast.makeText(getApplicationContext(), "Item deleted", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

        /* Week 10 gesture
         * x, y = start from top left corner of frame layout
         * raw x, raw y = top left of activity, x and y value for upper layer value (constraint)
         * textView: most inner view return false pass the control back to its parent
         * return true: satisfies everything
         *  */
//        View view = findViewById(R.id.constraint_id);
//        view.setOnTouchListener(new View.OnTouchListener() {
//            // motion event can retrieve many information such as x and y
//            // detect event such as down, move, up
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                int action = event.getActionMasked();
//                switch (action) {
//                    // event.getY() data type is float
//                    case (MotionEvent.ACTION_DOWN):
//                        // initial x and y value
//                        x = (int) event.getX(); // cast it to int
//                        y = (int) event.getY();
//                        return true;
//                    case (MotionEvent.ACTION_MOVE):
//                        return true;    // java is happy
//                    case (MotionEvent.ACTION_UP):
//                        // initial y compare with current y
//                        // to diff horizontal & diagonal
//                        if (Math.abs(y - event.getY()) < 40 && Math.abs(x - event.getX()) > 100) {
//                            // tolerance the y value
//                            // x is to draw longer horizontal line
//                            if (x - event.getX() < 0) {   // L - R
//                                addNewCars();
//                            }
//                        } else if (Math.abs(y - event.getY()) > 100 && Math.abs(x - event.getX()) < 40) {
//                            // y is to draw longer vertical line
//                            if (y - event.getY() < 0) { // top - bottom
//                                clearAllButton();
//                            }
//                        } else if (y < 250 && x < 250) {  // top left corner
//                            deductCarPrice();
//                        } else if (y < 250 && x > 750) { // top right corner
//                            addCarPrice();
//                        }
//                        return true;
//                    default:
//                        // not happy with it (not detected) --> going back to parent
//                        return false;
//                }
//            }
//        });

        // Week 11: Gesture detector

        gestureDetector = new GestureDetector(this, new MyW11GestureDetector());
        scaleGestureDetector = new ScaleGestureDetector(this, new MyW11ScaleGestureDetector());
        /*  new GestureDetector(this, new GestureDetector.OnGestureListener());
            must implement all method in the interface class
            anonymous class: onDown, onShowPress, onSingleTapUp, OnScroll, onLongPress, onFling */
        View view = findViewById(R.id.constraint_id);
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetector.onTouchEvent(event);
                scaleGestureDetector.onTouchEvent(event);
                // ID is unique; Index is changeable
//                event.getPointerId(0);  // first finger
                return true;    // happy & satisfied with it then consume
            }
        });
    }

    // extends convenience class [it implements all the method and return false] to use those required method only
    class MyW11GestureDetector extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            // increment the number of seats by one
            seats = findViewById(R.id.txtSeats);
            if (seats.getText().toString().equals("")) {
                seats.setText("1");
            } else {
                Integer newSeat = Integer.parseInt(seats.getText().toString());
                newSeat += 1;
                seats.setText(newSeat.toString());
            }
            return true;
        }

        // onDoubleTapEvent is just a message that tell us this series of instruction is belongs to double tap
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            // load default values to all fields
            maker = findViewById(R.id.txtMaker);
            model = findViewById(R.id.txtModel);
            year = findViewById(R.id.txtYear);
            color = findViewById(R.id.txtColor);
            seats = findViewById(R.id.txtSeats);
            price = findViewById(R.id.txtPrice);
            maker.setText("Toyota");
            model.setText("Camry Hybrid");
            year.setText("2021");
            color.setText("White");
            seats.setText("5");
            price.setText("200");
            return true;
        }

        // happen continuously without speed
        // e1: first event touch down; e2: current event
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            // current(latest)[touch up] - previous[touch down]
            /*
             distanceX in your onScroll represents the distance/difference of the X coordinate values between
             the previous motion event and the current motion event(e2).
             So when you move from left to right, your previous motion event will have an X value less
             than the current motion event's X coordinate value.
             This is the reason why distanceX is negative for a left to right swipe.
             */
            if (distanceY < 40 && distanceY >= 0) {
                if (distanceX > 0) {
                    // horizontal right to left scroll == increment the price  == positive
                    addCarPrice(Math.abs(distanceX));
                }
                // current - previous [previous e2]
                else if (distanceX < 0) {
                    // horizontal left to right scroll == decrement the price == negative
                    deductCarPrice(Math.abs(distanceX));
                }
            }
            return true;
        }

        // detected at the end of the event with speed
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (velocityY > 400 || velocityX > 400) {
                moveTaskToBack(true);
            }
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            clearAllButton();
            super.onLongPress(e);
        }
    }

    public void deductCarPrice(float priceToDeduct) {
        price = findViewById(R.id.txtPrice);
        if (price.getText().toString().equals("")) {
            price.setText("0.0");
        } else {
            Double myCurrentPrice = Double.valueOf(price.getText().toString());
            if (myCurrentPrice <= 0.0) {
                price.setText("0.0");
            } else {
                myCurrentPrice -= (int) priceToDeduct;
                price.setText(myCurrentPrice.toString());
            }
        }
    }

    public void addCarPrice(float priceToAdd) {
        price = findViewById(R.id.txtPrice);
        if (price.getText().toString().equals("") || price.getText().toString().equals("0.0")) {
            price.setText("1.0");
        } else {
            Double myCurrentPrice = Double.valueOf(price.getText().toString());
            if (myCurrentPrice < 5000) {
                myCurrentPrice += (int) priceToAdd;
                if (myCurrentPrice >= 5000) {
                    myCurrentPrice = 5000.0;
                }
            }
            price.setText(myCurrentPrice.toString());
        }
    }

    class MyW11ScaleGestureDetector extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        // begin: started scaling
        // end: remove finger
        // pinching zooming
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            year = findViewById(R.id.txtYear);
            int myCurrentYear = Integer.parseInt(year.getText().toString());
            // multiple current value with the scale factor
            // scale factor will be more than 1 if pinch out/zoom in
            // scale factor will be less than 1 if pinch in/zoom out
            // better
            if(detector.getScaleFactor() > 1){

            }else if (detector.getScaleFactor() < 1){

            }
            myCurrentYear *=  detector.getScaleFactor();
//            System.out.println(detector.getScaleFactor());
//            System.out.println("year"+myCurrentYear);
            if(myCurrentYear >= 2021){
                myCurrentYear = 2021;
            }else if (myCurrentYear <= 1930){
                myCurrentYear = 1930;
            }
            year.setText(Integer.toString(myCurrentYear));
            return true;
        }
    }

    // week 5
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // option menu
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.option_menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_clearAll) {
            clearAllButton();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_addCar) {
            addNewCars();
        } else if (id == R.id.nav_clearLast) {
            removeLastCar();
        } else if (id == R.id.nav_clearAll) {
            removeAllCar();
        } else if (id == R.id.nav_listAll) {
            showListGSON();
        }else if (id == R.id.nav_filterCar) {
            showFilterCar();
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // week 12
    public void showFilterCar(){
        // change to another activity
        Intent intent = new Intent(this, MainActivity3.class);
        startActivity(intent);
    }

    public void removeLastCar() {
        if (myCarList.size() > 0) {
            myCarList.remove(myCarList.size() - 1);
            myCarAdapter.notifyDataSetChanged();
        }
    }

    public void removeAllCar() {
        if (myCarList.size() > 0) {
            myCarList.removeAll(myCarList);
            mCarViewModel.deleteAll();
            myRef.removeValue();
            myCarAdapter.notifyDataSetChanged();
        }
    }

    // week 6
    public void showListGSON() {
        // pass the data to main activity 2
//        String carString = gson.toJson(newMyCarList);
//        SharedPreferences sp = getSharedPreferences("CARDCARLIST", 0);
//        SharedPreferences.Editor editor = sp.edit();
//        editor.putString("KEY_CARLIST", carString);
//        editor.apply();

        // change to another activity
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }


    // Week 4
    class MyBroadCastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            // retrieve the message from the intent
            EditText makerr = findViewById(R.id.txtMaker);
            EditText modell = findViewById(R.id.txtModel);
            EditText yearr = findViewById(R.id.txtYear);
            EditText colorr = findViewById(R.id.txtColor);
            EditText seatss = findViewById(R.id.txtSeats);
            EditText pricee = findViewById(R.id.txtPrice);


            String detail = intent.getStringExtra("KEY_DETAILS");
            // get the current context
            Toast.makeText(getApplicationContext(), detail, Toast.LENGTH_LONG).show();
            StringTokenizer stringTokenizer = new StringTokenizer(detail, ";");
            String maker4 = stringTokenizer.nextToken();
            String model4 = stringTokenizer.nextToken();
            String year4 = stringTokenizer.nextToken();
            String color4 = stringTokenizer.nextToken();
            String seats4 = stringTokenizer.nextToken();
            String price4 = stringTokenizer.nextToken();

            // update UI
            makerr.setText(maker4);
            modell.setText(model4);
            yearr.setText(year4);
            colorr.setText(color4);
            seatss.setText(seats4);
            pricee.setText(price4);
        }

    }

    // === Week 3 ===
    public void addNewCars() {
        // save data permanently
        // data is saved after terminating the activity
        SharedPreferences.Editor editor = sharedPreferences.edit();

        maker = findViewById(R.id.txtMaker);
        model = findViewById(R.id.txtModel);
        year = findViewById(R.id.txtYear);
        color = findViewById(R.id.txtColor);
        seats = findViewById(R.id.txtSeats);
        price = findViewById(R.id.txtPrice);

        myMaker = maker.getText().toString();
        myModel = model.getText().toString();
        myYear = year.getText().toString();
        myColor = color.getText().toString();
        mySeats = seats.getText().toString();
        myPrice = price.getText().toString();

        editor.putString("sp_maker", myMaker);
        editor.putString("sp_model", myModel);
        editor.putString("sp_year", myYear);
        editor.putString("sp_color", myColor);
        editor.putString("sp_seats", mySeats);
        editor.putString("sp_price", myPrice);
        editor.apply();

        // for add new car button
        CharSequence txt = "We added a new car (" + myMaker + ")";
        Toast toast = Toast.makeText(this, txt, Toast.LENGTH_SHORT);
        toast.show();

        String output = myMaker + " | " + myModel;
        myCarList.add(output);

        //week 6/7
        Car carClass = new Car(myMaker, myModel, myYear, myColor, mySeats, myPrice);
        mCarViewModel.insert(carClass);
        myRef.push().setValue(carClass);    // week 8
        myCarAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onStart() {
        super.onStart();
        // the non view data is null because it haven invoke restore
        // restore data after activity is terminated
        sharedPreferences = getPreferences(0);  // default filename;
        myMaker = sharedPreferences.getString("sp_maker", "");
        myModel = sharedPreferences.getString("sp_model", "");
        myYear = sharedPreferences.getString("sp_year", "");
        myColor = sharedPreferences.getString("sp_color", "");
        mySeats = sharedPreferences.getString("sp_seats", "");
        myPrice = sharedPreferences.getString("sp_price", "");

        maker = findViewById(R.id.txtMaker);
        model = findViewById(R.id.txtModel);
        year = findViewById(R.id.txtYear);
        color = findViewById(R.id.txtColor);
        seats = findViewById(R.id.txtSeats);
        price = findViewById(R.id.txtPrice);

        maker.setText(myMaker);
        model.setText(myModel);
        year.setText(myYear);
        color.setText(myColor);
        seats.setText(mySeats);
        price.setText(myPrice);

        Log.i(CAR_APP_WEEK_3, "onStart");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // bundle is a box to save the non view data into variable
        // bundle is volatile
//        maker = savedInstanceState.getString("maker_key");
//        model = savedInstanceState.getString("model_key");
//        year = savedInstanceState.getInt("year_key");
//        color = savedInstanceState.getString("color_key");
//        seats = savedInstanceState.getInt("seats_key");
//        price = savedInstanceState.getInt("price_key");
//
//        Log.i("maker_key", "maker = " + maker);
//        Log.i("model_key", "model = " + model);
//        Log.i("year_key", "year = " + year);
//        Log.i("color_key", "color = " + color);
//        Log.i("seats_key", "seats = " + seats);
//        Log.i("price_key", "price = " + price);
        Log.i(CAR_APP_WEEK_3, "onRestoreInstanceState");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(CAR_APP_WEEK_3, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(CAR_APP_WEEK_3, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(CAR_APP_WEEK_3, "onStop");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        // save the non view data when changing orientation
//        outState.putString("maker_key", maker);
//        outState.putString("model_key", model);
//        outState.putInt("year_key", year);
//        outState.putString("color_key", color);
//        outState.putInt("seats_key", seats);
//        outState.putInt("price_key", price);
        Log.i(CAR_APP_WEEK_3, "onSaveInstanceState");
        // week 5
        outState.putStringArrayList("CARLIST", myCarList);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(CAR_APP_WEEK_3, "onDestroy");
    }

    public void clearAllButton() {
        maker = findViewById(R.id.txtMaker);
        model = findViewById(R.id.txtModel);
        year = findViewById(R.id.txtYear);
        color = findViewById(R.id.txtColor);
        seats = findViewById(R.id.txtSeats);
        price = findViewById(R.id.txtPrice);

        // delete the view data
        maker.setText("");
        model.setText("");
        year.setText("0");
        color.setText("");
        seats.setText("0");
        price.setText("0.0");

        // delete the volatile data
        sharedPreferences.edit().remove("sp_maker").commit();
        sharedPreferences.edit().remove("sp_model").commit();
        sharedPreferences.edit().remove("sp_year").commit();
        sharedPreferences.edit().remove("sp_color").commit();
        sharedPreferences.edit().remove("sp_seats").commit();
        sharedPreferences.edit().remove("sp_price").commit();
    }
}