/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sayronarmy.OrkBuilders;

import com.mycompany.sayronarmy.Ork;
import com.mycompany.sayronarmy.gearFactories.MordorGearFactory;
import com.mycompany.sayronarmy.gearFactories.OrkGearFactory;

/**
 *
 * @author lihac
 */
public class MordorOrkBuilderFactory implements OrkBuilderFactory {
    private final OrkGearFactory gearFactory = new MordorGearFactory();
    
    @Override
    public OrkBuilder createOrkBuilder(String name) {
        return new OrkBuilder(Ork.Tribe.MORDOR, name)
                .weapon(gearFactory.createWeapon())
                .armor(gearFactory.createArmor())
                .banner(gearFactory.createBanner());
    }
}
