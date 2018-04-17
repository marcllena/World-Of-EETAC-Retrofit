package JOC.Mains_test;

import JOC.Mon.Escena;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface ServeiRetrofit {

    @GET("/myapp/service/escenaris/{id}")
    Call<Escena> escenas(
            @Path("id") String id);
}
