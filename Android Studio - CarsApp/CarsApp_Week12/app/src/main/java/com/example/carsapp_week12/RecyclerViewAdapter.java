package com.example.carsapp_week12;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carsapp_week12.provider.Car;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    List<Car> carList = new ArrayList<>();

    public void setData(List<Car> carList) {
        this.carList = carList;
    }
    public void setCars(List<Car> newCars) { carList = newCars; }

    public RecyclerViewAdapter()
    {
    }

//    public RecyclerViewAdapter(ArrayList<Car> carList) {
//        this.carList = carList;
//    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false); //CardView inflated as RecyclerView list item
        // create an new instance of the inner class
        ViewHolder viewHolder = new ViewHolder(v);
        // return value is used for the onBindViewHolder call back
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        // receive outcome from onCreateViewHolder == input
        // put in data that need to show at recycler view
        holder.carID.setText(carList.get(position).getCarID() + "");
        holder.maker.setText(carList.get(position).getMaker());  // iterate automatically
        holder.model.setText(carList.get(position).getModel());
        holder.year.setText(carList.get(position).getYear());
        holder.color.setText(carList.get(position).getColor());
        holder.seats.setText(carList.get(position).getSeats());
        holder.price.setText(carList.get(position).getPrice());

//        final int fPosition = position+1;
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Toast.makeText(v.getContext(), "Car No. " + fPosition + " with Maker: " + carList.get(position).getMaker() + " with Model: " + carList.get(position).getModel() + " is selected.", Toast.LENGTH_SHORT).show();
//                // extra
//                MainActivity2.mCarViewModel.deleteCar(carList.get(position).getModel());
//                Toast.makeText(v.getContext(),"Model: " + carList.get(position).getModel() + " is deleted", Toast.LENGTH_SHORT).show();
//            }
//        });



    }


    @Override
    public int getItemCount() {
        return carList.size();
    }

    // inner class to send view to onCreateViewHolder
    public class ViewHolder extends RecyclerView.ViewHolder{
        // bind all view components in the cardView
        public View itemView;
        public TextView carID;
        public TextView maker;
        public TextView model;
        public TextView year;
        public TextView color;
        public TextView seats;
        public TextView price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            carID = itemView.findViewById(R.id.carIDDetails);
            maker = itemView.findViewById(R.id.makerDetails);
            model = itemView.findViewById(R.id.modelDetails);
            year = itemView.findViewById(R.id.yearDetails);
            color = itemView.findViewById(R.id.colorDetails);
            seats = itemView.findViewById(R.id.seatsDetails);
            price = itemView.findViewById(R.id.priceDetails);
        }
    }
}
