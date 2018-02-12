package com.couponapp.home.deals;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.couponapp.home.category.AllDealsOfSelectedCategory;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import example.couponapp.com.couponapp.R;


public class AllDealsFragment extends Fragment implements DealContract.View {

    public static final String TAG = AllDealsFragment.class.getSimpleName();
    @BindView(R.id.categories_list)
    RecyclerView recyclerView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    Unbinder unbinder;
    @BindView(R.id.progress_text)
    TextView progressText;
    private DealPresenter dealPresenter;
    private AllDealFragmentAdapter allSiteFragmentCardViewAdapter;
    private String categoryName;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null &&
                getArguments().getString(AllDealsOfSelectedCategory.EXTRA_CATEGORY_NAME) != null) {
            categoryName = getArguments().getString(AllDealsOfSelectedCategory.EXTRA_CATEGORY_NAME);

        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.category_list_layout, container, false);
        recyclerView = root.findViewById(R.id.categories_list);
        dealPresenter = new DealPresenter(FirebaseDatabase.getInstance(), this);
        unbinder = ButterKnife.bind(this, root);
        return root;
    }

    public static AllDealsFragment newInstance() {
        return new AllDealsFragment();
    }

    @Override
    public void onStart() {
        super.onStart();
        setCardViewAdapter();
        if (categoryName == null) {
            dealPresenter.fetchAllDeals();
        } else {
            dealPresenter.fetchAllDealsByCategory(categoryName);
        }

    }

    private void setCardViewAdapter() {
        final LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        allSiteFragmentCardViewAdapter = new AllDealFragmentAdapter(new ArrayList<DealPojo>());
        recyclerView.setAdapter(allSiteFragmentCardViewAdapter);
    }

    @Override
    public void showAllDeals(List<DealPojo> dealPojoList) {
        Log.e("test", "************inside showAllDeals method == " + dealPojoList.size());
        progressBar.setVisibility(View.GONE);
        progressText.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        allSiteFragmentCardViewAdapter.updateList(dealPojoList);
    }

    @Override
    public void setPresenter(DealContract.Presenter presenter) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
