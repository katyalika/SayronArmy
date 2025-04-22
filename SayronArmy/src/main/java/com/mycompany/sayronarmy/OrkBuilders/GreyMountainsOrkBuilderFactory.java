package com.mycompany.sayronarmy.OrkBuilders;

import com.mycompany.sayronarmy.Ork;
import com.mycompany.sayronarmy.gearFactories.GreyMountainsGearFactory;
import com.mycompany.sayronarmy.gearFactories.OrkGearFactory;

/**
 *
 * @author lihac
 */
public class GreyMountainsOrkBuilderFactory implements OrkBuilderFactory {
    private final OrkGearFactory gearFactory = new GreyMountainsGearFactory();
    
    @Override
    public OrkBuilder createOrkBuilder(String name) {
        return new OrkBuilder(Ork.Tribe.GREY_MOUNTAINS, name)
                .weapon(gearFactory.createWeapon())
                .armor(gearFactory.createArmor())
                .banner(gearFactory.createBanner());
    }
}
