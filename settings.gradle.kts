rootProject.name = "tray-gtk"
include("tray-gtk")
if (System.getenv("JITPACK") == null) {
    include("sample")
}

