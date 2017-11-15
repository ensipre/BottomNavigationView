package com.example.enspire.bottomnavigationview;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.enspire.bottomnavigationview.model.OrgModel;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by nicenicenice on 11/10/2017.
 */

public class OrgCardAdapter extends RecyclerView.Adapter<OrgCardAdapter.OrgViewHolder>{
    private Context mCtx;
    private ArrayList<OrgModel> orgmodelList;

    public OrgCardAdapter(Context mCtx, ArrayList<OrgModel> orgmodelList) {
        this.mCtx = mCtx;
        this.orgmodelList = orgmodelList;
    }

    @Override
    public OrgViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(mCtx);
        View view=inflater.inflate(R.layout.fragment_home_list,parent,false);
        OrgViewHolder holder=new OrgViewHolder(view,mCtx,orgmodelList);
        return holder;
    }

    @Override
    public void onBindViewHolder(OrgViewHolder holder, int position) {
        OrgModel orgmodel=orgmodelList.get(position);

        holder.desc.setText(orgmodel.getShortdesc());
        holder.head.setText(orgmodel.getTitle());


    }

    @Override
    public int getItemCount() {

        return orgmodelList.size();
    }

    class OrgViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView desc;
        TextView head;
        Context mCtx;
        ArrayList<OrgModel>orgmodelList;

        public OrgViewHolder(View itemView, Context mCtx, ArrayList<OrgModel>orgmodelList) {
            super(itemView);
            itemView.setOnClickListener(this);
            this.mCtx=mCtx;
            this.orgmodelList=orgmodelList;
            desc=(TextView)itemView.findViewById(R.id.textViewShortDesc);
            head=(TextView)itemView.findViewById(R.id.textViewTitle);
        }

        @Override
        public void onClick(View view) {
            int position=getAdapterPosition();
            OrgModel orgModel=this.orgmodelList.get(position);
            Intent intent=new Intent(this.mCtx,detailsActivity.class);
            intent.putExtra("headid",orgModel.getTitle());
            intent.putExtra("descid",orgModel.getShortdesc());
            this.mCtx.startActivity(intent);
        }
    }
    public void setFilter(ArrayList<OrgModel>newList)
    {
        ArrayList arrayList=new ArrayList<>();
        arrayList.addAll(newList);
        notifyDataSetChanged();
    }
}
