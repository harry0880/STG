package hsquare.com.stg.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by harpreetsingh on 10/01/17.
 */

public class DbHandler extends SQLiteOpenHelper implements DbConstant {
    public DbHandler(Context context) {
        super(context, DBName, null, DBversion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Create_Table_DiseasesCatgory);
        db.execSQL(Create_Table_DiseasesHeader);
        db.execSQL(Create_Table_Diseases);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
