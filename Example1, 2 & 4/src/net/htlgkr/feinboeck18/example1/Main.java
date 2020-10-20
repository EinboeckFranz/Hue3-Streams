package net.htlgkr.feinboeck18.example1;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Weapon wpn = new Weapon();
        List<Weapon> weaponList = wpn.readInWeaponsOnlyOneStream("weapons.csv");
        System.out.println("PRINT TABLE");
        wpn.printWeaponsAsTable.print(weaponList);
        System.out.println("PRINT NORMAL");
        wpn.printWeaponsNormal.print(weaponList);
    }
}
