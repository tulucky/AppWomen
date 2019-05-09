package Model;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import lucky.dev.tu.devandroid.R;

public class ListProduct extends Fragment {
    RecyclerView listProduct;
    List<Product> listData;
    private static final String urlData3 = "https://shoplady668.000webhostapp.com//allproducts.php";
    /* private static final String urlData3 = "http://192.168.1.108/wmshop/allproducts.php";*/

    public ListProduct() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i("hg", "" + container);
        View view = inflater.inflate(R.layout.listproduct, container, false);
        listProduct = view.findViewById(R.id.list_product);
        listProduct.setNestedScrollingEnabled(false);
        listProduct.setHasFixedSize(true);
        getList();
        return view;

    }

    private void getList() {
        listData = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        listProduct.setLayoutManager(linearLayoutManager);
        StringRequest obj = new StringRequest(Request.Method.GET, urlData3,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Log.i("l", "" + response);
                        JSONArray aray = null;
                        try {
                            aray = new JSONArray(response);

                            for (int i = 0; i < aray.length(); i++) {
                                // chu y du lieu tra ve tu url len de la acsoc thi ta moi getdc jsonobject
                                JSONObject a = aray.getJSONObject(i);
                                listData.add(new Product(a.getInt("id"), a.getString("title"),
                                        a.getString("image"),
                                        a.getString("name"),
                                        a.getString("originprice"),
                                        a.getString("sale"))
                                );
                            }
                            ListAdapter madapter = new ListAdapter(getActivity(), listData);
                            listProduct.setAdapter(madapter);
                        } catch (JSONException e1) {
                            Log.i("j", "khong");
                        }
                    }
                }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        MySingleton.getInstance(getActivity()).addToRequestQueue(obj);

    }


}
