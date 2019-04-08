package Model.Bag;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdapterBag extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ImageView imageBag;
    TextView sizeBag;
    Context mcontext;
    List<String> data;

    public AdapterBag(Context mcontext, List<String> data) {
        this.mcontext = mcontext;
        this.data = data;
    }

    public void setImageBag(ImageView imageBag) {
        this.imageBag = imageBag;
    }

    public void setSizeBag(TextView sizeBag) {
        this.sizeBag = sizeBag;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
