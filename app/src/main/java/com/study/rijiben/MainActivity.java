package com.study.rijiben;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * 主Activity
 * 用来显示日记条目 添加 搜索操作
 */
public class MainActivity extends AppCompatActivity {

    NoteDao dao = null;
    FloatingActionButton buttonInsert = null;
    ListView listViewNote = null;
    EditText editTextSearch = null;
    TextView textViewTitle = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dao = new NoteDao(this);
        setContentView(R.layout.activity_main);

        textViewTitle = (TextView) findViewById(R.id.textViewTitle);
        listViewNote = (ListView) findViewById(R.id.listViewNote);
        buttonInsert = (FloatingActionButton) findViewById(R.id.buttonInsert);
        editTextSearch = (EditText) findViewById(R.id.editTextSearch);

        isShowHelp();

        reFreshListViewByAllNote();
        textViewTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextSearch.setText("");
                reFreshListViewByAllNote();
            }
        });
        editTextSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    reFreshListViewBySearch(v.getText().toString());
                    Utils.hideInput(MainActivity.this);
                }
                return true;
            }
        });
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, InsertActivity.class);
                startActivity(intent);
                MainActivity.this.finish();
            }
        });
        listViewNote.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Note note = (Note) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(MainActivity.this, ShowNoteActivity.class);
                intent.putExtra("note", note);
                startActivity(intent);
                MainActivity.this.finish();
            }
        });
    }

    /**
     * 第一次打开的时候的弹出框
     */
    private void isShowHelp() {
        final SharedPreferences sp = getSharedPreferences("save", Activity.MODE_PRIVATE);
        boolean isShowHelp = sp.getBoolean("isShowHelp", true);
        if (isShowHelp) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            final android.app.AlertDialog alertDialog = builder.create();
            View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.first_start_layout, null);
            view.findViewById(R.id.buttonClose).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialog.cancel();
                }
            });
            sp.edit().putBoolean("isShowHelp", false).commit();
            alertDialog.setView(view);
            alertDialog.show();
        }
    }

    /**
     * 把ListView显示所有的日记
     */
    void reFreshListViewByAllNote() {
        ArrayList<Note> mArrayList = dao.showAllNote();
        NoteAdapter mAdapter = new NoteAdapter(MainActivity.this, mArrayList);
        listViewNote.setAdapter(mAdapter);
    }

    /**
     * 让ListView显示搜索的日记
     * @param searchName 只要是一个日记所有内容出现的字符串都能搜索
     */
    void reFreshListViewBySearch(String searchName) {
        ArrayList<Note> mArrayList = dao.searchNote(searchName);
        NoteAdapter mAdapter = new NoteAdapter(MainActivity.this, mArrayList);
        listViewNote.setAdapter(mAdapter);
    }

    long firstTime = 0;
    @Override
    public void onBackPressed() {
        long secondTime = System.currentTimeMillis();
        if (secondTime - firstTime > 2000) {
            Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            firstTime = secondTime;
        } else {
            System.exit(0);
        }
    }
}
