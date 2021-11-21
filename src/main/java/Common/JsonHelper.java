package Common;

import Common.Recipe.Recipe;
import Common.Recipe.RecipeFilter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonHelper {

    public String objectToJson(Object object){
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        return gson.toJson(object);
    }

    public Recipe jsonStringToRecipe(String jsonString){
        return new Gson().fromJson(jsonString, Recipe.class);
    }

    public String recipeFilterToJson (RecipeFilter recipeFilter){
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        return gson.toJson(recipeFilter);
    }
}