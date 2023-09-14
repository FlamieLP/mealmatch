package com.example.mealmatchapp.ui.dashboard;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealmatchapp.R;

import java.util.List;
import java.util.function.Consumer;

public class IngredientRecyclerViewAdapter extends RecyclerView.Adapter<IngredientRecyclerViewAdapter.IngredientRecyclerViewDataHolder> {

    private List<String> ingredients;
    private Consumer<FilteredIngredient> fiCallback;
    private Context context;

    IngredientRecyclerViewAdapter(Context context, List<String> ingredients, Consumer<FilteredIngredient> fiCallback) {
        this.ingredients = ingredients;
        this.fiCallback = fiCallback;
        this.context = context;
    }

    @NonNull
    @Override
    public IngredientRecyclerViewDataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingridient_view, parent, false);
        return new IngredientRecyclerViewDataHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientRecyclerViewDataHolder holder, int position) {
        String currentItem  = ingredients.get(position);

        TextView tvNumber = holder.itemView.findViewById(R.id.ingridientName);
        tvNumber.setText(currentItem);

        ImageView icon = holder.itemView.findViewById(R.id.ingredientIcon);
        ImageView plus = holder.itemView.findViewById(R.id.plus);
        ImageView minus = holder.itemView.findViewById(R.id.minus);

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setColor(IconState.INCLUDED, icon);
                fiCallback.accept(new FilteredIngredient(currentItem, true));
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setColor(IconState.EXCLUDED, icon);
                fiCallback.accept(new FilteredIngredient(currentItem, false));
            }
        });
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    private void setColor(IconState state, ImageView view) {
        switch (state) {
            case NORMAL -> view.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(this.context, R.color.white)));
            case INCLUDED -> view.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(this.context, R.color.fresh_green)));
            case EXCLUDED -> view.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(this.context, R.color.bell_pepper)));
        }
    }

    private enum IconState {
        NORMAL,
        INCLUDED,
        EXCLUDED
    }

    class IngredientRecyclerViewDataHolder extends RecyclerView.ViewHolder {
        public IngredientRecyclerViewDataHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
