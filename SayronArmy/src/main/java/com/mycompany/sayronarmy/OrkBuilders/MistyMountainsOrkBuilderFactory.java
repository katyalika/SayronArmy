package com.mycompany.sayronarmy.OrkBuilders;

import com.mycompany.sayronarmy.Ork;
import com.mycompany.sayronarmy.gearFactories.MistyMountainsGearFactory;
import com.mycompany.sayronarmy.gearFactories.OrkGearFactory;

/**
 *
 * @author lihac
 */
public class MistyMountainsOrkBuilderFactory implements OrkBuilderFactory {
    private final OrkGearFactory gearFactory = new MistyMountainsGearFactory();
    
    @Override
    public OrkBuilder createOrkBuilder(String name) {
        return new OrkBuilder(Ork.Tribe.MISTY_MOUNTAINS, name)
                .weapon(gearFactory.createWeapon())
                .armor(gearFactory.createArmor())
                .banner(gearFactory.createBanner());
    }
}
