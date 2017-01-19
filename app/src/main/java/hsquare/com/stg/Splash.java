package hsquare.com.stg;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.File;

import cn.pedant.SweetAlert.SweetAlertDialog;
import hsquare.com.stg.utils.DbHandler;

public class Splash extends AppCompatActivity {
DbHandler dbh;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        context=this;
        dbh=new DbHandler(Splash.this);
        if(!doesDatabaseExist(getApplicationContext(),dbh.DBName))
        {
            new LoadData().execute();
        }
        else
        {
            startActivity(new Intent(Splash.this,Main2Activity.class));
            finish();
        }

    }
    private static boolean doesDatabaseExist(Context context, String dbName) {
        File dbFile = context.getDatabasePath(dbName);
        return dbFile.exists();
    }

    private class LoadData extends AsyncTask<Void,Void,String>
    {

        @Override
        protected String doInBackground(Void... params) {
            return dbh.Load_Master_tables();

        }

        @Override
        protected void onPostExecute(String s) {
            if(s.equals("Error"))
            {
                new SweetAlertDialog(context,SweetAlertDialog.ERROR_TYPE).setTitleText("No Internet Connection").show();
            }
            else if(s.equals("ErrorServer"))
            {
                new SweetAlertDialog(context,SweetAlertDialog.ERROR_TYPE).setTitleText("Unable to connect with Server!!!").show();
            }
            else {
                startActivity(new Intent(Splash.this, Main2Activity.class));
                finish();
            }
            super.onPostExecute(s);
        }
    }
}
