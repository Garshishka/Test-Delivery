package ru.garshishka.testdelivery.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import ru.garshishka.testdelivery.R

fun ImageView.load(url: String, timeout: Int = 10_000) {
    Glide.with(this)
        .load(url)
        .circleCrop()
        .error(R.drawable.ic_error_48)
        .placeholder(R.drawable.ic_downloading_48)
        .timeout(timeout)
        .into(this)
}