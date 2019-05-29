package ets.udacity.sandwichclub.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import ets.udacity.sandwichclub.model.Sandwich;


public class JsonUtils {

    public static JSONArray ingredientsJSONArray, alsoknownJSONArray;
    public static JSONObject sandwichObject;
    public static String name, placeOfOrigin, description, image;
    public static String[] ingredientsArray, alsoknownArray;
    public static Sandwich sandwich;
    public static final String NAME_KEY = "name";
    public static final String MAINNAME_KEY = "mainName";
    public static final String ALSOKNOWNAS_KEY = "alsoKnownAs";
    public static final String PLACEOFORIGIN_KEY = "placeOfOrigin";
    public static final String DESCRIPTION_KEY = "description";
    public static final String IMAGE_KEY = "image";
    public static final String INGREDIENTS_KEY = "ingredients";






    public static Sandwich parseSandwichJson(String json) {

        try {
            sandwichObject = new JSONObject(json);

            //Retrieving Sandwich name..
            JSONObject nameOBJ = sandwichObject.getJSONObject(NAME_KEY);
            name = nameOBJ.optString(MAINNAME_KEY);

            //Retrieving Sandwich available known names..
            alsoknownJSONArray = nameOBJ.getJSONArray(ALSOKNOWNAS_KEY);
            if (alsoknownJSONArray.length() != 0) {
            alsoknownArray = new String[alsoknownJSONArray.length()];

                for (int i = 0; i < alsoknownJSONArray.length(); i++) {
                    alsoknownArray[i] = alsoknownJSONArray.optString(i);
                }

            }else {
                alsoknownArray = new String[]{"N/A"};
            }

            //Retrieving Sandwich Place Of Origin..
            placeOfOrigin = sandwichObject.optString(PLACEOFORIGIN_KEY);
            if (placeOfOrigin.equals("")){
                placeOfOrigin = "N/A";
            }

            //Retrieving Sandwich Description..
            description = sandwichObject.optString(DESCRIPTION_KEY);

            //Retrieving Sandwich Image..
            image = sandwichObject.optString(IMAGE_KEY);

            //Retrieving Sandwich Ingredients..
            ingredientsJSONArray = sandwichObject.getJSONArray(INGREDIENTS_KEY);
            ingredientsArray = new String[ingredientsJSONArray.length()];
            for (int i = 0; i < ingredientsJSONArray.length(); i++) {
                ingredientsArray[i] = ingredientsJSONArray.optString(i);


            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


        //Pushing JSON values to Sandwich model..
        sandwich = new Sandwich();

        sandwich.setMainName(name);
        sandwich.setAlsoKnownAs(Arrays.asList(alsoknownArray));
        sandwich.setPlaceOfOrigin(placeOfOrigin);
        sandwich.setDescription(description);
        sandwich.setImage(image);
        sandwich.setIngredients(Arrays.asList(ingredientsArray));




        return sandwich;

    }


}


