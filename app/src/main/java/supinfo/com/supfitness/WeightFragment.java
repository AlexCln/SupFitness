package supinfo.com.supfitness;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class WeightFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Voir : http://stackoverflow.com/questions/22512833/create-listview-in-fragment-android
        // Mettre la fonction pour récupérer les poids dans le Database Handler

        return inflater.inflate(R.layout.fragment_weight, container,false);
    }
}
