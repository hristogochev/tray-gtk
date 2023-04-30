import com.hristogochev.tray.gtk.*
import com.hristogochev.tray.gtk.components.TrayIcon
import java.net.URI
import kotlin.system.exitProcess

class ExampleTray {
    private lateinit var trayIconImagePath: String
    private lateinit var trayMenuImagePath: String
    private lateinit var trayIcon: TrayIcon

    init {
        setupImages()
        setupTray()
    }

    private fun setupImages() {
        val trayIconImageResource = javaClass.getResource("/tray_icon.png")?.toExternalForm() ?: return
        val trayMenuImageResource =
            javaClass.getResource("/tray_menu.png")?.toExternalForm() ?: return
        trayIconImagePath = URI(trayIconImageResource).path
        trayMenuImagePath = URI(trayMenuImageResource).path
    }

    private fun setupTray() {
        trayIcon = trayIcon(
            imagePath = trayIconImagePath,
            visible = true,
            title = "GTK Tray Icon",
            tooltip = "GTK Tray Icon"
        ) {
            menu {
                item(text = "GTK Tray", enabled = false, imagePath = trayMenuImagePath)
                separator()
                item(text = "Click me!") {
                    action {
                        println("Yey!")
                    }
                }
                separator()
                checkbox(text = "Enable") {
                    action {
                        text = if (it) "Disable" else "Enable"
                        println("Currently enabled: $it")
                    }
                }
                separator()
                submenu(text = "Submenu") {
                    item(text = "Hello")
                    separator()
                    item(text = "How")
                    separator()
                    item(text = "Are")
                    separator()
                    item(text = "You")
                }
                separator()
                item(text = "Exit") {
                    action {
                        exitProcess(0)
                    }
                }
            }
        }
    }
}