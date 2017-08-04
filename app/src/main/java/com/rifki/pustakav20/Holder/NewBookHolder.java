package com.rifki.pustakav20.Holder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rifki.pustakav20.R;

/**
 * Created by USER on 5/4/2017.
 */

public class NewBookHolder extends RecyclerView.ViewHolder {

    public CardView cv;
    public ImageView personPhoto;

    public NewBookHolder(View itemView) {
        super(itemView);
        cv = (CardView)itemView.findViewById(R.id.cv);
        personPhoto = (ImageView)itemView.findViewById(R.id.person_photo);
    }
}
