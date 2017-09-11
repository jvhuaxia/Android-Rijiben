package com.study.rijiben;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

/**
 * 数据库的Dao(Data Access Object)对象 用来重复增删改查日记
 */
public class NoteDao {
    Context context;
    NoteSQLOpenHelper dbCreate;

    /**
     * 接收一个应用的 Context 用来实例化NoteSQLOpenHelper
     *
     * @param context 应用的Context
     */
    public NoteDao(Context context) {
        this.context = context;
        dbCreate = new NoteSQLOpenHelper(context);
    }

    /**
     * 添加日记
     *
     * @param note 要添加的日记
     * @return 返回添加是否成功 -1 为出错
     */
    public long insertNote(Note note) {
        SQLiteDatabase db = dbCreate.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", note.getName());
        cv.put("content", note.getContent());
        cv.put("time", note.getTime());
        cv.put("tianqi", note.getTianqi());
        cv.put("xinqing", note.getXinqing());

        long result = db.insert("riji", null, cv);
        db.close();
        return result;
    }

    /**
     * 显示所有的日记
     *
     * @return 所有的日记
     */
    public ArrayList<Note> showAllNote() {
        ArrayList<Note> allNote = new ArrayList<Note>();
        SQLiteDatabase db = dbCreate.getReadableDatabase();
        Cursor allNoteCursor = db.rawQuery("SELECT * FROM riji", null);
        while (allNoteCursor.moveToNext()) {
            int id = allNoteCursor.getInt(allNoteCursor.getColumnIndex("id"));
            String name = allNoteCursor.getString(allNoteCursor.getColumnIndex("name"));
            String content = allNoteCursor.getString(allNoteCursor.getColumnIndex("content"));
            String time = allNoteCursor.getString(allNoteCursor.getColumnIndex("time"));
            String xinqing = allNoteCursor.getString(allNoteCursor.getColumnIndex("xinqing"));
            String tianqi = allNoteCursor.getString(allNoteCursor.getColumnIndex("tianqi"));
            String lastTime = allNoteCursor.getString(allNoteCursor.getColumnIndex("lasttime"));
            Note note = new Note(id, name, time, xinqing, tianqi, content, lastTime);
            allNote.add(note);
        }
        db.close();
        return allNote;
    }

    /**
     * 根据ID删除日记
     *
     * @param id 要删除日记的ＩＤ
     */
    public void delNote(int id) {
        SQLiteDatabase db = dbCreate.getWritableDatabase();
        db.execSQL("DELETE FROM riji WHERE id = ?", new String[]{id + ""});
        db.close();
    }

    /**
     * 更新日记
     *
     * @param id   要更新日记的ID
     * @param note 要更新的日记的实例对象
     */
    public void updateNote(int id, Note note) {
        SQLiteDatabase db = dbCreate.getWritableDatabase();
        db.execSQL("UPDATE riji SET name = ?,content = ?,tianqi = ?,xinqing = ?,lasttime = ? WHERE id = ?",
                new String[]{note.getName(), note.getContent(), note.getTianqi(), note.getXinqing(), note.getLastTime(), id + ""});

        db.close();
    }

    /**
     * 搜索日记 匹配日记所有字段的值 只要有LIKE的就返回
     *
     * @param searchString 要搜索的字符串
     * @return 搜索到的日记
     */
    public ArrayList<Note> searchNote(String searchString) {
        searchString = "%" + searchString + "%";
        ArrayList<Note> searchNote = new ArrayList<Note>();
        SQLiteDatabase db = dbCreate.getReadableDatabase();
        Cursor searchNoteCursor = db.rawQuery("SELECT * FROM riji WHERE name LIKE ? OR content LIKE ? OR tianqi LIKE ? OR xinqing LIKE ? OR time LIKE ?", new String[]{searchString, searchString,
                searchString, searchString, searchString});
        while (searchNoteCursor.moveToNext()) {
            int id = searchNoteCursor.getInt(searchNoteCursor.getColumnIndex("id"));
            String name = searchNoteCursor.getString(searchNoteCursor.getColumnIndex("name"));
            String content = searchNoteCursor.getString(searchNoteCursor.getColumnIndex("content"));
            String time = searchNoteCursor.getString(searchNoteCursor.getColumnIndex("time"));
            String xinqing = searchNoteCursor.getString(searchNoteCursor.getColumnIndex("xinqing"));
            String tianqi = searchNoteCursor.getString(searchNoteCursor.getColumnIndex("tianqi"));
            String lastTime = searchNoteCursor.getString(searchNoteCursor.getColumnIndex("lasttime"));
            Note note = new Note(id, name, time, xinqing, tianqi, content, lastTime);
            searchNote.add(note);
        }
        db.close();
        return searchNote;
    }

    /**
     * 从指定ID获取Note
     *
     * @param id 要获取Note的ID
     * @return
     */
    public Note searchByID(int id) {
        SQLiteDatabase db = dbCreate.getReadableDatabase();
        Cursor noteCursor = db.rawQuery("SELECT * FROM riji WHERE id = ?", new String[]{id + ""});
        noteCursor.moveToNext();
        String name = noteCursor.getString(noteCursor.getColumnIndex("name"));
        String content = noteCursor.getString(noteCursor.getColumnIndex("content"));
        String time = noteCursor.getString(noteCursor.getColumnIndex("time"));
        String xinqing = noteCursor.getString(noteCursor.getColumnIndex("xinqing"));
        String tianqi = noteCursor.getString(noteCursor.getColumnIndex("tianqi"));
        String lastTime = noteCursor.getString(noteCursor.getColumnIndex("lasttime"));
        Note note = new Note(id, name, time, xinqing, tianqi, content, lastTime);
        db.close();
        return note;
    }
}
