package hsquare.com.stg;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;

import hsquare.com.stg.getset.Getset_ListView;
import hsquare.com.stg.utils.DbHandler;

/**
 * Created by Jain on 1/20/2017.
 */

public class Search_Fragment extends Fragment {
    private MaterialSearchView searchView;
    ArrayAdapter<Getset_ListView> adapter;
    ArrayList<Getset_ListView> arr_db;
    DbHandler dbh;
    ListView lv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View list = inflater.inflate(R.layout.search_frag, container, false);
        searchView = (MaterialSearchView)  getActivity().findViewById(R.id.search_view);
        searchView.setVoiceSearch(false);
        searchView.setCursorDrawable(R.drawable.custom_cursor);
        searchView.setEllipsize(true);
        lv=(ListView) list.findViewById(R.id.list_search);
        dbh=new DbHandler(getActivity());
        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {

            }

            @Override
            public void onSearchViewClosed() {

            }
        });

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText.equals("")) {
                    arr_db.clear();
                    adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, arr_db);
                    lv.setAdapter(adapter);
//                Search.this.adapter.getFilter().filter(cs);
                    //         String d1=arr_db.get()
                    adapter.notifyDataSetChanged();
                }
                else {
                    arr_db = dbh.searchWords(newText);
                    adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, arr_db);
                    lv.setAdapter(adapter);
//                Search.this.adapter.getFilter().filter(cs);
                    //         String d1=arr_db.get()
                    adapter.notifyDataSetChanged();
                }

                return false;
            }
        });


        return list;
    }
}
