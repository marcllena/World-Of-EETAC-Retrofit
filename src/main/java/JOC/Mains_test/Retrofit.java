package JOC.Mains_test;

import JOC.Mon.Escena;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface Retrofit {
    @GET("/escenaris/{id}")
    Call<Escena> escena(
            @Path("id") String id);
}
