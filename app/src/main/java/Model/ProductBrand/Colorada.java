package Model.ProductBrand;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.List;

import Model.Service;
import lucky.dev.tu.devandroid.R;

public class Colorada extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context mcontext;
    List<FilterText> colors;
    int slected = -1;
    ImageView iconFilter;
    KProgressHUD kProgressHUD;
    ConstraintLayout filter;

    public Colorada(Context mcontext, List<FilterText> colors, ImageView imageView, ConstraintLayout filter) {
        this.mcontext = mcontext;
        this.colors = colors;
        iconFilter = imageView;
        this.filter = filter;
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
        holder.color.setText(colors.get(i).getContent());
        if (slected == i) {
            holder.color.setBackgroundResource(R.drawable.background_color);
            holder.color.setTextColor(Color.WHITE);
            holder.color.setBackgroundResource(R.drawable.background_color);

        } else {
            holder.color.setText(colors.get(i).getContent());
            holder.color.setTextColor(Color.parseColor("#0e0e0e"));
            holder.color.setBackgroundResource(R.drawable.border);
        }
        holder.color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.color.setBackgroundResource(R.drawable.background_color);
                Log.i("ss", "onClick: ");
                if (i == 0) {
                    StateHolder.setMausac("white");
                } else if (i == 1) {
                    StateHolder.setMausac("blue");
                } else if (i == 2) {
                    StateHolder.setMausac("green");
                } else if (i == 3) {
                    StateHolder.setMausac("black");
                } else if (i == 4) {
                    StateHolder.setMausac("brown");
                } else if (i == 5) {
                    StateHolder.setMausac("yallow");
                } else if (i == 6) {
                    StateHolder.setMausac("gray");
                } else if (i == 7) {
                    StateHolder.setMausac("pink");
                }
                filter.setVisibility(View.GONE);
                slected = i;
                //set size for object to update;
                notifyDataSetChanged();
                new Asyn().execute();

            }
        });
    }

    public class Asyn extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            kProgressHUD = KProgressHUD.create(mcontext)
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setCancellable(true)
                    .setAnimationSpeed(2)
                    .setDimAmount(0.5f)
                    .setBackgroundColor(Color.DKGRAY)
                    .show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Service.Request();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            iconFilter.setImageResource(R.drawable.ic_arrow_drop_down_black_24dp);
            kProgressHUD.dismiss();

        }


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

