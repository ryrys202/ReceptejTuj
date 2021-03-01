package io.github.ryrys202.receptejtuj.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import io.github.ryrys202.receptejtuj.R;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.Viewholder> {

    private Context context;
    private ArrayList<RecipeModel> recipeModelArrayList;

    public RecipeAdapter(Context context, ArrayList<RecipeModel> recipeModelArrayList) {
        this.context = context;
        this.recipeModelArrayList = recipeModelArrayList;
    }

    @NonNull
    @Override
    public RecipeAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeAdapter.Viewholder holder, int position) {
        // to set data to textview and imageview of each card layout
        RecipeModel model = recipeModelArrayList.get(position);
        holder.recipeTitle.setText(model.getName());
        holder.recipeDescription.setText(model.getDescription());
        new ImageLoadTask(model.getImage(), holder.recipeImage).execute();
        holder.recipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(model.getUrl())));
            }
        });
    }

    @Override
    public int getItemCount() {
        return recipeModelArrayList.size();
    }

    public static class Viewholder extends RecyclerView.ViewHolder {
        private ImageView recipeImage;
        private TextView recipeTitle, recipeDescription;
        private Button recipeButton;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            recipeImage = itemView.findViewById(R.id.recipeImage);
            recipeTitle = itemView.findViewById(R.id.recipeTitle);
            recipeDescription = itemView.findViewById(R.id.recipeDescription);
            recipeButton = itemView.findViewById(R.id.recipeAction);
        }
    }
}
