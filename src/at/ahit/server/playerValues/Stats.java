package at.ahit.server.playerValues;

public class Stats {
    private static Stats instance;
    private boolean showOverlay;

    private Stats() {}

    public static Stats getInstance() {
        return instance;
    }

    public void setShowOverlay(boolean showOverlay) {
        this.showOverlay = showOverlay;
    }
}
