package com.example.itearecyclerviewlesson.view;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.itearecyclerviewlesson.R;
import com.example.itearecyclerviewlesson.adapters.Contact;
import com.example.itearecyclerviewlesson.adapters.ContactsRecyclerAdapter;
import com.github.javafaker.Faker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ContactsRecyclerAdapter.OnContactListener {

    private static final int NUMBER_OF_CONTACTS = 100;

    // ui components
    private RecyclerView mRecyclerView;

    //adapters & variables
    private ArrayList<Contact> mContacts = new ArrayList<>();
    private ContactsRecyclerAdapter mContactsRecyclerAdapter;
    private String imageUrl = "https://cdn.pixabay.com/photo/2017/09/01/00/15/png-2702691_960_720.png";
    private ImageView imageProfile;

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

        //assign method to fab adding element
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = Integer.parseInt(editTextInsert.getText().toString());
                insertItem(position);
                mContactsRecyclerAdapter.notifyDataSetChanged();
            }
        });






        //using touch helper for swipe
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        //we need to attach it to recyclerview
        itemTouchHelper.attachToRecyclerView(mRecyclerView);





        initRecyclerView ();
        insertStandardContacts();
    }

    //method for button add
    public void insertItem (int position) {

        String fakeName = faker.name().firstName();
        String fakeSurname = faker.name().lastName();




        mContacts.add(position,new Contact(fakeName,fakeSurname,"Contact # "+position, imageUrl));
    }

    //creating contacts
    private void insertStandardContacts() {
        for (int i = 0; i <NUMBER_OF_CONTACTS ; i++) {

            String fakeName = faker.name().firstName();
            String fakeSurname = faker.name().lastName();

            //imageProfile = findViewById(R.id.iv_profilePhoto);

            //Glide.with(this).load(imageUrl).into(imageProfile);

            //imageView = R.drawable.ic_basicimage;

            //problem - how to cast
            Contact contact = new Contact(fakeName,fakeSurname,"Contact # "+i, imageUrl);



            
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

    // deleting item through swipping
    Contact deletedContact = null;

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {


           final int position = viewHolder.getAdapterPosition();
           switch (direction) {
               case ItemTouchHelper.LEFT:
                   deletedContact = mContacts.get(position);


                   mContacts.remove(position);
                   mContactsRecyclerAdapter.notifyItemRemoved(position);

                   Snackbar.make(mRecyclerView, Integer.parseInt(deletedContact.toString()),Snackbar.LENGTH_LONG)
                           .setAction("Undo", new View.OnClickListener() {
                               @Override
                               public void onClick(View v) {
                                   mContacts.add(position,deletedContact);
                                   mContactsRecyclerAdapter.notifyItemInserted(position);
                               }
                           }).show();
                   break;

               case ItemTouchHelper.RIGHT:
                   //for returning
                   break;
           }
        }
    };




    //method for contact click - yet to be implemented --- passage to separate activity
    @Override
    public void onContactClick(int position) {

        Log.d(TAG, "onContactClick: clicked. ");
        //Intent intent = new Intent(this, ContactItem.java);
        //startActivity(intent);
    }
}