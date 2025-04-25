/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sayronarmy.model.builderFactories;

import com.mycompany.sayronarmy.model.builders.OrkBuilder;
import com.mycompany.sayronarmy.model.builders.MordorOrkBuilder;
import com.mycompany.sayronarmy.model.gearFactories.MordorGearFactory;
import com.mycompany.sayronarmy.model.gearFactories.OrkGearFactory;

/**
 *
 * @author lihac
 */
public class MordorOrkBuilderFactory implements OrkBuilderFactory {
    private final OrkGearFactory gearFactory = new MordorGearFactory();

    @Override
    public OrkBuilder createOrkBuilder() {
        return new MordorOrkBuilder()
                .weapon(gearFactory.createWeapon())
                .armor(gearFactory.createArmor());
    }
}
