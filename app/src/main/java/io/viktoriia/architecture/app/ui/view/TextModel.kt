package io.viktoriia.architecture.app.ui.view

import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.annotation.StyleRes
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import io.viktoriia.architecture.app.R

@EpoxyModelClass
abstract class TextModel : EpoxyModelWithHolder<TextModel.EntryHolder>() {
    @EpoxyAttribute
    var viewState: ViewState = ViewState()

    override fun bind(holder: EntryHolder) {
        with(viewState) {
            holder.textViewContent.setTextAppearance(textAppearance)
            holder.textViewContent.text = text
        }
    }

    @LayoutRes
    override fun getDefaultLayout(): Int {
        return R.layout.view_model_text
    }

    data class ViewState(
        var text: CharSequence = "",
        @StyleRes var textAppearance: Int = android.R.style.TextAppearance_Material_Body1
    )

    companion object {
        fun prepareText(viewState: ViewState.() -> Unit): ViewState {
            return ViewState().apply(viewState)
        }
    }

    inner class EntryHolder : KotlinEpoxyHolder() {
        val textViewContent: TextView by bind(R.id.view_model_text_txt)
    }
}
