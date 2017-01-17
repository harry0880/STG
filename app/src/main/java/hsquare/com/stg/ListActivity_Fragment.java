package hsquare.com.stg;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import java.util.ArrayList;

import hsquare.com.stg.getset.Disease_Fragments;
import hsquare.com.stg.getset.Getset_ListView;
import hsquare.com.stg.utils.DbHandler;

/**
 * Created by harpreetsingh on 28/12/16.
 */

public class ListActivity_Fragment extends Fragment {

    ListView lv;
    String[] items;
    static Disease_Fragments getset=null;
    ArrayList<Getset_ListView> listItems;
    DbHandler dbh;
    static  int cnt=0;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View list = inflater.inflate(R.layout.list_frag, container, false);

        init(list);
  //      dbh.insertData();
        if(getset.getFragment_id()==null) {

            listItems=dbh.getMainList();
        }
        else if(getset.getFragment_id().substring(0,1).equals("1"))
        {
            listItems=dbh.getInnerList(getset);
        }
        final ArrayAdapter<Getset_ListView> adapter=new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,listItems);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        cnt++;
              if(cnt==3)
              {

                  startActivity(new Intent(getActivity(),displayHTML.class));
              }
    else
                {

                   Getset_ListView ob=(Getset_ListView) adapter.getItem(position);
                    getset.setFragment_id(ob.getId());
                Fragment fragment=new ListActivity_Fragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.frame_container, fragment).commit();
                }



            }
        });

        lv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getset.setFragment_id(((Getset_ListView) lv.getSelectedItem()).getId());
                Fragment fragment=new ListActivity_Fragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.frame_container, fragment).commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return list;
    }

    private void init(View list)
    {
        getset=new Disease_Fragments();
        dbh=new DbHandler(getActivity());
        lv = (ListView) list.findViewById(R.id.lv);
        listItems=new ArrayList<>();
    }

}
