package com.example.lennox.flexture;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;

public class About extends AppCompatActivity {

    Animation anim1, anim2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        moveViewToScreenCenter(findViewById(R.id.dev_header));
        moveViewToScreenCenter(findViewById(R.id.author1));
        moveIcon(findViewById(R.id.imageViewAbout));
    }

    private void moveIcon(View viewById) {
        int originalPos[] = new int[2];
        viewById.getLocationOnScreen(originalPos);

        anim2 = new TranslateAnimation(0, 0, 0, originalPos[1] + 100);
        anim2.setDuration(2000);
        anim2.setFillAfter(true);
        viewById.startAnimation(anim2);
    }

    private void moveViewToScreenCenter(View viewById) {
            RelativeLayout root = (RelativeLayout) findViewById(R.id.rel_root);
            DisplayMetrics dm = new DisplayMetrics();
            this.getWindowManager().getDefaultDisplay().getMetrics(dm);
            int statusBarOffset = dm.heightPixels - root.getMeasuredHeight();

            int originalPos[] = new int[2];
            viewById.getLocationOnScreen(originalPos);

            int xDest = dm.widthPixels / 2;
            xDest -= (viewById.getMeasuredWidth() / 2);
            int yDest = dm.heightPixels / 2 - (viewById.getMeasuredHeight() / 2) - statusBarOffset;

            anim1 = new TranslateAnimation(0, 0, 0, yDest - originalPos[1] + 250);
            anim1.setDuration(1500);
            anim1.setFillAfter(true);
            viewById.startAnimation(anim1);

    }
}
