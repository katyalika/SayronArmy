/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sayronarmy.model.gearFactories;

/**
 *
 * @author lihac
 */
public class DolGuldurGearFactory implements OrkGearFactory{
    @Override 
    public String createWeapon() {
        return "Копье";
    }
    @Override 
    public String createArmor() {
        return "Кольчуга";
    }
    @Override 
    public String createBanner() {
        return "Знамя с пауком";
    }
}
