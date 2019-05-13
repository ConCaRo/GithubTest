package trong.test.github

import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule

// New since Glide v4
@GlideModule
class MyAppGlideModule : AppGlideModule()// leave empty for now
/*@Override
    public void applyOptions(Context context, GlideBuilder builder) {
        // Glide default Bitmap Format is set to RGB_565 since it
        // consumed just 50% memory footprint compared to ARGB_8888.
        // Increase memory usage for quality with:

        builder.setDefaultRequestOptions(new RequestOptions().format(DecodeFormat.PREFER_ARGB_8888));
}*/
