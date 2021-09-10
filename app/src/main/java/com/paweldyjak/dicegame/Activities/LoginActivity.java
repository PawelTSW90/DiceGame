package com.paweldyjak.dicegame.Activities;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.paweldyjak.dicegame.R;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    private TextInputEditText email;
    private TextInputEditText password;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //hides status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //hides title bar
        Objects.requireNonNull(getSupportActionBar()).hide();

        email = findViewById(R.id.email_editText_login);
        password = findViewById(R.id.password_editText_login);
        Button login = findViewById(R.id.login_button);
        firebaseAuth = FirebaseAuth.getInstance();
        login.setOnClickListener(v -> {
            String txt_email = email.getText().toString();
            String txt_password = password.getText().toString();
            loginUser(txt_email, txt_password);
        });

    }

    private void loginUser(String email, String password) {
        if(!isNetworkConnected()){
            Toast.makeText(this, getApplicationContext().getString(R.string.connection_error), Toast.LENGTH_SHORT).show();
        } else {
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener(authResult -> {
                startActivity(new Intent(LoginActivity.this, MainMenuActivity.class));
                finish();
            });
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnFailureListener(e -> Toast.makeText(LoginActivity.this, getApplicationContext().getText(R.string.authentication_error), Toast.LENGTH_SHORT).show());
        }
    }
        //method checks if user is connected to internet
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

}