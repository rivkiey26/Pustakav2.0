package com.rifki.pustakav20.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.rifki.pustakav20.DetailActivity;
import com.rifki.pustakav20.Holder.HolderItems;
import com.rifki.pustakav20.Holder.NewBookHolder;
import com.rifki.pustakav20.ItemObjects;
import com.rifki.pustakav20.R;

import java.util.List;

/**
 * Created by Muhammad Rifqi on 26/01/2017.
 */

public class NewBookAdapter extends RecyclerView.Adapter<NewBookHolder>  {
    Context context;
    List<ItemObjects.Children> itemObjects;

    public NewBookAdapter(Context context, List<ItemObjects.Children> itemObjects) {
        this.context = context;
        this.itemObjects = itemObjects;
    }

    @Override
    public NewBookHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.newbookitem,null);
        NewBookHolder holderItem = new NewBookHolder(view);

        return holderItem;
    }

    @Override
    public void onBindViewHolder(NewBookHolder holder, final int position) {
        if (position <= 3){
            Glide.with(context).load("http://kebudayaan.kemdikbud.go.id/mobile-pustaka/pustakadummy/pustaka/ikon/"
                    +itemObjects.get(position).buku_icon)
                    .into(holder.personPhoto);
            holder.cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(),DetailActivity.class);
                    intent.putExtra("judul",itemObjects.get(position).nama_buku);
                    intent.putExtra("nama_file",itemObjects.get(position).nama_file);
                    intent.putExtra("waktu",itemObjects.get(position).date_created);
                    intent.putExtra("penulis",itemObjects.get(position).nama_penulis);
                    intent.putExtra("buku_ikon",itemObjects.get(position).buku_icon);
                    intent.putExtra("penerbit",itemObjects.get(position).nama_penerbit);
                    intent.putExtra("url_preview",itemObjects.get(position).url_preview);
                    intent.putExtra("ketersediaan",itemObjects.get(position).ketersediaan);
                    intent.putExtra("deskripsi",itemObjects.get(position).description);
                    intent.putExtra("foto sampul",itemObjects.get(position).foto_sampul);

                    v.getContext().startActivity(intent);


                }
            });
        }else{
            holder.personPhoto.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {

        return itemObjects.size();
    }
}
