package com.example.avinash.bachat;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by avinash on 4/8/17.
 */

public class CustomListAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final ArrayList<String> itemname;
    private final Bitmap imgId;
    public CustomListAdapter(Activity context, ArrayList<String> itemname, Bitmap imgId) {
        super(context,R.layout.mylist,itemname);

        this.context=context;
        this.itemname=itemname;
        this.imgId=imgId;
    }
    @Override
    public int getCount() {

        int count=itemname.size(); //counts the total number of elements from the arrayList.
        return count;//returns the total count to adapter
    }
    public View getView(int position,View view,ViewGroup parent) {
        Log.e("inside:","custom");
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.mylist, null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.item);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView extratxt = (TextView) rowView.findViewById(R.id.textView1);

        txtTitle.setText(itemname.get(position));
        imageView.setImageBitmap(imgId);
        extratxt.setText("Description "+itemname.get(position));
        return rowView;

    };
}
