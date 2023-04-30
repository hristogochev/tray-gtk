import com.hristogochev.tray.gtk.*
import java.lang.ref.WeakReference

lateinit var exampleTray: ExampleTray

fun main() {
    if (!loadGtk()) return
    startGtkDispatcher()
    exampleTray = ExampleTray()
    // Check if garbage collection causes any issues
    gc()
}

/**
 * This method guarantees that garbage collection is done unlike `[System.gc]`
 * Reference: https://stackoverflow.com/questions/1481178/how-to-force-garbage-collection-in-java
 */
@Suppress("UNUSED_VALUE")
fun gc() {
    var obj: Any? = Any()
    val ref: WeakReference<*> = WeakReference(obj)
    obj = null
    while (ref.get() != null) {
        System.gc()
    }
}