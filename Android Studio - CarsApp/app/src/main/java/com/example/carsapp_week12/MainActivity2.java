package com.example.carsapp_week12;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.carsapp_week12.provider.CarViewModel;

public class MainActivity2 extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerViewAdapter adapter;

//    ArrayList<Car> data;

    // make it public and static
//    public static CarViewModel mCarViewModel;
    CarViewModel mCarViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        recyclerView =  findViewById(R.id.recyclerView);

        layoutManager = new LinearLayoutManager(this);  //A RecyclerView.LayoutManager implementation which provides similar functionality to ListView.
        recyclerView.setLayoutManager(layoutManager);   // Also StaggeredGridLayoutManager and GridLayoutManager or a custom Layout manager

        // get data from main activity
//        SharedPreferences sP = getSharedPreferences("CARDCARLIST",0);
//        String dbStr = sP.getString("KEY_CARLIST", "");
//        Type type = new TypeToken<ArrayList<Car>>() {}.getType();
//        Gson gson = new Gson();
//        data = gson.fromJson(dbStr,type);

        // need to create extra class
        adapter = new RecyclerViewAdapter();
        recyclerView.setAdapter(adapter);

        mCarViewModel = new ViewModelProvider(this).get(CarViewModel.class);
        mCarViewModel.getAllCars().observe(this, newData -> {
            adapter.setData(newData);
            adapter.notifyDataSetChanged();
        });
    }
}