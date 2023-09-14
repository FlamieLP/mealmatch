package com.example.mealmatchapp.ui.dashboard;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealmatchapp.R;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class RecepieRecyclerViewAdapter extends RecyclerView.Adapter<RecepieRecyclerViewAdapter.RecepieRecyclerViewDataHolder> {

    private List<Recepie> recepies;
    private Consumer<Recepie> fiCallback;
    private Context context;

    RecepieRecyclerViewAdapter(Context context, List<Recepie> recepies, Consumer<Recepie> fiCallback) {
        this.recepies = recepies;
        this.fiCallback = fiCallback;
        this.context = context;
    }

    @NonNull
    @Override
    public RecepieRecyclerViewDataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recepie_card, parent, false);
        return new RecepieRecyclerViewDataHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecepieRecyclerViewDataHolder holder, int position) {
        Recepie recepie  = recepies.get(position);

        ImageView foodPic = holder.itemView.findViewById(R.id.foodPic);
        TextView name = holder.itemView.findViewById(R.id.rezipeTitle);
        TextView diff = holder.itemView.findViewById(R.id.r_difficultyValue);
        TextView time = holder.itemView.findViewById(R.id.r_time);
        CardView card = holder.itemView.findViewById(R.id.r_food_card);

        foodPic.setImageResource(recepie.imageId);
        name.setText(recepie.title);
        diff.setText(recepie.difficulty);
        time.setText(recepie.duration);

        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fiCallback.accept(recepie);
            }
        });
    }

    @Override
    public int getItemCount() {
        return recepies.size();
    }


    class RecepieRecyclerViewDataHolder extends RecyclerView.ViewHolder {
        public RecepieRecyclerViewDataHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
