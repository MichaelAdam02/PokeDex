package at.michaeladam.pokemonviewer.Businesslogic;

import at.michaeladam.pokemonviewer.DataLayer.Pokemon;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Michael ADAM
 */
public class API_Reader {

    private static Client client;
    private static final GsonBuilder BUILDER = new GsonBuilder();
    private static final Gson GSON = BUILDER.create();

    public API_Reader() {

        client = ClientBuilder.newClient();

    }

    public Pokemon getPokemon(int id) {
        if (id < 1 || id > PokeConfig.POKECOUNT) {
            return null;
        }

        JsonObject result = getJson(PokeConfig.PKMN_URL.replace("%id%", "" + id));
        JsonElement getTypes = result.get("types");
        JsonElement getForms = result.get("forms");
        String name = getForms.getAsJsonArray().get(0).getAsJsonObject().get("name").getAsString();
        String spriteApi = getForms.getAsJsonArray().get(0).getAsJsonObject().get("url").getAsString();

        JsonObject spriteResult = getJson(spriteApi);
        String front_default = spriteResult.get("sprites").getAsJsonObject().get("front_default").getAsString();
        String front_shiny = spriteResult.get("sprites").getAsJsonObject().get("front_shiny").getAsString();
        String back_default = spriteResult.get("sprites").getAsJsonObject().get("back_default").getAsString();
        String back_shiny = spriteResult.get("sprites").getAsJsonObject().get("back_shiny").getAsString();

        Pokemon returnal = new Pokemon(id, name, front_default, front_shiny, back_default, back_shiny);
        returnal.addTypes(getTypes.getAsJsonArray());
        return returnal;
    }

    private static JsonObject getJson(String uri) {
        WebTarget target = client.target(uri);
        Response result = target.request().accept(MediaType.APPLICATION_JSON).get();

        String res = result.readEntity(String.class);

        return new Gson().fromJson(res, JsonObject.class);
    }
}
