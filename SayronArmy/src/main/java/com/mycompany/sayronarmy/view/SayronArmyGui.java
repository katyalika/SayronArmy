package com.mycompany.sayronarmy.view;

import com.mycompany.sayronarmy.controller.ArmyController;
import com.mycompany.sayronarmy.model.Ork;
import com.mycompany.sayronarmy.model.Ork.OrkType;
import com.mycompany.sayronarmy.model.Ork.Tribe;
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
     private final ArmyController controller;
    private final Map<Tribe, DefaultMutableTreeNode> tribeNodes = new HashMap<>();
    private final DefaultTreeModel treeModel;
    private final JTree armyTree;
    private final JPanel infoPanel;
    
    private final Color backgroundColor = new Color(30, 30, 30);
    private final Color panelColor = new Color(45, 45, 45);
    private final Color textColor = new Color(200, 200, 200);
    private final Color accentColor = new Color(139, 0, 0);

    public SayronArmyGui(ArmyController controller) {
        super("Армия Саурона");
        this.controller = controller;
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLayout(new BorderLayout());
        getContentPane().setBackground(backgroundColor);

        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Армия Мордора");
        for (Tribe tribe : Tribe.values()) {
            DefaultMutableTreeNode tribeNode = new DefaultMutableTreeNode(tribe.toString());
            tribeNodes.put(tribe, tribeNode);
            root.add(tribeNode);
        }
        
        treeModel = new DefaultTreeModel(root);
        armyTree = new JTree(treeModel);
        styleTreeComponents();
        
        infoPanel = new JPanel();
        styleInfoPanel();

        JPanel controlPanel = createControlPanel();

        add(new JScrollPane(armyTree), BorderLayout.WEST);
        add(new JScrollPane(infoPanel), BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);
    }

    private void styleTreeComponents() {
        armyTree.setBackground(panelColor);
        armyTree.setForeground(textColor);
        armyTree.setFont(new Font("Serif", Font.BOLD, 14));
        UIManager.put("Tree.selectionBackground", accentColor);
        UIManager.put("Tree.selectionForeground", Color.WHITE);
        armyTree.addTreeSelectionListener(e -> updateInfoPanel());
    }

    private void styleInfoPanel() {
        infoPanel.setLayout(new GridLayout(0, 2, 10, 10));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        infoPanel.setBackground(panelColor);
    }

    private JPanel createControlPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panel.setBackground(panelColor);

        JComboBox<Tribe> tribeCombo = new JComboBox<>(Tribe.values());
        JComboBox<OrkType> typeCombo = new JComboBox<>(OrkType.values());
        JButton createButton = new JButton("Создать орка");

        styleComboBox(tribeCombo);
        styleComboBox(typeCombo);
        styleButton(createButton);

        createButton.addActionListener(e -> {
            Tribe tribe = (Tribe) tribeCombo.getSelectedItem();
            OrkType type = (OrkType) typeCombo.getSelectedItem();
            controller.createOrk(tribe, type);
        });

        panel.add(new JLabel("Племя:")).setForeground(textColor);
        panel.add(tribeCombo);
        panel.add(new JLabel("Тип:")).setForeground(textColor);
        panel.add(typeCombo);
        panel.add(createButton);

        return panel;
    }

    private void styleComboBox(JComboBox<?> comboBox) {
        comboBox.setBackground(Color.DARK_GRAY);
        comboBox.setForeground(Color.WHITE);
    }

    private void styleButton(JButton button) {
        button.setBackground(accentColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
    }

    public void addOrkToDisplay(Ork ork) {
        DefaultMutableTreeNode orkNode = new DefaultMutableTreeNode(ork.getName());
        tribeNodes.get(ork.getTribe()).add(orkNode);
        treeModel.reload();
        
        TreePath path = new TreePath(orkNode.getPath());
        armyTree.setSelectionPath(path);
        armyTree.scrollPathToVisible(path);
    }

    private void updateInfoPanel() {
        infoPanel.removeAll();
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) armyTree.getLastSelectedPathComponent();
        
        if (selectedNode != null && !selectedNode.isRoot()) {
            String orkName = selectedNode.getUserObject().toString();
            Ork ork = controller.getOrkByName(orkName);
            
            if (ork != null) {
                addInfoRow("Имя:", ork.getName());
                addInfoRow("Племя:", ork.getTribe().toString());
                addInfoRow("Тип:", ork.getType().toString());
                addInfoRow("Оружие:", ork.getWeapon());
                addInfoRow("Броня:", ork.getArmor());
                addInfoRow("Знамя:", ork.getBanner() != null ? ork.getBanner() : "нет");
                
                addProgressBar("Сила:", ork.getStrength());
                addProgressBar("Ловкость:", ork.getAgility());
                addProgressBar("Интеллект:", ork.getIntelligence());
                addProgressBar("Здоровье:", ork.getHealth());
            }
        }
        
        infoPanel.revalidate();
        infoPanel.repaint();
    }

    private void addInfoRow(String label, String value) {
        JLabel lbl = new JLabel(label);
        lbl.setForeground(textColor);
        JLabel val = new JLabel(value);
        val.setForeground(Color.WHITE);
        infoPanel.add(lbl);
        infoPanel.add(val);
    }

    private void addProgressBar(String label, int value) {
        JLabel lbl = new JLabel(label);
        lbl.setForeground(textColor);
        infoPanel.add(lbl);

        JProgressBar progressBar = new JProgressBar(0, 100);
        progressBar.setValue(Math.min(value, 100));
        progressBar.setStringPainted(true);
        progressBar.setString(String.valueOf(value));
        progressBar.setForeground(new Color(0, 128, 0));
        progressBar.setBackground(new Color(60, 60, 60));
        infoPanel.add(progressBar);
    }
}
