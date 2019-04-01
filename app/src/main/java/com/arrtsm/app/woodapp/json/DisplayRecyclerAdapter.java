package com.arrtsm.app.woodapp.json;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arrtsm.app.woodapp.R;
import com.arrtsm.app.woodapp.dao.displayfragmentdao;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DisplayRecyclerAdapter extends RecyclerView.Adapter<DisplayRecyclerAdapter.MyViewHolder> {

    ArrayList<displayfragmentdao> arrayList = new ArrayList<>();

    private int previousPosition = 0;

    //interface
    private OnItemClickListener mListener;

    public DisplayRecyclerAdapter(ArrayList<displayfragmentdao> arrayList) {
        this.arrayList = arrayList;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {

        holder.Name.setText(arrayList.get(position).getName());
        //Picasso.get().load(arrayList.get(position).getImage()).into(holder.Image);
        Picasso.get().load("file:///android_asset/woodtexture/"+arrayList.get(position).getImage()).into(holder.Image);
        Log.d("tsst", "onBindViewHolder: "+arrayList.get(position).getImage());
        //Animation For Json Image
        /*if(position > previousPosition){ // We are scrolling DOWN

            AnimationUtil.animate(holder, true);

        }else{ // We are scrolling UP

            AnimationUtil.animate(holder, false);


        }

        previousPosition = position;*/


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Name;
        ImageView Image;

        public MyViewHolder(View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.name);
            Image = itemView.findViewById(R.id.image);

            //interf ace
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION)
                            mListener.onItemClick(position);
                    }
                }
            });
        }
    }
}
