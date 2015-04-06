package com.academy.semtrac;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;


public class SubjectAdderActivity extends ActionBarActivity implements View.OnClickListener {

    private ArrayList<Subject> newSubjects;
    private RecyclerView mRecyclerView;
    private SubjectEntryAdapter mAdapter;
    private Button addMore;
    private Button done;
    private LayoutInflater inflater;
    private LinearLayout mLayout;
    private ArrayList<View> views;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_adder);

        inflater = LayoutInflater.from(this);
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mLayout = (LinearLayout) findViewById(R.id.subjectLayout);

        addMore = (Button) findViewById(R.id.addMore);
        done = (Button) findViewById(R.id.done);

        addMore.setOnClickListener(this);
        newSubjects = new ArrayList<>();
        views = new ArrayList<>();

        addViewToLayout();

        /*mRecyclerView = (RecyclerView) findViewById(R.id.subjectAdderRecycleView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new SubjectEntryAdapter(null, R.layout.subject_entry_row_layout, this);
        mRecyclerView.setAdapter(mAdapter);*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_first_run, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addMore:
                newSubjects.add(new Subject());
                /*mAdapter.notifyDataSetChanged();
                mRecyclerView.smoothScrollToPosition(newSubjects.size());*/
                addViewToLayout();
                break;
            case R.id.done:
                for (int i = 0; i < newSubjects.size(); i++) {
                    Subject subject = newSubjects.get(i);
                    View view = views.get(i);


                }
        }
    }

    private void addViewToLayout() {
        Subject newSubject = new Subject();
        newSubjects.add(newSubject);

        View cardView = inflater.inflate(R.layout.subject_entry_row_layout, mLayout);
        views.add(cardView);
    }
}
