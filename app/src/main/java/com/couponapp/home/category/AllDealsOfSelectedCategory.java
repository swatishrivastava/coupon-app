package com.couponapp.home.category;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.couponapp.home.deals.AllDealsFragment;
import com.couponapp.home.deals.DealContract;
import com.couponapp.home.deals.DealPresenter;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import example.couponapp.com.couponapp.R;


public class AllDealsOfSelectedCategory extends AppCompatActivity {

    public static final String EXTRA_CATEGORY_NAME = "CATEGORY_NAME";
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alldeals_selected_category_layout);
        ButterKnife.bind(this);
        String categoryName = getIntent().getStringExtra(EXTRA_CATEGORY_NAME);
        tvTitle.setText(categoryName);
        AllDealsFragment allDealsFragment=getAllDealsFragment(categoryName);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_activity_content_frame, allDealsFragment)
                .addToBackStack(AllDealsFragment.TAG)
                .commit();
        new DealPresenter(FirebaseDatabase.getInstance(),  allDealsFragment);
    }

    private AllDealsFragment getAllDealsFragment(String categoryName) {
        final AllDealsFragment allDealsFragment = new AllDealsFragment();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_CATEGORY_NAME, categoryName);
        allDealsFragment.setArguments(bundle);
        return allDealsFragment;

    }


    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStackImmediate();
        } else {
            super.onBackPressed();
        }
        finish();
    }
}
