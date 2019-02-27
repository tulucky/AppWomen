package Controllner;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import Model.Itemthree;
import lucky.dev.tu.devandroid.R;

public class AdapterZezo extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public List<Itemthree> mitem;
    public Context mcontext;

    public AdapterZezo(List<Itemthree> mitems, Context mcontext) {
        Log.i("a", "" + mitem);
        mitem = new ArrayList<>();
        this.mitem = mitems;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        LayoutInflater myInflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = myInflater.inflate(R.layout.recy_main0, viewGroup, false);
        return new MyHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Log.i("a",""+mitem.get(position).getImage());
        MyHolder mholder = (MyHolder) holder;
        Glide.with(mcontext)
                .load(mitem.get(position).getImage())
                .into(mholder.mImage);
        mholder.mPrice.setText(mitem.get(position).getPrice());
        mholder.phanTram.setText(mitem.get(position).getPhanTram());

    }

    @Override
    public int getItemCount() {
        return mitem.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        ImageView mImage;
        TextView mPrice;
        TextView phanTram;

        public MyHolder(View itemView) {
            super(itemView);
            mImage = itemView.findViewById(R.id.image);
            mPrice = itemView.findViewById(R.id.price);
            phanTram = itemView.findViewById(R.id.phantram);
        }

    }
}
