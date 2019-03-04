package pl.localdeals.localdealsapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.Map;


public class DealAdapter extends RecyclerView.Adapter {

    private ArrayList<Deal> listOfDeals;
    private Map listOfLocals;
    private RecyclerView myRecyclerView;
    private Context context;

    private class MyViewHolder extends RecyclerView.ViewHolder{

        public ImageView iv_deal_photo;
        public TextView tv_deal_title;
        public TextView tv_deal_local;
        public TextView tv_deal_price;
        public TextView tv_deal_distance;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv_deal_photo = itemView.findViewById(R.id.deal_photo);
            tv_deal_title = itemView.findViewById(R.id.deal_name);
            tv_deal_local = itemView.findViewById(R.id.deal_local_name);
            tv_deal_price = itemView.findViewById(R.id.deal_price);
            tv_deal_distance = itemView.findViewById(R.id.deal_distance);
        }
    }

    public DealAdapter(ArrayList<Deal> listOfDeals, Map<Integer, Local> listOfLocals, RecyclerView myRecyclerView, Context context) {
        this.listOfDeals = listOfDeals;
        this.listOfLocals = listOfLocals;
        this.myRecyclerView = myRecyclerView;
        this.context = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_deal_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Deal single_deal = listOfDeals.get(position);
        Local local = (Local)listOfLocals.get(single_deal.get_local());
        Picasso.get().load(single_deal.get_jpgUrl()).fit().error(R.drawable.ic_launcher_background).into(((MyViewHolder) holder).iv_deal_photo);
        ((MyViewHolder) holder).tv_deal_distance.setText(local.get_distance());
        ((MyViewHolder) holder).tv_deal_price.setText(single_deal.get_price());
        ((MyViewHolder) holder).tv_deal_local.setText(local.get_name());
        ((MyViewHolder) holder).tv_deal_title.setText(single_deal.get_title());

    }

    @Override
    public int getItemCount() {
        return listOfDeals.size();
    }
}
