package com.couponapp.home.deals;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.couponapp.application.CouponApplication;

import java.util.ArrayList;
import java.util.List;

import example.couponapp.com.couponapp.R;

public class DealFragmentAdapter<T>
        extends RecyclerView.Adapter<DealFragmentAdapter.DealViewHolder> {

    private List<NewDealInterface> deals;
    private View mView;

    public DealFragmentAdapter(List<NewDealInterface> dealDtoListObj) {
        super();
        this.deals = dealDtoListObj;
    }

    @Override
    public DealViewHolder onCreateViewHolder(ViewGroup parent,
                                             int viewType) {
        mView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.all_deal_layout, parent, false);
        DealViewHolder dealViewHolder = new DealViewHolder(mView);
        return dealViewHolder;
    }

    @Override
    public void onBindViewHolder(DealViewHolder holder,
                                 int position) {
        holder.textView_company_name.setText(deals.get(position)
                                                     .getCompanyName().toString());
        holder.textView_discount.setText(deals.get(position)
                                                 .getDescription().toString());
        holder.textView_category.setText(deals.get(position).getCategory().toString());
        loadImageForLocation(position, holder.deal_company_logo);

    }

    private void loadImageForLocation(int position,
                                      ImageView imageView) {
        Glide.with(CouponApplication.getAppContext())
                .load(deals.get(position)
                              .getCompanyUrl())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(imageView);
    }

    @Override
    public int getItemCount() {
        return deals.size();
    }

    static class DealViewHolder extends RecyclerView.ViewHolder {

        public final TextView textView_company_name;
        public final TextView textView_discount;
        public final ImageView deal_company_logo;
        public final TextView textView_category;

        public DealViewHolder(View itemView) {
            super(itemView);
            textView_company_name =  itemView.findViewById(R.id.tv_company_name);
            textView_discount =  itemView.findViewById(R.id.textView_dicount);
            deal_company_logo=itemView.findViewById(R.id.deal_company_logo);
            textView_category=itemView.findViewById(R.id.textView_category);
        }
    }

    public void updateList(List<NewDealInterface> dealsList) {
        deals.clear();
        deals.addAll(dealsList);
        notifyDataSetChanged();
    }


}
