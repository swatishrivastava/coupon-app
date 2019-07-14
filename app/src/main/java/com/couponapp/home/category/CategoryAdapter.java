package com.couponapp.home.category;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import example.couponapp.com.couponapp.R;


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>
        implements View.OnClickListener {

    private List<Category> dataSource;
    CategoryContract.OnCategoryClickListener categoryClickListener;

    @Override
    public void onClick(View v) {
        categoryClickListener.onCategoryClick(v.getTag()
                                                      .toString());
    }

    public CategoryAdapter(List<Category> dataArgs,
                           CategoryContract.OnCategoryClickListener categoryClickListenerObj) {
        dataSource = dataArgs;
        this.categoryClickListener = categoryClickListenerObj;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent,
                                                 int viewType) {
        View mView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_layout, parent, false);
        return new CategoryViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder,
                                 int position) {
        holder.textView.setText(dataSource.get(position).getName());
        holder.textView.setTag(dataSource.get(position));
        holder.textView.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {
        public final TextView textView;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView_category);
        }
    }

    public void updateList(List<Category> categoryList) {
        dataSource.clear();
        dataSource.addAll(categoryList);
        notifyDataSetChanged();
    }

}
