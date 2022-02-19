package com.example.itearecyclerviewlesson.parsingbetween;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.example.itearecyclerviewlesson.model.Contact;
import com.github.javafaker.Faker;

public class ObjectToBeParsed implements Parcelable {

    final static String LOG_TAG = "parcelTag";
    public String imageUrl = "https://cdn.pixabay.com/photo/2017/09/01/00/15/png-2702691_960_720.png";

    public String pName;
    public String pSurname;

    Faker faker = new Faker();

   //constructor which reads
    public ObjectToBeParsed(Parcel in,int position) {

        Contact contact = new Contact(faker.name().firstName(),faker.name().lastName(),"Contact # "+position,imageUrl,position);
        Log.d(LOG_TAG, "MyObject(Parcel parcel)");
        pName = contact.getName();
        pSurname = contact.getSurname();
    }

    public static final Creator<ObjectToBeParsed> CREATOR = new Creator<ObjectToBeParsed>() {

        //unpacking parcel


        @Override
        public ObjectToBeParsed createFromParcel(Parcel source) {

            //need to return new onject
            return new ObjectToBeParsed(source, source.dataPosition());
        }

        @Override
        public ObjectToBeParsed[] newArray(int size) {
            return new ObjectToBeParsed[size];
        }
    };

    //simple constructor

    public ObjectToBeParsed(String pName, String pSurname) {
        Log.d(LOG_TAG, "MyObject(String pName and String pSurname)");
        this.pName = pName;
        this.pSurname = pSurname;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    //packing into parcel
    @Override
    public void writeToParcel(Parcel dest, int flags) {


        Log.d(LOG_TAG, "writeToParcel");
        dest.writeString(pName);
        dest.writeString(pSurname);
    }
}
