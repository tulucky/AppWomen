package Model.ProductBrand;

import android.annotation.SuppressLint;
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

public class Colorada extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context mcontext;
    List<FilterText> colors;
    int slected = -1;

    public Colorada(Context mcontext, List<FilterText> colors) {
        this.mcontext = mcontext;
        this.colors = colors;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.item_text_filter, viewGroup, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        final ViewHolder holder = (ViewHolder) viewHolder;
        if (slected == i) {
            holder.color.setText(colors.get(i).getContent());
            holder.color.setTextColor(Color.parseColor("#FF4081"));
            holder.color.setBackgroundResource(R.drawable.red_border);
        } else {
            holder.color.setText(colors.get(i).getContent());
            holder.color.setTextColor(Color.parseColor("#0e0e0e"));
            holder.color.setBackgroundResource(R.drawable.border);
        }
        holder.color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slected = i;
                //set size for object to update;
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return colors.size();
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        TextView color;

        public ViewHolder(View view) {
            super(view);
            color = view.findViewById(R.id.text_filter);
        }
    }
}

