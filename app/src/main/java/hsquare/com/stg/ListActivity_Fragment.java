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

/**
 * Created by harpreetsingh on 28/12/16.
 */

public class ListActivity_Fragment extends Fragment {

    ListView lv;
    String[] items;
    Disease_Fragments getset;
    ArrayList<Getset_ListView> listItems;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View list = inflater.inflate(R.layout.list_frag, container, false);

        init(list);
        if(getset.getFragment_id()==null) {
            items = getResources().getStringArray(R.array.listitems);
            listItems=new ArrayList<>();
            int cnt=0;
            for (String arr:items) {
            listItems.add(new Getset_ListView(cnt,items[cnt]));
                cnt++;
            }
        }
        else
        {
            items=getResources().getStringArray(R.array.listItem2);
        }
        ArrayAdapter<String> adapter=new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,items);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

              if(getset!=null)
              {
                  startActivity(new Intent(getActivity(),displayHTML.class));
              }
                getset.setFragment_id(parent.getItemAtPosition(position).toString());
                Fragment fragment=new ListActivity_Fragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.frame_container, fragment).commit();

            }
        });
        return list;
    }

    private void init(View list)
    {
        getset=new Disease_Fragments();
        lv = (ListView) list.findViewById(R.id.lv);
    }
}
