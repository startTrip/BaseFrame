package com.mihua.frameproject.dongnao.lsn4_paint_shader;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.mihua.frameproject.dongnao.scroller.ScrollLayout;

public class ShaderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScrollLayout scrollLayout = new ScrollLayout(this);
        setContentView(scrollLayout);
        scrollLayout.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        scrollLayout.setBackgroundColor(Color.GREEN);
        scrollLayout.scrollTo(200);

    }

}
