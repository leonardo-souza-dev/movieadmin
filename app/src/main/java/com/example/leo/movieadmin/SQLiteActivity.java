package com.example.leo.movieadmin;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.List;

/**
 * Created by rm48306 on 27/06/2016.
 */
public class SQLiteActivity extends AppCompatActivity {

    private EditText edtName, edtPlot;
    private MovieDAO movieDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
    }

    public void cadastrar(View v) {

        edtName = (EditText) findViewById(R.id.edtName);
        edtPlot = (EditText) findViewById(R.id.edtPlot);

        Movie movie = new Movie();
        movie.setId(1);
        movie.setName(edtName.getText().toString());
        movie.setPlot(edtPlot.getText().toString());
        this.movieDAO = new MovieDAO(this);
        //this.movieDAO.deleteAll();
        this.movieDAO.insert(movie);

        List<Movie> movies = this.movieDAO.selectAll();
        StringBuilder sb = new StringBuilder();

        for (Movie c : movies) {
            sb.append(c.getId() + " - ");
            sb.append(c.getName()  + " - ");
            sb.append(c.getPlot() + "\n");
        }

        Log.d("EXAMPLE", "names size - " + movies.size());


        AlertDialog.Builder dialogo = new AlertDialog.Builder(SQLiteActivity.this);
        dialogo.setTitle("Movies");
        dialogo.setMessage(sb);
        dialogo.setNeutralButton("OK", null);
        dialogo.show();

    }

    private void encerrar() {

        movieDAO.encerrarDB();
        this.finish();
        System.exit(0);

    }
}
