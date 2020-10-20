package net.htlgkr.feinboeck18.example1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;

public class Weapon {
    private String name;
    private CombatType combatType;
    private DamageType damageType;
    private int damage;
    private int speed;
    private int strength;
    private int value;
    Printable printWeaponsNormal = weapons -> {
        List<Weapon> sortedWeapons = Weapon.sortListByCombatDamageName(weapons);
        sortedWeapons.forEach(System.out::println);
    };
    Printable printWeaponsAsTable = weapons -> {
        int[] biggestValuesLength = getBiggestValueOfWeaponParameter(weapons);
        printHeaderFooter(biggestValuesLength);
        weapons.forEach(weapon -> {
            System.out.print("\n| ");

            printPart(biggestValuesLength[0], weapon.getName());
            printPart(biggestValuesLength[1], weapon.getCombatType().toString());
            printPart(biggestValuesLength[2], weapon.getDamageType().toString());
            printPart(biggestValuesLength[3], weapon.getDamage()+"");
            printPart(biggestValuesLength[4], weapon.getSpeed()+"");
            printPart(biggestValuesLength[5], weapon.getStrength()+"");
            printPart(biggestValuesLength[6], weapon.getValue()+"");
        });
        System.out.print("\n");
        printHeaderFooter(biggestValuesLength);
    };

    public Weapon() {
        List<Weapon> weapons = readInWeapons("weapons.csv");
        printWeaponsAsTable.print(weapons);
    }

    public static void main(String[] args) {
        Weapon wpn = new Weapon();
    }

    public Weapon(String name, CombatType combatType, DamageType damageType, int damage, int speed, int strength, int value) {
        this.name = name;
        this.combatType = combatType;
        this.damageType = damageType;
        this.damage = damage;
        this.speed = speed;
        this.strength = strength;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public CombatType getCombatType() {
        return combatType;
    }

    public DamageType getDamageType() {
        return damageType;
    }

    public int getDamage() {
        return damage;
    }

    public int getSpeed() {
        return speed;
    }

    public int getStrength() {
        return strength;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Weapon{" +
                "name='" + name + '\'' +
                ", combatType=" + combatType +
                ", damageType=" + damageType +
                ", damage=" + damage +
                ", speed=" + speed +
                ", strength=" + strength +
                ", value=" + value +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Weapon weapon = (Weapon) o;
        return damage == weapon.damage && speed == weapon.speed && strength == weapon.strength && value == weapon.value && Objects.equals(name, weapon.name) && combatType == weapon.combatType && damageType == weapon.damageType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, combatType, damageType, damage, speed, strength, value);
    }

    public List<Weapon> readInWeapons(String path) {
        List<Weapon> weapons = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new File(path));
            if(sc.hasNextLine()) {
                String header = sc.nextLine();
                while(sc.hasNextLine()) {
                    String[] weaponArgs = sc.nextLine().split(";");
                    weapons.add(new Weapon(weaponArgs[0],
                            CombatType.valueOf(weaponArgs[1]),
                            DamageType.valueOf(weaponArgs[2]),
                            Integer.parseInt(weaponArgs[3]),
                            Integer.parseInt(weaponArgs[4]),
                            Integer.parseInt(weaponArgs[5]),
                            Integer.parseInt(weaponArgs[6])));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File could not be found");
        }
        return weapons;
    }

    public List<Weapon> readInWeaponsOnlyOneStream(String path) {
        List<Weapon> weapons = new ArrayList<>();
        try {
            weapons = Files.lines(new File(path).toPath())
                    .skip(1)
                    .map(string -> string.split(";"))
                    .map(s -> new Weapon(s[0],
                            CombatType.valueOf(s[1]),
                            DamageType.valueOf(s[2]),
                            Integer.parseInt(s[3]),
                            Integer.parseInt(s[4]),
                            Integer.parseInt(s[5]),
                            Integer.parseInt(s[6])))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("File could not be found");
        }
        return weapons;
    }

    public static List<Weapon> sortListByDamage(List<Weapon> weapons){
        weapons.sort(Comparator.comparingInt(Weapon::getDamage));
        return weapons;
    }

    public static List<Weapon> sortListByCombatDamageName(List<Weapon> weapons) {
        weapons.sort(Comparator.comparing(Weapon::getCombatType).thenComparing(Weapon::getDamageType).thenComparing(Weapon::getName));
        return weapons;
    }

    private int[] getBiggestValueOfWeaponParameter(List<Weapon> weapons) {
        return new int[] {
            weapons.stream().map(Weapon::getName).mapToInt(String::length).max().orElse(0),
                weapons.stream().map(Weapon::getCombatType).mapToInt(value1 -> value1.toString().length()).max().orElse(0),
                weapons.stream().map(Weapon::getDamageType).mapToInt(value1 -> value1.toString().length()).max().orElse(0),
            weapons.stream().mapToInt(Weapon::getDamage).max().toString().toCharArray().length,
            weapons.stream().mapToInt(Weapon::getSpeed).max().toString().toCharArray().length,
            weapons.stream().mapToInt(Weapon::getStrength).max().toString().toCharArray().length,
            weapons.stream().mapToInt(Weapon::getValue).max().toString().toCharArray().length
        };
    }

    private void printHeaderFooter(int[] columnSizes) {
        System.out.print("+");
        Arrays.stream(columnSizes).forEach(column -> {
            for (int i = 0; i < column+2; i++)
                System.out.print("-");
            System.out.print("+");
        });
    }

    private void printPart(int length, String arg) {
        System.out.print(arg);
        for (int i = 0; i < (length - arg.toCharArray().length); i++)
            System.out.print(" ");
        System.out.print(" | ");
    }
}
enum CombatType {
    MELEE,
    RANGED,
    NONE
}
enum DamageType {
    SLASHING,
    MISSILE,
    BLUNT,
    PIERCING,
    NONE
}
interface Printable {
    void print(List<Weapon> weapons);
}