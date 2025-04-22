package com.mycompany.sayronarmy.gui;

import com.github.javafaker.Faker;
import com.mycompany.sayronarmy.Ork;
import com.mycompany.sayronarmy.OrkBuilders.DolGuldurOrkBuilderFactory;
import com.mycompany.sayronarmy.OrkBuilders.GreyMountainsOrkBuilderFactory;
import com.mycompany.sayronarmy.OrkBuilders.MistyMountainsOrkBuilderFactory;
import com.mycompany.sayronarmy.OrkBuilders.MordorOrkBuilderFactory;
import com.mycompany.sayronarmy.OrkBuilders.OrkBuilderFactory;
import com.mycompany.sayronarmy.director.OrkDirector;
import javax.swing.*;
import javax.swing.tree.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author lihac
 */
public class SayronArmyGui extends JFrame {
    private final Map<Ork.Tribe, DefaultMutableTreeNode> tribeNodes = new HashMap<>();
    private final Map<String, Ork> orks = new HashMap<>();
    private final DefaultTreeModel treeModel;
    private final JTree armyTree;
    private final JPanel infoPanel;
    private final Faker faker = new Faker();
    
    public SayronArmyGui {
        super("Sayron's Army");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLayout(new BorderLayout());

        // Инициализация дерева армии
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Армия Мордора");
        for (Ork.Tribe tribe : Ork.Tribe.values()) {
            DefaultMutableTreeNode tribeNode = new DefaultMutableTreeNode(tribe.toString());
            tribeNodes.put(tribe, tribeNode);
            root.add(tribeNode);
        }
        treeModel = new DefaultTreeModel(root);
        armyTree = new JTree(treeModel);
        armyTree.addTreeSelectionListener(e -> updateInfoPanel());

        // Панель информации
        infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(0, 2, 10, 10));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Панель управления
        JPanel controlPanel = createControlPanel();
        // Размещение компонентов
        add(new JScrollPane(armyTree), BorderLayout.WEST);
        add(new JScrollPane(infoPanel), BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);
    }
    
    private JPanel createControlPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        JComboBox<Ork.Tribe> tribeCombo = new JComboBox<>(Ork.Tribe.values());
        JComboBox<Ork.OrkType> typeCombo = new JComboBox<>(Ork.OrkType.values());
        JButton createButton = new JButton("Создать орка");
        
        createButton.addActionListener(e -> {
            Ork.Tribe tribe = (Ork.Tribe) tribeCombo.getSelectedItem();
            Ork.OrkType type = (Ork.OrkType) typeCombo.getSelectedItem();
            createOrk(tribe, type);
        });
        
        panel.add(new JLabel("Племя:"));
        panel.add(tribeCombo);
        panel.add(new JLabel("Тип:"));
        panel.add(typeCombo);
        panel.add(createButton);
        
        return panel;
    }
    
    private void createOrk(Ork.Tribe tribe, Ork.OrkType type) {
        OrkBuilderFactory factory = getBuilderFactory(tribe);
        OrkDirector director = new OrkDirector(factory);
        
        String name = faker.lordOfTheRings().character();
        Ork ork = switch (type) {
            case BASIC -> director.createBasicOrk(name);
            case COMMANDER -> director.createLeaderOrk(name);
            case SCOUT -> director.createScoutOrk(name);
        };
        
        orks.put(ork.getName(), ork);
        DefaultMutableTreeNode orkNode = new DefaultMutableTreeNode(ork.getName());
        tribeNodes.get(tribe).add(orkNode);
        treeModel.reload();
        
        // Автоматически выбираем созданного орка
        TreePath path = new TreePath(orkNode.getPath());
        armyTree.setSelectionPath(path);
        armyTree.scrollPathToVisible(path);
    }
    
    private void updateInfoPanel() {
        infoPanel.removeAll();
        Object selected = ((DefaultMutableTreeNode) armyTree.getLastSelectedPathComponent()).getUserObject();
        Ork selectedOrk = orks.get(selected);
        
        if (selectedOrk != null) {
            addInfoRow("Имя:", selectedOrk.getName());
            addInfoRow("Племя:", selectedOrk.getTribe().toString());
            addInfoRow("Тип:", selectedOrk.getType().toString());
            addInfoRow("Оружие:", selectedOrk.getWeapon());
            addInfoRow("Броня:", selectedOrk.getArmor());
            addInfoRow("Знамя:", selectedOrk.getBanner());
            addProgressBar("Сила:", selectedOrk.getStrength());
            addProgressBar("Ловкость:", selectedOrk.getAgility());
            addProgressBar("Интеллект:", selectedOrk.getIntelligence());
            addProgressBar("Здоровье:", selectedOrk.getHealth());
        }
        
        infoPanel.revalidate();
        infoPanel.repaint();
    }
    
    private void addInfoRow(String label, String value) {
        infoPanel.add(new JLabel(label));
        infoPanel.add(new JLabel(value));
    }

    private void addProgressBar(String label, int value) {
        infoPanel.add(new JLabel(label));
        JProgressBar progressBar = new JProgressBar(0, 100);
        progressBar.setValue(Math.min(value, 100));
        progressBar.setStringPainted(true);
        progressBar.setString(String.valueOf(value));
        infoPanel.add(progressBar);
    }
    
    private OrkBuilderFactory getBuilderFactory(Ork.Tribe tribe) {
        return switch (tribe) {
            case MORDOR -> new MordorOrkBuilderFactory();
            case DOL_GULDUR -> new DolGuldurOrkBuilderFactory();
            case MISTY_MOUNTAINS -> new MistyMountainsOrkBuilderFactory();
            case GREY_MOUNTAINS -> new GreyMountainsOrkBuilderFactory();
        };
    }   
}
