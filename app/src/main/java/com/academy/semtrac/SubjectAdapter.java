package com.academy.semtrac;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import java.text.DecimalFormat;
import java.util.ArrayList;

/*
 * Created by Abhishek Shanthkumar on 05-04-2015.
 */
public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.ViewHolder> {

    private ArrayList<Subject> subjects;
    private int rowLayout;
    private Context mContext;

    public SubjectAdapter(ArrayList<Subject> subjects, int rowLayout, Context mContext) {
        this.subjects = subjects;
        this.rowLayout = rowLayout;
        this.mContext = mContext;
    }

    @Override
    public SubjectAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SubjectAdapter.ViewHolder holder, int position) {
        final Subject subject = subjects.get(position);
        holder.percent.setText(new DecimalFormat("##.#").format(subject.getAttendancePercentage()) + "%");
        String name = subject.getName();
        if (name.length() > 3) {
            String[] things = name.split(" ");
            name = "";
            for (String temp : things) {
                name += temp.charAt(0);
            }
            if (things[things.length - 1].compareToIgnoreCase("Lab") == 0) {
                name = name.substring(0, name.length() - 1) + " Lab";
            }
        }
        holder.name.setText(subject.getCode() + ": " + name);
        Gson gson = new Gson();
        final String subjectText = gson.toJson(subject);
        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, com.academy.semtrac.SubjectInfo.class);
                GlobalClass globalClass = (GlobalClass) mContext.getApplicationContext();
                globalClass.setCurrentSubject(subject);
                intent.putExtra("subject", subjectText);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return subjects == null ? 0 : subjects.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public Button name;
        public TextView percent;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (Button) itemView.findViewById(R.id.subject);
            percent = (TextView) itemView.findViewById(R.id.percent);
        }
    }
}
