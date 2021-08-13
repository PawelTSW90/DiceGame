package com.paweldyjak.dicegame.Activities;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.paweldyjak.dicegame.R;

import java.lang.ref.Reference;
import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {
    private TextInputEditText email;
    private TextInputEditText password;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

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
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("users");

        register.setOnClickListener(v -> {
            String txt_email = email.getText().toString();
            String txt_password = password.getText().toString();

            if(TextUtils.isEmpty(txt_email)|| TextUtils.isEmpty(txt_password)){
                Toast.makeText(RegisterActivity.this, "Empty credentials", Toast.LENGTH_SHORT).show();
            } else if(txt_password.length()<6){
                Toast.makeText(RegisterActivity.this, "Password too short", Toast.LENGTH_SHORT).show();
            } else{
                registerUser(txt_email, txt_password);
            }
        });
    }

    private void registerUser(String email, String password){

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                Toast.makeText(this, "Registering user successful", Toast.LENGTH_SHORT).show();
                updatedDatabase(email, password);
                Intent intent = new Intent(this, GameBoardActivity.class);
                startActivity(intent);
                this.finish();
            }else{
                Toast.makeText(this, "Registration failed!", Toast.LENGTH_SHORT).show();
            }

        });

    }

    private void updatedDatabase(String email, String password){
        String userUID = firebaseAuth.getUid();
        databaseReference.child(userUID).child("name").setValue("false");
        databaseReference.child(userUID).child("email").setValue(email);
        databaseReference.child(userUID).child("password").setValue(password);
    }

}