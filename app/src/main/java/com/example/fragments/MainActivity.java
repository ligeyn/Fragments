package com.example.fragments;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnItemClick {

    private FrameLayout mFrameLayout;
    private RecyclerView mRecyclerView;
    private ContactsRecyclerAdapter mRecyclerAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ArrayList<Contact> mContacts;
    private ArrayList<Contact> mRemoveContacts;
    private boolean isPortrait;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFrameLayout = (FrameLayout) findViewById(R.id.frame_layout);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRemoveContacts = new ArrayList<>();
        createContact();
        mRecyclerAdapter = new ContactsRecyclerAdapter(MainActivity.this, mContacts, this, false);
        mRecyclerView.setAdapter(mRecyclerAdapter);

        isPortrait = isPortraitOrientation();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            if(mRecyclerView.getVisibility() == View.INVISIBLE){
                mRecyclerView.setVisibility(View.VISIBLE);
                mFrameLayout.setVisibility(View.INVISIBLE);
                return true;
            }
            return super.onKeyDown(keyCode, event);
        }
        return super.onKeyDown(keyCode, event);
    }

    private boolean isPortraitOrientation(){
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            return true;
        else
            return false;
    }

    public void createContact(){
        mContacts = new ArrayList<>();
        mContacts.add(new Contact("89270220367","Polina"));
        mContacts.add(new Contact("89278466413","Oleg"));
        mContacts.add(new Contact("89875215633", "Grisha"));
        mContacts.add(new Contact("89876543123", "Pasha"));
    }

    @Override
    public void onItemClick(Contact contact) {
        DetailsFragment detailsFragment = new DetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("contact", contact);
        detailsFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, detailsFragment, DetailsFragment.class.getSimpleName()).commit();
        if(isPortrait) {
            mFrameLayout.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void deleteContact(Contact contact) {
        mRemoveContacts.add(contact);
        RemovesFragment removesFragment = new RemovesFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("contacts", mRemoveContacts);
        removesFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, removesFragment, DetailsFragment.class.getSimpleName()).commit();
        if(isPortrait) {
            mFrameLayout.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.INVISIBLE);
        }
    }
}
