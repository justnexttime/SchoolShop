package com.example.schoolshop.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.schoolshop.R;
import com.example.schoolshop.entity.Goods;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class HomePagerContentAdapter extends RecyclerView.Adapter<HomePagerContentAdapter.InnerHolder> {
    private List<Goods> data = new ArrayList<>();

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_pager_content,parent,false);

        return new InnerHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        //设置数据
        Goods goods = data.get(position);
        holder.setDate(goods);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setDate(List<Goods> content) {
        data.clear();
        data.addAll(content);
        notifyDataSetChanged();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.goods_img)
        public ImageView img;
        @BindView(R.id.goods_name)
        public TextView goodname;
        @BindView(R.id.goods_provider)
        public TextView goodsprovider;
        @BindView(R.id.goods_price)
        public TextView goodsprice;
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void setDate(Goods goods) {
            goodname.setText(goods.getGoodsName());
            Log.d("price", "setDate: "+goods.getGoodsPrice());
            goodsprice.setText(String.valueOf(goods.getGoodsPrice()));
            goodsprovider.setText(goods.getGoodsUser());
        }
    }
}
