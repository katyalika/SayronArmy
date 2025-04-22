package com.mycompany.sayronarmy.OrkBuilders;

import com.mycompany.sayronarmy.Ork;
import com.mycompany.sayronarmy.gearFactories.DolGuldurGearFactory;
import com.mycompany.sayronarmy.gearFactories.OrkGearFactory;

/**
 *
 * @author lihac
 */
public class DolGuldurOrkBuilderFactory implements OrkBuilderFactory {
    private final OrkGearFactory gearFactory = new DolGuldurGearFactory();
    
    @Override
    public OrkBuilder createOrkBuilder(String name) {
        return new OrkBuilder(Ork.Tribe.DOL_GULDUR, name)
                .weapon(gearFactory.createWeapon())
                .armor(gearFactory.createArmor())
                .banner(gearFactory.createBanner());
    }
}
