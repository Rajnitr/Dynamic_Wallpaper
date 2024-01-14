package com.example.myscreensaver

import android.app.WallpaperManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.DisplayMetrics
import android.util.Log
import android.view.WindowManager
import androidx.core.content.getSystemService
import com.example.myscreensaver.service.MyService
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import kotlin.random.Random


class MyScreenSaverUtils {
    val TAG = "raj_dynamic_wallpaper"

    fun setWallpaper(context: Context) {
        /*Glide.with(context).asBitmap().load("https://picsum.photos/200").into(object : Target<Bitmap> {
            override fun onStart() {
                TODO("Not yet implemented")
            }

            override fun onStop() {
                TODO("Not yet implemented")
            }

            override fun onDestroy() {
                TODO("Not yet implemented")
            }

            override fun onLoadStarted(placeholder: Drawable?) {
                TODO("Not yet implemented")
            }

            override fun onLoadFailed(errorDrawable: Drawable?) {
                TODO("Not yet implemented")
            }

            override fun onLoadCleared(placeholder: Drawable?) {
                TODO("Not yet implemented")
            }

            override fun getSize(cb: SizeReadyCallback?) {
                TODO("Not yet implemented")
            }

            override fun removeCallback(cb: SizeReadyCallback?) {
                TODO("Not yet implemented")
            }

            override fun setRequest(request: Request?) {
                TODO("Not yet implemented")
            }

            override fun getRequest(): Request? {
                TODO("Not yet implemented")
            }

            override fun onResourceReady(resource: Bitmap?, transition: Transition<in Bitmap>?) {
                val wallpaperManager= WallpaperManager.getInstance(context)
                wallpaperManager.setBitmap(resource)
            }
        })*/

        /*var imageList= ArrayList<String>()
        imageList.add("https://cdn.pixabay.com/user/2013/11/05/02-10-23-764_250x250.jpg")
        imageList.add("https://picsum.photos/201")
        imageList.add("https://picsum.photos/202")
        val random = Random.nextInt(0, 2)
        val prev=random


        val imgUrl= imageList.get(random)
        Log.d(TAG, "setWallpaper: imgurl_selected=" + imgUrl)*/

        //context.getSystemService<MyService>().wallpaperDesiredMinimumHeight

        Picasso.with(context).load("https://cdn.pixabay.com/user/2023/08/31/14-34-06-589_250x250.jpg").into(object : Target {
            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
               // Log.d(TAG, "setWallpaper: imgurl_loaded=" + imgUrl)
                val displayMetrics = DisplayMetrics()

                //getWindowManager().getDefaultDisplay().getMetrics(displayMetrics)
                val height = context.wallpaperDesiredMinimumHeight
                val width = context.wallpaperDesiredMinimumWidth
                val bitmap2= bitmap?.let { Bitmap.createScaledBitmap(it, width, height, true) }
                val wallpaperManager= WallpaperManager.getInstance(context)
                wallpaperManager.setWallpaperOffsetSteps(1F, 1F);
                wallpaperManager.suggestDesiredDimensions(width, height);

                wallpaperManager.setBitmap(bitmap2)
            }

            override fun onBitmapFailed(errorDrawable: Drawable?) {
                //TODO("Not yet implemented")
            }

            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                //TODO("Not yet implemented")
            }
        })


    }
}