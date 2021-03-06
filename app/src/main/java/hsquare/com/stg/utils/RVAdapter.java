package hsquare.com.stg.utils;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import hsquare.com.stg.R;

/**
 * Created by harpreetsingh on 02/02/17.
 */

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder> {
    List<CardGetSet> data;

    public RVAdapter(List<CardGetSet> data) {
        this.data = data;
    }
    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview, parent, false);
        return    new PersonViewHolder(v);

    }
    @Override
    public void onBindViewHolder(RVAdapter.PersonViewHolder holder, int position) {
        holder.Institute.setText(data.get(position).Instname);
        holder.StockQty.setText(data.get(position).Stock);

    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public  class PersonViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView Institute;
        TextView StockQty;



        PersonViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            Institute = (TextView) itemView.findViewById(R.id.tvInstitute);
            StockQty = (TextView) itemView.findViewById(R.id.tvStock);
        }
    }
    }
