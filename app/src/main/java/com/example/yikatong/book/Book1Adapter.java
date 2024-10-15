package com.example.yikatong.book;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yikatong.R;

import java.util.List;

public class Book1Adapter extends RecyclerView.Adapter<Book1Adapter.ContactViewHolder>{
    private List<Book1> mList;
    public Book1Adapter(List<Book1> mlist){
        this.mList = mlist;
    }

    @NonNull
    @Override
    public Book1Adapter.ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_item1, parent, false);
        return new Book1Adapter.ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Book1Adapter.ContactViewHolder holder, int position) {
        holder.name.setText(mList.get(position).getName());
        holder.author.setText(mList.get(position).getAuthor());
        holder.borrow_time.setText(mList.get(position).getBorrow_time());
        holder.due_time.setText(mList.get(position).getDue_time());
        holder.day.setText(mList.get(position).getDay());
    }
    @Override
    public int getItemCount() {
        return mList.size();
    }
    static class ContactViewHolder extends RecyclerView.ViewHolder{
        TextView name,author,borrow_time,due_time,day;
        public ContactViewHolder(View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.tv_name1);
            author = itemView.findViewById(R.id.tv_author1);
            borrow_time = itemView.findViewById(R.id.tv_borrow_time1);
            due_time = itemView.findViewById(R.id.tv_due_time1);
            day = itemView.findViewById(R.id.tv_day);
        }
    }
}
