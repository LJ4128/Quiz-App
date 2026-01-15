package org.balitmorecityschools.quizappljv1;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class LeaderboardScreen extends AppCompatActivity {
    TextView leaderboardNamesTV, leaderboardScoresTV;
    FirebaseDatabase database;
    DatabaseReference myRef;
    LeaderboardEntry myUser, temp;
    int userIndex;
    String leaderboardNamesSTR, leaderboardScoresSTR;
    ArrayList<LeaderboardEntry> leaderboardList;
    int leaderboardPosition;
    final String TAG = "FANPOFPAJNOIEWFNNpNIRNANIAPIRE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard_screen);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("message");
        userIndex = 0;
        leaderboardPosition = 1;
        leaderboardNamesSTR = "";
        leaderboardNamesSTR = "";
        leaderboardNamesTV = findViewById(R.id.leaderboardNamesTV);
        leaderboardScoresTV = findViewById(R.id.leaderboardScoresTV);
        leaderboardList = new ArrayList<>();
        ValueEventListener idk = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                // Iterate through all the children in the snapshot, this should be
                // all the children in the "customers" object
                leaderboardList = new ArrayList<>();
                for (DataSnapshot leaderboardSnapShot : dataSnapshot.getChildren()) {
                    //From our snapshot, get the value of our key/value pair. This value
                    //contains a customer object
                    //myCustomer = customerSnapShot.getValue(Customer.class);
                    LeaderboardEntry temp = leaderboardSnapShot.getValue(LeaderboardEntry.class);
                    String name = leaderboardSnapShot.child("name").getValue(String.class);
                    int score = leaderboardSnapShot.child("score").getValue(Integer.class);
                    String deviceID = leaderboardSnapShot.child("deviceID").getValue(String.class);
                    myUser = new LeaderboardEntry(name, score, deviceID);

                    leaderboardList.add(myUser);
                    leaderboardPosition = 1;
                    leaderboardNamesSTR = "";
                    leaderboardScoresSTR = "";
                }
                Collections.sort(leaderboardList,Collections.reverseOrder());

                if (leaderboardList.size() >= 1){
                    for (LeaderboardEntry user : leaderboardList){
                        leaderboardNamesSTR = leaderboardNamesSTR + leaderboardPosition + ". " + user.getName() + "\n";
                        leaderboardScoresSTR = leaderboardScoresSTR + user.getScore() + "\n";
                        leaderboardPosition++;
                    }
                    leaderboardNamesTV.setText(leaderboardNamesSTR);
                    leaderboardScoresTV.setText(leaderboardScoresSTR);
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        };
        myRef.addValueEventListener(idk);





    }
}