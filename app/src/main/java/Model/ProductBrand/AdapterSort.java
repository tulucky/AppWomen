package Model.ProductBrand;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.ArrayList;
import java.util.List;

import Model.Service;
import lucky.dev.tu.devandroid.R;
import lucky.dev.tu.devandroid.Sale;

public class AdapterSort extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    View view;
    Context mcontext;
    List<MenuList> menuListItem;
    RecyclerView recyclerV;
    KProgressHUD kProgressHUD;
    ImageView iconMenu;
    Service service;
    Colorada colorada;
    AdapterCate adapterCate;

    public AdapterSort(Context mcontext, List<MenuList> menuList, RecyclerView recyclerView, ImageView imageView, Service service, Colorada colorada, AdapterCate adapterCate) {
        this.mcontext = mcontext;
        menuListItem = new ArrayList<>();
        this.menuListItem = menuList;
        this.recyclerV = recyclerView;
        this.iconMenu = imageView;
        this.service = service;
        this.adapterCate = adapterCate;
        this.colorada = colorada;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater myinflate = LayoutInflater.from(mcontext);
        view = myinflate.inflate(R.layout.item_menu_list, viewGroup, false);
        return new MyHolderMenu(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        final MyHolderMenu holder = (MyHolderMenu) viewHolder;
        holder.textView.setText(menuListItem.get(i).getText());
        holder.textView.setTextColor(menuListItem.get(i).getColor());
        holder.imageIcon.setImageResource(menuListItem.get(i).getImageicon());
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int k = 0; k < menuListItem.size(); k++) {
                    if (k == i) {
                        if (i == 0) {
                            StateHolder.setSapxep("desc");
                        } else if (i == 1) {
                            StateHolder.setSapxep("asc");
                        } else if (i == 2) {
                            StateHolder.reset();
                            if (colorada != null && adapterCate != null) {
                                adapterCate.reset();
                                colorada.reset();
                                adapterCate.notifyDataSetChanged();
                                colorada.notifyDataSetChanged();
                            } else if (colorada == null && adapterCate != null) {
                                adapterCate.reset();
                                adapterCate.notifyDataSetChanged();
                            } else if (colorada != null && adapterCate == null) {
                                colorada.reset();
                                colorada.notifyDataSetChanged();
                            }
                        }
                        menuListItem.get(k).setColor(Color.RED);
                        menuListItem.get(k).setImageicon(R.drawable.ic_check_black_24dp);
                    } else {
                        menuListItem.get(k).setColor(Color.BLACK);
                        menuListItem.get(k).setImageicon(0);
                    }

                }
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
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            service.Request();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            iconMenu.setImageResource(R.drawable.ic_arrow_drop_down_black_24dp);
            menuListItem.get(2).setColor(Color.BLACK);
            menuListItem.get(2).setImageicon(0);
            recyclerV.setVisibility(View.GONE);
            kProgressHUD.dismiss();

        }


    }


    @Override
    public int getItemCount() {
        return menuListItem.size();
    }

    private class MyHolderMenu extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageIcon;

        public MyHolderMenu(View view) {
            super(view);
            textView = view.findViewById(R.id.text_menu_list);
            imageIcon = view.findViewById(R.id.image_menu_list);
        }
    }
}

