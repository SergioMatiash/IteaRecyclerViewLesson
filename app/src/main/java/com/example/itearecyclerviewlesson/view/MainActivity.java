package com.example.itearecyclerviewlesson.view;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.itearecyclerviewlesson.R;
import com.example.itearecyclerviewlesson.adapters.Contact;
import com.example.itearecyclerviewlesson.adapters.ContactsRecyclerAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ContactsRecyclerAdapter.onContactListener{

    private static final int NUMBER_OF_CONTACTS = 100;

    // ui components
    private RecyclerView mRecyclerView;

    //adapters & variables
    private ArrayList<Contact> mContacts = new ArrayList<>();
    private ContactsRecyclerAdapter mContactsRecyclerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerView);

        initRecyclerView ();
        insertStandardContacts();
    }

    //creating contacts
    private void insertStandardContacts() {
        for (int i = 0; i <NUMBER_OF_CONTACTS ; i++) {
            Contact contact = new Contact("Name","Surname","Contact # "+i);
        }
    }

    //setting layoutmanager
    private void initRecyclerView () {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        VerticalSpacingItemDecorator itemDecorator = new VerticalSpacingItemDecorator(10);
        mRecyclerView.addItemDecoration(itemDecorator);
        mContactsRecyclerAdapter = new ContactsRecyclerAdapter(mContacts,);
        mRecyclerView.setAdapter(mContactsRecyclerAdapter);


    }



    @Override
    public void onContactClick(int position) {
        //mContacts.get(position);
        //Intent intent = new Intent(this. ContactActivity.java);
        //startActivity(intent);

        Log.d(TAG,"onContactClick: clicked"+position);
    }
}