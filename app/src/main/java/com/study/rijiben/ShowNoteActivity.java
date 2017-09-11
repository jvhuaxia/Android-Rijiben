package com.study.rijiben;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Created by JvHuaxia on 2017/2/28.
 */

public class ShowNoteActivity extends AppCompatActivity {
    TextView textViewTianqi, textViewXinqing, textViewContent, textViewName, textViewTime;
    Toolbar toolbar;
    Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_note_layout);

        Intent intent = getIntent();
        note = (Note) intent.getSerializableExtra("note");

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        textViewTianqi = (TextView) findViewById(R.id.textViewTianqi);
        textViewXinqing = (TextView) findViewById(R.id.textViewXinqing);
        textViewContent = (TextView) findViewById(R.id.textViewContent);
        textViewName = (TextView) findViewById(R.id.textViewName);
        textViewTime = (TextView) findViewById(R.id.textViewTime);

        textViewTime.setText(note.getTime() + "创建" + "\n" + (note.getLastTime() == null ? "" : note.getLastTime() + "最后修改"));
        textViewName.setText(note.getName());
        textViewContent.setText(note.getContent());
        textViewTianqi.setText(note.getTianqi());
        textViewXinqing.setText(note.getXinqing());
    }

    private void gotoEditActivity(Note note) {
        Intent intent = new Intent(ShowNoteActivity.this, EditActivity.class);
        intent.putExtra("note", note);
        startActivity(intent);
        ShowNoteActivity.this.finish();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ShowNoteActivity.this, MainActivity.class);
        startActivity(intent);
        ShowNoteActivity.this.finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.show_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.toolbar_edit:
                gotoEditActivity(note);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
