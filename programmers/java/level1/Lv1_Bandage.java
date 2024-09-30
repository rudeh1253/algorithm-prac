package level1;
// https://school.programmers.co.kr/learn/courses/30/lessons/250137
public class Lv1_Bandage {

    public int solution(int[] bandage, int health, int[][] attacks) {
        int t = bandage[0];
        int recoveryPerSec = bandage[1];
        int bonusRecovery = bandage[2];
        int maxHealth = health;
        int currentHealth = health;

        int currentTime = 1;
        for (int[] attack : attacks) {
            int time = attack[0];
            int damage = attack[1];
            int duration = 0;
            while (currentTime < time) {
                currentHealth = getIncrementedHealth(currentHealth, maxHealth, recoveryPerSec);
                duration++;
                if (duration >= t) {
                    currentHealth = getIncrementedHealth(currentHealth, maxHealth, bonusRecovery);
                    duration = 0;
                }
                currentTime++;
            }
            currentHealth -= damage;
            if (currentHealth <= 0) {
                return -1;
            }
            currentTime++;
        }
        return currentHealth;
    }

    private int getIncrementedHealth(int currentHealth, int maxHealth, int increment) {
        return currentHealth + increment > maxHealth ? maxHealth : currentHealth + increment;
    }
}