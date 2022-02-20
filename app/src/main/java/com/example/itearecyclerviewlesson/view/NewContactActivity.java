package com.example.itearecyclerviewlesson.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.itearecyclerviewlesson.R;
import com.example.itearecyclerviewlesson.parsingbetween.ObjectToBeParsed;

public class NewContactActivity extends AppCompatActivity {

    TextView name,surname;

    Button goToEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);

        name = findViewById(R.id.et_add_name);
        surname = findViewById(R.id.et_add_surname);

        ObjectToBeParsed objectToBeParsed = (ObjectToBeParsed) getIntent().getParcelableExtra(ObjectToBeParsed.class.getCanonicalName());


        name.setText(String.valueOf(objectToBeParsed.pName));
        surname.setText(String.valueOf(objectToBeParsed.pSurname));

       /* goToEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"mail@mail.com"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "email subject"); // optional
                intent.setType("message/rfc822"); // useful define which kind of app to perform the action
                startActivity(Intent.createChooser(intent, "Send Email"));
            }
        });*/



    }
}