package com.example.itearecyclerviewlesson.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.itearecyclerviewlesson.R;

import java.util.ArrayList;

public class ContactsRecyclerAdapter extends RecyclerView.Adapter<ContactsRecyclerAdapter.ViewHolder> {

    private ArrayList<Contact> mContacts = new ArrayList<>();
    private OnContactListener onContactListener;

    public ContactsRecyclerAdapter(ArrayList<Contact> mContacts, OnContactListener monContactListener) {
        this.mContacts = mContacts;
        this.onContactListener = monContactListener;
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

        //horrible and wrong practice to attack clicklistener here - not good performance at all

    }


    @Override
    public int getItemCount() {
        return mContacts.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView title, name, surname;
        OnContactListener onContactListener;

        public ViewHolder(@NonNull View itemView, OnContactListener onContactListener) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_title);
            name = itemView.findViewById(R.id.tv_name);
            surname = itemView.findViewById(R.id.tv_surname);
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
