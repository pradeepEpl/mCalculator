package ps.android.mcalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

import ps.android.mcalculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private ActivityMainBinding mainBinding;
    private FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        firebaseFirestore = FirebaseFirestore.getInstance();
        mainBinding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });
        mainBinding.get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrive();
            }
        });
    }

    private void retrive() {
        DocumentReference docRef = firebaseFirestore.collection("merit").document("Main");
        docRef.collection("merit")
                //.whereEqualTo("Main", "106")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    private void save() {
        Map<String, Object> user = new HashMap<>();
        user.put("Name", mainBinding.name.getText().toString());
        user.put("City", mainBinding.city.getText().toString());
        user.put("Hsc", mainBinding.ten.getText().toString());
        user.put("Ssc", mainBinding.hsc.getText().toString());
        user.put("Grad", mainBinding.grad.getText().toString());
        user.put("Dled", mainBinding.dled.getText().toString());
        user.put("Pre", mainBinding.pre.getText().toString());
        user.put("Main", mainBinding.main.getText().toString());
        firebaseFirestore.
                collection("merit").
                add(user).
                addOnSuccessListener(new OnSuccessListener<DocumentReference>(){
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                        Snackbar.make(mainBinding.getRoot(), "Successfully Submit", Snackbar.LENGTH_SHORT).show();
                    }
                }).
                addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }
}
