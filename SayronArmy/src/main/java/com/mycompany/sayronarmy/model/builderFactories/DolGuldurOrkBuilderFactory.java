package com.mycompany.sayronarmy.model.builderFactories;

import com.mycompany.sayronarmy.model.builders.OrkBuilder;
import com.mycompany.sayronarmy.model.builders.DolGuldurOrkBuilder;
import com.mycompany.sayronarmy.model.gearFactories.DolGuldurGearFactory;
import com.mycompany.sayronarmy.model.gearFactories.OrkGearFactory;

/**
 *
 * @author lihac
 */
public class DolGuldurOrkBuilderFactory implements OrkBuilderFactory {
    private final OrkGearFactory gearFactory = new DolGuldurGearFactory();

    @Override
    public OrkBuilder createOrkBuilder() {
        return new DolGuldurOrkBuilder()
                .weapon(gearFactory.createWeapon())
                .armor(gearFactory.createArmor());
    }
}
