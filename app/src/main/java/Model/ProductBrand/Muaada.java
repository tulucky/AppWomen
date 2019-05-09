//package Model.ProductBrand;
//
//import android.content.Context;
//import android.graphics.Color;
//import android.support.annotation.NonNull;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import java.util.List;
//
//import lucky.dev.tu.devandroid.R;
//
//public class Muaada extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
//    Context mcontext;
//    List<FilterText> seasons;
//    int slected = -1;
//
//    public Muaada(Context mcontext, List<FilterText> mua) {
//        this.mcontext = mcontext;
//        this.seasons = mua;
//    }
//
//    @NonNull
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        View view = LayoutInflater.from(mcontext).inflate(R.layout.item_text_filter, viewGroup, false);
//        return new ViewHolder(view);
//    }
//
//
//    @Override
//    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
//        StateHolder.setType3(1);
//        final ViewHolder holder = (ViewHolder) viewHolder;
//        if (slected == i) {
//            if(i==0){
//                StateHolder.setMua("xuan");
//            }
//            else  if(i==1){
//                StateHolder.setMausac("ha");
//            }
//            else  if(i==2){
//                StateHolder.setMausac("thu");
//            }
//            else  if(i==3){
//                StateHolder.setMausac("dong");
//            }
//            holder.mua.setText(seasons.get(i).getContent());
//            holder.mua.setTextColor(Color.parseColor("#FF4081"));
//            holder.mua.setBackgroundResource(R.drawable.red_border);
//        } else {
//            holder.mua.setText(seasons.get(i).getContent());
//            holder.mua.setTextColor(Color.parseColor("#0e0e0e"));
//            holder.mua.setBackgroundResource(R.drawable.border);
//        }
//        holder.mua.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                slected = i;
//                //set size for object to update;
//                notifyDataSetChanged();
//            }
//        });
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return seasons.size();
//    }
//
//    private class ViewHolder extends RecyclerView.ViewHolder {
//        TextView mua;
//
//        public ViewHolder(View view) {
//            super(view);
//            mua = view.findViewById(R.id.text_filter);
//        }
//    }
//}
