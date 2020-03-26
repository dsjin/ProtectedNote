package tk.dsjin.protectednote.unittest.util

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer

class OneTimeObserver<T>(private val handler: (T) -> Unit) : Observer<T>, LifecycleOwner{
    private val lifecycle = LifecycleRegistry(this)

    init{
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
    }

    override fun onChanged(t: T?) {
        t?.let{
            handler(it)
        }
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    }

    override fun getLifecycle(): Lifecycle = lifecycle
}