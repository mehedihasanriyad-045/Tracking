package com.inovex.tracking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {
    Context context;
    String[] grid_btn;
    int[] image_btn;

    private LayoutInflater layoutInflater;

    public CustomAdapter(Context context, String[] grid_btn, int[] image_btn) {
        this.context = context;
        this.grid_btn = grid_btn;
        this.image_btn = image_btn;
    }

    @Override
    public int getCount() {
        return grid_btn.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view == null){
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.grid_sample, viewGroup, false);
        }

        ImageView imageView  = view.findViewById(R.id.imgId);
        imageView.setImageResource(image_btn[i]);
        TextView textView = view.findViewById(R.id.txtId);
        textView.setText(grid_btn[i]);
        return view;
    }
}
