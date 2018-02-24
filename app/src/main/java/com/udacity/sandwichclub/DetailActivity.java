package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    public static TextView alsoKnownAs, placeOfOrigin, description, ingredient;
    public static Sandwich sandwich;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        placeOfOrigin = findViewById(R.id.origin_tv);
        alsoKnownAs = findViewById(R.id.also_known_tv);
        ingredient = findViewById(R.id.ingredients_tv);
        description = findViewById(R.id.description_tv);

        sandwich = new Sandwich();


        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI();
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {


        placeOfOrigin.setText(sandwich.getPlaceOfOrigin());

        //Append List into lisView
        for (int i = 0; i < sandwich.getAlsoKnownAs().size(); i++) {
            if (sandwich.getAlsoKnownAs().size() - i-1 > 1) {
                alsoKnownAs.setText(sandwich.getAlsoKnownAs().get(i) + ", ");
            } else {
                alsoKnownAs.setText(sandwich.getAlsoKnownAs().get(i) + ".");
            }
        }

        for (int i = 0; i < sandwich.getIngredients().size(); i++) {
            if (sandwich.getIngredients().size() - i > 1) {
                ingredient.append(sandwich.getIngredients().get(i) + ", ");
            } else {
                ingredient.append(sandwich.getIngredients().get(i) + ".");
            }
        }

        description.setText(sandwich.getDescription());
    }
}
