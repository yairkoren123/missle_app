package com.example.searchonmaps.RoomDataBase;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.searchonmaps.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;


public class AllTaskTable extends AppCompatActivity {

    private static final String TAG = "p";
    double[] yes1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("ss", "onCreate: pp");
        System.out.println("asdadasd");
    }

    public void insertSingleTodo(Context context,String loc, double Latitude , double Longitude) {
        Todo todo = new Todo(loc, true,Latitude,Longitude);
        Log.d("insert", "insertSingleTodo:  lat : "+ todo.getLatitude() + "todo : long "+ todo.getLongitude());
        todo.setLatitude(Latitude);
        todo.setLongitude(Longitude);
        InsertAsyncTask insertAsyncTask = new InsertAsyncTask();
        insertAsyncTask.setContext(context);
        insertAsyncTask.execute(todo);
    }

    public void getAllTodos(Context context) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                List<Todo> todoList = TodoRoomDatabase.getInstance(context)
                        .todoDao()
                        .getAllTodos();

                Log.d(TAG, "run: " + todoList.toString());
            }
        });
        thread.start();
    }

    public void deleteATodo(Context context) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                Todo todo = TodoRoomDatabase.getInstance(context)
                        .todoDao()
                        .findTodoById("2");

                Log.d(TAG, "run: " + todo.toString());
                if (todo != null) {
                    TodoRoomDatabase.getInstance(context)
                            .todoDao()
                            .deleteTodo(todo);

                    Log.d(TAG, "run: todo has been deleted...");
                }

            }
        }).start();

    }

    public CompletableFuture<double[]> findbyidTodo(Context context , String loc) {

        final CompletableFuture<double[]> future =  new CompletableFuture<>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Todo todo = TodoRoomDatabase.getInstance(context)
                        .todoDao()
                        .findTodoById(loc);

                if (todo != null) {
                    future.complete(new double[]{todo.getLatitude(), todo.getLongitude()});
                } else {
                    future.complete(null);
                }
            }

            }).start();
        return future;
    }



















//    double[] yes = new double[]{};
//
//    public double[] findbyidTodo(Context context, String loc) {
//
//        Log.d("wwe", "findbyidTodo: ok");
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Todo todo = TodoRoomDatabase.getInstance(context)
//                        .todoDao()
//                        .findTodoById(loc);
//                if (todo != null) {
//                    Log.d("wwe2", "run: " + todo.toString());
//                    yes = new double[]{todo.getLatitude(), todo.getLongitude()};
//                    Log.d("wwe3", "run: " + yes[0] + " and  " + yes[1]);
//
//                }
//
//            }
//        }).start();
//
//        return yes;
//    }




    public void updateATodo(Context context) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Todo todo = TodoRoomDatabase.getInstance(context)
                            .todoDao()
                            .findTodoById("2");

                    if (todo != null) {
                        todo.setCompleted(true);

                        TodoRoomDatabase.getInstance(context)
                                .todoDao()
                                .updateTodo(todo);

                        Log.d("TAG", "run: todo has been updated...");
                    }
                }
            }).start();

        }

        public void insertMultipleTodos(Context context) {
            Log.d("TAG", "run: todos has been inserted...");


            new Thread(new Runnable() {
                @Override
                public void run() {
                    List<Todo> todoList = new ArrayList<>();
                    todoList.add(new Todo("make a video on kotlin", false,0,0));
                    todoList.add(new Todo("watch black panther", true,0,0));

                    TodoRoomDatabase.getInstance(context)
                            .todoDao()
                            .insertMultipleTodos(todoList);


                }
            }).start();

        }
        public void deletedallTata(Context context){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    TodoRoomDatabase.getInstance(context)
                            .todoDao()
                            .nukeTable();
                    Log.d("yes", "run: dsa");

                }
            }).start();
        }

        public void findCompletedTodos(Context context) {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    List<Todo> todoList = TodoRoomDatabase.getInstance(context)
                            .todoDao()
                            .getAllCompletedTodos();
                    Log.d("yes", "run: dsa");

                    Log.d(TAG, "run: " + todoList.toString());
                }
            }).start();

        }






    class InsertAsyncTask extends AsyncTask<Todo, Void, Void> {

            public Context context;

        public Context getContext() {
            return context;
        }

        public void setContext(Context context) {
            this.context = context;
        }

        @Override
            protected Void doInBackground(Todo... todos) {

                TodoRoomDatabase.getInstance(context)
                        .todoDao()
                        .insertTodo(todos[0]);

                return null;
            }
        }

}