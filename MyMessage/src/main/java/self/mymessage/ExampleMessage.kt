package self.mymessage

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import java.lang.ref.WeakReference

class ExampleMessage(
  private val context: Context,
  private val lifeCycle: Lifecycle
): DefaultLifecycleObserver {

  private var weakContext: WeakReference<Context>? = WeakReference(null)

  init {
    weakContext = WeakReference(context)
  }

  override fun onDestroy(owner: LifecycleOwner) {
    weakContext = null
  }

  fun showToastMessage(message: String) {
    if (lifeCycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
      Toast.makeText(weakContext?.get(), message, Toast.LENGTH_SHORT).show()
    }
  }
}