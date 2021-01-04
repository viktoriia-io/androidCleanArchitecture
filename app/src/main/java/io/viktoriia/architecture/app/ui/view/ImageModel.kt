package io.viktoriia.architecture.app.ui.view

import android.graphics.Bitmap
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import coil.load
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import io.viktoriia.architecture.app.R
import io.viktoriia.architecture.app.ui.view.ImageModel.EntryHolder

@EpoxyModelClass
abstract class ImageModel : EpoxyModelWithHolder<EntryHolder>() {
    @EpoxyAttribute
    var viewState: ViewState = ViewState()

    override fun bind(holder: EntryHolder) {
        with(viewState) {
            url?.let { holder.imageView.load(it) }
            drawableRes?.let { holder.imageView.load(it) }
            bitmap?.let { holder.imageView.load(it) }
        }
    }

    @LayoutRes
    override fun getDefaultLayout(): Int {
        return R.layout.view_model_image
    }

    data class ViewState(
        var url: String? = null,
        @DrawableRes var drawableRes: Int? = null,
        var bitmap: Bitmap? = null
    )

    companion object {
        fun prepareImage(viewState: ViewState.() -> Unit): ViewState {
            return ViewState().apply(viewState)
        }
    }

    inner class EntryHolder : KotlinEpoxyHolder() {
        val imageView: ImageView by bind(R.id.view_model_text_img)
    }
}
