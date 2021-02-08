package com.target.targetcasestudy.utilities

import android.widget.ImageView
import com.bumptech.glide.Glide

object ImageHandler{

    /**
     * Load the image from the url to image view using Glide
     */
    fun setGlideImage(imageUrl: String?, imageView: ImageView) {
        Glide.with(imageView.context)
            .load(imageUrl)
            .into(imageView)
    }
}