import com.hristogochev.tray.gtk.*
import com.hristogochev.tray.gtk.components.TrayIcon
import java.awt.image.BufferedImage
import java.net.URI
import java.net.URL
import javax.imageio.ImageIO
import kotlin.system.exitProcess


class ExampleTray {
    private lateinit var trayIconImage: BufferedImage
    private lateinit var trayMenuImageURI:URI
    private lateinit var trayIcon: TrayIcon

    init {
        setupImages()
        setupTray()
    }

    private fun setupImages() {
        val trayIconImageURL = javaClass.getResource("/tray_icon.png")?.toExternalForm()?.toURL() ?: return
        trayMenuImageURI = javaClass.getResource("/tray_menu.png")?.toExternalForm()?.toURI() ?: return
        trayIconImage = ImageIO.read(trayIconImageURL)
    }

    private fun setupTray() {
        trayIcon = trayIcon(
            image = trayIconImage,
            title = "GTK Tray Icon",
            tooltip = "GTK Tray Icon"
        ) {
            menu {
                item(text = "GTK Tray", enabled = false, imagePath=trayMenuImageURI.path)
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

    private fun String.toURI(): URI = URI(this)
    private fun String.toURL(): URL = this.toURI().toURL()
}