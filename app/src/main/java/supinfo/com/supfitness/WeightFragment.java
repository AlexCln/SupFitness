package supinfo.com.supfitness;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class WeightFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        View rootView = inflater.inflate(R.layout.fragment_weight, container,false);

        ArrayList<Weight> listWeight =
        // Voir : http://stackoverflow.com/questions/22512833/create-listview-in-fragment-android
        // Mettre la fonction pour récupérer les poids dans le Database Handler

        return
    }
}
