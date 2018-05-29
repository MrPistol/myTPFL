package red.mfm.recyclerview2.api;

import java.util.List;
import java.util.List;

import red.mfm.recyclerview2.Movie;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface HackerearthApi {
  @GET("/{user}")
  Call<List<Movie>> listSongs(@Path("user")String user) ;
}