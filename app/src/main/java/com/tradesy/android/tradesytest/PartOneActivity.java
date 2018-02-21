package com.tradesy.android.tradesytest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import model.ProductType;
import utils.FileUtils;

public class PartOneActivity extends AppCompatActivity {

    @BindView(R.id.product_type_recycler_view)
    RecyclerView productTypeListView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.part_one);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onStart() {
        super.onStart();
        ProductType[] productTypes = FileUtils.getProductTypesArray(getAssets());
        List<ProductType> productTypeList = new ArrayList<>(Arrays.asList(productTypes));

        PartOneAdapter partOneAdapter = new PartOneAdapter(productTypeList);
        productTypeListView.setAdapter(partOneAdapter);
        productTypeListView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
