package ps.android.mcalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

import ps.android.mcalculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private ActivityMainBinding mainBinding;
    private FirebaseFirestore firebaseFirestore;
    private float fM;

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
        firebaseFirestore.collection("merit")
                //.whereEqualTo("Main", "106")
                .orderBy("Merit", Query.Direction.DESCENDING)
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
        if(validate()) {
            mainBinding.progressbar.setVisibility(View.VISIBLE);
            calculateMerit();
            Map<String, Object> user = new HashMap<>();
            user.put("Name", mainBinding.name.getText().toString());
            user.put("City", mainBinding.city.getText().toString());
            user.put("Merit", fM);
            firebaseFirestore.
                    collection("merit").
                    add(user).
                    addOnSuccessListener(new OnSuccessListener<DocumentReference>(){
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            mainBinding.progressbar.setVisibility(View.GONE);
                            Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                            Snackbar.make(mainBinding.getRoot(), "Successfully Submit", Snackbar.LENGTH_SHORT).show();
                        }
                    }).
                    addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            mainBinding.progressbar.setVisibility(View.GONE);
                            Log.w(TAG, "Error adding document", e);
                        }
                    });
        } else {
            Snackbar.make(mainBinding.getRoot(), "Please fill all details properly", Snackbar.LENGTH_SHORT).show();
        }

    }

    private boolean validate() {
        boolean validate = false;
        if (mainBinding.name.getText().toString().equals("")) {
            mainBinding.name.setError("Please enter valid name");
        } else if (mainBinding.city.getText().toString().equals(""))  {
            mainBinding.city.setError("Please enter valid city");
        } else if (mainBinding.ten.getText().toString().equals("")) {
            mainBinding.ten.setError("Please 10th percentage");
        } else if (mainBinding.hsc.getText().toString().equals("")) {
            mainBinding.hsc.setError("Please 12th percentage");
        } else if (mainBinding.grad.getText().toString().equals("")) {
            mainBinding.grad.setError("Please graduation percentage");
        } else if (mainBinding.dled.getText().toString().equals("")) {
            mainBinding.ten.setError("Please Dled percentage");
        } else if (mainBinding.main.getText().toString().equals("")) {
            mainBinding.main.setError("Please mains marks");
        } else {
            validate = true;
        }
        return validate;
    }

    private void calculateMerit() {
        float ace = Utils.toPercent(Float.parseFloat(mainBinding.ten.getText().toString()) +
                Float.parseFloat(mainBinding.hsc.getText().toString()) +
                Float.parseFloat(mainBinding.grad.getText().toString()) +
                Float.parseFloat(mainBinding.dled.getText().toString()));
        Log.d(TAG, "Aca >> " + ace);
        float main = Utils.mainsMerit(Float.parseFloat(mainBinding.main.getText().toString()));
        Log.d(TAG, "Main >> " + main);
        fM = ace + main;
        Log.d(TAG, " FM >> " + fM);
    }
}
