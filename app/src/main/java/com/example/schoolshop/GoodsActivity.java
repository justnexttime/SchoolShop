package com.example.schoolshop;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.schoolshop.Base.BaseActivity;
import com.example.schoolshop.entity.Goods;
import com.example.schoolshop.entity.User;

import butterknife.BindView;

public class GoodsActivity extends BaseActivity {
    @BindView(R.id.good_goods_name)
    public TextView goodsname;
    private Goods goods;
    private String UserName;

    @Override
    protected void initView() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        goods = (Goods) bundle.getSerializable("goods");
        UserName = bundle.getString("User");
        Log.d("goodsname", "initView-----> " + goods.getGoodsName());
        Log.d("Username","username---->"+ UserName);
        goodsname.setText(goods.getGoodsName());
    }

    @Override
    protected int getlayoutId() {
        return R.layout.acitvity_goods;
    }
}
