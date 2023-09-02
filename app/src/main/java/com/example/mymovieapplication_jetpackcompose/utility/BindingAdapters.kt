package com.example.assignmentoptimized.utility

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.mymovieapplication_jetpackcompose.R
import com.example.mymovieapplication_jetpackcompose.data.repositories.RemoteDataSource
import com.example.mymovieapplication_jetpackcompose.domain.entities.MovieType
import com.squareup.picasso.Picasso
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PicassoImageAdapter @Inject constructor(
    private val remoteDS: RemoteDataSource
) {
    @BindingAdapter("url", "sendTag", requireAll = false)
    fun loadImage(imageView: ImageView, url: String?, sendTag: String?) {
        if (url != null && sendTag != null) {
            if (sendTag.equals(MovieType.CUSTOM.toString(), true)) {
                if (url != "") {
                    imageView.setImageURI(Uri.parse(url))
                } else {
                    imageView.setImageResource(R.drawable.icons8_no_image_100)
                }
            } else {
                Picasso.get().load(remoteDS.getAddress(url))
                    .placeholder(R.drawable.icons8_no_image_100)
                    .into(imageView)

            }
        }
    }

}

