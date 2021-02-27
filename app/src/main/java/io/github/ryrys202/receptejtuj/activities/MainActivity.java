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
    public static final String QUERY_RESULT = "io.github.ryrys202.receptejtuj.QUERY_RESULT";

    public FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    public ArrayList<Recipe> listOfRecipes = new ArrayList<>();
    private EditText inputTextField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputTextField = (EditText) findViewById(R.id.input_data);
    }

    public void onStartClick(View v) {
        String ingredientsList = inputTextField.getText().toString();

        // Строка -> Нормализованный лист с алфавитным порядком
        List<String> listOfIngredients = Arrays.asList(ingredientsList.split(","));

        /*for (int i = 0; i < listOfIngredients.size(); i++) {
            String trimmedIngredient = listOfIngredients.get(i);
            listOfIngredients.set(i, trimmedIngredient);
        }*/

        Collections.sort(listOfIngredients);

        // Запрос к Firestore
        firebaseFirestore.collection("recipes")
                .whereEqualTo("ingredients", listOfIngredients)
                .get()
                .addOnCompleteListener(task -> {
                    // List<Recipe> listOfRecipe = task.getResult().toObjects(Recipe.class);
                    if (task.getResult().isEmpty()) {
                        Toast.makeText(this, "Рецепты не найдены", Toast.LENGTH_SHORT ).show();
                    } else {
                        for (QueryDocumentSnapshot document : (task.getResult())) {
                            Recipe currentRecipe = document.toObject(Recipe.class);
                            listOfRecipes.add(currentRecipe);
                            Log.d("tag", "onStartClick: " + currentRecipe.getDocumentId());
                        }
                        openResultActivity();
                    }
                });
    }

    public void openResultActivity() {
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putParcelableArrayListExtra("recipeList", listOfRecipes);
        startActivity(intent);
    }


}