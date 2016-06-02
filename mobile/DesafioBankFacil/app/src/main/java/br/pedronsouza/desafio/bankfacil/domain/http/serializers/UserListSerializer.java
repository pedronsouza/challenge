package br.pedronsouza.desafio.bankfacil.domain.http.serializers;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

import br.pedronsouza.desafio.bankfacil.domain.http.GsonProvider;
import br.pedronsouza.desafio.bankfacil.domain.models.User;
import br.pedronsouza.desafio.bankfacil.domain.models.UserList;

public class UserListSerializer implements JsonDeserializer<UserList> {
    @Override
    public UserList deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonArray data = json.getAsJsonObject().get("results").getAsJsonArray();
        UserList out = new UserList();

        try {
            if (data != null && data.size() > 0) {
                ArrayList<User> users = new ArrayList<>();

                for (int i = 0; i < data.size(); i++) {
                    User u = GsonProvider.create().fromJson(data.get(i).getAsJsonObject(), User.class);
                    users.add(u);
                }

                out.setUsers(users);
            }
        } catch (RuntimeException e) {
            //DO Nothing
        }

        return out;
    }
}
