package at.ahit.server.playerValues;

public class PlayerStats {
    private int Amount;
    private boolean Overlay;

    private int CookLevel;
    private int FarmerLevel;
    private int HunterLevel;
    private int LumberjackLevel;
    private int MinerLevel;
    private int MonsterHunterLevel;

    private double CookExp;
    private double FarmerExp;
    private double HunterExp;
    private double LumberjackExp;
    private double MinerExp;
    private double MonsterHunterExp;

    private boolean[] CookSkills = new boolean[3];
    private boolean[] FarmerSkills = new boolean[3];
    private boolean[] HunterSkills = new boolean[3];
    private boolean[] LumberjackSkills = new boolean[3];
    private boolean[] MinerSkills = new boolean[3];
    private boolean[] MonsterHunterSkills = new boolean[3];

    public boolean[] getCookSkills() {
        return CookSkills;
    }

    public void setCookSkills(boolean[] cookSkills) {
        CookSkills = cookSkills;
    }

    public boolean[] getFarmerSkills() {
        return FarmerSkills;
    }

    public void setFarmerSkills(boolean[] farmerSkills) {
        FarmerSkills = farmerSkills;
    }

    public boolean[] getHunterSkills() {
        return HunterSkills;
    }

    public void setHunterSkills(boolean[] hunterSkills) {
        HunterSkills = hunterSkills;
    }

    public boolean[] getLumberjackSkills() {
        return LumberjackSkills;
    }

    public void setLumberjackSkills(boolean[] lumberjackSkills) {
        LumberjackSkills = lumberjackSkills;
    }

    public boolean[] getMinerSkills() {
        return MinerSkills;
    }

    public void setMinerSkills(boolean[] minerSkills) {
        MinerSkills = minerSkills;
    }

    public boolean[] getMonsterHunterSkills() {
        return MonsterHunterSkills;
    }

    public void setMonsterHunterSkills(boolean[] monsterHunterSkills) {
        MonsterHunterSkills = monsterHunterSkills;
    }
}
