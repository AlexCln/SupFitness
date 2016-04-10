package supinfo.com.supfitness;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.SimpleCursorAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WeightFragment extends Fragment implements NumberPicker.OnValueChangeListener {
    public DatabaseHandler db;
    public ListView weightListView;
    public static List<Weight> weightList;
    public NumberPicker numberPicker;
    public static Dialog dialog;
    public Context context;
    public int _weight;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_weight, container, false);
        weightListView = (ListView) view.findViewById(R.id.listWeightView);


        FloatingActionButton floatingActionButton = (FloatingActionButton) view.findViewById(R.id.widgetFloatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addingWeightDialog();
            }
        });

        openDb();

        LoadWeight();
        return view;
    }

    public void LoadWeight() {
       Cursor cursor = db.getCursor();
     String[] field = new String[]{
             DatabaseHandler.columnId,
             DatabaseHandler.columnWeight,
             DatabaseHandler.columnDate,
             DatabaseHandler.columnImc,
     };
     int[] to = new int[]{R.id.textWeightTitle, R.id.textWeightDate, R.id.textWeightImc};
     SimpleCursorAdapter cursorAdapter;
      cursorAdapter = new SimpleCursorAdapter (getContext(), R.layout.recycler_weight, cursor, field, to, 0);

       weightListView = (ListView) weightListView.findViewById(R.id.listWeightView);
        weightListView.setAdapter(cursorAdapter);
    }

  public void addingWeightDialog() {
      final Dialog dialog = new Dialog(getActivity());
      dialog.setTitle("Weight");
      dialog.setContentView(R.layout.dialog_weight);
      Button buttonCancel = (Button) dialog.findViewById(R.id.buttonCancel);
      Button buttonAdd = (Button) dialog.findViewById(R.id.buttonAdd);
      //final NumberPicker numberPicker = (NumberPicker) dialog.findViewById(R.id.numberPickerWeight);
      //numberPicker.setMaxValue(300);
      //numberPicker.setMinValue(20);
      //numberPicker.setWrapSelectorWheel(false);
      //numberPicker.setOnValueChangedListener(this);
      final EditText weightText = (EditText) dialog.findViewById(R.id.textEditWeight);
      weightText.setRawInputType(Configuration.KEYBOARD_12KEY);
      buttonAdd.setOnClickListener(new View.OnClickListener()
      {
          @Override
          public void onClick(View v) {
              db.addWeight(Integer.parseInt(weightText.getText().toString()));
              LoadWeight();
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

    }
    private void openDb(){
        db = new DatabaseHandler(getActivity());
        db.open();
    }

}
