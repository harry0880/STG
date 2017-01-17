package hsquare.com.stg.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.Html;
import android.text.Spanned;

import org.json.JSONArray;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;

import hsquare.com.stg.getset.Disease_Fragments;
import hsquare.com.stg.getset.Getset_ListView;



/**
 * Created by harpreetsingh on 10/01/17.
 */

public class DbHandler extends SQLiteOpenHelper implements DbConstant {
    public DbHandler(Context context) {
        super(context, DBName, null, DBversion);
    }

    final String NameSpace = "http://tempuri.org/";
    String LoadMasterMathod = "master";
    String SoapLinkMaster = "http://tempuri.org/master";
    final String URL = "http://android.dpmuhry.gov.in";

    JSONObject jsonResponse;

    @Override
    public void onCreate(SQLiteDatabase db) {
/*        db.execSQL(Create_Table_DiseasesCatgory);
        db.execSQL(Create_Table_DiseasesHeader);
        db.execSQL(Create_Table_Diseases);*/
        db.execSQL(Create_Table_Diseasecontainer);
        // insertData();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public ArrayList<Getset_ListView> getMainList() {
        SQLiteDatabase db = getReadableDatabase();
        // Cursor cr= db.rawQuery("select * from "+Tbl_DiseasesCaegory+" where "+C_DiseasesCategory_Details+" like %"+query+"%",null);
        Cursor cr = db.rawQuery("select DISTINCT "+C_DiseasesCategory_ID+","+C_DiseasesCategory_Details+" from " + Tbl_DiseasesContainer, null);
        ArrayList<Getset_ListView> arr = new ArrayList<>();

        if (cr.getCount() > 0) {
            cr.moveToFirst();

            do {
                arr.add(new Getset_ListView(cr.getString(0), cr.getString(1)));
            } while (cr.moveToNext());
        }
        cr.close();
        db.close();
        return arr;
    }

    public ArrayList<Getset_ListView> getInnerList(Disease_Fragments obj) {
        SQLiteDatabase db = getReadableDatabase();
        // Cursor cr= db.rawQuery("select * from "+Tbl_DiseasesCaegory+" where "+C_DiseasesCategory_Details+" like %"+query+"%",null);
        Cursor cr = db.rawQuery("select DISTINCT "+C_Diseases_ID+","+C_Diseases_Details+" from " + Tbl_DiseasesContainer+" where "+C_DiseasesCategory_ID+"="+obj.getFragment_id(), null);
        ArrayList<Getset_ListView> arr = new ArrayList<>();

        if (cr.getCount() > 0) {
            cr.moveToFirst();

            do {
                arr.add(new Getset_ListView(cr.getString(0), cr.getString(1)));
            } while (cr.moveToNext());
        }
        cr.close();
        db.close();
        return arr;
    }


    public String Load_Master_tables() {
        String res = null;
        SoapObject request = new SoapObject(NameSpace, LoadMasterMathod);
        SoapSerializationEnvelope envolpe = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envolpe.dotNet = true;
        envolpe.setOutputSoapObject(request);
        HttpTransportSE androidHTTP = new HttpTransportSE(URL);

        try {
            androidHTTP.call(SoapLinkMaster, envolpe);
            SoapPrimitive response = (SoapPrimitive) envolpe.getResponse();
            res = response.toString();
            //System.out.println(res);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }
        int lengthJsonArr;
        try {
            res = "{ master :" + res + " }";
            jsonResponse = new JSONObject(res);
            JSONArray jsonMainNode = jsonResponse.optJSONArray("master");
            lengthJsonArr = jsonMainNode.length();
            for (int j = 0; j < lengthJsonArr; j++) {

                ContentValues values = new ContentValues();
                JSONObject jsonChildNode = jsonMainNode.getJSONObject(j);
                values.put(C_DiseasesCategory_ID, jsonChildNode.optString("category_id").toString());
                values.put(C_DiseasesCategory_Details, jsonChildNode.optString("category_detail").toString());
                values.put(C_Diseases_ID, jsonChildNode.optString("Diseaseid").toString());
                values.put(C_Diseases_Details, jsonChildNode.optString("Diseasedetail").toString());
                values.put(C_SubDiseaseid, jsonChildNode.optString("SubDiseaseid").toString());
                values.put(C_SubDiseaseDetail, jsonChildNode.optString("SubDiseasecategory_detail").toString());
                Spanned HTMLData= Html.fromHtml(jsonChildNode.optString("Contentdetail").toString());
                String s=HTMLData.toString();
                values.put(C_diseaseheaddetail_content,s );
                SQLiteDatabase writeableDB = getWritableDatabase();
                writeableDB.insert(Tbl_DiseasesContainer, null, values);
                writeableDB.close();

            }
        } catch (Exception e) {
            e.printStackTrace();
            return "ErrorServer";
        }
        return "Success";
    }

    public String getHTML() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c=db.rawQuery("select "+C_diseaseheaddetail_content+" from "+Tbl_DiseasesContainer,null);
        if(c.getCount()>0)
        {
            c.moveToFirst();
            do{
                return c.getString(0);
            }while (c.moveToNext());
        }
        return "error";
    }

}
