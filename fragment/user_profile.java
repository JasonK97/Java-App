package com.example.myapplication.fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.fragment.UserHelperClass;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class user_profile extends AppCompatActivity {

    EditText name, gmail, requested;
    Button updated;

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        name = findViewById(R.id.user_name);
        gmail = findViewById(R.id.user_mail);
        requested = findViewById(R.id.request);
        updated = findViewById(R.id.update);
        updated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("Users");

                //Get all the values
                String signName = name.getText().toString();
                String userEmail = gmail.getText().toString();
                String requests = requested.getText().toString();


                UserHelperClass helperClass = new UserHelperClass(signName, userEmail, requests);
                reference.child(signName).setValue(helperClass);
                Toast.makeText(user_profile.this, "Submitted", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
