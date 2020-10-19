package net.htlgkr.feinboeck18.example1;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WeaponTest {
    private List<Weapon> unsortedList = new ArrayList<Weapon>();
    private final Weapon[] expected = {
        new Weapon("Test1", CombatType.MELEE, DamageType.BLUNT, 10, 15, 12, 400),
        new Weapon("Test2", CombatType.MELEE, DamageType.BLUNT, 15, 20, 8, 300),
        new Weapon("Test3", CombatType.MELEE, DamageType.BLUNT, 15, 20, 8, 200)
    };

    private void fillList() {
        unsortedList.add(new Weapon("Test3", CombatType.MELEE, DamageType.BLUNT, 15, 20, 8, 200));
        unsortedList.add(new Weapon("Test1", CombatType.MELEE, DamageType.BLUNT, 10, 15, 12, 400));
        unsortedList.add(new Weapon("Test2", CombatType.MELEE, DamageType.BLUNT, 15, 20, 8, 300));
    }

    @org.junit.jupiter.api.Test
    void sortListByDamage() {
        fillList();
        assertEquals(expected[0], Weapon.sortListByDamage(unsortedList).toArray()[0]);
    }

    @org.junit.jupiter.api.Test
    void sortListByCombatDamageName() {
        fillList();
        assertEquals(expected[0], Weapon.sortListByCombatDamageName(unsortedList).toArray()[0]);
    }
}