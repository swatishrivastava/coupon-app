package com.couponapp.home;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.couponapp.admin.AdminActivity;
import com.couponapp.home.category.CategoryClient;
import com.couponapp.home.category.CategoryContract;
import com.couponapp.home.category.CategoryFragment;
import com.couponapp.home.category.CategoryPresenter;
import com.couponapp.home.category.GetCategoriesUseCase;
import com.couponapp.home.deals.DealClient;
import com.couponapp.home.deals.DealContract;
import com.couponapp.home.deals.DealPresenter;
import com.couponapp.home.deals.DealsFragment;
import com.couponapp.home.deals.GetDealsUseCase;
import com.couponapp.login.LoginActivity;
import com.couponapp.login.UserInfo;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import example.couponapp.com.couponapp.R;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String USER_INFO = "USER_INFO";
    public static final String ADMIN = "ADMIN";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deals_home_layout);
        UserInfo userInfo = getIntent().getParcelableExtra(USER_INFO);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        tabLayout = findViewById(R.id.tab_layout);
        viewpager = findViewById(R.id.viewpager);
        createTabs();
        setViewPagerToTabs();
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle =
                new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open,
                        R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        showAdminFeature(userInfo, navigationView.getMenu());
    }


    private void showAdminFeature(UserInfo userInfo,
                                  Menu nav_Menu) {
        if (userInfo.getRole() != null && userInfo.getRole()
                .equalsIgnoreCase(ADMIN)) {
            nav_Menu.findItem(R.id.add_deal)
                    .setVisible(true);
        } else {
            nav_Menu.findItem(R.id.add_deal)
                    .setVisible(false);
        }
    }


    private void createTabs() {
        addTabsToTabLayout();
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabTextColors(Color.LTGRAY, Color.WHITE);
        tabLayout.setSelectedTabIndicatorColor(
                ContextCompat.getColor(this, R.color.white));
    }


    private void addTabsToTabLayout() {
        tabLayout.addTab(tabLayout.newTab()
                .setText(getString(R.string.best_offer_tab_label)));
        tabLayout.addTab(tabLayout.newTab()
                .setText(getString(R.string.category_tab_label)));
    }


    private void setViewPagerToTabs() {
        HomeTabsPagerAdapter homeTabsPagerAdapter =
                new HomeTabsPagerAdapter(getSupportFragmentManager());
        setFragmentsInAdapter(homeTabsPagerAdapter);
        viewpager.setAdapter(homeTabsPagerAdapter);
        viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setFragmentsInAdapter(HomeTabsPagerAdapter homeTabsPagerAdapter) {

        Fragment categoryFragment = getCategoryTabFragment();
        Fragment dealsFragment = getAllDealsTabFragment();
        homeTabsPagerAdapter.addFragments(dealsFragment);
        homeTabsPagerAdapter.addFragments(categoryFragment);

        Retrofit retrofit = getRetrofit();
        new DealPresenter((DealContract.View) dealsFragment,
                new GetDealsUseCase(new DealClient(retrofit)));
        new CategoryPresenter(new GetCategoriesUseCase(new CategoryClient(retrofit)),
                (CategoryContract.View) categoryFragment);
    }

    @NonNull
    private Retrofit getRetrofit() {
        return new Retrofit.Builder()
                    .baseUrl("http://192.168.43.15:8085/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

        android.support.v4.app.Fragment f =
                getSupportFragmentManager().findFragmentById(R.id.tab_layout);

        FragmentManager fm = getSupportFragmentManager();
        int count = fm.getBackStackEntryCount();
        if (count == 1) {
            finish();
        }

        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_logout) {
            FirebaseAuth.getInstance()
                    .signOut();
            startActivity(new Intent(HomeActivity.this, LoginActivity.class));
        }

        if (id == R.id.add_deal) {
            startActivity(new Intent(HomeActivity.this, AdminActivity.class));
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private DealsFragment getAllDealsTabFragment() {
        DealsFragment dealsFragment = DealsFragment.newInstance();
        return dealsFragment;
    }

    private CategoryFragment getCategoryTabFragment() {
        CategoryFragment categoryFragment = new CategoryFragment();
        return categoryFragment;
    }
}
