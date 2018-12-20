package view;

import controller.DifficulteListener;
import controller.EnregistrerChargerListener;
import controller.EpoqueChooser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VueMenuBar extends JMenuBar {

    private JFrame fenetre;
    private EpoqueChooser epoqueChooser;
    private EnregistrerChargerListener enregistrerChargerListener;
    private DifficulteListener difficulteListener;

    private JMenu menuFichier;
        private JMenu menuDemarrer;
            private JMenuItem menuItemContemporain;
            private JMenuItem menuItemFutur;
            private JMenuItem menuItemMedieval;
            private JMenuItem menuItemStarWars;
        private JMenuItem menuItemSauvegarder;
        private JMenuItem menuItemCharger;
        private JMenuItem menuItemQuitter;
    private JMenu menuIA;
    private JRadioButtonMenuItem menuItemFacile;
    private JRadioButtonMenuItem menuItemNormal;
    private JRadioButtonMenuItem menuItemHardcore;

    public VueMenuBar(JFrame fenetre, VueJeu vj) {
        this.fenetre=fenetre;
        this.epoqueChooser=new EpoqueChooser(vj,this);
        this.enregistrerChargerListener=new EnregistrerChargerListener(fenetre,vj);
        this.difficulteListener=new DifficulteListener();

        menuFichier=new JMenu("Fichier");
            menuDemarrer=new JMenu("Démarrer");
                menuItemContemporain=new JMenuItem("1-Epoque contemporaine");
                menuItemFutur=new JMenuItem("2-Epoque futuriste");
                menuItemMedieval=new JMenuItem("3-Epoque medievale");
                menuItemStarWars=new JMenuItem("4-Epoque starwars");
            menuItemSauvegarder=new JMenuItem("Sauvegarder");
            menuItemCharger=new JMenuItem("Charger");
            menuItemQuitter=new JMenuItem("Quitter");
        menuIA=new JMenu("IA");
            menuItemFacile=new JRadioButtonMenuItem("Facile");
            menuItemNormal=new JRadioButtonMenuItem("Normal");
            menuItemHardcore=new JRadioButtonMenuItem("Hardcore");


        menuItemQuitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fenetre.dispose();
                System.exit(0);
            }
        });
        menuItemContemporain.addActionListener(this.epoqueChooser);
        menuItemFutur.addActionListener(this.epoqueChooser);
        menuItemMedieval.addActionListener(this.epoqueChooser);
        menuItemStarWars.addActionListener(this.epoqueChooser);
        menuItemSauvegarder.addActionListener(this.enregistrerChargerListener);
        menuItemCharger.addActionListener(this.enregistrerChargerListener);

        menuItemFacile.addActionListener(this.difficulteListener);
        menuItemNormal.addActionListener(this.difficulteListener);
        menuItemHardcore.addActionListener(this.difficulteListener);

        menuDemarrer.add(menuItemContemporain);
                    menuDemarrer.add(menuItemFutur);
                    menuDemarrer.add(menuItemMedieval);
                    menuDemarrer.add(menuItemStarWars);
            menuFichier.add(menuDemarrer);
            menuFichier.add(menuItemSauvegarder);
            menuFichier.add(menuItemCharger);
            menuFichier.add(menuItemQuitter);
        menuIA.add(menuItemFacile);
        menuIA.add(menuItemNormal);
        menuIA.add(menuItemHardcore);

        ButtonGroup buttonGroup= new ButtonGroup();
        buttonGroup.add(menuItemFacile);
        buttonGroup.add(menuItemNormal);
        buttonGroup.add(menuItemHardcore);
        menuItemFacile.setSelected(true);

        peutSauvegarder(false);
        this.add(menuFichier);
        this.add(menuIA);
    }

    public void peutSauvegarder(boolean enable) {
        menuItemSauvegarder.setEnabled(enable);
    }

}
