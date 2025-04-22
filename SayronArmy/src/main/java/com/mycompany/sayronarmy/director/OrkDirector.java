package com.mycompany.sayronarmy.director;

import com.mycompany.sayronarmy.Ork;
import com.mycompany.sayronarmy.OrkBuilders.OrkBuilderFactory;

/**
 *
 * @author lihac
 */
public class OrkDirector {
    private final OrkBuilderFactory builderFactory;
    
    public OrkDirector(OrkBuilderFactory builderFactory) {
        this.builderFactory = builderFactory;
    }
    
    public Ork createBasicOrk(String name) {
        return builderFactory.createOrkBuilder(name)
                .type(Ork.OrkType.BASIC)
                .build();
    }
    
    public Ork createLeaderOrk(String name) {
        return builderFactory.createOrkBuilder(name)
                .type(Ork.OrkType.COMMANDER)
                .banner(builderFactory.createOrkBuilder(name).banner + " и горн")
                .build();
    }
    
    public Ork createScoutOrk(String name) {
        return builderFactory.createOrkBuilder(name)
                .type(Ork.OrkType.SCOUT)
                .weapon("Лук")
                .build();
    }
}
