package com.rtechon.motoriz;

import androidx.fragment.app.Fragment;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.os.Bundle;
import java.util.List;
import java.util.ArrayList;
import com.rtechon.motoriz.MotorcycleAdapter;

public class HomeFragment extends Fragment {

    RecyclerView motorcycleRecycler;
    List<Motorcycle> motorcycleList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        motorcycleRecycler = view.findViewById(R.id.motorcycleRecycler);

        // Manually adding different motorcycles to the list
        motorcycleList = new ArrayList<>();
        motorcycleList.add(new Motorcycle(1, "YAMAHA YTX", "2023", "125 CC Displacement", 1500.0));
        motorcycleList.add(new Motorcycle(2, "Honda CBR", "2022", "150 CC Displacement", 1800.0));
        motorcycleList.add(new Motorcycle(3, "Kawasaki Ninja", "2021", "200 CC Displacement", 2500.0));
        motorcycleList.add(new Motorcycle(4, "Suzuki GSX-R", "2023", "250 CC Displacement", 3500.0));
        motorcycleList.add(new Motorcycle(5, "Ducati Panigale", "2023", "1000 CC Displacement", 15000.0));

        // Set up the adapter and RecyclerView
        MotorcycleAdapter adapter = new MotorcycleAdapter(requireContext(), motorcycleList);
        motorcycleRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        motorcycleRecycler.setAdapter(adapter);

        return view;
    }
}
