package tk.dsjin.protectednote.presenter.notes.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat
import java.util.*

object ListNoteBinding {
    @BindingAdapter("app:setLockVisible")
    @JvmStatic fun setLockVisible(view : ImageView, isProtected : Boolean){
        view.visibility = if (isProtected) View.VISIBLE else View.GONE
    }
    @BindingAdapter("app:setDate")
    @JvmStatic fun setDate(view : TextView, date : Date){
        val formatter = SimpleDateFormat("yy/MM/dd", Locale.US)
        view.text = formatter.format(date)
    }
    @BindingAdapter("app:setTextVisible")
    @JvmStatic fun setTextVisible(view : TextView, isProtected : Boolean){
        view.visibility = if (isProtected) View.GONE else View.VISIBLE
    }
}