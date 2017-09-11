package com.study.rijiben;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * 主要用于编辑 显示 日记的Activity
 */
public class EditActivity extends AppCompatActivity {
    int id;
    EditText editTextName, editTextContent;
    Spinner spinnerXinqing, spinnerTianqi;
    Note getNote;
    Toolbar toolbar;

    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_layout);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        editTextContent = (EditText) findViewById(R.id.editTextContent);
        editTextName = (EditText) findViewById(R.id.editTextName);
        spinnerTianqi = (Spinner) findViewById(R.id.spinnerTianqi);
        spinnerXinqing = (Spinner) findViewById(R.id.spinnerXinqing);

        Intent intent = getIntent();
        getNote = (Note) intent.getSerializableExtra("note");
        id = getNote.getId();
        editTextName.setText(getNote.getName());
        editTextContent.setText(getNote.getContent());
        spinnerTianqi.setSelection(Utils.getStringInStringsIndex(getResources().getStringArray(R.array.tianqi), getNote.getTianqi()));
        spinnerXinqing.setSelection(Utils.getStringInStringsIndex(getResources().getStringArray(R.array.xinqing), getNote.getXinqing()));

    }

    private void doSave() {
        String name = editTextName.getText().toString();
        String content = editTextContent.getText().toString();
        String tianqi = spinnerTianqi.getSelectedItem().toString();
        String xinqing = spinnerXinqing.getSelectedItem().toString();
        if (name.equals("") | content.equals("") | tianqi.equals("") | xinqing.equals("")) {
            Toast.makeText(EditActivity.this, "请检查是否有东西没有填", Toast.LENGTH_SHORT).show();
        } else {
            Note note = Note.getUpdateNote(name, content, Utils.getTime(), xinqing, tianqi);
            NoteDao dao = new NoteDao(EditActivity.this);
            dao.updateNote(id, note);

            Intent intent = new Intent(EditActivity.this, ShowNoteActivity.class);
            Note newNote = dao.searchByID(id);
            intent.putExtra("note", newNote);
            startActivity(intent);
            EditActivity.this.finish();
        }
    }

    private void doDel() {
        AlertDialog.Builder builder = new AlertDialog.Builder(EditActivity.this);
        builder.setMessage("确定要删除吗?");
        builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                NoteDao dao = new NoteDao(EditActivity.this);
                dao.delNote(id);
                gotoMainActivity();
            }
        });
        builder.setNegativeButton("否", null);
        builder.create().show();
    }


    @Override
    public void onBackPressed() {
        doBack();
    }

    private void doBack() {
        AlertDialog.Builder adb = new AlertDialog.Builder(EditActivity.this);
        if (editTextName.getText().toString().equals(getNote.getName()) && spinnerTianqi.getSelectedItem().toString().equals(getNote.getTianqi()) && editTextContent.getText().toString().equals(getNote.getContent()) && spinnerXinqing.getSelectedItem().toString().equals(getNote.getXinqing())) {
            Intent intent = new Intent(EditActivity.this, ShowNoteActivity.class);
            intent.putExtra("note", getNote);
            startActivity(intent);
            EditActivity.this.finish();
        } else {
            adb.setMessage("是否确定放弃编辑的内容");
            adb.setPositiveButton("是", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent(EditActivity.this, ShowNoteActivity.class);
                    intent.putExtra("note", getNote);
                    startActivity(intent);
                    EditActivity.this.finish();
                }
            });
            adb.setNegativeButton("否", null);
            adb.create().show();
        }
    }

    void gotoMainActivity() {
        Intent intent = new Intent(EditActivity.this, MainActivity.class);
        startActivity(intent);
        EditActivity.this.finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.toolbar_del:
                doDel();
                break;
            case R.id.toolbar_save:
                doSave();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
