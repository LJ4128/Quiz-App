package org.balitmorecityschools.quizappljv1;

public class LeaderboardEntry implements Comparable{
    String name;
    int score;
    String deviceID;
    public LeaderboardEntry(){
        name = "null";
        score = 0;
        deviceID = "WompWomp";
    }
    public LeaderboardEntry(String name, int score, String deviceID){
        this.name = name;
        this.score = score;
        this.deviceID = deviceID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    @Override
    public String toString() {
        return "" + name + "\t" + "\t" + score;
    }

    @Override
    public int compareTo(Object o) {
        LeaderboardEntry other = (LeaderboardEntry) o;
        int difference = this.score - other.score;

        return difference;
    }
}
