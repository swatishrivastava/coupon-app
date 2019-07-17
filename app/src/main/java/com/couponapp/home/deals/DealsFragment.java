package com.couponapp.home.deals;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.couponapp.home.category.CategoryForSelectedDeal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import example.couponapp.com.couponapp.R;


public class DealsFragment extends Fragment implements DealContract.View {

    public static final String TAG = DealsFragment.class.getSimpleName();
    @BindView(R.id.categories_list)
    RecyclerView recyclerView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    Unbinder unbinder;
    @BindView(R.id.progress_text)
    TextView progressText;
    private DealPresenter dealPresenter;
    private DealFragmentAdapter allSiteFragmentCardViewAdapter;
    private String categoryName;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null &&
                getArguments().getString(CategoryForSelectedDeal.EXTRA_CATEGORY_NAME) != null) {
            categoryName = getArguments().getString(CategoryForSelectedDeal.EXTRA_CATEGORY_NAME);

        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.category_list_layout, container, false);
        recyclerView = root.findViewById(R.id.categories_list);
        unbinder = ButterKnife.bind(this, root);
        return root;
    }

    public static DealsFragment newInstance() {
        return new DealsFragment();
    }

    @Override
    public void onStart() {
        super.onStart();
        setCardViewAdapter();
        if (categoryName == null) {
            dealPresenter.fetchAllDeals();
        } else {
           // dealPresenter.fetchAllDealsByCategory(categoryName);
        }

    }

    private void setCardViewAdapter() {
        final LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        allSiteFragmentCardViewAdapter = new DealFragmentAdapter(new ArrayList<Deal>());
        recyclerView.setAdapter(allSiteFragmentCardViewAdapter);
    }

    @Override
    public void showAllDeals(List<NewDealInterface> dealDtoList) {
        progressBar.setVisibility(View.GONE);
        progressText.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        allSiteFragmentCardViewAdapter.updateList(dealDtoList);
    }

    @Override
    public void failedToGetDeals() {
        progressBar.setVisibility(View.GONE);
        progressText.setVisibility(View.VISIBLE);
        progressText.setText(R.string.failed_to_get_deals);
    }

    @Override
    public void setPresenter(DealContract.Presenter presenter) {
        this.dealPresenter= (DealPresenter) presenter;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
