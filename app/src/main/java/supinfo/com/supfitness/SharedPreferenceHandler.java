package supinfo.com.supfitness;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Alexis on 10/04/2016.
 */
public class SharedPreferenceHandler {
    public static final String PREFS_NAME = "SUPFITNESS_APP";
    public static final String WEIGHT = "Weight";

    public SharedPreferenceHandler() {
        super();
    }

    // This four methods are used for maintaining favorites.
    public void saveWeights(Context context, ArrayList<Weight> weights) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        editor = settings.edit();

        Gson gson = new Gson();
        String jsonWeights = gson.toJson(weights);

        editor.putString(WEIGHT, jsonWeights);

        editor.commit();
    }

    public void addWeight(Context context, Weight weight) {
        ArrayList<Weight> weights = getWeights(context);
        if (weights == null)
            weights = new ArrayList<Weight>();
        weights.add(weight);
        saveWeights(context, weights);
    }

    public void removeWeight(Context context, Weight weight) {
        ArrayList<Weight> weights = getWeights(context);
        if (weights != null) {
            weights.remove(weight);
            saveWeights(context, weights);
        }
    }

    public ArrayList<Weight> getWeights(Context context) {
        SharedPreferences settings;
        List<Weight> weights;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

        if (settings.contains(WEIGHT)) {
            String jsonWeights = settings.getString(WEIGHT, null);
            Gson gson = new Gson();
            Weight[] weightItems = gson.fromJson(jsonWeights,
                    Weight[].class);

            weights = Arrays.asList(weightItems);
            weights = new ArrayList<Weight>(weights);
        } else
            return null;

        return (ArrayList<Weight>) weights;
    }
}
