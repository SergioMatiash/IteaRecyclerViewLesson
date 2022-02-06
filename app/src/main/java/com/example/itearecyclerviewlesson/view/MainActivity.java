package com.example.itearecyclerviewlesson.view;

import static android.content.ContentValues.TAG;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toolbar;

import com.example.itearecyclerviewlesson.R;
import com.example.itearecyclerviewlesson.adapters.Contact;
import com.example.itearecyclerviewlesson.adapters.ContactsRecyclerAdapter;
import com.github.javafaker.Faker;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.snackbar.Snackbar;
import java.util.ArrayList;




public class MainActivity extends AppCompatActivity implements ContactsRecyclerAdapter.OnContactListener {

    private static final int NUMBER_OF_CONTACTS = 100;

    // ui components
    private RecyclerView mRecyclerView;

    //adapters & variables
    private ArrayList<Contact> mContacts = new ArrayList<>();
    private ContactsRecyclerAdapter mContactsRecyclerAdapter;
    public String imageUrl = "https://cdn.pixabay.com/photo/2017/09/01/00/15/png-2702691_960_720.png";
    public ImageView imageProfile;
    private Menu menu;
    private ActionBar toolbar;

    public Context context;

    Faker faker = new Faker();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //adding collapsing toolbar behavior


        AppBarLayout mAppBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    isShow = true;
                    showOption(R.id.action_info);
                } else if (isShow) {
                    isShow = false;
                    hideOption(R.id.action_info);
                }
            }
        });

        mRecyclerView = findViewById(R.id.recyclerView);

        //adit text and fab are deleted

        //using touch helper for swipe
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        //we need to attach it to recyclerview
        itemTouchHelper.attachToRecyclerView(mRecyclerView);

        initRecyclerView ();
        insertStandardContacts();
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




        mContacts.add(position,new Contact(fakeName,fakeSurname,"Contact # "+position, imageUrl));
    }

    //creating contacts
    private void insertStandardContacts() {
        for (int i = 0; i <NUMBER_OF_CONTACTS ; i++) {

            String fakeName = faker.name().firstName();
            String fakeSurname = faker.name().lastName();



           imageProfile = findViewById(R.id.iv_profilePhoto);
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
        mContactsRecyclerAdapter = new ContactsRecyclerAdapter(mContacts,this, context );
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