package com.example.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class RemovesFragment extends Fragment {

    ImageView imageView;
    TextView name;
    TextView number;
    Button button;
    RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_removes, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        ArrayList<Contact> removeContacts = getArguments().getParcelableArrayList("contacts");
        ContactsRecyclerAdapter adapter = new ContactsRecyclerAdapter(getContext(), removeContacts, null, true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
        mRecyclerView.setAdapter(adapter);

        return view;
    }

}
