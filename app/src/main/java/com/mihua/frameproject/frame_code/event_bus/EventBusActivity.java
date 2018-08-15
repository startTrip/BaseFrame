package com.mihua.frameproject.frame_code.event_bus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mihua.frameproject.R;
import com.mihua.frameproject.annotation.Subscribe;
import com.mihua.frameproject.annotation.ThreadMode;
import com.mihua.frameproject.design_mode.builder.Person;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EventBusActivity extends AppCompatActivity {

    @BindView(R.id.tv_content)
    TextView mTvContent;
    @BindView(R.id.bt_skip)
    Button mBtSkip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.Main, priority = 1, sticky = true, value = "jaja")
    public void eat(Person person) {
        mTvContent.setText(person.toString());
    }

    @OnClick({R.id.tv_content, R.id.bt_skip})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_content:

                break;
            case R.id.bt_skip:
                Intent intent = new Intent(this,EventBusActivity2.class);
                startActivity(intent);
                break;
        }
    }
}
