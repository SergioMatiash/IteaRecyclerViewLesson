package com.example.itearecyclerviewlesson.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.itearecyclerviewlesson.R;
import com.example.itearecyclerviewlesson.parsingbetween.ObjectToBeParsed;

public class NewContactActivity extends AppCompatActivity {

    TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);

        ObjectToBeParsed objectToBeParsed = (ObjectToBeParsed) getIntent().getParcelableExtra(ObjectToBeParsed.class.getCanonicalName());

    }
}