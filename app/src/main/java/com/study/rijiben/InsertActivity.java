package com.study.rijiben;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * 主要用于添加日记的Activity
 */

public class InsertActivity extends AppCompatActivity {
    String xinqing, tianqi;
    String[] xinqings, tianqis;
    Spinner spinnerTianqi, spinnerXinqing;
    EditText editTextName, editTextContent;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_layout);
        initSpinnerString();
        initView();
        setSupportActionBar(toolbar);

        spinnerTianqi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tianqi = tianqis[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinnerXinqing.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                xinqing = xinqings[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void doInsert() {
        NoteDao dao = new NoteDao(InsertActivity.this);
        String name = editTextName.getText().toString();
        String content = editTextContent.getText().toString();
        String time = Utils.getTime();
        if (name.equals("") | content.equals("")) {
            Toast.makeText(InsertActivity.this, "请检查是否有值没有填写", Toast.LENGTH_SHORT).show();
        } else {
            Note note = new Note(name, content, time, xinqing, tianqi);
            //Toast.makeText(InsertActivity.this,note.toString(),1).show();
            dao.insertNote(note);
            gotoMainActivity();
        }
    }


    private void gotoMainActivity() {
        Intent intent = new Intent(InsertActivity.this, MainActivity.class);
        startActivity(intent);
        InsertActivity.this.finish();
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        spinnerTianqi = (Spinner) findViewById(R.id.spinnerTianqi);
        spinnerXinqing = (Spinner) findViewById(R.id.spinnerXinqing);
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextContent = (EditText) findViewById(R.id.editTextContent);
    }

    private void initSpinnerString() {
        tianqis = getResources().getStringArray(R.array.tianqi);
        xinqings = getResources().getStringArray(R.array.xinqing);
        xinqing = xinqings[0];
        tianqi = tianqis[0];
    }

    @Override
    public void onBackPressed() {
        String name = editTextName.getText().toString();
        String content = editTextContent.getText().toString();
        if (name.equals("") & content.equals("")) {
            gotoMainActivity();
        } else {
            AlertDialog.Builder adb = new AlertDialog.Builder(InsertActivity.this);
            adb.setMessage("是否确定放弃编辑的内容 返回主界面");
            adb.setPositiveButton("是", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    gotoMainActivity();
                }
            });
            adb.setNegativeButton("否", null);
            adb.create().show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.insert_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.toolbar_add:
                doInsert();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
