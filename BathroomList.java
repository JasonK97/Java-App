package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class BathroomList extends AppCompatActivity {


    ArrayList<Bathroom> bathrooms;
    ListView listView;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference mRootRef = database.getReference();
    private BathroomList smith;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bathroom_list);
        // listView = findViewById(R.id.List);
    }

    public void populateBathroomList(String name) {
        Intent intent = new Intent(this, BathroomActivity.class);
        bathrooms = new ArrayList<>();
        mRootRef.child("Bathrooms").child("Bathrooms").child(name).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                Log.i("setting Bathrooms", map.toString());
                //ListView.s = text;
                for (Object j : map.keySet()) {
                    Log.i("mapKeySet", j.toString());
                    Bathroom b = new Bathroom();
                    b.setBuilding(name);
                    b.setRoomNumber(j.toString());
                    Log.i("mapValueSet", map.get(j).toString());
                    Map<String, Object> d = (Map) map.get(j);
                    Log.i("d=", d.toString());
                    b.setDescription(d.get("Description").toString());
                    b.setRating((long) d.get("OverallPoop"));
                    b.setRatings((ArrayList) d.get("Ratings"));
                    Map<String, Object> tagsmap = (Map) d.get("Tags");
                    ArrayList<String> tags = new ArrayList<>();
                    for (Object s : tagsmap.keySet()) {
                        Log.i("tagskeyset", s.toString());
                        tags.add(s.toString());
                    }
                    b.setTags(tags);
                    Log.i("bathroom", b.toString());
                    bathrooms.add(b);
                }
                //  ArrayAdapter<Bathroom> adapter = new ArrayAdapter<>(getParent(), android.R.layout.simple_list_item_1, bathrooms);
                //    listView.setAdapter(adapter);
                //      adapter.notifyDataSetChanged();
                TextView one = findViewById(R.id.One);
                TextView two = findViewById(R.id.Two);
                TextView three = findViewById(R.id.Three);
                TextView four = findViewById(R.id.Four);
                Button butt1 = findViewById(R.id.butt1);
                Button butt2 = findViewById(R.id.butt2);
                Button butt3 = findViewById(R.id.butt3);
                Button butt4 = findViewById(R.id.butt4);
                Log.i("bathroomsSize", bathrooms.size() + "");

                one.setText(bathrooms.get(0).getRoomNumber());
                butt1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        intent.putExtra("Building", bathrooms.get(0).getBuilding());
                        intent.putExtra("Description", bathrooms.get(0).getDescription());
                        intent.putExtra("Rating", bathrooms.get(0).getRating());
                        intent.putExtra("Room Number", bathrooms.get(0).getRoomNumber());
                        intent.putExtra("Ratings",  bathrooms.get(0).getRatings());
                        intent.putStringArrayListExtra("Tags", bathrooms.get(0).getTags());
                        startActivity(intent);
                    }
                });
                if (bathrooms.size() == 1) {
                    two.setVisibility(View.GONE);
                    three.setVisibility(View.GONE);
                    four.setVisibility(View.GONE);
                    butt2.setVisibility(View.GONE);
                    butt3.setVisibility(View.GONE);
                    butt4.setVisibility(View.GONE);
                    return;
                }
                two.setText(bathrooms.get(1).getRoomNumber());
                butt2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        intent.putExtra("Building", bathrooms.get(1).getBuilding());
                        intent.putExtra("Description", bathrooms.get(1).getDescription());
                        intent.putExtra("Rating", bathrooms.get(1).getRating());
                        intent.putExtra("Room Number", bathrooms.get(1).getRoomNumber());
                        intent.putExtra("Ratings", bathrooms.get(1).getRatings());
                        intent.putStringArrayListExtra("Tags", bathrooms.get(1).getTags());
                        startActivity(intent);
                    }
                });
                if (bathrooms.size() == 2) {
                    three.setVisibility(View.GONE);
                    four.setVisibility(View.GONE);
                    butt3.setVisibility(View.GONE);
                    butt4.setVisibility(View.GONE);
                    return;
                }
                three.setText(bathrooms.get(2).getRoomNumber());
                butt3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        intent.putExtra("Building", bathrooms.get(2).getBuilding());
                        intent.putExtra("Description", bathrooms.get(2).getDescription());
                        intent.putExtra("Rating", bathrooms.get(2).getRating());
                        intent.putExtra("Room Number", bathrooms.get(2).getRoomNumber());
                        intent.putExtra("Ratings",  bathrooms.get(2).getRatings());
                        intent.putStringArrayListExtra("Tags", bathrooms.get(2).getTags());
                        startActivity(intent);
                    }
                });
                if (bathrooms.size() == 3) {
                    four.setVisibility(View.GONE);
                    butt4.setVisibility(View.GONE);
                    return;
                }
                four.setText(bathrooms.get(3).getRoomNumber());
                butt4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        intent.putExtra("Building", bathrooms.get(3).getBuilding());
                        intent.putExtra("Description", bathrooms.get(3).getDescription());
                        intent.putExtra("Rating", bathrooms.get(3).getRating());
                        intent.putExtra("Room Number", bathrooms.get(3).getRoomNumber());
                        intent.putExtra("Ratings",  bathrooms.get(3).getRatings());
                        intent.putStringArrayListExtra("Tags", bathrooms.get(3).getTags());
                        startActivity(intent);
                    }
                });
                //   bathrooms = map;

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.i("setting textview", "failed");
            }
        });
    }

    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        String name = intent.getStringExtra("Building");
        populateBathroomList(name);


    }

    public void addItems(View v) {
        //     ListView.add("Clicked : "+clickCounter++);
        //    adapter.notifyDataSetChanged();
    }
}
