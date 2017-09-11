package com.study.rijiben;

import java.io.Serializable;

/**
 * 日记的Bean对象
 */

public class Note implements Serializable {
	private int id;

	public Note(int id, String name, String time, String xinqing, String tianqi, String content) {
		super();
		this.id = id;
		this.name = name;
		this.time = time;
		this.xinqing = xinqing;
		this.tianqi = tianqi;
		this.content = content;
	}

	@Override
	public String toString() {
		return "Note{" +
				"id=" + id +
				", name='" + name + '\'' +
				", time='" + time + '\'' +
				", xinqing='" + xinqing + '\'' +
				", tianqi='" + tianqi + '\'' +
				", content='" + content + '\'' +
				", lastTime='" + lastTime + '\'' +
				'}';
	}

	private String name;
	private String time;
	private String xinqing;
	private String tianqi;
	private String content;

	public String getLastTime() {
		return lastTime;
	}

	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}

	public Note(String name, String time, String xinqing, String tianqi, String content, String lastTime) {
		this.name = name;
		this.time = time;
		this.xinqing = xinqing;
		this.tianqi = tianqi;
		this.content = content;
		this.lastTime = lastTime;
	}
	public static Note getUpdateNote(String name,String content,String lastTime,String xinqing,String tianqi)
	{
		Note note = new Note();
		note.setName(name);
		note.setContent(content);
		note.setLastTime(lastTime);
		note.setXinqing(xinqing);
		note.setTianqi(tianqi);
		return  note;
	}

	public Note(int id, String name, String time, String xinqing, String tianqi, String content, String lastTime) {
		this.id = id;
		this.name = name;
		this.time = time;
		this.xinqing = xinqing;
		this.tianqi = tianqi;
		this.content = content;
		this.lastTime = lastTime;
	}

	private String lastTime;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getXinqing() {
		return xinqing;
	}

	public void setXinqing(String xinqing) {
		this.xinqing = xinqing;
	}

	public String getTianqi() {
		return tianqi;
	}

	public void setTianqi(String tianqi) {
		this.tianqi = tianqi;
	}

	public Note(String name, String content, String time, String xinqing, String tianqi) {
		this.name = name;
		this.content = content;
		this.time = time;
		this.xinqing = xinqing;
		this.tianqi = tianqi;
	}

	public Note() {

	}

}
