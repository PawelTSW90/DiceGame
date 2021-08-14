package com.paweldyjak.dicegame.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.paweldyjak.dicegame.R;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {
    private TextInputEditText email;
    private TextInputEditText password;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private DatabaseReference databaseEmailsReference;
    boolean isEmailUsed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //hides status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //hides title bar
        Objects.requireNonNull(getSupportActionBar()).hide();
        email = findViewById(R.id.email_editText);
        password = findViewById(R.id.password_editText);
        Button register = findViewById(R.id.register_button2);
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("users");
        databaseEmailsReference = FirebaseDatabase.getInstance().getReference().child("names&emailsInUse").child("emails");

        register.setOnClickListener(v -> {
            String txt_email = email.getText().toString();
            String txt_password = password.getText().toString();

            if (TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)) {
                Toast.makeText(RegisterActivity.this, "Empty credentials", Toast.LENGTH_SHORT).show();
            } else if (txt_password.length() < 6) {
                Toast.makeText(RegisterActivity.this, "Password too short", Toast.LENGTH_SHORT).show();
            } else {
                checkIfEmailIsUsed(txt_email);
                registerUser(txt_email, txt_password);
            }
        });
    }

    private void registerUser(String email, String password) {

        if (!isNetworkConnected()) {
            Toast.makeText(this, getApplicationContext().getString(R.string.connection_error), Toast.LENGTH_SHORT).show();
        } else if (isEmailUsed = true) {
            Toast.makeText(this, getApplicationContext().getText(R.string.email_in_use_error), Toast.LENGTH_SHORT).show();

        } else {
            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(this, getApplicationContext().getText(R.string.registration_successful), Toast.LENGTH_SHORT).show();
                    addUserToDatabase(email, password);
                    Intent intent = new Intent(this, GameBoardActivity.class);
                    startActivity(intent);
                    this.finish();
                } else {
                    Toast.makeText(this, "Registration failed!", Toast.LENGTH_SHORT).show();
                }

            });
        }


    }

    private void addUserToDatabase(String email, String password) {
        String userUID = firebaseAuth.getUid();
        databaseReference.child(userUID).child("name").setValue("null");
        databaseReference.child(userUID).child("email").setValue(email);
        databaseReference.child(userUID).child("password").setValue(password);
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    public boolean checkIfEmailIsUsed(String email) {

        databaseEmailsReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue().equals(email)){
                    isEmailUsed = true;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return isEmailUsed;
    }


}