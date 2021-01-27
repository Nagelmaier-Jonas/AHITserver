package at.ahit.server.playerValues;

public class Coins {
    private static Coins instance;
    private int Amount;

    private Coins() {}

    public static Coins getInstance() {
        return instance;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }
}
