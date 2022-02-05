package com.example.itearecyclerviewlesson.view;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.itearecyclerviewlesson.R;
import com.example.itearecyclerviewlesson.adapters.Contact;
import com.example.itearecyclerviewlesson.adapters.ContactsRecyclerAdapter;
import com.github.javafaker.Faker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ContactsRecyclerAdapter.OnContactListener {

    private static final int NUMBER_OF_CONTACTS = 100;

    // ui components
    private RecyclerView mRecyclerView;

    //adapters & variables
    private ArrayList<Contact> mContacts = new ArrayList<>();
    private ContactsRecyclerAdapter mContactsRecyclerAdapter;
    private int imageView;

    private FloatingActionButton buttonInsert;
    private EditText editTextInsert;

    Faker faker = new Faker();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerView);

        buttonInsert = findViewById(R.id.fab_additem);
        editTextInsert = findViewById(R.id.et_newItem);

        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = Integer.parseInt(editTextInsert.getText().toString());
                insertItem(position);
                mContactsRecyclerAdapter.notifyDataSetChanged();
            }
        });

        initRecyclerView ();
        insertStandardContacts();
    }

    //method for button
    public void insertItem (int position) {

        String fakeName = faker.name().firstName();
        String fakeSurname = faker.name().lastName();
        imageView = R.drawable.ic_basicimage;

        mContacts.add(position,new Contact(fakeName,fakeSurname,"Contact # "+position, imageView));
    }

    //creating contacts
    private void insertStandardContacts() {
        for (int i = 0; i <NUMBER_OF_CONTACTS ; i++) {

            String fakeName = faker.name().firstName();
            String fakeSurname = faker.name().lastName();
            imageView = R.drawable.ic_basicimage;
            Contact contact = new Contact(fakeName,fakeSurname,"Contact # "+i, imageView);



            
            mContacts.add(contact);
        }
        mContactsRecyclerAdapter.notifyDataSetChanged();
    }

    //setting layoutmanager
    private void initRecyclerView () {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        VerticalSpacingItemDecorator itemDecorator = new VerticalSpacingItemDecorator(10);
        mRecyclerView.addItemDecoration(itemDecorator);
        mContactsRecyclerAdapter = new ContactsRecyclerAdapter(mContacts,this);
        mRecyclerView.setAdapter(mContactsRecyclerAdapter);


    }


    @Override
    public void onContactClick(int position) {

        Log.d(TAG, "onContactClick: clicked. ");
        //Intent intent = new Intent(this, ContactItem.java);
        //startActivity(intent);
    }
}