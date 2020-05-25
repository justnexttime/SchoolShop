package com.example.schoolshop.fragment;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.schoolshop.Base.BaseFragment;
import com.example.schoolshop.GoodsActivity;
import com.example.schoolshop.LoginActivity;
import com.example.schoolshop.MeunActivity;
import com.example.schoolshop.R;
import com.example.schoolshop.UserBackActivity;
import com.example.schoolshop.adapter.HomePagerContentAdapter;
import com.example.schoolshop.controller.IGoodsPagerPresenter;
import com.example.schoolshop.controller.impl.GoodsPagerPresenterImpl;
import com.example.schoolshop.entity.Goods;
import com.example.schoolshop.entity.User;
import com.example.schoolshop.view.IGoodsPagerCallback;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

import static com.example.schoolshop.utils.Constants.KEY_HOME_PAGER_TITLE;

public class HomePagerFragment extends BaseFragment implements IGoodsPagerCallback, HomePagerContentAdapter.OnListeItemClickListener {

    private IGoodsPagerPresenter mgoodsPagerPresenter;
    private String title;

    @BindView(R.id.home_pager_title)
    public TextView goodstitle;
    @BindView(R.id.home_pager_list)
    public RecyclerView mContentList;
    private HomePagerContentAdapter homePagerContentAdapter;
    private String UserName;

    public static HomePagerFragment newInstance(String title,String username) {
        HomePagerFragment homePagerFragment = new HomePagerFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_HOME_PAGER_TITLE,title);
        bundle.putString("User",username);
        homePagerFragment.setArguments(bundle);
        return homePagerFragment;
    }

    @Override
    protected int getRootViewId() {
        return R.layout.fragment_home_pager;
    }

    @Override
    protected void initView(View rootview) {
        //设置布局管理器
        mContentList.setLayoutManager(new LinearLayoutManager(getContext()));
        mContentList.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                outRect.top = 5;
                outRect.bottom = 5;
            }
        });
        //创建适配器
        homePagerContentAdapter = new HomePagerContentAdapter();
        //
        mContentList.setAdapter(homePagerContentAdapter);
    }

    @Override
    protected void initListener() {
        homePagerContentAdapter.setOnListeItemClickListener(this);
    }

    @Override
    protected void initPresenter() {
        mgoodsPagerPresenter = GoodsPagerPresenterImpl.getsInstance();
        mgoodsPagerPresenter.registerViewCallback(this);
    }

    @Override
    protected void loadDate() {
        Bundle arguments = getArguments();
        title = arguments.getString(KEY_HOME_PAGER_TITLE);
        Log.d("title","title---->"+ title);
        //加载title查到的数据
        UserName = arguments.getString("User");
        if (mgoodsPagerPresenter!=null) {
            goodstitle.setText(title);
            mgoodsPagerPresenter.getGoodsByTitle(title);
        }
    }

    @Override
    public void onContentload(List<Goods> content) {
        //数据加载
        //TODO:更新ui
        homePagerContentAdapter.setDate(content);
        setUpState(State.SUCCESS);
    }

    @Override
    public String titleget() {
        return title;
    }

    @Override
    public void onLoading() {
        setUpState(State.LOADING);
    }

    @Override
    public void onNetworkError() {
        setUpState(State.ERROR);
    }

    @Override
    public void onEmpty() {
        setUpState(State.EMPTY);
    }

    @Override
    public void onLoadMoreLoaded(List<Goods> content) {

    }


    @Override
    public void onLoadMoreError() {

    }

    @Override
    public void onLoadMoreEmpty() {

    }

    @Override
    protected void release() {
        if (mgoodsPagerPresenter!=null) {
            mgoodsPagerPresenter.unregisterViewCallback(this);
        }
    }

    @Override
    public void Onitemclick(Goods item) {
        //列表内容
        Log.d("item", "goods-->"+item.toString());
        Intent intent = new Intent(getContext(), GoodsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("goods",item);
        bundle.putString("User", UserName);
        intent.putExtras(bundle);
        startActivity(intent);

    }
}
