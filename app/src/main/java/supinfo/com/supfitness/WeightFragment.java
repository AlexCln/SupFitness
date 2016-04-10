package supinfo.com.supfitness;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.NumberPicker;

import java.util.ArrayList;


public class WeightFragment extends Fragment implements NumberPicker.OnValueChangeListener {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerWeightAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private static ArrayList<Weight> weightDataList = null;
    private Context context;
    private Activity activity;
    private static String LOG = "RecyclerViewAdapter";
    public static SharedPreferenceHandler sharedPreferenceHandler;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_weight, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.widgetWeightRecycler);
        FragmentActivity fragmentActivity = getActivity();
        layoutManager = new LinearLayoutManager(fragmentActivity);
        recyclerView.setLayoutManager(layoutManager);

        context = getContext();

        sharedPreferenceHandler = new SharedPreferenceHandler();
        weightDataList = new ArrayList<Weight>();
        weightDataList = sharedPreferenceHandler.getWeights(context);

        if (weightDataList == null){
            addingWeightDialog();
        }
       // else {
       //     if (weightDataList.size() == 0){
       //         addingWeightDialog();
       //     }
       //     ListView weightList = (ListView) view.findViewById(R.id.widgetWeightRecycler);
       //     if (weightDataList != null){
       //         recyclerWeightAdapter = new RecyclerWeightAdapter(context, weightDataList);
       //         weightList.setAdapter(recyclerWeightAdapter);
       //
       //
       //     }
       // }

        // Code to Add an item with default animation
        //((MyRecyclerViewAdapter) mAdapter).addItem(obj, index);

        // Code to remove an item with default animation
        //((MyRecyclerViewAdapter) mAdapter).deleteItem(index);


        recyclerWeightAdapter = new RecyclerWeightAdapter(getActivity(),weightDataList);
        recyclerView.setAdapter(recyclerWeightAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        FloatingActionButton floatingActionButton = (FloatingActionButton) view.findViewById(R.id.widgetFloatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addingWeightDialog();
              //int index = recyclerWeightAdapter.getItemCount();
              //AlertDialog.Builder dialog = new AlertDialog.Builder(context);

              //dialog.setTitle("Weight");
              //dialog.setMessage("Add your weight");

              //Weight weight = new Weight(34, "4/2/1", "45");
              //((RecyclerWeightAdapter) recyclerWeightAdapter).addWeight(weight, index);
            }
        });


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((RecyclerWeightAdapter) recyclerWeightAdapter).setOnItemClickListener(new RecyclerWeightAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                Log.i(LOG, "Click on item" + position);
            }
        });
    }

    public void addingWeightDialog(){
        final Dialog dialog = new Dialog(context);
        dialog.setTitle("Weight");
        dialog.setContentView(R.layout.dialog_weight);
        Button buttonCancel = (Button) dialog.findViewById(R.id.buttonCancel);
        Button buttonAdd = (Button) dialog.findViewById(R.id.buttonAdd);
        final NumberPicker weightPicker = (NumberPicker) dialog.findViewById(R.id.numberPickerWeight);
        weightPicker.setMaxValue(300);
        weightPicker.setMinValue(20);
        weightPicker.setWrapSelectorWheel(false);
        weightPicker.setOnValueChangedListener(this);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Weight weight = new Weight(weightPicker.getValue(),String.valueOf(new java.util.Date()), "30" );
                sharedPreferenceHandler.addWeight(context, weight);
                dialog.dismiss();
            }
        });
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        Log.i("value is",""+newVal);
    }
}
