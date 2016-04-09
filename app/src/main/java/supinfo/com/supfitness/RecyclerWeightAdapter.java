package supinfo.com.supfitness;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

// Reste du tuto : http://www.truiton.com/2015/02/android-recyclerview-tutorial/

public class RecyclerWeightAdapter extends  RecyclerView
        .Adapter<RecyclerWeightAdapter
        .WeightObjectHolder>  {


    private static String LOG_TAG = "RecyclerWeightAdapter";
    private ArrayList<Weight> weightArrayList;
    private static ClickListener clickListener;

    @Override
    public WeightObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(WeightObjectHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class WeightObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView weight;
        TextView dateTime;
        TextView imc;

        public WeightObjectHolder(View view){
            super(view);
            weight = (TextView) view.findViewById(R.id.textWeightTitle);
            dateTime = (TextView) view.findViewById(R.id.textWeightDate);
            imc = (TextView) view.findViewById(R.id.textWeightImc);
            view.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            clickListener.onItemClick(getPosition(), view);
        }
    }

    public class DataObjectHolder {
    }

    public interface ClickListener{
        public void onItemClick(int position, View view);
    }
}
