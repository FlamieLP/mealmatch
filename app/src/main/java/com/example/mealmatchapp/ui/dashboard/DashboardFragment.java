package com.example.mealmatchapp.ui.dashboard;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListPopupWindow;
import android.widget.PopupMenu;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealmatchapp.R;
import com.example.mealmatchapp.databinding.FragmentDashboardBinding;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment implements PopupMenu.OnMenuItemClickListener {

    private FragmentDashboardBinding binding;
    private LinearLayout allergyLayout;
    private LinearLayout ingredientLayout;
    private RecyclerView ingredientRecyclerView;
    private RecyclerView recepieRecyclerView;
    private SearchView searchView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        allergyLayout = root.findViewById(R.id.allegiesList);
        ingredientLayout = root.findViewById(R.id.filteredIngredientList);
        searchView = root.findViewById(R.id.searchView2);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty()) {
                    ingredientRecyclerView.setVisibility(View.GONE);
                } else {
                    ingredientRecyclerView.setVisibility(View.VISIBLE);
                }
                return false;
            }
        });

        ImageView btn1 = root.findViewById(R.id.addAllergies);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(v);
                //l.addView(getButton(root, l));
            }
        });

        return root;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ingredientRecyclerView = view.findViewById(R.id.ingredientList);
        ingredientRecyclerView.setVisibility(View.GONE);
        List<String> ingredients = List.of("Apfel", "Annanas", "Ameisen");
        LinearLayoutManager manager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        IngredientRecyclerViewAdapter adapter = new IngredientRecyclerViewAdapter(
                this.getContext(),
                ingredients,
                this::addIngredient);
        ingredientRecyclerView.setAdapter(adapter);
        ingredientRecyclerView.setLayoutManager(manager);
        DividerItemDecoration devider = new DividerItemDecoration(view.getContext(), manager.getOrientation());
        devider.setDrawable(view.getResources().getDrawable(R.drawable.divider_v));
        ingredientRecyclerView.addItemDecoration(devider);
        adapter.notifyDataSetChanged();


        recepieRecyclerView = view.findViewById(R.id.recepies);
        //recepieRecyclerView.setVisibility(View.GONE);
        List<Recepie> recepies = List.of(
                new Recepie(
                        "Indisches Blumenkohl Curry",
                        "Advanced",
                        "30 min.",
                        R.drawable.blumenkohl_curry_indisches_gobi_butter_masala_rezept_3_of_4
                ),
                new Recepie(
                        "Dan Dan Noodles",
                        "Intermediate",
                        "1,5 Std.",
                        R.drawable.blumenkohl_curry_indisches_gobi_butter_masala_rezept_3_of_4
                ),
                new Recepie(
                        "Anderes Curry",
                        "Easy",
                        "10 min.",
                        R.drawable.blumenkohl_curry_indisches_gobi_butter_masala_rezept_3_of_4
                ),
                new Recepie(
                        "Wackelpudding",
                        "Advanced",
                        "2 Tage",
                        R.drawable.blumenkohl_curry_indisches_gobi_butter_masala_rezept_3_of_4
                )
        );
        LinearLayoutManager rmanager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        RecepieRecyclerViewAdapter radapter = new RecepieRecyclerViewAdapter(
                this.getContext(),
                recepies,
                v -> Navigation.findNavController(view).navigate(R.id.navigation_home));
        recepieRecyclerView.setAdapter(radapter);
        recepieRecyclerView.setLayoutManager(rmanager);
        DividerItemDecoration rdevider = new DividerItemDecoration(view.getContext(), rmanager.getOrientation());
        rdevider.setDrawable(view.getResources().getDrawable(R.drawable.divider_v));
        recepieRecyclerView.addItemDecoration(rdevider);
        radapter.notifyDataSetChanged();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void showPopup(View view) {
        PopupMenu p = new PopupMenu(this.getContext(), view);
        p.setOnMenuItemClickListener(this);
        p.inflate(R.menu.allergies);
        p.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.item1) {
            this.addAllergy("Laktose");
            return true;
        } else if (itemId == R.id.item2) {
            this.addAllergy("Gluten");
            return true;
        } else if (itemId == R.id.item3) {
            this.addAllergy("Erdnuss");
            return true;
        } else if (itemId == R.id.item4) {
            this.addAllergy("Lachs");
            return true;
        }
        return false;
    }

    private void addAllergy(String name) {
        TextView allergy = new TextView(allergyLayout.getContext());
        allergy.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        allergy.setText(name);
        allergy.setBackground(ContextCompat.getDrawable(allergyLayout.getContext(), R.drawable.roundstyle));
        allergy.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(allergyLayout.getContext(), R.color.bell_pepper)));
        allergy.setPadding(40, 0,40,0);
        allergy.setTextSize(17);
        allergy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allergyLayout.removeView(allergy);
            }
        });
        allergyLayout.addView(allergy);
    }

    private void addIngredient(FilteredIngredient ingredient) {
        TextView allergy = new TextView(ingredientLayout.getContext());
        allergy.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        allergy.setText(ingredient.name);
        allergy.setBackground(ContextCompat.getDrawable(ingredientLayout.getContext(), R.drawable.roundstyle));
        allergy.setBackgroundTintList(ColorStateList.valueOf(
                ContextCompat.getColor(ingredientLayout.getContext(),
                        ingredient.include ? R.color.fresh_green : R.color.bell_pepper))
        );
        allergy.setPadding(40, 0,40,0);
        allergy.setTextSize(17);
        allergy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ingredientLayout.removeView(allergy);
            }
        });
        ingredientLayout.addView(allergy);
    }

}