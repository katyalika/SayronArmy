/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sayronarmy.OrkBuilders;

import com.mycompany.sayronarmy.Ork;
import com.github.javafaker.Faker;
import java.util.Random;

/**
 *
 * @author lihac
 */
public class OrkBuilder {
    protected static final Faker faker = new Faker();
    protected static final Random random = new Random();
    
    protected String name;
    protected String weapon;
    protected String armor;
    protected String banner;
    protected int strength;
    protected int agility;
    protected int intelligence;
    protected int health;
    protected Ork.Tribe tribe;
    protected Ork.OrkType type = Ork.OrkType.BASIC;

    public OrkBuilder(Ork.Tribe tribe, String name) {
        this.tribe = tribe;
        this.name = name != null ? name : generateOrkName();
        generateBaseAttributes();
    }

    protected void generateBaseAttributes() {
        switch (tribe) {
            case MORDOR:
                strength = random.nextInt(71) + 30;
                agility = random.nextInt(50) + 1;
                intelligence = random.nextInt(30) + 1;
                health = random.nextInt(151) + 50;
                break;
            case DOL_GULDUR:
                strength = random.nextInt(70) + 30;
                agility = random.nextInt(70) + 30;
                intelligence = random.nextInt(30) + 20;
                health = random.nextInt(101) + 100;
                break;
            case MISTY_MOUNTAINS:
                strength = random.nextInt(50) + 1;
                agility = random.nextInt(71) + 30;
                intelligence = random.nextInt(20) + 1;
                health = random.nextInt(101) + 50;
                break;
            case GREY_MOUNTAINS:
                strength = random.nextInt(60) + 40;
                agility = random.nextInt(60) + 40;
                intelligence = random.nextInt(25) + 25;
                health = random.nextInt(101) + 100;
                break;
        }
    }

    protected String generateOrkName() {
        return faker.options().option(
                "Gorbag", "Shagrat", "Ugluk", "Grishnakh", 
                "Mauhur", "Snaga", "Lugdush", "Golfimbul",
                "Azog", "Bolg", "Uruk", "Gothmog");
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
