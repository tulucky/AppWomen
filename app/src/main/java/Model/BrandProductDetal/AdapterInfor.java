package Model.BrandProductDetal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import lucky.dev.tu.devandroid.R;

public class AdapterInfor extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    View view;
    Context mcontext;
    List<String> listInfor;

    public AdapterInfor(Context mcontext, List<String> listInform) {
        this.mcontext = mcontext;
        listInfor = new ArrayList<>();
        this.listInfor = listInform;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        view = LayoutInflater.from(mcontext).inflate(R.layout.item_info_detail, viewGroup, false);
        return new MyHold(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (i == 0) {
            view.setBackgroundResource(R.drawable.border_top_bot);
        } else {
            view.setBackgroundResource(R.drawable.sticky_product_brand);
        }
        MyHold myHold = (MyHold)viewHolder;
        myHold.textView.setText(listInfor.get(i));
        myHold.imageView.setImageResource(R.drawable.right_chevron);

    }

    @Override
    public int getItemCount() {
        return listInfor.size();
    }

    private class MyHold extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        public MyHold(View view) {
            super(view);
           textView = view.findViewById(R.id.infor);
           imageView = view.findViewById(R.id.image_infor);
        }
    }
}
