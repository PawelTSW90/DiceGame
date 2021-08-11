package com.paweldyjak.dicegame.Activities;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.paweldyjak.dicegame.R;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {
    private TextInputEditText email;
    private TextInputEditText password;
    private FirebaseAuth firebaseAuth;

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
            }else{
                Toast.makeText(this, "Registration failed!", Toast.LENGTH_SHORT).show();
            }

        });

    }

}