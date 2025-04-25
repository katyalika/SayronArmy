package com.mycompany.sayronarmy.model.builderFactories;

import com.mycompany.sayronarmy.model.builders.OrkBuilder;
import com.mycompany.sayronarmy.model.builders.GreyMountainsOrkBuilder;
import com.mycompany.sayronarmy.model.gearFactories.GreyMountainsGearFactory;
import com.mycompany.sayronarmy.model.gearFactories.OrkGearFactory;

/**
 *
 * @author lihac
 */
public class GreyMountainsOrkBuilderFactory implements OrkBuilderFactory {
    private final OrkGearFactory gearFactory = new GreyMountainsGearFactory();

    @Override
    public OrkBuilder createOrkBuilder() {
        return new GreyMountainsOrkBuilder()
                .weapon(gearFactory.createWeapon())
                .armor(gearFactory.createArmor());
    }
}
