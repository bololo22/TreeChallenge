package com.tradesy.android.tradesytest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import model.Tree;
import utils.FileUtils;

public class PartTwoActivity extends AppCompatActivity {

    @BindView(R.id.result_text_view)
    TextView resultTextView;
    @BindView(R.id.dft_button)
    Button dftButton;
    @BindView(R.id.bft_button)
    Button bftButton;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    Tree tree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.part_two);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onStart() {
        super.onStart();
        tree = Tree.from(FileUtils.getRelationships(getAssets()));
    }

    @OnClick({R.id.dft_button, R.id.bft_button})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.dft_button:
                resultTextView.setText(tree.dft(tree.getRoot()));
                break;
            case R.id.bft_button:
                resultTextView.setText(tree.bft(tree.getRoot()));
                break;
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
