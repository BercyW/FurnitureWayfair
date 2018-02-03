package security.bercy.com.furniturewayfair.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

import java.util.List;

import security.bercy.com.furniturewayfair.R;


/**
 * Created by Bercy on 2/2/18.
 */

public class RecylerviewAdapter extends RecyclerView.Adapter<RecylerviewAdapter.ViewHolder>{

    List<Integer> imgs;
    private RecylerviewAdapter.OnItemClickListener listener;

    public RecylerviewAdapter(List<Integer> imgs) {
        this.imgs = imgs;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_layout_grid_items,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final int imgSource = imgs.get(position);
        holder.imageView.setImageResource(imgSource);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(holder.itemView, position);
            }
        });

    }


    @Override
    public int getItemCount() {
        return imgs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.cardview_img);

        }
    }



    //add item click event

    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }

    public void setOnItemClickLitener(RecylerviewAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }


}
