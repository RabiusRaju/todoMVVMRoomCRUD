package com.example.todomvvm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todomvvm.R;
import com.example.todomvvm.db.entity.Category;

import java.util.List;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.MyViewHolder> {

    private final Context context;
    private List<Category> categoryList;
    private final HandleCategoryClick clickListener;

    public CategoryListAdapter(Context context, HandleCategoryClick clickListener) {
        this.context = context;
        this.clickListener = clickListener;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryListAdapter.MyViewHolder holder, int position) {

        holder.tvCategoryName.setText(this.categoryList.get(position).categoryName);

        holder.itemView.setOnClickListener(v -> clickListener.itemClick(categoryList.get(position)));

        holder.editCategory.setOnClickListener(v -> clickListener.editItem(categoryList.get(position)));

        holder.removeCategory.setOnClickListener(v -> clickListener.removeItem(categoryList.get(position)));
    }

    @Override
    public int getItemCount() {
        if (categoryList == null || categoryList.size() == 0) {
            return 0;
        } else {
            return categoryList.size();
        }

    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        final TextView tvCategoryName;
        final ImageView removeCategory;
        final ImageView editCategory;

        public MyViewHolder(View view) {
            super(view);
            tvCategoryName = view.findViewById(R.id.tvCategoryName);
            removeCategory = view.findViewById(R.id.removeCategory);
            editCategory = view.findViewById(R.id.editCategory);
        }
    }

    public interface HandleCategoryClick {
        void itemClick(Category category);

        void removeItem(Category category);

        void editItem(Category category);
    }
}