package io.github.ryrys202.receptejtuj.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import io.github.ryrys202.receptejtuj.R;

public class ResultActivity extends AppCompatActivity {
    public FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    private RecyclerView resultList;
    private ArrayList<RecipeModel> recipeModelArrayList = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        resultList = findViewById(R.id.resultList);

        List<String> ingredientsAsList = Arrays.asList(this.getIntent().getStringArrayExtra("ingredientsList"));
        Collections.sort(ingredientsAsList);

        firebaseFirestore.collection("recipes")
                .whereArrayContainsAny("ingredients", ingredientsAsList)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.getResult().isEmpty()) {
                        Toast.makeText(this, "Рецепты не найдены", Toast.LENGTH_SHORT ).show();
                    } else {
                        for (QueryDocumentSnapshot document : (task.getResult())) {
                            recipeModelArrayList.add(new RecipeModel(document.getString("name"), document.getString("url"), document.getString("description"), document.getString("img_url")));
                        }
                    }
                    RecipeAdapter recipeAdapter = new RecipeAdapter(this, recipeModelArrayList);

                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

                    resultList.setLayoutManager(linearLayoutManager);
                    resultList.setAdapter(recipeAdapter);
                });
    }

   /* public ArrayList<List<String>> generateArrays(List<String> initial) {
        ArrayList<List<String>> result = new ArrayList<>();

        for (int i = 0; i < initial.size(); i++) {
            List<String> currentList = new ArrayList<>();
            for (int j = 0; j < i+1; j++) {
                currentList.add(initial.get(j));
            }
            result.add(currentList);
        }
        return result;
    } */



}