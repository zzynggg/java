package com.example.carsapp_week12;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.carsapp_week12.provider.Car;
import com.example.carsapp_week12.provider.CarViewModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity3 extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerViewAdapter adapter;
    CarViewModel mCarViewModel;
    ArrayList<Car> outputData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        recyclerView = findViewById(R.id.filterRecyclerView);

        layoutManager = new LinearLayoutManager(this);  //A RecyclerView.LayoutManager implementation which provides similar functionality to ListView.
        recyclerView.setLayoutManager(layoutManager);   // Also StaggeredGridLayoutManager and GridLayoutManager or a custom Layout manager
        adapter = new RecyclerViewAdapter();
        recyclerView.setAdapter(adapter);

        mCarViewModel = new ViewModelProvider(this).get(CarViewModel.class);
        mCarViewModel.getAllCars().observe(this, newData -> {
            Button filterBtn = findViewById(R.id.btn_filter);
            filterBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditText maker = findViewById(R.id.filter_maker_input);
                    EditText year = findViewById(R.id.filter_year_input);
                    EditText price = findViewById(R.id.filter_price_input);
                    String myMaker = maker.getText().toString();
                    int myYear = Integer.parseInt(year.getText().toString());
                    double myPrice = Double.parseDouble(price.getText().toString());
                    for (int i = 0; i < newData.size(); i++) {
                        String filterMaker = newData.get(i).getMaker();
                        Integer filterYear = Integer.parseInt(newData.get(i).getYear());
                        Double filterPrice = Double.parseDouble(newData.get(i).getPrice());
                        if (filterMaker.equals(myMaker) && filterYear < myYear && filterPrice < myPrice) {
                            outputData.add(newData.get(i));
                        }
                    }
                    adapter.setCars(outputData);
                    adapter.notifyDataSetChanged();
                }
            });
        });
    }
}