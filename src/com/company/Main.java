package com.company;

import java.util.Random;

public class Main {
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Medic"};
    public static int[] heroesHealth = {270, 280, 250, 300};
    public static int[] heroesDamage = {20, 15, 25, 0};
    public static int bossDamage = 50;
    public static int bossHealth = 700;
    public static String bossDefenceType = "";
    public static int roundNumber = 0;
    public static int healsMedic = 25;
    public static int indexMedic = 3;


    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinish()) {
            round();
            medic();

        }
    }

    private static void medic() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] < 100 && heroesHealth[i] > 0 && heroesHealth[i] != heroesHealth[indexMedic] &&
                    heroesHealth[indexMedic] > 0) {
                Random random = new Random();
                int randomMedic = random.nextInt(heroesHealth.length);
                if (heroesHealth[randomMedic] != heroesHealth[indexMedic]) {
                    heroesHealth[randomMedic] = heroesHealth[randomMedic] + healsMedic;
                    System.out.println("Help" + healsMedic + "" + heroesAttackType[i]);
                    break;
                }
            }

        }
    }

    public static void chooseBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length);
        bossDefenceType = heroesAttackType[randomIndex];
        System.out.println("Boss choose:" + bossDefenceType);
    }

    public static void round() {
        roundNumber++;
        chooseBossDefence();
        bossHits();
        heroesHits();
        printStatistics();
    }

    public static void heroesHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (bossDefenceType == heroesAttackType[i]) {
                    Random random = new Random();
                    int coef = random.nextInt(8) + 2;// 2,4,6,8,10
                    if (bossHealth < heroesDamage[i] * coef) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * coef;
                    }
                } else {
                    if (bossHealth < heroesDamage[i]) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i];
                    }
                }
            }
        }
    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] < bossDamage) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }


    public static void printStatistics() {
        System.out.println(roundNumber + " ROUND ===========================");
        System.out.println(" Boss health " + bossHealth + " [ " + bossDamage + " ] ");
        for (int i = 0; i < heroesAttackType.length; i++) {
            System.out.println(heroesAttackType[i] + " health " + heroesHealth[i] + " [ " + heroesDamage[i] + " ] ");

        }
        System.out.println("===========================");

    }

    public static boolean isGameFinish() {
        if (bossHealth <= 0) {
            System.out.println(" Heroes won! ");
            return true;
        }

        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println(" Boss won! ");
        }
        return allHeroesDead;
    }
}



