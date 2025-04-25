package com.mycompany.sayronarmy.model.builders;

import com.github.javafaker.Faker;
import com.mycompany.sayronarmy.model.Ork;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author lihac
 */
public abstract class OrkBuilder {
    protected static final Faker faker = new Faker();
    protected static final Random random = new Random();
    protected static final Map<String, Integer> nameCounters = new HashMap<>();

    protected String name;
    protected String weapon;
    protected String armor;
    public String banner;
    protected int strength;
    protected int agility;
    protected int intelligence;
    protected int health;
    protected Ork.Tribe tribe;
    protected Ork.OrkType type = Ork.OrkType.BASIC;

    public OrkBuilder(Ork.Tribe tribe) {
        this.tribe = tribe;
        this.name = generateOrkName();
        generateBaseAttributes();
    }

    protected abstract void generateBaseAttributes();

    protected String generateOrkName() {
        String baseName = faker.lordOfTheRings().character();
        int count = nameCounters.getOrDefault(baseName, 0) + 1;
        nameCounters.put(baseName, count);
        return count == 1 ? baseName : baseName + " " + count;
    }

    public OrkBuilder weapon(String weapon) {
        this.weapon = weapon;
        return this;
    }

    public OrkBuilder armor(String armor) {
        this.armor = armor;
        return this;
    }

    public OrkBuilder banner(String banner) {
        this.banner = banner;
        return this;
    }

    public OrkBuilder type(Ork.OrkType type) {
        this.type = type;
        if (type == Ork.OrkType.COMMANDER) {
            this.strength += 20;
            this.health += 50;
        } else if (type == Ork.OrkType.SCOUT) {
            this.agility += 20;
        }
        return this;
    }

    public Ork build() {
        return new Ork(name, weapon, armor, banner, 
                     strength, agility, intelligence, health,
                     tribe, type);
    }
}