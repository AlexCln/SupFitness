package supinfo.com.supfitness;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;

public class RecyclerWeightAdapter extends  RecyclerView
        .Adapter<RecyclerWeightAdapter
        .WeightObjectHolder>  {


    private static String LOG_TAG = "RecyclerWeightAdapter";
    private Context context;
    private ArrayList<Weight> weightArrayList;
    private static ClickListener clickListener;

    public RecyclerWeightAdapter (Context c, ArrayList<Weight> weights){
        this.context = c;
        this.weightArrayList = new ArrayList<>(weights);
    }

    @Override
    public WeightObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_weight, parent, false);
        WeightObjectHolder weightObjectHolder = new WeightObjectHolder(view);

        return weightObjectHolder;
    }

    @Override
    public void onBindViewHolder(WeightObjectHolder holder, int position) {
        holder.weight.setText(String.valueOf(weightArrayList.get(position).get_weight()));
        holder.dateTime.setText(String.valueOf(weightArrayList.get(position).get_date()));
        holder.imc.setText(String.valueOf(weightArrayList.get(position).get_imc()));
    }

    @Override
    public int getItemCount() {
        return weightArrayList.size();
    }

    public void addWeight(Weight weight, int index){
        weightArrayList.add(weight);
        notifyItemChanged(index);
    }


    public void deleteWeight(int index){
        weightArrayList.remove(index);
        notifyItemRemoved(index);
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

    public void setOnItemClickListener(ClickListener listener){
        this.clickListener = listener;
    }

    public interface ClickListener{
        void onItemClick(int position, View view);
    }
}
