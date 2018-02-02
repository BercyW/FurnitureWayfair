package security.bercy.com.furniturewayfair.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import security.bercy.com.furniturewayfair.R;

/**
 * Created by Bercy on 2/2/18.
 */

public class RecylerviewAdapter extends RecyclerView.Adapter<RecylerviewAdapter.ViewHolder>{

    List<Integer> imgs;

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
    public void onBindViewHolder(ViewHolder holder, int position) {
        int imgSource = imgs.get(position);
        holder.imageView.setImageResource(imgSource);
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
}
