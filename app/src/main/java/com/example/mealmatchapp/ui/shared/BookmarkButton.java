package com.example.mealmatchapp.ui.shared;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mealmatchapp.R;

public class BookmarkButton extends androidx.appcompat.widget.AppCompatImageView {

    boolean active = false;
    public BookmarkButton(@NonNull Context context) {
        super(context);
        this.setImageResource(R.drawable.baseline_bookmark_add_24);
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setIcon();
            }
        });
    }

    public BookmarkButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.setImageResource(R.drawable.baseline_bookmark_add_24);
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setIcon();
            }
        });
    }

    public BookmarkButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setImageResource(R.drawable.baseline_bookmark_add_24);
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setIcon();
            }
        });
    }

    private void setIcon() {
        if (active) {
            active = false;
            this.setImageResource(R.drawable.baseline_bookmark_add_24);
        } else {
            active = true;
            this.setImageResource(R.drawable.bookmarks_outline);
        }
    }


}
