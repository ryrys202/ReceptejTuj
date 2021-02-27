package io.github.ryrys202.receptejtuj.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import io.github.ryrys202.receptejtuj.R;

public class ResultActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView resultList;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ArrayList<Recipe> recipesList = getIntent().getParcelableArrayListExtra("recipeList");

        resultList = findViewById(R.id.resultList);

        ArrayAdapter<Recipe> recipeArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, recipesList);
        resultList.setAdapter(recipeArrayAdapter);
        resultList.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ArrayList<Recipe> recipesList = getIntent().getParcelableArrayListExtra("recipeList");
        Toast.makeText(ResultActivity.this, recipesList.get(position).getName(), Toast.LENGTH_SHORT).show();

    }
}