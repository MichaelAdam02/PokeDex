package at.michaeladam.pokemonviewer.Businesslogic;

import at.michaeladam.pokemonviewer.DataLayer.Pokemon;
import at.michaeladam.pokemonviewer.PokemonHolder;
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
 * Singletonclass
 * @author Michael ADAM
 */
public class PokemonApiExtractor {

    private static Client client;
    private static final GsonBuilder BUILDER = new GsonBuilder(); 

    public static PokemonApiExtractor getInstance() {
        return PokemonApiExtractorHolder.INSTANCE;
    }

    private static class PokemonApiExtractorHolder {

        private static final PokemonApiExtractor INSTANCE = new PokemonApiExtractor();
    }

    private PokemonApiExtractor() {
        client = ClientBuilder.newClient();
    }

    /**
     * @param id ID of the pokemon wich will be looked up on the APIs
     * @return the pokemon of the ID wich will be extracted of the PKMN Url
     */
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

    /**
     * Temporary Function which loads the JSON of the API and translates into a
     * JSON Object
     *
     * @param uri Link to the api
     * @return JsonObject of the api
     */
    private static JsonObject getJson(String uri) {
        WebTarget target = client.target(uri);
        Response result = target.request().accept(MediaType.APPLICATION_JSON).get();

        String res = result.readEntity(String.class);

        return new Gson().fromJson(res, JsonObject.class);
    }
}
