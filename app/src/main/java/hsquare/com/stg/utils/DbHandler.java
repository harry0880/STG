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
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.List;

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

    String LoadGetStock = "GetStock";
    String SoapLinkGetStock = "http://tempuri.org/GetStock";

   // final String URL = "http://android.dpmuhry.gov.in";
   final String URL = "http://192.168.8.100/GetStockDetails.asmx";

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

    public ArrayList<Getset_ListView> getSubInnerList(Disease_Fragments obj) {
        SQLiteDatabase db = getReadableDatabase();
        // Cursor cr= db.rawQuery("select * from "+Tbl_DiseasesCaegory+" where "+C_DiseasesCategory_Details+" like %"+query+"%",null);
        Cursor cr = db.rawQuery("select DISTINCT "+C_SubDiseaseid+","+C_SubDiseaseDetail+" from " + Tbl_DiseasesContainer+" where "+C_Diseases_ID+"="+obj.getFragment_id(), null);
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

    public ArrayList<Getset_ListView> searchWords(String searchWord){
        ArrayList<Getset_ListView> mItems = new ArrayList<Getset_ListView>();
        SQLiteDatabase db= getReadableDatabase();
        String query = "Select distinct "+C_DiseasesCategory_ID+","+C_DiseasesCategory_Details+ " from " +Tbl_DiseasesContainer+" where "+C_DiseasesCategory_Details+" like " + "'%" + searchWord + "%'";
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()) {
            do {
                String id = cursor.getString(cursor.getColumnIndexOrThrow(C_DiseasesCategory_ID));
                String word = cursor.getString(cursor.getColumnIndexOrThrow(C_DiseasesCategory_Details));
                mItems.add(new Getset_ListView(id, word));
            }

            while (cursor.moveToNext());
        }


        query = "Select distinct " + C_Diseases_ID +","+ C_Diseases_Details + " from " + Tbl_DiseasesContainer + " where " + C_Diseases_Details + " like " + "'%" + searchWord + "%'";
        cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(cursor.getColumnIndexOrThrow(C_Diseases_ID));
                String word = cursor.getString(cursor.getColumnIndexOrThrow(C_Diseases_Details));
                mItems.add(new Getset_ListView(id, word));
            }
            while (cursor.moveToNext());
        }
        query = "Select distinct " + C_SubDiseaseid + "," + C_SubDiseaseDetail + " from " + Tbl_DiseasesContainer + " where " + C_SubDiseaseDetail + " like " + "'%" + searchWord + "%'";
        cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {

                String id = cursor.getString(cursor.getColumnIndexOrThrow(C_SubDiseaseid));
                String word = cursor.getString(cursor.getColumnIndexOrThrow(C_SubDiseaseDetail));
                mItems.add(new Getset_ListView(id, word));
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return mItems;
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

    public String getHTMLfromDisease(Disease_Fragments getset) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c=db.rawQuery("select "+C_diseaseheaddetail_content+" from "+Tbl_DiseasesContainer+" where "+C_Diseases_ID+" = "+getset.getFragment_id(),null);
        if(c.getCount()>0)
        {
            c.moveToFirst();
            do{
                return c.getString(0);
            }while (c.moveToNext());
        }
        return "error";
    }

    public ArrayList<CardGetSet> GetStock(String Drugid) {
        String res = null;
        ArrayList<CardGetSet> data=new ArrayList<>();
        SoapObject request = new SoapObject(NameSpace, LoadGetStock);

        PropertyInfo pi=new PropertyInfo();
        pi.setName("instName");
        pi.setType(String.class);
        pi.setValue("000525");
        request.addProperty(pi);

        pi=new PropertyInfo();
        pi.setName("Drugcode");
        pi.setType(String.class);
        pi.setValue(Drugid);
        request.addProperty(pi);

        SoapSerializationEnvelope envolpe = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envolpe.dotNet = true;
        envolpe.setOutputSoapObject(request);
        HttpTransportSE androidHTTP = new HttpTransportSE(URL);

        try {
            androidHTTP.call(SoapLinkGetStock, envolpe);
            SoapPrimitive response = (SoapPrimitive) envolpe.getResponse();
            res = response.toString();
            //System.out.println(res);
        } catch (Exception e) {
            e.printStackTrace();
            data.clear();
            data=null;
            return data;
        }
        int lengthJsonArr;
        try {
            res = "{ Stock :" + res + " }";
            jsonResponse = new JSONObject(res);
            JSONArray jsonMainNode = jsonResponse.optJSONArray("Stock");
            lengthJsonArr = jsonMainNode.length();
            for (int j = 0; j < lengthJsonArr; j++) {
                JSONObject jsonChildNode = jsonMainNode.getJSONObject(j);
              data.add( new CardGetSet("Stock: "+jsonChildNode.optString("ClosingStock").toString(),"Institute Name: "+jsonChildNode.optString("Institute").toString()));
            }
        }
        catch (Exception e)
        {
            data.clear();
            data=null;
            return data;
        }
return data;
    }


    public String getHTMLfromSubDisease(Disease_Fragments getset) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c=db.rawQuery("select "+C_diseaseheaddetail_content+" from "+Tbl_DiseasesContainer+" where "+C_SubDiseaseid+" = "+getset.getFragment_id(),null);
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
