package at.ahit.server.playerValues;

public class PlayerStats {
    private boolean HasPlayedBefore;

    private int Amount;
    private boolean Overlay;

    private int CookLevel;
    private int FarmerLevel;
    private int HunterLevel;
    private int LumberjackLevel;
    private int MinerLevel;
    private int MonsterHunterLevel;

    private int CookXp;
    private int FarmerXp;
    private int HunterXp;
    private int LumberjackXp;
    private int MinerXp;
    private int MonsterHunterXp;

    private boolean[] CookSkills = new boolean[1];
    private boolean[] FarmerSkills = new boolean[1];
    private boolean[] HunterSkills = new boolean[1];
    private boolean[] LumberjackSkills = new boolean[1];
    private boolean[] MinerSkills = new boolean[1];
    private boolean[] MonsterHunterSkills = new boolean[1];

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
