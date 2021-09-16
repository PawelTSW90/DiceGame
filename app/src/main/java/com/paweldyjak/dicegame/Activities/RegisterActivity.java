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
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.paweldyjak.dicegame.R;
import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {
    private TextInputEditText email;
    private TextInputEditText password;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference usersDatabaseReference;

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
        usersDatabaseReference = FirebaseDatabase.getInstance().getReference().child("users");

        register.setOnClickListener(v -> {
            String txt_email = email.getText().toString();
            String txt_password = password.getText().toString();
            if (!txt_email.isEmpty() && !txt_password.isEmpty()) {
                registerUser(txt_email, txt_password);
            } else {
                Toast.makeText(this, getApplicationContext().getText(R.string.empty_field_error), Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void registerUser(String email, String password) {


        if (!isConnectedToNetwork()) {
            Toast.makeText(this, getApplicationContext().getString(R.string.connection_error), Toast.LENGTH_SHORT).show();
        } else {

            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(this, getApplicationContext().getText(R.string.registration_successful), Toast.LENGTH_SHORT).show();
                            addUserToDatabase(email, password);
                            Intent intent = new Intent(this, MainMenuActivity.class);
                            startActivity(intent);
                            this.finish();
                        } else {
                            try {
                                throw task.getException();
                            } catch (FirebaseAuthUserCollisionException e) {
                                Toast.makeText(this, getApplicationContext().getText(R.string.email_in_use_error), Toast.LENGTH_SHORT).show();
                            } catch (FirebaseAuthWeakPasswordException e) {
                                Toast.makeText(this, getApplicationContext().getText(R.string.password_error), Toast.LENGTH_SHORT).show();
                            } catch (FirebaseAuthInvalidCredentialsException e) {
                                Toast.makeText(this, getApplicationContext().getText(R.string.wrong_email_error), Toast.LENGTH_SHORT).show();
                            } catch (Exception e) {
                                Toast.makeText(this, "" + task.getException(), Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
            );


        }
    }

    private void addUserToDatabase(String email, String password) {
        String userUID = firebaseAuth.getUid();
        usersDatabaseReference.child(userUID).child("name").setValue("null");
        usersDatabaseReference.child(userUID).child("email").setValue(email);
        usersDatabaseReference.child(userUID).child("password").setValue(password);
        usersDatabaseReference.child(userUID).child("ranking").setValue(0);
    }

    private boolean isConnectedToNetwork() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    @Override
    public void onBackPressed(){

    }

}