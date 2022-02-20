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
import com.example.itearecyclerviewlesson.model.Contact;
import com.example.itearecyclerviewlesson.retrofitmodule.ProfilePhoto;
import com.example.itearecyclerviewlesson.retrofitmodule.RetrofitDownloadController;

import java.util.ArrayList;
import java.util.List;


public class ContactsRecyclerAdapter extends RecyclerView.Adapter<ContactsRecyclerAdapter.ViewHolder> {

    private ArrayList<Contact> mContacts;
    private OnContactListener onContactListener;
    private List<ProfilePhoto> profilePhotos;
    private ArrayList<String> imageUrls;

    public Context context;



    private String imageUrl = "https://cdn.pixabay.com/photo/2017/09/01/00/15/png-2702691_960_720.png";


    public ContactsRecyclerAdapter(ArrayList<Contact> mContacts, OnContactListener monContactListener, Context context, List<ProfilePhoto> profilePhotos) {
        this.mContacts = mContacts;
        this.onContactListener = monContactListener;
        this.context = context;
        this.profilePhotos=profilePhotos;
    }

    public void setPhotoChangedAdapter(List<ProfilePhoto> profilePhotos) {
        this.profilePhotos=profilePhotos;
        //check if there is more effective method, cause using notifydatasetchanged is so costly
        notifyDataSetChanged();
    }





    @NonNull
    @Override
    public ContactsRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_contact_list_item, viewGroup,false);
        return new ViewHolder(view,onContactListener);
    }



    @Override
    public void onBindViewHolder(@NonNull ContactsRecyclerAdapter.ViewHolder viewHolder, int position) {

        viewHolder.bindTo(mContacts.get(position));

        /*Contact contact = mContacts.get(position);

        viewHolder.title.setText(contact.getTitle());
        viewHolder.name.setText(contact.getName());
        viewHolder.surname.setText(contact.getSurname());*/


        context = viewHolder.itemView.getContext();

        Glide.with(context).load(imageUrls.get(position))
                .centerCrop()
                .into(viewHolder.profilePhoto);

        Log.d("url", "url check");




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

            onContactListener.onLongContactClick(getAdapterPosition());
        }

        //3rd attempt to make view holder happy
        public void bindTo (Contact contact) {

         title.setText(contact.getTitle());
         name.setText(contact.getName());
         surname.setText(contact.getSurname());

        // contact doesnt have context thus its hard to attach glide and photo
        }


    }






    //interface for detecting and interpret the click
    public interface OnContactListener {
        void onLongContactClick (int position);
    }

}
