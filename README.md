# tray-gtk

Build GTK-3 status icons with Kotlin.</br>

### Usage

To use this library, add jitpack to your project's repositories:

```kotlin
maven { url = URI("https://jitpack.io") }
```

And then add the following dependency to your build.gradle.kts file:

```kotlin
implementation("com.github.hristogochev:tray-gtk:0.2.0")
```

### Example

![Preview](./preview.png)

```kotlin
if (!loadGtk()) throw Exception("Unable to load a core gtk library!")

if (!startGtkDispatcher()) throw Exception("Unable to start the gtk dispatcher!")

trayIcon(
    imagePath = "/path/to/tray/icon/image",
    visible = true,
    title = "GTK Tray Icon",
    tooltip = "GTK Tray Icon"
) {
    menu {
        item(
            text = "GTK Tray",
            enabled = false,
            imagePath = "/path/to/tray/menu/image"
        )
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
```

### Notes

* Keeps an internal reference to all created tray icons in order to avoid garbage collection of GTK structs.

### Building with ProGuard
When building with ProGuard make sure to add these to your proguard-rules.pro file

```
-keep class com.sun.jna.** { *; }
-keep class * implements com.sun.jna.** { *; }
```

### License

Licensed under [Apache-2.0 license](https://github.com/hristogochev/tray-gtk/blob/master/LICENSE).
