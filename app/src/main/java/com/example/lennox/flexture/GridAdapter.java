package com.example.lennox.flexture;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class GridAdapter extends BaseAdapter{
    private ArrayList names;
    private Activity activity;
    private Boolean studentSelected;

    public GridAdapter(Activity activity, ArrayList names, Boolean role) {
        this.activity = activity;
        this.names = names;
        studentSelected = role;
    }

    @Override
    public int getCount() {
        return names.size();
    }

    @Override
    public Object getItem(int i) {
        return names.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater vi = LayoutInflater.from(activity);
            view = vi.inflate(R.layout.grid_adapter, null);
        }
        TextView textView = (TextView)view.findViewById(R.id.namePlacer);
        ImageView imageView = (ImageView)view.findViewById(R.id.imageHolder);

        if(names.get(i).toString().equals("CLASS SESSION")) {
            imageView.setImageResource(R.drawable.ic_attendance);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent classSession = new Intent(activity, ClassSession.class);
                    classSession.putExtra("ROLE", studentSelected);
                    activity.startActivity(classSession);
                }
            });
            Animation anim = new ScaleAnimation(
                    0.95f, 1f, // Start and end values for the X axis scaling
                    0.95f, 1f, // Start and end values for the Y axis scaling
                    Animation.RELATIVE_TO_SELF, 0.5f, // Pivot point of X scaling
                    Animation.RELATIVE_TO_SELF, 0.5f); // Pivot point of Y scaling
            anim.setFillAfter(true); // Needed to keep the result of the animation
            anim.setDuration(2000);
            anim.setRepeatMode(Animation.INFINITE);
            anim.setRepeatCount(Animation.INFINITE);
            imageView.startAnimation(anim);
        }else if(names.get(i).toString().equals("SCHEDULER"))
        {
            imageView.setImageResource(R.drawable.ic_schedule);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent schedule = new Intent(activity, Scheduler.class);
                    schedule.putExtra("ROLE", studentSelected);
                    activity.startActivity(schedule);
                }
            });
            Animation anim = new ScaleAnimation(
                    0.95f, 1f, // Start and end values for the X axis scaling
                    0.95f, 1f, // Start and end values for the Y axis scaling
                    Animation.RELATIVE_TO_SELF, 0.5f, // Pivot point of X scaling
                    Animation.RELATIVE_TO_SELF, 0.5f); // Pivot point of Y scaling
            anim.setFillAfter(true); // Needed to keep the result of the animation
            anim.setDuration(2000);
            anim.setRepeatMode(Animation.INFINITE);
            anim.setRepeatCount(Animation.INFINITE);
            imageView.startAnimation(anim);

        }else if(names.get(i).toString().equals("TIME TABLE"))
        {
            imageView.setImageResource(R.drawable.ic_schedule);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent timeTable = new Intent(activity, Scheduler.class);
                    timeTable.putExtra("ROLE", studentSelected);
                    activity.startActivity(timeTable);
                }
            });
            Animation anim = new ScaleAnimation(
                    0.95f, 1f, // Start and end values for the X axis scaling
                    0.95f, 1f, // Start and end values for the Y axis scaling
                    Animation.RELATIVE_TO_SELF, 0.5f, // Pivot point of X scaling
                    Animation.RELATIVE_TO_SELF, 0.5f); // Pivot point of Y scaling
            anim.setFillAfter(true); // Needed to keep the result of the animation
            anim.setDuration(2000);
            anim.setRepeatMode(Animation.INFINITE);
            anim.setRepeatCount(Animation.INFINITE);
            imageView.startAnimation(anim);

        }else if(names.get(i).toString().equals("NOTES"))
        {
            imageView.setImageResource(R.drawable.ic_notes);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent notes = new Intent(activity, Notes.class);
                    notes.putExtra("ROLE", studentSelected);
                    activity.startActivity(notes);
                }
            });
            Animation anim = new ScaleAnimation(
                    0.95f, 1f, // Start and end values for the X axis scaling
                    0.95f, 1f, // Start and end values for the Y axis scaling
                    Animation.RELATIVE_TO_SELF, 0.5f, // Pivot point of X scaling
                    Animation.RELATIVE_TO_SELF, 0.5f); // Pivot point of Y scaling
            anim.setFillAfter(true); // Needed to keep the result of the animation
            anim.setDuration(2000);
            anim.setRepeatMode(Animation.INFINITE);
            anim.setRepeatCount(Animation.INFINITE);
            imageView.startAnimation(anim);

        }else if(names.get(i).toString().equals("PROFILE"))
        {
            imageView.setImageResource(R.drawable.ic_profile);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   Intent profile = new Intent(activity, Profile.class);
                   profile.putExtra("ROLE", studentSelected);
                   activity.startActivity(profile);
                }
            });
            Animation anim = new ScaleAnimation(
                    0.95f, 1f, // Start and end values for the X axis scaling
                    0.95f, 1f, // Start and end values for the Y axis scaling
                    Animation.RELATIVE_TO_SELF, 0.5f, // Pivot point of X scaling
                    Animation.RELATIVE_TO_SELF, 0.5f); // Pivot point of Y scaling
            anim.setFillAfter(true); // Needed to keep the result of the animation
            anim.setDuration(2000);
            anim.setRepeatMode(Animation.INFINITE);
            anim.setRepeatCount(Animation.INFINITE);
            imageView.startAnimation(anim);
        }
        else if(names.get(i).toString().equals("RESULTS"))
        {
            imageView.setImageResource(R.drawable.ic_cgpa);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent results = new Intent(activity, Results.class);
                    results.putExtra("ROLE", studentSelected);
                    activity.startActivity(results);
                }
            });
            Animation anim = new ScaleAnimation(
                    0.95f, 1f, // Start and end values for the X axis scaling
                    0.95f, 1f, // Start and end values for the Y axis scaling
                    Animation.RELATIVE_TO_SELF, 0.5f, // Pivot point of X scaling
                    Animation.RELATIVE_TO_SELF, 0.5f); // Pivot point of Y scaling
            anim.setFillAfter(true); // Needed to keep the result of the animation
            anim.setDuration(2000);
            anim.setRepeatMode(Animation.INFINITE);
            anim.setRepeatCount(Animation.INFINITE);
            imageView.startAnimation(anim);
        }

        textView.setText(names.get(i).toString());
        return view;
    }
}
