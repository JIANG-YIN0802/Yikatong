package com.example.yikatong;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class XiaofeijiluAdapter extends RecyclerView.Adapter<XiaofeijiluAdapter.ContactViewHolder>{
    private List<Xiaofei> mList;

    public XiaofeijiluAdapter(List<Xiaofei> mlist) {
        this.mList = mlist;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.xiaofeijilu_item, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        holder.shanghu.setText(mList.get(position).getShanghu());
        holder.time.setText(mList.get(position).getTime());
        holder.money.setText(mList.get(position).getMoney());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class ContactViewHolder extends RecyclerView.ViewHolder{
        TextView shanghu,time,money;
        public ContactViewHolder(View itemView){
            super(itemView);
            shanghu = itemView.findViewById(R.id.tv_shanghu);
            time = itemView.findViewById(R.id.tv_time);
            money = itemView.findViewById(R.id.tv_money);
        }
    }
}
