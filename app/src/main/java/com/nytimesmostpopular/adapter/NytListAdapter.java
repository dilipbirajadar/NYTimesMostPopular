package com.nytimesmostpopular.adapter;


import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nytimesmostpopular.R;
import com.nytimesmostpopular.activity.DetailNews;
import com.nytimesmostpopular.model.mostpopular.NycMostPopularRsponse;
import com.nytimesmostpopular.model.mostpopular.ResultResponse;
import com.nytimesmostpopular.security.AES;

import java.util.List;

/**
 * Created by Dilip Birajadar on 2019-12-14.
 */
public class NytListAdapter extends RecyclerView.Adapter<NytListAdapter.MyViewHolder>{
    private List<ResultResponse> resultResponses;
    private Activity context;

    /*
     *Constructor for nyt list adapter
     * @param nyt list
     * @param context context
     */
    public NytListAdapter(List<ResultResponse> resultResponses, Activity context) {

        this.resultResponses = resultResponses;
        this.context = context;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_most_vivewd, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.nyt_title.setText(resultResponses.get(position).getTitle());
        holder.byline.setText(resultResponses.get(position).getByline());
        holder.time.setText(resultResponses.get(position).getPublishedDate());

        holder.parentRel.setOnClickListener(view -> context.startActivity(new Intent(context, DetailNews.class).putExtra("url", AES.encrypt(resultResponses.get(position).getUrl()))));
    }

    @Override
    public int getItemCount() {
        return resultResponses.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView byline, time, nyt_title ;
        private RelativeLayout parentRel;

        private MyViewHolder(View view) {
            super(view);

            nyt_title = view.findViewById(R.id.nyt_title);
            time = view.findViewById(R.id.time);
            byline = view.findViewById(R.id.byline);
            parentRel = view.findViewById(R.id.parent_rel);

        }
    }

}
