package com.rtechon.motoriz;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class ProfileFragment extends Fragment {

    private TextView nameValue, emailValue, phoneValue;

    private final String apiUrl = "http://egone.cn/api_get.php";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        nameValue = view.findViewById(R.id.nameValue);
        emailValue = view.findViewById(R.id.emailValue);
        phoneValue = view.findViewById(R.id.phoneValue);

        TextView myTextView = view.findViewById(R.id.myTextView);

        // Open new Activity or Fragment when clicked
        myTextView.setOnClickListener(v -> {
            // Navigate to FinanceInfoActivity
            Intent intent = new Intent(getActivity(), FinanceInfoActivity.class);
            startActivity(intent);
        });

        fetchProfileInfo();

        return view;
    }


    private void fetchProfileInfo() {
        RequestQueue queue = Volley.newRequestQueue(requireContext());

        StringRequest stringRequest = new StringRequest(Request.Method.GET, apiUrl,
                response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String status = jsonObject.getString("status");

                        if (status.equals("success")) {
                            String name = jsonObject.getString("name");
                            String email = jsonObject.getString("email");
                            String phone = jsonObject.getString("phone");

                            nameValue.setText(name);
                            emailValue.setText(email);
                            phoneValue.setText(phone);
                        } else {
                            nameValue.setText("User not found");
                            emailValue.setText("N/A");
                            phoneValue.setText("N/A");
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        nameValue.setText("Error");
                        emailValue.setText("Error");
                        phoneValue.setText("Error");
                    }
                },
                error -> {
                    nameValue.setText("Failed to load");
                    emailValue.setText("Failed to load");
                    phoneValue.setText("Failed to load");
                });

        queue.add(stringRequest);
    }
}
