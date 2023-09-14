package com.example.mealmatchapp.ui.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.mealmatchapp.R;
import com.example.mealmatchapp.databinding.FragmentDetailBinding;
import com.example.mealmatchapp.ui.dashboard.FilteredIngredient;
import com.example.mealmatchapp.ui.dashboard.IngredientRecyclerViewAdapter;

public class DetailFragment extends Fragment {

    private FragmentDetailBinding binding;
    private TextView portions;
    private ImageView add;
    private ImageView sub;


    private int portionsAmount = 3;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DetailViewModel detailViewModel =
                new ViewModelProvider(this).get(DetailViewModel.class);

        binding = FragmentDetailBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        portions = view.findViewById(R.id.sText);
        add = view.findViewById(R.id.plusButton);
        sub = view.findViewById(R.id.minusButton);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                portionsAmount++;
                portions.setText(""+portionsAmount);
            }
        });
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                portionsAmount = Math.max(portionsAmount - 1,1);
                portions.setText(""+portionsAmount);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}