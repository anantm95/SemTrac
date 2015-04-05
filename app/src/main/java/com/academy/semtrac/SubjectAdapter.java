package com.academy.semtrac;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

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
        Subject subject = subjects.get(position);
        holder.percent.setText(subject.getAttendancePercentage() + "%");
        holder.name.setText(subject.getCode() + ": " + subject.getName());
        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO:Intent to next activity
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
