package com.example.mealmatchapp.ui.shared;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mealmatchapp.R;

public class LikeButton extends androidx.appcompat.widget.AppCompatImageView {

    boolean active = false;
    public LikeButton(@NonNull Context context) {
        super(context);
        this.setImageResource(R.drawable.likes_outline);
        this.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setIcon();
            }
        });
    }

    public LikeButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.setImageResource(R.drawable.likes_outline);
        this.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setIcon();
            }
        });
    }

    public LikeButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setImageResource(R.drawable.likes_outline);
        this.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setIcon();
            }
        });
    }

    private void setIcon() {
        if (active) {
            active = false;
            this.setImageResource(R.drawable.likes_outline);
        } else {
            active = true;
            this.setImageResource(R.drawable.likes);
        }
    }


}
