package com.rifki.pustakav20.Holder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rifki.pustakav20.R;

/**
 * Created by Muhammad Rifqi on 26/01/2017.
 */


public class HolderItems extends RecyclerView.ViewHolder {
    public TextView txt_judul,txt_penulis;
    public LinearLayout carditem_pustaka;
    public ImageView img_icon;

    public HolderItems(View itemView) {
        super(itemView);
        txt_judul = (TextView) itemView.findViewById(R.id.txt_judul);
        txt_penulis = (TextView) itemView.findViewById(R.id.txt_penulis);
        carditem_pustaka = (LinearLayout) itemView.findViewById(R.id.carditem_pustaka);
        img_icon = (ImageView) itemView.findViewById(R.id.img_icon);
    }
}
