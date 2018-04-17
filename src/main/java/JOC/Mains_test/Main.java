package JOC.Mains_test;

import JOC.Mon.*;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.io.IOException;
import java.util.List;

public class Main {
    public static final String API_URL = "http://localhost:8080/myapp/service/";

    public static void main( String[] args ) throws IOException
    {

        // Create a very simple REST adapter which points the GitHub API.
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create an instance of our GitHub API interface.
        Retrofit servei = retrofit.create(Retrofit.class);

        // Create a call instance for looking up Retrofit contributors.
        Call<Escena> call = servei.escena();

        // Fetch and print a list of the contributors to the library.
        List<Contributor> contributors = call.execute().body();
        for (Contributor contributor : contributors) {
            System.out.println(contributor.login + " (" + contributor.contributions + ") "+ contributor.avatar_url);
        }
    }
}


