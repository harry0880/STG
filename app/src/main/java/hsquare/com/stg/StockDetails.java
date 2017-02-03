package hsquare.com.stg;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;
import hsquare.com.stg.utils.CardGetSet;
import hsquare.com.stg.utils.DbHandler;
import hsquare.com.stg.utils.RVAdapter;

public class StockDetails extends AppCompatActivity {
DbHandler dbh;
    RVAdapter rv;
    RecyclerView lvItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_details);
        Bundle bundle=getIntent().getExtras();
        lvItems = (RecyclerView) findViewById(R.id.rv);
        dbh=new DbHandler(StockDetails.this);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        lvItems.setLayoutManager(llm);
        lvItems.setHasFixedSize(true);

    if(isNetworkAvailable()){
        new GetStockDetails().execute(bundle.getString("Drug"));
    }
  else
    {
        SweetAlertDialog sweetAlertDialog=  new SweetAlertDialog(StockDetails.this,SweetAlertDialog.ERROR_TYPE).setTitleText("Network Error").setCustomImage(R.drawable.error);
        sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                finish();
            }
        });
        sweetAlertDialog.show();
    }

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private class GetStockDetails extends AsyncTask<String,Void,ArrayList<CardGetSet>>
    {
        ProgressDialog progressDialog=new ProgressDialog(StockDetails.this);

        @Override
        protected void onPreExecute() {
          progressDialog.setMessage("Please Wait!!");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
            super.onPreExecute();
        }

        @Override
        protected ArrayList<CardGetSet> doInBackground(String... params) {
//initialize RVAdapter

            return dbh.GetStock(params[0]);
        }



        @Override
        protected void onPostExecute(ArrayList<CardGetSet> s) {
            progressDialog.dismiss();
            if(s==null)
            {
              SweetAlertDialog sweetAlertDialog=  new SweetAlertDialog(StockDetails.this,SweetAlertDialog.ERROR_TYPE).setTitleText("Error");
                sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        finish();
                    }
                });
                sweetAlertDialog.show();
            }
            else
            {
              rv=new RVAdapter(s);
                lvItems.setAdapter(rv);
            }
            super.onPostExecute(s);
        }
    }
}
