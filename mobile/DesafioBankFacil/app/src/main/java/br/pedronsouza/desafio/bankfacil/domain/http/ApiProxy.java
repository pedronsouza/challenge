package br.pedronsouza.desafio.bankfacil.domain.http;


import android.os.Parcelable;
import android.util.Log;

import org.androidannotations.annotations.EBean;

import java.io.IOException;

import br.pedronsouza.desafio.bankfacil.BuildConfig;
import br.pedronsouza.desafio.bankfacil.domain.http.exceptions.HttpError;
import br.pedronsouza.desafio.bankfacil.domain.models.UserList;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

@EBean(scope = EBean.Scope.Singleton)
public class ApiProxy implements IProxy {
    private static final String TAG = "RestApiProxy";
    private static final String BASE_URL = " http://api.randomuser.me/";
    private static final String[] BLACKLIST = {"Access-Control",
            "Cache-Control",
            "Connection",
            "Content-Type",
            "Keep-Alive",
            "Pragma",
            "Server",
            "Vary",
            "X-Powered-By"};

    private Retrofit.Builder builder;
    private Endpoints _service;

    public ApiProxy() {
        final OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            okHttpClient.addNetworkInterceptor(new HttpLoggingInterceptor(message -> {

                for (String bString : BLACKLIST)
                    if (message.startsWith(bString))
                        return;

                Log.d(TAG, message);

            }).setLevel(HttpLoggingInterceptor.Level.BODY));
        }

        builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient.build())
                .addConverterFactory(GsonConverterFactory.create(GsonProvider.create()));

        _service = builder.build().create(Endpoints.class);
    }

    private static <T extends Parcelable> T execute(Call<T> call) throws HttpError {
        try {
            final Response<T> execute = call.execute();

            if (execute.isSuccessful()) {
                final T result = execute.body();
                return result;
            } else {
                if (BuildConfig.DEBUG)
                    Log.d("RestAPIError", execute.errorBody().string());

                throw  new HttpError(execute.code(), execute.errorBody().string());
            }

        } catch (IOException e) {
            throw new HttpError(-1, e.getMessage());
        }
    }

    @Override
    public UserList getUserList(final int limit) throws HttpError {
        return execute(_service.getUserList(limit));
    }

    interface Endpoints {
        @GET("/")
        public Call<UserList> getUserList(@Query("results")int limit);
    }
}
