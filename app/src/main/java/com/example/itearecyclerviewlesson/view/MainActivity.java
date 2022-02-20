package com.example.itearecyclerviewlesson.view;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Parcel;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import com.example.itearecyclerviewlesson.R;
import com.example.itearecyclerviewlesson.model.Contact;
import com.example.itearecyclerviewlesson.adapters.ContactsRecyclerAdapter;
import com.example.itearecyclerviewlesson.parsingbetween.ObjectToBeParsed;
import com.example.itearecyclerviewlesson.retrofitmodule.ApiData;
import com.example.itearecyclerviewlesson.retrofitmodule.OnPhotoDownloadedListener;
import com.example.itearecyclerviewlesson.retrofitmodule.ProfilePhoto;
import com.example.itearecyclerviewlesson.retrofitmodule.RetrofitDownloadController;
import com.github.javafaker.Faker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements ContactsRecyclerAdapter.OnContactListener, OnPhotoDownloadedListener {

    private static final int NUMBER_OF_CONTACTS = 100;


    // ui components
    private RecyclerView mRecyclerView;

    //adapters & variables
    private ArrayList<Contact> mContacts = new ArrayList<>();
    private List<ProfilePhoto> avatars = new ArrayList<>();
    private ContactsRecyclerAdapter mContactsRecyclerAdapter;
    public String imageUrl = "https://cdn.pixabay.com/photo/2017/09/01/00/15/png-2702691_960_720.png";
    public ImageView imageProfile;
    private Menu menu;
    private ActionBar toolbar;
    private FloatingActionButton addNewItem;

    public Context context;
    public Parcel in;

    Faker faker = new Faker();




    //packing objects into parcel


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //calling retrofit for downloading images from apicat


        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},NUMBER_OF_CONTACTS);
        }




        mRecyclerView = findViewById(R.id.recyclerView);

        //adit text and fab are deleted

        //using touch helper for swipe
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        //we need to attach it to recyclerview
        itemTouchHelper.attachToRecyclerView(mRecyclerView);

        initRecyclerView ();
        insertStandardContacts();
        RetrofitDownloadController retrofitDownloadController = new RetrofitDownloadController();
        //dont know if its right solution




        //initialize transmission to the new activity
       //addNewItem = findViewById(R.id.btn_add_new_item);


    }



    //methods for options menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        this.menu = menu;
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        hideOption(R.id.action_info);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_info) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void hideOption(int id) {
        MenuItem item = menu.findItem(id);
        item.setVisible(false);
    }

    private void showOption(int id) {
        MenuItem item = menu.findItem(id);
        item.setVisible(true);
    }





    //method for button add
    public void insertItem (int position) {

        String fakeName = faker.name().firstName();
        String fakeSurname = faker.name().lastName();




        mContacts.add(position,new Contact(fakeName,fakeSurname,"Contact # "+position, imageUrl,position));
    }

    //creating contacts
    private void insertStandardContacts() {
        for (int i = 0; i <NUMBER_OF_CONTACTS ; i++) {

            String fakeName = faker.name().firstName();
            String fakeSurname = faker.name().lastName();



           imageProfile = findViewById(R.id.iv_profilePhoto);
            Contact contact = new Contact(fakeName,fakeSurname,"Contact # "+i, imageUrl,i);



            
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
        mContactsRecyclerAdapter = new ContactsRecyclerAdapter(mContacts,this, context ,avatars);
        mRecyclerView.setAdapter(mContactsRecyclerAdapter);


    }


    // deleting item through swipe and turn back - possibility
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

                   Snackbar.make(mRecyclerView, deletedContact.toString(),Snackbar.LENGTH_LONG)
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


    @Override
    public void onLongContactClick(int position) {

        String fakeName = faker.name().firstName();
        String fakeSurname = faker.name().lastName();

        Contact contact = new Contact(fakeName,fakeSurname,"Contect #"+position,imageUrl,position);
        Intent intent = new Intent(this, NewContactActivity.class);
        //need to be parsed?

        //we need to "teach to understand" parcel to deconstruct Contact
        //and here we need to "pack" not contact itself but object inside which we are inserting our strings
        ObjectToBeParsed objectToBeParsed = new ObjectToBeParsed(in, position);
        intent.putExtra(ObjectToBeParsed.class.getCanonicalName(), objectToBeParsed);
        intent.putExtra(ObjectToBeParsed.class.getCanonicalName(), objectToBeParsed);


        startActivity(intent);
    }




    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onPhotoDownloadedCallBack(List<String> urldownload, int position) {

        String fakeName = faker.name().firstName();
        String fakeSurname = faker.name().lastName();
        Contact contact = new Contact(fakeName,fakeSurname,"Contect #"+position,imageUrl,position);
        contact.setPhoto(urldownload.toString());

    }
}