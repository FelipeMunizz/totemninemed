package com.femuniz.totenninemed.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GenericRecyclerViewAdapter<T> extends RecyclerView.Adapter<GenericRecyclerViewAdapter.ViewHolder> {

    private final List<T> items;
    private final int layoutId;
    private final Binder<T> binder;

    public interface Binder<T> {
        void bind(ViewHolder holder, T item);
    }

    public GenericRecyclerViewAdapter(List<T> items, int layoutId, Binder<T> binder) {
        this.items = items;
        this.layoutId = layoutId;
        this.binder = binder;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        T item = items.get(position);
        binder.bind(holder, item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public <V extends View> V findViewById(int id) {
            return itemView.findViewById(id);
        }
    }
}
