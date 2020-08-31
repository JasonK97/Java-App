package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.fragment.stcN;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

//import dukeapps.naturecalls.ui.map.MapFragment;

public class BathroomActivity extends AppCompatActivity {
    private String description;
    private List<String> tags;
    private List<Ratings> ratings;
    private String roomNumber;
    private String photo;
    private String building;
    private long rating;
    private Button button;

    //DatabaseReference childRef = conditionRef.child("tester");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bathroom_info);
        button = (Button)findViewById(R.id.reviewBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivty2();
            }
        });

        Intent intent = getIntent();
        description = intent.getStringExtra("Description");
        roomNumber = intent.getStringExtra("Room Number");
        building = intent.getStringExtra("Building");
        rating = intent.getLongExtra("Rating", 0);
        tags = intent.getStringArrayListExtra("Tags");
        //ratings = intent.getParcelableArrayListExtra("Ratings");
        TextView name = findViewById(R.id.bRoomName);
        name.setText(building + " " + roomNumber);
        String s = " " ;
        for (String k: tags){
            s += k+" ";
        }

        TextView tagsView = findViewById(R.id.Tags);
        tagsView.setText(s);

        TextView descView = findViewById(R.id.description);
        descView.setText(description);
        findViewById(R.id.poop1).setVisibility(View.GONE);
        findViewById(R.id.poop2).setVisibility(View.GONE);
        findViewById(R.id.poop3).setVisibility(View.GONE);
        findViewById(R.id.poop4).setVisibility(View.GONE);
        findViewById(R.id.poop5).setVisibility(View.GONE);


        switch ((int) rating) {
            case 0:
                break;
            case 1:
                findViewById(R.id.poop1).setVisibility(View.VISIBLE);
                break;
            case 2:
                findViewById(R.id.poop1).setVisibility(View.VISIBLE);
                findViewById(R.id.poop2).setVisibility(View.VISIBLE);
                break;
            case 3:
                findViewById(R.id.poop1).setVisibility(View.VISIBLE);
                findViewById(R.id.poop2).setVisibility(View.VISIBLE);
                findViewById(R.id.poop3).setVisibility(View.VISIBLE);
                break;
            case 4:
                findViewById(R.id.poop1).setVisibility(View.VISIBLE);
                findViewById(R.id.poop2).setVisibility(View.VISIBLE);
                findViewById(R.id.poop3).setVisibility(View.VISIBLE);
                findViewById(R.id.poop4).setVisibility(View.VISIBLE);
                break;
            case 5:
                findViewById(R.id.poop1).setVisibility(View.VISIBLE);
                findViewById(R.id.poop2).setVisibility(View.VISIBLE);
                findViewById(R.id.poop3).setVisibility(View.VISIBLE);
                findViewById(R.id.poop4).setVisibility(View.VISIBLE);
                findViewById(R.id.poop5).setVisibility(View.VISIBLE);
                break;
        }

    }

    public void openActivty2(){
        Intent intent = new Intent(this, stcN.class);
        intent.putExtra("building", building);
        intent.putExtra("roomNumber", roomNumber);
        startActivity(intent);

    }

    protected void onStart() {
        super.onStart();

    }
}
