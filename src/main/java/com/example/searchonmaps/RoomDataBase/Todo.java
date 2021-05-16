package com.example.searchonmaps.RoomDataBase;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "todo_table")
public class Todo {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "todo_uid")
    private int uid;

    @ColumnInfo(name = "todo_text")
    private String text;

    @ColumnInfo(name = "todo_completed")
    private boolean completed;

    @ColumnInfo(name = "todo_Latitude")
    private double Latitude;

    @ColumnInfo(name = "todo_Longitude")
    private double Longitude;

    public Todo(String text, boolean completed , double Longitude , double Latitude ) {
        this.text = text;
        this.completed = completed;
        this.Longitude = Longitude;
        this.Latitude = Latitude;
    }

    public double getLatitude() { return Latitude; }

    public void setLatitude(double latitude) { Latitude = latitude; }

    public double getLongitude() { return Longitude; }

    public void setLongitude(double longitude) { Longitude = longitude; }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "\nTodo{" +
                "uid=" + uid +
                ", text='" + text + '\'' +
                ", completed=" + completed +
                ", todo_Longitude="+Longitude +
                ", todo_todo_Latitude"+Latitude +
                '}';
    }
}