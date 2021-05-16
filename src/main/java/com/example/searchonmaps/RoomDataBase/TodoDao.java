package com.example.searchonmaps.RoomDataBase;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TodoDao {

    @Insert
    void insertTodo(Todo todo);

    @Query("SELECT * FROM todo_table")
    List<Todo> getAllTodos();

    @Query("SELECT * FROM todo_table WHERE todo_text LIKE :uid")
    Todo findTodoById(String uid);

    @Delete
    void deleteTodo(Todo todo);

    @Update
    void updateTodo(Todo todo);

    @Insert
    void insertMultipleTodos(List<Todo> todoList);

    @Query("SELECT * FROM todo_table WHERE todo_completed LIKE 1")
    List<Todo> getAllCompletedTodos();

    // Deleted all
    @Query("DELETE FROM todo_table")
    public void nukeTable();


}