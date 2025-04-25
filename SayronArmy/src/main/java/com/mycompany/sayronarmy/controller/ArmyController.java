/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sayronarmy.controller;

import com.mycompany.sayronarmy.model.gearFactories.MistyMountainsGearFactory;
import com.mycompany.sayronarmy.model.gearFactories.DolGuldurGearFactory;
import com.mycompany.sayronarmy.model.gearFactories.OrkGearFactory;
import com.mycompany.sayronarmy.model.gearFactories.GreyMountainsGearFactory;
import com.mycompany.sayronarmy.model.gearFactories.MordorGearFactory;
import com.mycompany.sayronarmy.model.builderFactories.DolGuldurOrkBuilderFactory;
import com.mycompany.sayronarmy.model.builderFactories.MistyMountainsOrkBuilderFactory;
import com.mycompany.sayronarmy.model.builderFactories.OrkBuilderFactory;
import com.mycompany.sayronarmy.model.builderFactories.GreyMountainsOrkBuilderFactory;
import com.mycompany.sayronarmy.model.builderFactories.MordorOrkBuilderFactory;
import com.mycompany.sayronarmy.model.Ork;
import com.mycompany.sayronarmy.model.Ork.Tribe;
import com.mycompany.sayronarmy.model.OrkDirector;
import com.mycompany.sayronarmy.view.SayronArmyGui;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.SwingUtilities;

/**
 *
 * @author lihac
 */
public class ArmyController {
     private final Map<String, Ork> orks = new HashMap<>();
    private final Map<Tribe, OrkBuilderFactory> builderFactories;
    private final Map<Tribe, OrkGearFactory> gearFactories;
    private SayronArmyGui view;

    public ArmyController() {
        this.builderFactories = initializeBuilderFactories();
        this.gearFactories = initializeGearFactories();
    }

    public void launchApplication() {
        SwingUtilities.invokeLater(() -> {
            this.view = new SayronArmyGui(this);
            view.setVisible(true);
        });
    }

    private Map<Tribe, OrkBuilderFactory> initializeBuilderFactories() {
        return Map.of(
            Tribe.MORDOR, new MordorOrkBuilderFactory(),
            Tribe.DOL_GULDUR, new DolGuldurOrkBuilderFactory(),
            Tribe.MISTY_MOUNTAINS, new MistyMountainsOrkBuilderFactory(),
            Tribe.GREY_MOUNTAINS, new GreyMountainsOrkBuilderFactory()
        );
    }

    private Map<Tribe, OrkGearFactory> initializeGearFactories() {
        return Map.of(
            Tribe.MORDOR, new MordorGearFactory(),
            Tribe.DOL_GULDUR, new DolGuldurGearFactory(),
            Tribe.MISTY_MOUNTAINS, new MistyMountainsGearFactory(),
            Tribe.GREY_MOUNTAINS, new GreyMountainsGearFactory()
        );
    }
    
    public void createOrk(Ork.Tribe tribe, Ork.OrkType type) {
        OrkBuilderFactory builderFactory = builderFactories.get(tribe);
        OrkGearFactory gearFactory = gearFactories.get(tribe);
        OrkDirector director = new OrkDirector(builderFactory, gearFactory);
        Ork ork = switch (type) {
            case COMMANDER ->
                director.createLeaderOrk();
            case SCOUT ->
                director.createScoutOrk();
            case BASIC ->
                director.createBasicOrk();
        };

        orks.put(ork.getName(), ork);
        view.addOrkToDisplay(ork);
    }

    public Ork getOrkByName(String name) {
        return orks.get(name);
    }

    public List<Ork> getOrksByTribe(Tribe tribe) {
        return orks.values().stream()
                .filter(ork -> ork.getTribe() == tribe)
                .toList();
    }

}
