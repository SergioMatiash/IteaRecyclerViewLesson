package com.example.itearecyclerviewlesson.parsingbetween;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class ObjectToBeParsed implements Parcelable {

    final static String LOG_TAG = "parcelTag";

    public String pName;
    public String pSurname;

   //constructor which reads
    protected ObjectToBeParsed(Parcel in) {

        Log.d(LOG_TAG, "MyObject(Parcel parcel)");
        pName = in.readString();
        pSurname = in.readString();
    }

    public static final Creator<ObjectToBeParsed> CREATOR = new Creator<ObjectToBeParsed>() {

        //unpacking parcel
        @Override
        public ObjectToBeParsed createFromParcel(Parcel in) {
            return new ObjectToBeParsed(in);
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

    //packing into parsel
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        Log.d(LOG_TAG, "writeToParcel");
        dest.writeString(pName);
        dest.writeString(pSurname);
    }
}
