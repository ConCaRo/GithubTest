package trong.test.github.features.utils

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.request.RequestOptions
import trong.test.github.GlideApp
import trong.test.github.R

/**
 * Responsible for binding data
 */

@BindingAdapter("app:avatar")
fun setAvatarImageView(view: ImageView, url: String?) {
    GlideApp.with(view.context)
        .load(url)
        .placeholder(R.mipmap.ic_launcher_round)
        .error(R.mipmap.ic_launcher_round)
        .apply(RequestOptions().circleCrop())
        .into(view)
}
