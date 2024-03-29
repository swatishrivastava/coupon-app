package com.couponapp.home.category;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import example.couponapp.com.couponapp.R;

public class CategoryFragment extends Fragment
        implements CategoryContract.View, CategoryContract.OnCategoryClickListener {

    @BindView(R.id.categories_list)
    RecyclerView recyclerView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.progress_text)
    TextView progressText;
    Unbinder unbinder;
    private List<Category> categoryPojoList = new ArrayList<>();
    private CategoryAdapter categoryAdapter;
    CategoryPresenter categoryPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.category_list_layout, container, false);
        recyclerView = root.findViewById(R.id.categories_list);
        setRecycleViewLayoutManager();
        categoryAdapter = new CategoryAdapter(categoryPojoList, this);
        recyclerView.setAdapter(categoryAdapter);
        unbinder = ButterKnife.bind(this, root);
        return root;
    }


    @Override
    public void onStart() {
        super.onStart();
        categoryPresenter.fetchAllCategories();
    }

    private void setRecycleViewLayoutManager() {
        final LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        recyclerView.setAdapter(categoryAdapter);
      //  setRecyclerViewAttributes(linearLayoutManager);

    }

    private void setRecyclerViewAttributes(RecyclerView.LayoutManager mLayoutManager) {
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }


    @Override
    public void showAllCategory(List<Category> categoryList) {
        progressBar.setVisibility(View.GONE);
        progressText.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        categoryAdapter.updateList(categoryList);
    }

    @Override
    public void showSelectedCategoryDealsUI(String categoryName) {
        Intent intent = new Intent(getContext(), CategoryForSelectedDeal.class);
        intent.putExtra(CategoryForSelectedDeal.EXTRA_CATEGORY_NAME, categoryName);
        startActivity(intent);
    }

    @Override
    public void failedToGetCategory() {
        progressBar.setVisibility(View.GONE);
        progressText.setVisibility(View.VISIBLE);
        progressText.setText(R.string.failed_to_get_category);
    }

    @Override
    public void setPresenter(CategoryContract.Presenter presenter) {
        this.categoryPresenter = (CategoryPresenter) presenter;
    }

    @Override
    public void onCategoryClick(String categoryName) {
        categoryPresenter.openSelectedCategoryDeals(categoryName);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}



