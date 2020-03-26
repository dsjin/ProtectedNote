package tk.dsjin.protectednote.unittest.util

import androidx.lifecycle.LiveData

fun <T> LiveData<T>.observeOnce(onChangeHandler: (T) -> Unit){
    val observer = OneTimeObserver(onChangeHandler)
    observe(observer, observer)
}