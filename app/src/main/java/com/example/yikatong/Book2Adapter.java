package com.example.yikatong;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Book2Adapter extends RecyclerView.Adapter<Book2Adapter.ContactViewHolder>{
    private List<Book2> mList;
    public Book2Adapter(List<Book2> mlist){
        this.mList = mlist;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_item2, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        holder.name.setText(mList.get(position).getName());
        holder.author.setText(mList.get(position).getAuthor());
        holder.borrow_time.setText(mList.get(position).getBorrow_time());
        holder.due_time.setText(mList.get(position).getDue_time());
        holder.actual_time.setText(mList.get(position).getActual_time());
    }
    @Override
    public int getItemCount() {
        return mList.size();
    }
    static class ContactViewHolder extends RecyclerView.ViewHolder{
        TextView name,author,borrow_time,due_time,actual_time;
        public ContactViewHolder(View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.tv_name2);
            author = itemView.findViewById(R.id.tv_author2);
            borrow_time = itemView.findViewById(R.id.tv_borrow_time2);
            due_time = itemView.findViewById(R.id.tv_due_time2);
            actual_time = itemView.findViewById(R.id.tv_actual_time2);
        }
    }
}



