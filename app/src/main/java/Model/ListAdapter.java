package Model;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
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

import lucky.dev.tu.devandroid.ProductDetail;
import lucky.dev.tu.devandroid.R;

public class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public List<Product> listProduct;
    public Context mcontext;

    public ListAdapter(Context context, List<Product> products) {
        listProduct = new ArrayList<>();
        this.listProduct = products;
        this.mcontext = context;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        LayoutInflater myInflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = myInflater.inflate(R.layout.lea_item_product, viewGroup, false);
        return new MyHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        Log.i("a",""+listProduct.get(position).getImage());
        final MyHolder mholder = (MyHolder) holder;
        Glide.with(mcontext)
                .load(RetrofitO.url + listProduct.get(position).getImage())
                .into(mholder.mImage);
        mholder.titleName.setText(listProduct.get(position).getTitle());
        mholder.mPrice.setText(listProduct.get(position).getNameB() + "-" + listProduct.get(position).getPrice() + "$");
        mholder.mGiaGoc.setText(listProduct.get(position).getNameB() + "" + listProduct.get(position).getOriginprice() + "$");
        mholder.phanTram.setText(listProduct.get(position).getSale());
        mholder.mGiaGoc.setPaintFlags( mholder.mGiaGoc.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        mholder.mImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mcontext, ProductDetail.class);
                intent.putExtra("id", listProduct.get(position).getId());
                intent.putExtra("image", listProduct.get(position).getImage());
                Log.i("d", " " + listProduct.get(position).getId());
                mcontext.startActivity(intent);
            }
        });
        final State state = new State();
        state.on = 1;
        mholder.loved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (state.on == 1) {
                    mholder.loved.setImageResource(R.drawable.ic_favorite_pink_24dp);
                    state.on = 0;
                    State.addP(listProduct.get(position));
                } else if (state.on == 0) {
                    mholder.loved.setImageResource(R.drawable.ic_favorite_border_gray_24dp);
                    state.on = 1;
                    State.removeP(listProduct.get(position));
                }
            }
        });
        if (State.ids != null) {
            Log.i("ou", "lololo ");
            if (State.ids.contains(listProduct.get(position))) {
                mholder.loved.setImageResource(R.drawable.ic_favorite_pink_24dp);
                state.on = 0;
            } else {
                mholder.loved.setImageResource(R.drawable.ic_favorite_border_gray_24dp);

            }

        }
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        ImageView mImage;
        TextView mPrice;
        TextView mGiaGoc;
        TextView phanTram;
        TextView titleName;
        ImageView loved;

        public MyHolder(View itemView) {
            super(itemView);
            mImage = itemView.findViewById(R.id.image);
            mPrice = itemView.findViewById(R.id.size);
            mGiaGoc = itemView.findViewById(R.id.price_1);
            phanTram = itemView.findViewById(R.id.phantram);
            titleName = itemView.findViewById(R.id.title_name);
            loved = itemView.findViewById(R.id.loved);
        }

    }
}

