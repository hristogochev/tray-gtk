@file:Suppress("FunctionName", "LocalVariableName")

package com.hristogochev.tray.gtk.jna

import com.hristogochev.tray.gtk.jna.structs.FuncCallback
import com.hristogochev.tray.gtk.jna.structs.GMainContext
import com.hristogochev.tray.gtk.jna.structs.GMainLoop
import com.sun.jna.Function
import com.sun.jna.Pointer


internal object Gtk3 {
    fun load() = runCatching {
        val library = JNA.register(gtk3LibName, Gtk3::class.java)

        gtk_status_icon_position_menu = library.getFunction("gtk_status_icon_position_menu")

        true
    }.onFailure {
        System.err.println("Failed to bind to GTK3: $it")
    }.getOrElse { false }

    lateinit var gtk_status_icon_position_menu: Function

    external fun gtk_init_check(argc: Int): Boolean
    external fun g_main_loop_new(context: Pointer?, is_running: Boolean): GMainLoop?
    external fun g_main_loop_get_context(loop: GMainLoop?): GMainContext?
    external fun g_main_loop_run(loop: GMainLoop?)
    external fun g_main_context_invoke(c: GMainContext?, func: FuncCallback?, data: Pointer?)
    external fun g_main_loop_quit(loop: GMainLoop?)
    external fun gtk_menu_new(): Pointer?
    external fun gtk_menu_item_set_submenu(menuEntry: Pointer?, menu: Pointer?)
    external fun gtk_separator_menu_item_new(): Pointer?
    external fun gtk_image_new_from_file(iconPath: String?): Pointer?
    external fun gtk_check_menu_item_set_active(check_menu_item: Pointer?, isChecked: Boolean)
    external fun gtk_image_menu_item_new_with_mnemonic(label: String?): Pointer?
    external fun gtk_check_menu_item_new_with_mnemonic(label: String?): Pointer?
    external fun gtk_image_menu_item_set_image(image_menu_item: Pointer?, image: Pointer?)
    external fun gtk_image_menu_item_set_always_show_image(menu_item: Pointer?, forceShow: Boolean)
    external fun gtk_status_icon_new(): Pointer?
    external fun gtk_status_icon_set_from_pixbuf(widget: Pointer?, pixbuf: Pointer?)
    external fun gtk_status_icon_set_from_file(widget: Pointer?, label: String?)
    external fun gtk_status_icon_set_visible(widget: Pointer?, visible: Boolean)
    external fun gtk_status_icon_set_tooltip_text(widget: Pointer?, tooltipText: String?)
    external fun gtk_status_icon_set_title(widget: Pointer?, titleText: String?)

    @Suppress("unused")
    external fun gtk_status_icon_set_name(widget: Pointer?, name: String?)
    external fun gtk_menu_popup(
        menu: Pointer?,
        widget: Pointer?,
        bla: Pointer?,
        func: Function?,
        data: Pointer?,
        button: Int,
        time: Int
    )

    external fun gtk_menu_item_set_label(menu_item: Pointer?, label: String?)
    external fun gtk_menu_shell_append(menu_shell: Pointer?, child: Pointer?)
    external fun gtk_widget_set_sensitive(widget: Pointer?, sensitive: Boolean)
    external fun gtk_widget_show_all(widget: Pointer?)
    external fun gtk_container_remove(parentWidget: Pointer?, widget: Pointer?)
    external fun gtk_widget_destroy(widget: Pointer?)
    external fun gtk_widget_set_tooltip_text(widget: Pointer?, text: String?)
    external fun gtk_image_new_from_pixbuf(pixbuf: Pointer?): Pointer?

}

