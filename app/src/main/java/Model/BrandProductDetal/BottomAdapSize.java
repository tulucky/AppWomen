package Model.BrandProductDetal;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import lucky.dev.tu.devandroid.R;

public class BottomAdapSize extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context mcontext;
    List<String> listSize;
    OrderP orderP;
    TextView sizeDes;
    int slected = -1;

    public BottomAdapSize(Context mcontext, List<String> listSize, OrderP order, TextView textView) {
        this.mcontext = mcontext;
        this.listSize = listSize;
        this.orderP = order;
        sizeDes = textView;
    }

    public BottomAdapSize(Context mcontext, List<String> listSize, OrderP order, TextView textView, int i) {
        this.mcontext = mcontext;
        this.listSize = listSize;
        this.orderP = order;
        sizeDes = textView;
        this.slected = i;
    }

    //coutructo2 to get a size from db for adapterbag for update to show
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.item_bottomsheet_size, viewGroup, false);
        return new SizeHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        final SizeHolder holder = (SizeHolder) viewHolder;
        if (slected == i) {
            holder.sizeB.setText(listSize.get(i));
            holder.sizeB.setTextColor(Color.WHITE);
            holder.sizeB.setBackgroundResource(R.drawable.background_color);
        } else {
            holder.sizeB.setText(listSize.get(i));
            holder.sizeB.setTextColor(Color.BLACK);
            holder.sizeB.setBackgroundResource(R.drawable.border);
        }
        holder.sizeB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sizeDes.setVisibility(View.VISIBLE);
                slected = i;
                orderP.setSizebag(listSize.get(i));
                //set size for object to update;
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return listSize.size();
    }

    private class SizeHolder extends RecyclerView.ViewHolder {
        TextView sizeB;

        public SizeHolder(View view) {
            super(view);
            sizeB = view.findViewById(R.id.size_b);
        }
    }
}


