package net.htlgrieskirchen.pos3.streams;

import java.util.*;
import java.util.stream.Collectors;

public class Streams {

    public double average(int[] numbers) {
        return Arrays.stream(numbers)
                .average()
                .orElse(0.0);
    }
    
    public List<String> upperCase(String[] strings) {
        return Arrays.stream(strings)
                .map(String::toUpperCase)
                .collect(Collectors.toList());
    }
    
    public Weapon findWeaponWithLowestDamage(List<Weapon> weapons) {
        return weapons.stream()
                .min(Comparator.comparing(Weapon::getDamage))
                .orElse(null);
    }
    
    public Weapon findWeaponWithHighestStrength(List<Weapon> weapons) {
        return weapons.stream()
                .max(Comparator.comparing(Weapon::getMinStrength))
                .orElse(null);
    }
    
    public List<Weapon> collectMissileWeapons(List<Weapon> weapons) {
        return weapons
                .stream()
                .filter(weapon -> weapon.getDamageType() == DamageType.MISSILE)
                .collect(Collectors.toList());
    }
    
    public Weapon findWeaponWithLongestName(List<Weapon> weapons) {
        return weapons.stream()
                .max(Comparator.comparing(weapon -> weapon.getName().length()))
                .orElse(null);
    }
    
    public List<String> toNameList(List<Weapon> weapons) {
        return weapons
                .stream()
                .map(Weapon::getName)
                .collect(Collectors.toList());
    }
    
    public int[] toSpeedArray(List<Weapon> weapons) {
        return weapons.stream()
                .mapToInt(Weapon::getSpeed)
                .toArray();
    }
    
    public int sumUpValues(List<Weapon> weapons) {
        return weapons.stream()
                .mapToInt(Weapon::getValue)
                .sum();
    }
    
    public long sumUpHashCodes(List<Weapon> weapons) {
        return (int) weapons.stream()
                .mapToLong(Weapon::hashCode)
                .sum();
    }
    
    public List<Weapon> removeDuplicates(List<Weapon> weapons) {
        return weapons.stream()
                .distinct()
                .collect(Collectors.toList());
    }
    
    public List<Weapon> increaseValuesByTenPercent(List<Weapon> weapons) {
       weapons.forEach(weapon -> weapon.setValue((int) (weapon.getValue() * 1.1)));
       return weapons;
    }
}