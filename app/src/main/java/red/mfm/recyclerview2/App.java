package red.mfm.recyclerview2;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import red.mfm.recyclerview2.api.HackerearthApi;

public class App extends Application {

  private static HackerearthApi umoriliApi;
  private Retrofit retrofit;

  @Override
  public void onCreate() {
    super.onCreate();

    retrofit = new Retrofit.Builder()
        .baseUrl("http://starlord.hackerearth.com") //Базовая часть адреса
        .addConverterFactory(GsonConverterFactory.create()) //Конвертер, необходимый для преобразования JSON'а в объекты
        .build();
    umoriliApi = retrofit.create(HackerearthApi.class); //Создаем объект, при помощи которого будем выполнять запросы
  }

  public static HackerearthApi getApi() {
    return umoriliApi;
  }
}