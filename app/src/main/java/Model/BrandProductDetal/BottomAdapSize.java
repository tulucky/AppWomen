package Model.BrandProductDetal;

import android.content.Context;
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

    public BottomAdapSize(Context mcontext, List<String> listSize) {
        this.mcontext = mcontext;
        this.listSize = listSize;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.bottomsheet_size, viewGroup, false);
        return new SizeHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        SizeHolder holder = (SizeHolder) viewHolder;
        holder.sizeB.setText(listSize.get(i));

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


