package red.mfm.recyclerview2;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
  private List<Movie> movieList = new ArrayList<>();
  private RecyclerView recyclerView;
  private MoviesAdapter mAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);


    recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

    mAdapter = new MoviesAdapter(movieList);
    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
    recyclerView.setLayoutManager(mLayoutManager);
    recyclerView.setItemAnimator(new DefaultItemAnimator());

    recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16));
    recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
      @Override
      public void onClick(View view, int position) {
        Movie movie = movieList.get(position);
        Toast.makeText(getApplicationContext(), movie.getSong() + " is selected!", Toast.LENGTH_SHORT).show();
      }

      @Override
      public void onLongClick(View view, int position) {

      }
    }));

    recyclerView.setAdapter(mAdapter);
    App.getApi().listSongs("studio").enqueue(new Callback<List<Movie>>() {
      @Override
      public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
        movieList.addAll(response.body());
        recyclerView.getAdapter().notifyDataSetChanged();
      }

      @Override
      public void onFailure(Call<List<Movie>> call, Throwable t) {
        Toast.makeText(MainActivity.this, "An error occurred during networking", Toast.LENGTH_SHORT)
            .show();
      }
    });

  }


}