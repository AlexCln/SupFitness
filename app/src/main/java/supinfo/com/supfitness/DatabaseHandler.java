package supinfo.com.supfitness;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int dbVersion = 1;
    private static final String dbName = "SupFitness.db";

    public static final String tableWeight = "sp_weight";

    public static final String columnId = "_id";
    public static final String columnWeight = "_weight";
    public static final String columnDate = "_date";

    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, dbName, factory, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + tableWeight + "(" +
                columnId + " INTEGER PRIMARY KEY AUTOINCREMENT" +
                columnWeight + " INTEGER " +
                columnDate + " DATETIME DEFAULT current_timestamp" +
                ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + tableWeight);
        onCreate(db);
    }

    //Add new row on database
    public void addWeight(Weight weight){
        ContentValues values = new ContentValues();
        values.put(columnWeight, weight.get_weight());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(tableWeight, null, values);
        db.close();
    }

    //Delete row from database
    public void deleteWeight(Weight weight){
        SQLiteDatabase db = getWritableDatabase();
        String query = "DELETE FROM " + tableWeight  + " WHERE "  + columnId + "'" + weight.get_id() + "'";
        db.execSQL(query);
    }

    public ArrayList<Weight> getListWeight(){
        ArrayList<Weight> list = new ArrayList<Weight>();

        Weight weight
    }
}
