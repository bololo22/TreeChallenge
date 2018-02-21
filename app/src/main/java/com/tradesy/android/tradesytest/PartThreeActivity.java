package com.tradesy.android.tradesytest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import model.Tree;
import utils.FileUtils;
import views.TreeView;

public class PartThreeActivity extends AppCompatActivity {

    @BindView(R.id.tree_view)
    View treeView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.part_three);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onStart() {
        super.onStart();

        TreeView treeView = (TreeView) this.treeView;
        Tree tree = Tree.from(FileUtils.getRelationships(getAssets()));

        treeView.setTree(tree);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
