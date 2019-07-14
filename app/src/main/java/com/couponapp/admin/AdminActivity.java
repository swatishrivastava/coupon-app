package com.couponapp.admin;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.couponapp.home.deals.DealDto;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import example.couponapp.com.couponapp.R;


public class AdminActivity extends AppCompatActivity implements AdminContract.View {


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
    @BindView(R.id.button_expiry_date)
    Button button_expiry_date;
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
        DealDto dealDto = new DealDto();
        dealDto.setExpiry_date(button_expiry_date.getText()
                .toString());
        dealDto.setDescription(editTextDiscountDesc.getText()
                .toString());
        dealDto.setCompanyName(editTextCompanyName.getText()
                .toString());
        dealDto.setCategory(spinner.getSelectedItem()
                .toString());
        dealDto.setCompanyUrl("https://i-cdn.phonearena.com/images/article/51374-image/Deal-tracker-450-LG-G2-440-iPad-mini-4G-free-Disney-games-more-deals-on-phones-tablets-and-apps.jpg");
        dealDto.setLocation(editTextLocation.getText()
                .toString());
        presenter.saveDeal(dealDto);

    }


    @Override
    public void showDealSavedOnUi() {
        Toast.makeText(this, getString(R.string.deal_saved), Toast.LENGTH_SHORT)
                .show();
        clearViews();
    }

    @Override
    public void setPresenter(AdminContract.AdminPresenter presenterObj) {
        this.presenter = presenterObj;
    }

    @OnClick(R.id.button_expiry_date)
    public void showDatePickerDialog() {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
    private void clearViews() {
        editTextCompanyName.getText().clear();
        editTextDiscountDesc.getText().clear();
        editTextLocation.getText().clear();
        button_expiry_date.setText(R.string.choose_expiry_date);
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            Button button=getActivity().findViewById(R.id.button_expiry_date);
            button.setText(""+view.getDayOfMonth() + " - "+view.getMonth() +" - "+ view.getYear());
        }
    }
}
