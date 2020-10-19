package net.htlgrieskirchen.pos3.streams;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    public void run() {
        Streams streams = new Streams();
        //Example 2.1 + 2.2
        int[] generatedRandoms = initArrayWithRandomInts(10000, 100);
        System.out.println(streams.average(generatedRandoms));
        //Example 2.3 + 2.4
        String[] generatedRandomStrings = initArrayWithRandomStrings(10, 10);
        System.out.println(streams.upperCase(generatedRandomStrings));
        //Example 2.5 + 2.6
        List<Weapon> weapons = Weapon.readInWeapons("weapons.csv");
        System.out.println(streams.findWeaponWithLowestDamage(weapons));
        System.out.println(streams.findWeaponWithHighestStrength(weapons));
        System.out.println(streams.collectMissileWeapons(weapons));
        System.out.println(streams.findWeaponWithLongestName(weapons));
        System.out.println(streams.toNameList(weapons));
        System.out.println(Arrays.toString(streams.toSpeedArray(weapons)));
        System.out.println(streams.sumUpValues(weapons));
        System.out.println(streams.sumUpHashCodes(weapons));
        System.out.println(streams.removeDuplicates(weapons));
        System.out.println(streams.increaseValuesByTenPercent(weapons));
    }

    private int[] initArrayWithRandomInts(int size, int range) {
        Random r = new Random();
        int[] filledArray = new int[size];

        for (int i = 0; i < size; i++)
            filledArray[i] = r.nextInt(range);
        return filledArray;
    }

    private String[] initArrayWithRandomStrings(int sizeOfArray, int stringSize) {
        Random r = new Random();
        String[] filledArray = new String[sizeOfArray];

        for (int i = 0; i < sizeOfArray; i++)
            filledArray[i] = generateString(stringSize);
        return filledArray;
    }

    private String generateString(int stringSize) {
        Random r = new Random();
        String allChars = "abcdefghijklmnopqrstuvwxyz1234567890!§$%&/()=?";
        StringBuilder buildString = new StringBuilder();
        for (int i = 0; i < stringSize; i++)
            buildString.append(allChars.charAt(r.nextInt(allChars.length()-1)));
        return buildString.toString();
    }
}
