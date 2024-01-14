package com.example.myscreensaver.adapters

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.myscreensaver.R
import com.example.myscreensaver.network.data.Images
import com.example.myscreensaver.network.pexel_model.ImagesPexel
import com.example.myscreensaver.network.pexel_model.SingleImagePexel
import com.squareup.picasso.Picasso

class MyAdapter(private val images: List<SingleImagePexel>, private val context: Context) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.single_image_item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //Picasso.with(context).load(images.get(position).src?.portrait).into(holder.imageView)
        Glide.with(context).asBitmap().load(images.get(position).src?.portrait).diskCacheStrategy(
            DiskCacheStrategy.ALL).into(object :
            CustomTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                holder.imageView.setImageBitmap(resource)
            }

            override fun onLoadCleared(placeholder: Drawable?) {
                holder.imageView.setImageDrawable(placeholder)
            }

        })
    }

    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val imageView= view.findViewById<ImageView>(R.id.single_image)
    }
}