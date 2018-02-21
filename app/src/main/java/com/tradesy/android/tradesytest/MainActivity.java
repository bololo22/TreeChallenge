package com.tradesy.android.tradesytest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.part_one)
    Button partOneButton;
    @BindView(R.id.part_two)
    Button partTwoButton;
    @BindView(R.id.part_three)
    Button partThreeButton;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
    }

    @OnClick({R.id.part_one, R.id.part_two, R.id.part_three})
    public void onClick(View view){
        Intent intent;
        switch (view.getId()){
            case R.id.part_one:
                intent = new Intent(this, PartOneActivity.class);
                startActivity(intent);
                break;
            case R.id.part_two:
                intent = new Intent(this, PartTwoActivity.class);
                startActivity(intent);
                break;
            case R.id.part_three:
                intent = new Intent(this, PartThreeActivity.class);
                startActivity(intent);
        }
    }
}
