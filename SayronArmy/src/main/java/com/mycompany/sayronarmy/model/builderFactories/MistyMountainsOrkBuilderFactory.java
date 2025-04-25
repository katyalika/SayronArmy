package com.mycompany.sayronarmy.model.builderFactories;

import com.mycompany.sayronarmy.model.builders.OrkBuilder;
import com.mycompany.sayronarmy.model.builders.MistyMountainsOrkBuilder;
import com.mycompany.sayronarmy.model.gearFactories.MistyMountainsGearFactory;
import com.mycompany.sayronarmy.model.gearFactories.OrkGearFactory;

/**
 *
 * @author lihac
 */
public class MistyMountainsOrkBuilderFactory implements OrkBuilderFactory {
    private final OrkGearFactory gearFactory = new MistyMountainsGearFactory();

    @Override
    public OrkBuilder createOrkBuilder() {
        return new MistyMountainsOrkBuilder()
                .weapon(gearFactory.createWeapon())
                .armor(gearFactory.createArmor());
    }
}
    
