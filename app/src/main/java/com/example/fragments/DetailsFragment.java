package com.example.fragments;

import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsFragment extends Fragment {

    ImageView imageView;
    TextView name;
    TextView number;
    Button button;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        imageView = (ImageView) view.findViewById(R.id.image);
        name = (TextView) view.findViewById(R.id.name);
        number = (TextView) view.findViewById(R.id.number);

        if(getArguments() != null) {
            Contact contact = getArguments().getParcelable("contact");
            name.setText(contact.getName());
            number.setText(contact.getNumber());
            imageView.setImageDrawable(getContext().getResources().getDrawable(R.drawable.contact));
        }

        return view;
    }

}
