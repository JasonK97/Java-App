package com.example.myapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class stcN extends AppCompatActivity {
    EditText txtcomment;
    EditText Rating;
    Button btnsave;
    model member;
    TextView a,b;
    ArrayList<String> myArrayList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    ListView mylistView;
    //Firebase myFirebase;
    DatabaseReference reff;
  //  DatabaseReference reff1;
    DatabaseReference data;

//    EditText addcomment;
//    ImageView image_profile;
//    TextView post;
//
//    String postId;
//    String poblisherid;

    //   FirebaseUser firebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stc_n);

        txtcomment = (EditText)findViewById(R.id.add_comment);
        //  b= (TextView)findViewById(R.id.add_comment);
        btnsave = (Button)findViewById(R.id.button2);
        member = new model();
        String building;
        String roomNuber;

        Intent intent = getIntent();
        building = intent.getStringExtra("building");
        roomNuber = intent.getStringExtra("roomNumber");


        reff = FirebaseDatabase.getInstance().getReference().child("Bathrooms").child("Bathrooms").child(building).child(roomNuber).child("comment");

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // int rate = Integer.parseInt(Rating.getText().toString().trim());
                member.setComment(txtcomment.getText().toString().trim());
                // member.setRating(rate);
                reff.push().setValue(member);

            }
        });

        data = FirebaseDatabase.getInstance().getReference().child("Bathrooms").child("Bathrooms").child(building).child(roomNuber).child("comment");
        mylistView =  (ListView) findViewById(R.id.ListView);
        arrayAdapter =  new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,myArrayList);
        mylistView.setAdapter(arrayAdapter);
        data.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String value = dataSnapshot.getValue(model.class).toString();
                myArrayList.add(value);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


}