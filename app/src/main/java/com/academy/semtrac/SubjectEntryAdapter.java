package com.academy.semtrac;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

/*
 * Created by Abhishek Shanthkumar on 05-04-2015.
 */
public class SubjectEntryAdapter extends RecyclerView.Adapter<SubjectEntryAdapter.ViewHolder> {

    private ArrayList<Subject> subjects;
    private int rowLayout;
    private Context mContext;

    public SubjectEntryAdapter(ArrayList<Subject> subjects, int rowLayout, Context mContext) {
        this.subjects = subjects;
        this.rowLayout = rowLayout;
        this.mContext = mContext;
    }

    @Override
    public SubjectEntryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SubjectEntryAdapter.ViewHolder holder, int position) {
        final Subject subject = subjects.get(position);

        /*holder.subjectCode.setText(subject.getCode());
        holder.subjectName.setText(subject.getName());
        if(subject.getCredits()!=0)
            holder.subjectCredits.setText(String.valueOf(subject.getCredits()));
        else
            holder.subjectCredits.setText("");*/

        holder.subjectCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                subject.setCode(s.toString().toUpperCase().trim());
            }
        });

        holder.subjectName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                subject.setName(s.toString().trim());
            }
        });

        holder.subjectCredits.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty())
                    subject.setCredits(Integer.parseInt(s.toString().trim()));
            }
        });

        subject.setCoursePlanPresent(!(holder.fileName.getText().toString().compareToIgnoreCase("None selected") == 0));
        holder.coursePlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent photoPickerIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                mContext.startActivityForResult(photoPickerIntent, mContext.PHOTO_PICKER_ID);*/
            }
        });
    }

    @Override
    public int getItemCount() {
        return subjects == null ? 0 : subjects.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private EditText subjectCode;
        private EditText subjectName;
        private EditText subjectCredits;
        private Button coursePlan;
        private TextView fileName;

        public ViewHolder(View itemView) {
            super(itemView);
            subjectCode = (EditText) itemView.findViewById(R.id.entry_subject_code);
            subjectName = (EditText) itemView.findViewById(R.id.entry_subject_name);
            subjectCredits = (EditText) itemView.findViewById(R.id.entry_subject_credits);
            coursePlan = (Button) itemView.findViewById(R.id.entry_courseplan);
            fileName = (TextView) itemView.findViewById(R.id.entry_filename);
        }
    }
}
