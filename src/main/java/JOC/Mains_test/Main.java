package JOC.Mains_test;

import JOC.Mon.*;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;
import java.io.IOException;
import retrofit2.converter.jackson.JacksonConverterFactory;


public class Main {
    public static final String API_URL = "http://localhost:8080";

    public static void pintar(Escena escena)
    {
        for(int i=0;i<escena.getAlto();i++) {
            String linea ="";
            for (int j = 0; j < escena.getAncho();j++) {
                linea=linea+" "+escena.getDatos()[i][j].getSimbolo();
            }
            System.out.println(linea);
        }
    }
    public static void main( String[] args ) throws IOException
    {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        // Create a very simple REST adapter which points the GitHub API.
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        // Create an instance of our GitHub API interface.
        ServeiRetrofit servei = retrofit.create(ServeiRetrofit.class);

        // Create a call instance for looking up Retrofit contributors.
        Call<Escena> call = servei.escenas("2");
        Call<Usuario> call2 = servei.regUsuario(new Usuario("Jordi","noob",1));
        Call<Usuario> call3 = servei.consultarUsuarioJSON("Jordi");

        // Fetch and print a list of the contributors to the library.
        Response resposta= call.execute();
        int codi= resposta.code();
        if(codi==200) {
            Escena escena = (Escena)resposta.body();
            pintar(escena);
        }
        else if(codi==204){
            System.out.printf("No hi ha cap escena amb aquell identificador\n");
        }
        else{
            System.out.printf("Error desconegut\n");
        }
        resposta = call2.execute();
        codi= resposta.code();
        if(codi==200) {
            Usuario user = (Usuario)resposta.body();
            System.out.printf(user.getNickname()+"\n");
        }
        else if(codi==204){
            System.out.printf("No hi ha cap usuari amb aquest identificador\n");
        }
        else{
            System.out.printf("Error desconegut\n");
        }
        resposta = call3.execute();
        codi= resposta.code();
        if(codi==200) {
            Usuario user = (Usuario) resposta.body();
            System.out.printf(user.getNickname()+"\n");
        }
        else if(codi==204){
            System.out.printf("No hi ha cap usuari amb aquest identificador\n");
        }
        else{
            System.out.printf("Error desconegut\n");
        }
    }
}


