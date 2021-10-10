package com.example.parliamentmemberapp.partyList

import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.parliamentmemberapp.R


@BindingAdapter("partyNameFormatted")
fun TextView.setPartyName(item: String){
    item.let{
        text = when (item) {
            "kd" -> "Christian Democrats"
            "kesk" -> "Centre Party"
            "kok" -> "National Coalition Party"
            "liik" -> "Movement Now"
            "ps" -> "Finns party"
            "r" -> "Swedish People's Party"
            "sd" -> "Social Democratic Party"
            "vas" -> "Left Alliance"
            "vihr" -> "Green League"
            else -> ""
        }
    }
}

@BindingAdapter("partyLogoDisplay")
fun ImageView.setPartyLogo(item: String){
    item.let{
        setImageResource(
            when (item) {
                "kd" -> R.drawable.kd
                "kesk" -> R.drawable.kesk
                "kok" -> R.drawable.kok
                "liik" -> R.drawable.liik
                "ps" -> R.drawable.ps
                "r" -> R.drawable.rkp
                "sd" -> R.drawable.sdp
                "vas" -> R.drawable.vas
                "vihr" -> R.drawable.vihr
                else -> R.drawable.ic_launcher_foreground
            }
        )
    }
}


@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                .placeholder(R.drawable.ic_loading_animation)
                .error(R.drawable.ic_broken_image))
            .into(imgView)
    }
}

