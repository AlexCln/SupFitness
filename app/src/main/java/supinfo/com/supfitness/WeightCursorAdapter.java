package supinfo.com.supfitness;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by Alexis on 10/04/2016.
 */
public class WeightCursorAdapter extends CursorAdapter{
    public WeightCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.recycler_weight, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView textWeight = (TextView) view.findViewById(R.id.textWeightTitle);
        TextView textDate = (TextView) view.findViewById(R.id.textWeightDate);
        TextView textImc = (TextView) view.findViewById(R.id.textWeightImc);

        int weight = cursor.getInt(cursor.getColumnIndexOrThrow("weight"));
        String date = cursor.getString(cursor.getColumnIndexOrThrow("date"));
        String imc = cursor.getString(cursor.getColumnIndexOrThrow("imc"));

        textWeight.setText(String.valueOf(weight));
        textDate.setText(date);
        textImc.setText(imc);
    }
}
