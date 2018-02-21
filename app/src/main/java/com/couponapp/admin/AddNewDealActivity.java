package com.couponapp.admin;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.couponapp.home.deals.DealPojo;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import example.couponapp.com.couponapp.R;


public class AddNewDealActivity extends AppCompatActivity implements AdminContract.View {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.editText_company_name)
    EditText editTextCompanyName;
    @BindView(R.id.editText_discount_desc)
    EditText editTextDiscountDesc;
    @BindView(R.id.editText_location)
    EditText editTextLocation;
    @BindView(R.id.editText_expiry_date)
    EditText editText4;
    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.save_deal_btn)
    Button button2;

    private AdminContract.AdminPresenter presenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_deal_layout);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
        tvTitle.setText(getString(R.string.add_deal_btn_label));
        presenter = new AdminPresenter(this, FirebaseDatabase.getInstance());
        fillCategorySpinner();

    }


    private void fillCategorySpinner() {
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this, R.array.category_array,
                                                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }

    @OnClick(R.id.save_deal_btn)
    public void saveDeals() {
        DealPojo dealPojo = new DealPojo();
        dealPojo.setExpiry_date(editText4.getText()
                                        .toString());
        dealPojo.setDescription(editTextDiscountDesc.getText()
                                        .toString());
        dealPojo.setCompanyName(editTextCompanyName.getText()
                                        .toString());
        dealPojo.setCategory(spinner.getSelectedItem()
                                     .toString());
        dealPojo.setCompanyUrl("https://i-cdn.phonearena.com/images/article/51374-image/Deal-tracker-450-LG-G2-440-iPad-mini-4G-free-Disney-games-more-deals-on-phones-tablets-and-apps.jpg");
        dealPojo.setLocation(editTextLocation.getText()
                                     .toString());
        presenter.saveDeal(dealPojo);

    }


    @Override
    public void showDealSavedOnUi() {
        Toast.makeText(this, getString(R.string.deal_saved), Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    public void setPresenter(AdminContract.AdminPresenter presenterObj) {
        this.presenter = presenterObj;
    }
}
