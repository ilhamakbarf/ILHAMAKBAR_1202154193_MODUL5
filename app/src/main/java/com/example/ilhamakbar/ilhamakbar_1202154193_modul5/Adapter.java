package com.example.ilhamakbar.ilhamakbar_1202154193_modul5;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.holder> {

    //deklarasi variabel objek
    private Context mcontex;
    private List<AddData> mlist;
    int mcolor;

    //konstruktor
    public Adapter(Context cntx, List<AddData> list, int color){
        this.mcontex=cntx;
        this.mlist=list;
        this.mcolor=color;
    }

    //viewholder untuk recyclerview
    @Override
    public holder onCreateViewHolder(ViewGroup parent, int viewType) {
        //view baru
        View view = LayoutInflater.from(mcontex).inflate(R.layout.cardview, parent, false);
        holder Mhldr = new holder(view);
        return Mhldr;
    }

    //set nilai yang didapatkan pada viewholder
    @Override
    public void onBindViewHolder(holder holder, int position) {
        AddData data = mlist.get(position);
        holder.mToDo.setText(data.getTodo());
        holder.mDesc.setText(data.getDesc());
        holder.mPriority.setText(data.getPrior());
        holder.mCardview.setCardBackgroundColor(mcontex.getResources().getColor(this.mcolor));
    }

    //get jumlah list
    @Override
    public int getItemCount() {
        return mlist.size();
    }

    //get list dari adapter
    public AddData getData(int position){
        return mlist.get(position);
    }

    // menghapus list pada todolist
    public void deleteData(int i){
        //hapus item yang terpilih
        mlist.remove(i);
        //notifikasi
        notifyItemRemoved(i);
        notifyItemRangeChanged(i, mlist.size());
    }

    //kelas untuk recyclerview
    class holder extends RecyclerView.ViewHolder{
        //deklarasi variable objek
        public TextView mToDo, mDesc, mPriority;
        public CardView mCardview;
        public holder(View itemView){
            super(itemView);

            //ambil data dari cardview
            mToDo = itemView.findViewById(R.id.todo);
            mDesc = itemView.findViewById(R.id.description);
            mPriority = itemView.findViewById(R.id.number);
            mCardview = itemView.findViewById(R.id.cardview);
        }
    }
}
