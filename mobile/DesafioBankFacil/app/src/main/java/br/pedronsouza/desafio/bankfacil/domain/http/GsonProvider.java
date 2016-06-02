package br.pedronsouza.desafio.bankfacil.domain.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.pedronsouza.desafio.bankfacil.domain.http.serializers.UserListSerializer;
import br.pedronsouza.desafio.bankfacil.domain.http.serializers.UserSerializer;
import br.pedronsouza.desafio.bankfacil.domain.models.User;
import br.pedronsouza.desafio.bankfacil.domain.models.UserList;

/**
 * Created by pedrosouza on 6/2/16.
 */
public class GsonProvider {
    public static Gson create() {
        return new GsonBuilder()
                .registerTypeAdapter(User.class, new UserSerializer())
                .registerTypeAdapter(UserList.class, new UserListSerializer())
                .create();
    }
}
