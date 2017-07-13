package edu.pe.ulima;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by CVALENCIA on 13/07/2017.
 */

public class FirebaseDataBase {

    public FirebaseDataBase() {
    }

    public void submitScore(String user, int score) {

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(user);
        databaseReference.child("PUNTAJE").setValue(score);
        System.out.println("NOMBRE : " + user );
        System.out.println("SCORE : " + score );
    }

}
