package com.example.itearecyclerviewlesson.adapters;



import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.itearecyclerviewlesson.R;
import java.util.ArrayList;

import jp.wasabeef.blurry.Blurry;


public class ContactsRecyclerAdapter extends RecyclerView.Adapter<ContactsRecyclerAdapter.ViewHolder> {

    private ArrayList<Contact> mContacts;
    private OnContactListener onContactListener;

    private ImageView imageProfile;
    public Context context;



    private String imageUrl = "https://cdn.pixabay.com/photo/2017/09/01/00/15/png-2702691_960_720.png";


    public ContactsRecyclerAdapter(ArrayList<Contact> mContacts, OnContactListener monContactListener, Context context) {
        this.mContacts = mContacts;
        this.onContactListener = monContactListener;
        this.context = context;
    }

    //constructor





    @NonNull
    @Override
    public ContactsRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_contact_list_item, viewGroup,false);
        return new ViewHolder(view,onContactListener);
    }



    @Override
    public void onBindViewHolder(@NonNull ContactsRecyclerAdapter.ViewHolder viewHolder, int position) {

        viewHolder.title.setText(mContacts.get(position).getTitle());
        viewHolder.name.setText(mContacts.get(position).getName());
        viewHolder.surname.setText(mContacts.get(position).getSurname());


       // imageProfile.findViewById(R.id.iv_profilePhoto);

        /*Blurry.with(context)
                .radius(10)
                .animate(500)

                .capture(viewHolder.profilePhoto)
                .into(imageProfile);*/

        //issue with context and glide

        /*Glide.with(context).load(imageUrl)
                .centerCrop()
                .into(R.id.iv_profilePhoto);

        Log.d("url", "url check");*/

        //horrible and wrong practice to attach clicklistener here - not good performance at all

    }


    @Override
    public int getItemCount() {
        return mContacts.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView title, name, surname;
        OnContactListener onContactListener;
        ImageView profilePhoto;

        public ViewHolder(@NonNull View itemView, OnContactListener onContactListener) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_title);
            name = itemView.findViewById(R.id.tv_name);
            surname = itemView.findViewById(R.id.tv_surname);
            profilePhoto=itemView.findViewById(R.id.iv_profilePhoto);

            //setting this onContectListener
            this.onContactListener= onContactListener;

            //attaching click listener to whole view holder
            itemView.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {

            onContactListener.onContactClick(getAdapterPosition());
        }



    }

    //interface for detecting and interpret the click
    public interface OnContactListener {
        void onContactClick (int position);
    }

}
