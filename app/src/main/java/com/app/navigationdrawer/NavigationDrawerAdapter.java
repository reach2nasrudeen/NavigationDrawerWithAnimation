package com.app.navigationdrawer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.Collections;
import java.util.List;

public class NavigationDrawerAdapter extends RecyclerView.Adapter<NavigationDrawerAdapter.MyViewHolder> {
    List<NavDrawerItem> data = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;
    private OnTapListener onTapListener;
    public static int selected_item = 0;

    public NavigationDrawerAdapter(Context context, List<NavDrawerItem> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    public void delete(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.nav_drawer_row, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

//        if(position == selected_item) {
//            holder.title.setTextColor(ContextCompat.getColor(holder.itemView.getContext(),android.R.color.white));
//        } else {
//            holder.title.setTextColor(ContextCompat.getColor(holder.itemView.getContext(),android.R.color.black)); //actually you should set to the normal text color
//        }
        NavDrawerItem current = data.get(position);
        holder.title.setText(current.getTitle());
        holder.imageView.setImageResource(current.getIcon());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onTapListener != null){
                    onTapListener.OnTapView(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextRegular title;
        ImageView imageView;
        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextRegular) itemView.findViewById(R.id.menuTitle);
            imageView = (ImageView) itemView.findViewById(R.id.menuIcon);
        }
    }

    public void setOnTapListener(OnTapListener onTapListener){
        this.onTapListener = onTapListener;
    }
}
