package io.github.ryrys202.receptejtuj.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import io.github.ryrys202.receptejtuj.R;

public class MainActivity extends AppCompatActivity {
    public String[] listOfIngredients;
    private EditText inputTextField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputTextField = (EditText) findViewById(R.id.input_data);
    }

    public void onStartClick(View v) {
        String ingredientsList = inputTextField.getText().toString();
        this.listOfIngredients = ingredientsList.split(",");
        openResultActivity();
    }

    public void openResultActivity() {
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("ingredientsList", listOfIngredients);
        startActivity(intent);
    }


}