package hsquare.com.stg.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import hsquare.com.stg.getset.Getset_ListView;

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
       // insertData();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertData()
    {
        SQLiteDatabase db= getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(C_Diseases_ID,"1001");
        cv.put(C_Diseases_Details,"Common Diseases");
        db.insert(Tbl_Diseases,null,cv);

        cv=new ContentValues();
        cv.put(C_DiseasesCategory_ID,"5001");
        cv.put(C_DiseasesCategory_Details,"Acute Fever");
        db.insert(Tbl_DiseasesCaegory,null,cv);

        cv=new ContentValues();
        cv.put(C_DiseasesCategory_ID,"5002");
        cv.put(C_DiseasesCategory_Details,"Fever in Children");
        db.insert(Tbl_DiseasesCaegory,null,cv);

        cv=new ContentValues();
        cv.put(C_DiseasesCategory_ID,"5003");
        cv.put(C_DiseasesCategory_Details,"Fever of unknown origin");
        db.insert(Tbl_DiseasesCaegory,null,cv);
    }


    public ArrayList<Getset_ListView> getInnerList()
    {
        SQLiteDatabase db=getReadableDatabase();
      // Cursor cr= db.rawQuery("select * from "+Tbl_DiseasesCaegory+" where "+C_DiseasesCategory_Details+" like %"+query+"%",null);
        Cursor cr= db.rawQuery("select * from "+Tbl_DiseasesCaegory,null);
        ArrayList<Getset_ListView> arr=new ArrayList<>();

        if(cr.getCount()>0)
        {
            cr.moveToFirst();

            do{
             arr.add(new Getset_ListView(cr.getString(0),cr.getString(1)));
            }while (cr.moveToNext());
        }
        cr.close();
        db.close();
        return arr;
    }
}
