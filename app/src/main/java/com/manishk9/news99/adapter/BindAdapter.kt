package com.manishk9.news99.adapter

import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.manishk9.news99.R
import java.text.SimpleDateFormat
import java.util.*

object BindAdapter {

    @JvmStatic @BindingAdapter(value = ["setImageUrl"])
    fun AppCompatImageView.bindImageUrl(url: String?) {
        if (url != null && url.isNotBlank()) {
            Glide.with(this.context)
                .load(url)
                .placeholder(R.drawable.default_img)
                .error(R.drawable.default_img)
                .transform(CenterCrop(),RoundedCorners(24))
                .into(this)
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["setDate"])
    fun AppCompatTextView.formatDateTime(dateTime: String){
        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        parser.timeZone = TimeZone.getTimeZone ("UTC");
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        this.text = formatter.format(parser.parse(dateTime)!!)
    }
}