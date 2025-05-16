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

public class HomeFragment extends Fragment {

    RecyclerView motorcycleRecycler;
    List<Motorcycle> motorcycleList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        motorcycleRecycler = view.findViewById(R.id.motorcycleRecycler);

        // Manually adding different motorcycles to the list (expanding for the Philippine market)
        motorcycleList = new ArrayList<>();

        motorcycleList.add(new Motorcycle(1, "Kawasaki Ninja 400", "2020",
                "399 CC\nWeight: 366 lbs\nBore x Stroke: 70mm x 51.8mm\nFuel Type: Gasoline\nFuel System: Fuel Injection",
                2500.0, new ArrayList<>(List.of(R.drawable.logo, R.drawable.facebook_icon))));

        motorcycleList.add(new Motorcycle(2, "Yamaha YZF-R3", "2021",
                "321 CC\nWeight: 373 lbs\nBore x Stroke: 68mm x 44.1mm\nFuel Type: Gasoline\nFuel System: Fuel Injection",
                2300.0, new ArrayList<>(List.of(R.drawable.logo))));

        motorcycleList.add(new Motorcycle(3, "Honda CBR500R", "2019",
                "471 CC\nWeight: 423 lbs\nBore x Stroke: 67mm x 66.8mm\nFuel Type: Gasoline\nFuel System: PGM-FI Fuel Injection",
                2700.0, new ArrayList<>(List.of(R.drawable.logo))));

        motorcycleList.add(new Motorcycle(4, "Suzuki SV650", "2022",
                "645 CC\nWeight: 437 lbs\nBore x Stroke: 81mm x 62.6mm\nFuel Type: Gasoline\nFuel System: Fuel Injection",
                3000.0, new ArrayList<>(List.of(R.drawable.logo))));

        motorcycleList.add(new Motorcycle(5, "KTM RC 390", "2021",
                "373 CC\nWeight: 364 lbs\nBore x Stroke: 89mm x 60mm\nFuel Type: Gasoline\nFuel System: Fuel Injection",
                2400.0, new ArrayList<>(List.of(R.drawable.logo))));

        motorcycleList.add(new Motorcycle(6, "Ducati Monster 797", "2018",
                "803 CC\nWeight: 422 lbs\nBore x Stroke: 88mm x 66mm\nFuel Type: Gasoline\nFuel System: Fuel Injection",
                4500.0, new ArrayList<>(List.of(R.drawable.logo))));

        motorcycleList.add(new Motorcycle(7, "BMW G 310 R", "2020",
                "313 CC\nWeight: 349 lbs\nBore x Stroke: 80mm x 62.1mm\nFuel Type: Gasoline\nFuel System: Fuel Injection",
                3200.0, new ArrayList<>(List.of(R.drawable.logo))));

        motorcycleList.add(new Motorcycle(8, "Harley-Davidson Iron 883", "2021",
                "883 CC\nWeight: 564 lbs\nBore x Stroke: 76mm x 96mm\nFuel Type: Gasoline\nFuel System: Electronic Sequential Port Fuel Injection",
                4800.0, new ArrayList<>(List.of(R.drawable.logo))));

        motorcycleList.add(new Motorcycle(9, "Triumph Trident 660", "2022",
                "660 CC\nWeight: 434 lbs\nBore x Stroke: 74mm x 61.9mm\nFuel Type: Gasoline\nFuel System: Fuel Injection",
                5000.0, new ArrayList<>(List.of(R.drawable.logo))));

        motorcycleList.add(new Motorcycle(10, "Royal Enfield Meteor 350", "2021",
                "349 CC\nWeight: 425 lbs\nBore x Stroke: 72mm x 85.8mm\nFuel Type: Gasoline\nFuel System: Fuel Injection",
                1800.0, new ArrayList<>(List.of(R.drawable.logo))));

        motorcycleList.add(new Motorcycle(11, "Yamaha MT-07", "2020",
                "689 CC\nWeight: 403 lbs\nBore x Stroke: 80mm x 68.6mm\nFuel Type: Gasoline\nFuel System: Fuel Injection",
                4100.0, new ArrayList<>(List.of(R.drawable.logo))));

        motorcycleList.add(new Motorcycle(12, "Honda Rebel 500", "2022",
                "471 CC\nWeight: 408 lbs\nBore x Stroke: 67mm x 66.8mm\nFuel Type: Gasoline\nFuel System: PGM-FI Fuel Injection",
                3700.0, new ArrayList<>(List.of(R.drawable.logo))));

        motorcycleList.add(new Motorcycle(13, "Kawasaki Z650", "2021",
                "649 CC\nWeight: 410 lbs\nBore x Stroke: 83mm x 60mm\nFuel Type: Gasoline\nFuel System: Fuel Injection",
                3900.0, new ArrayList<>(List.of(R.drawable.logo))));

        motorcycleList.add(new Motorcycle(14, "Suzuki GSX250R", "2019",
                "248 CC\nWeight: 397 lbs\nBore x Stroke: 53.5mm x 55.2mm\nFuel Type: Gasoline\nFuel System: Fuel Injection",
                2100.0, new ArrayList<>(List.of(R.drawable.logo))));

        motorcycleList.add(new Motorcycle(15, "Ducati Scrambler Icon", "2020",
                "803 CC\nWeight: 425 lbs\nBore x Stroke: 88mm x 66mm\nFuel Type: Gasoline\nFuel System: Fuel Injection",
                5300.0, new ArrayList<>(List.of(R.drawable.logo))));

        motorcycleList.add(new Motorcycle(16, "BMW F 900 R", "2021",
                "895 CC\nWeight: 434 lbs\nBore x Stroke: 86mm x 77mm\nFuel Type: Gasoline\nFuel System: Fuel Injection",
                6100.0, new ArrayList<>(List.of(R.drawable.logo))));

        motorcycleList.add(new Motorcycle(17, "Harley-Davidson Street 750", "2018",
                "749 CC\nWeight: 511 lbs\nBore x Stroke: 85.8mm x 66mm\nFuel Type: Gasoline\nFuel System: Electronic Fuel Injection",
                3500.0, new ArrayList<>(List.of(R.drawable.logo))));

        motorcycleList.add(new Motorcycle(18, "Triumph Street Triple R", "2022",
                "765 CC\nWeight: 434 lbs\nBore x Stroke: 76mm x 55mm\nFuel Type: Gasoline\nFuel System: Fuel Injection",
                6700.0, new ArrayList<>(List.of(R.drawable.logo))));

        motorcycleList.add(new Motorcycle(19, "Royal Enfield Himalayan", "2020",
                "411 CC\nWeight: 439 lbs\nBore x Stroke: 78mm x 86mm\nFuel Type: Gasoline\nFuel System: Fuel Injection",
                2900.0, new ArrayList<>(List.of(R.drawable.logo))));

        motorcycleList.add(new Motorcycle(20, "Yamaha XSR700", "2021",
                "689 CC\nWeight: 416 lbs\nBore x Stroke: 80mm x 68.6mm\nFuel Type: Gasoline\nFuel System: Fuel Injection",
                4300.0, new ArrayList<>(List.of(R.drawable.logo))));

        motorcycleList.add(new Motorcycle(21, "Honda CB500F", "2019",
                "471 CC\nWeight: 416 lbs\nBore x Stroke: 67mm x 66.8mm\nFuel Type: Gasoline\nFuel System: PGM-FI Fuel Injection",
                3100.0, new ArrayList<>(List.of(R.drawable.logo))));

        motorcycleList.add(new Motorcycle(22, "KTM Duke 390", "2020",
                "373 CC\nWeight: 364 lbs\nBore x Stroke: 89mm x 60mm\nFuel Type: Gasoline\nFuel System: Fuel Injection",
                2500.0, new ArrayList<>(List.of(R.drawable.logo))));

        motorcycleList.add(new Motorcycle(23, "Suzuki DR-Z400SM", "2021",
                "398 CC\nWeight: 317 lbs\nBore x Stroke: 90mm x 62.6mm\nFuel Type: Gasoline\nFuel System: Fuel Injection",
                3400.0, new ArrayList<>(List.of(R.drawable.logo))));

        motorcycleList.add(new Motorcycle(24, "Ducati Panigale V2", "2022",
                "955 CC\nWeight: 436 lbs\nBore x Stroke: 100mm x 60.8mm\nFuel Type: Gasoline\nFuel System: Fuel Injection",
                11500.0, new ArrayList<>(List.of(R.drawable.logo))));

        motorcycleList.add(new Motorcycle(25, "BMW R nineT", "2020",
                "1170 CC\nWeight: 438 lbs\nBore x Stroke: 101mm x 73mm\nFuel Type: Gasoline\nFuel System: Fuel Injection",
                12500.0, new ArrayList<>(List.of(R.drawable.logo))));

        motorcycleList.add(new Motorcycle(26, "Harley-Davidson Forty-Eight", "2021",
                "1202 CC\nWeight: 549 lbs\nBore x Stroke: 88.9mm x 96mm\nFuel Type: Gasoline\nFuel System: Electronic Sequential Port Fuel Injection",
                10700.0, new ArrayList<>(List.of(R.drawable.logo))));

        motorcycleList.add(new Motorcycle(27, "Triumph Bonneville T100", "2019",
                "900 CC\nWeight: 464 lbs\nBore x Stroke: 90.7mm x 68.8mm\nFuel Type: Gasoline\nFuel System: Fuel Injection",
                8700.0, new ArrayList<>(List.of(R.drawable.logo))));

        motorcycleList.add(new Motorcycle(28, "Royal Enfield Classic 350", "2022",
                "346 CC\nWeight: 430 lbs\nBore x Stroke: 70mm x 90mm\nFuel Type: Gasoline\nFuel System: Fuel Injection",
                2200.0, new ArrayList<>(List.of(R.drawable.logo))));

        motorcycleList.add(new Motorcycle(29, "Suzuki Address 110", "2022",
                "110 CC\nWeight: 196 lbs\nBore x Stroke: 50mm x 55.2mm\nFuel Type: Gasoline\nFuel System: Carburetor",
                1000.0, new ArrayList<>(List.of(R.drawable.logo))));

        motorcycleList.add(new Motorcycle(30, "Kawasaki Versys-X 300", "2021",
                "296 CC\nWeight: 373 lbs\nBore x Stroke: 62mm x 49.7mm\nFuel Type: Gasoline\nFuel System: Fuel Injection",
                3600.0, new ArrayList<>(List.of(R.drawable.logo))));

        // Set up the adapter and RecyclerView
        MotorcycleAdapter adapter = new MotorcycleAdapter(getContext(), motorcycleList);
        motorcycleRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        motorcycleRecycler.setAdapter(adapter);


        return view;
    }
}
