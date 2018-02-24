package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;


public class JsonUtils {

    public static JSONArray ingredientsJSONArray, alsoknownJSONArray;
    public static JSONObject sandwichObject;
    public static String name, placeOfOrigin, description, image;
    public static String[] ingredientsArray, alsoknownArray;
    public static Sandwich sandwich;


    public static Sandwich parseSandwichJson(String json) {

        try {
            sandwichObject = new JSONObject(json);

            //Retrieving Sandwich name..
            JSONObject nameOBJ = sandwichObject.getJSONObject("name");
            name = nameOBJ.getString("mainName");

            //Retrieving Sandwich available known names..
            alsoknownJSONArray = nameOBJ.getJSONArray("alsoKnownAs");
            if (alsoknownJSONArray.length() != 0) {
            alsoknownArray = new String[alsoknownJSONArray.length()];

                for (int i = 0; i < alsoknownJSONArray.length(); i++) {
                    alsoknownArray[i] = alsoknownJSONArray.getString(i);
                }

            }else {
                alsoknownArray = new String[]{"N/A"};
            }

            //Retrieving Sandwich Place Of Origin..
            placeOfOrigin = sandwichObject.getString("placeOfOrigin");
            if (placeOfOrigin.equals("")){
                placeOfOrigin = "N/A";
            }

            //Retrieving Sandwich Description..
            description = sandwichObject.getString("description");

            //Retrieving Sandwich Image..
            image = sandwichObject.getString("image");

            //Retrieving Sandwich Ingredients..
            ingredientsJSONArray = sandwichObject.getJSONArray("ingredients");
            ingredientsArray = new String[ingredientsJSONArray.length()];
            for (int i = 0; i < ingredientsJSONArray.length(); i++) {
                ingredientsArray[i] = ingredientsJSONArray.getString(i);


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


