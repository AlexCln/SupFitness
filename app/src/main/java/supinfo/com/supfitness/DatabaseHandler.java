package supinfo.com.supfitness;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseHandler{

    public static final String dbSupFitness = "SupFitnessDb";
    public static final String tableWeight = "weights";
    public static final int dbVersion = 1;

    public static final String columnId = "_id";
    public static final String columnWeight = "weight";
    public static final String columnDate = "date";
    public static final String columnImc = "imc";


    private static final String scriptCreateDb =
            "CREATE TABLE IF NOT EXISTS " + tableWeight + "(" +
                    columnId + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    columnWeight + " INTEGER NOT NULL, " +
                    columnDate + " TEXT NOT NULL, " +
                    columnImc + " INTEGER " +
                    ");";

    private final Context context;

    private SQLiteOpenHelper helper;
    private SQLiteDatabase _db;

    public DatabaseHandler(Context c){
        this.context = c;
        helper = new WeightHelper(c);
    }

    public class WeightHelper extends SQLiteOpenHelper {
        public WeightHelper(Context context){
            super(context, dbSupFitness, null, dbVersion);
        }

        @Override
        public void onCreate(SQLiteDatabase _db) {
            _db.execSQL(scriptCreateDb);
        }

        @Override
        public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion) {
            _db.execSQL("DROP TABLE IF EXISTS " + tableWeight);
            onCreate(_db);
        }
    }

    public DatabaseHandler open(){
        _db = helper.getWritableDatabase();
        return this;
    }

    public void close(){
        helper.close();
    }

    public List<Weight> getListWeight(){
        List<Weight> list = new ArrayList<Weight>();

        String query = "SELECT * FROM " + tableWeight;

        Cursor cursor = _db.rawQuery(query, null);

        if (cursor.moveToFirst()){
            do{
                Weight weight = new Weight();
                weight.set_id(Integer.parseInt(cursor.getString(0)));
                weight.set_weight(Integer.parseInt(cursor.getString(1)));
                weight.set_date(cursor.getString(2));
                weight.set_imc(Integer.parseInt(cursor.getString(3)));

                list.add(weight);
            }while (cursor.moveToNext());
        }
        _db.close();
        return list;
    }

    public Cursor getCursor(){
        Cursor c = _db.query(tableWeight, new String[]{columnId, columnWeight, columnDate, columnImc}, null, null, null, null, null);
        if(c!=null){
            c.moveToFirst();
        }
        return c;
    }

  //public void addWeight(Weight weight){
  //    SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
  //    String dateString = date.format(new Date());
  //    ContentValues values = new ContentValues();

  //    values.put(columnWeight, weight.get_weight());
  //    values.put(columnDate, dateString);
  //    values.put(columnImc, weight.get_imc());
  //    Log.d("Put add to value ", "");

  //    _db.insert(tableWeight, null, values);
  //    _db.close();
  //}


    public long addWeight(int weight){
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = date.format(new Date());
        ContentValues values = new ContentValues();

        values.put(columnWeight, weight);
        values.put(columnDate, dateString);
        values.put(columnImc, 23);
        Log.d("Put add to value", "");

        return _db.insert(dbSupFitness, null, values);
    }

    public void deleteWeight(Weight weight){
        _db.delete(tableWeight, columnId + " = " + weight.get_id(),new String[] {String.valueOf(weight.get_id())});
        _db.close();
    }
}
