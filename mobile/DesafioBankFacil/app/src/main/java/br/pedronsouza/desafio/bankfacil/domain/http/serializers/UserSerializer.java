package br.pedronsouza.desafio.bankfacil.domain.http.serializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Locale;

import br.pedronsouza.desafio.bankfacil.domain.models.User;

public class UserSerializer implements JsonDeserializer<User> {

    @Override
    public User deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        User out = new User();
        JsonObject data = json.getAsJsonObject();

        try {
            if (data != null) {
                String firstName = data.get("name").getAsJsonObject().get("first").getAsString();
                String lastName = data.get("name").getAsJsonObject().get("last").getAsString();
                String thumb = data.get("picture").getAsJsonObject().get("thumbnail").getAsString();
                String name = String.format(Locale.getDefault(), "%s %s",  firstName, lastName);

                out.setName(name);
                out.setThumbImgUrl(thumb);
            }
        } catch (RuntimeException e) {
            // DO NOTHING
        }


        return out;
    }
}
