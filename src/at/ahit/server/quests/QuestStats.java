package at.ahit.server.quests;

public class QuestStats {

    private int QuestTier;
    private int RequiredJob;
    private String QuestDescription;
    private int RewardExp;
    private int RewardCoins;

    public int getQuestTier() {
        return QuestTier;
    }

    public void setQuestTier(int questTier) {
        QuestTier = questTier;
    }

    public int getRequiredJob() {
        return RequiredJob;
    }

    public void setRequiredJob(int requiredJob) {
        RequiredJob = requiredJob;
    }

    public String getQuestDescription() {
        return QuestDescription;
    }

    public void setQuestDescription(String questDescription) {
        QuestDescription = questDescription;
    }

    public int getRewardExp() {
        return RewardExp;
    }

    public void setRewardExp(int rewardExp) {
        RewardExp = rewardExp;
    }

    public int getRewardCoins() {
        return RewardCoins;
    }

    public void setRewardCoins(int rewardCoins) {
        RewardCoins = rewardCoins;
    }

}
