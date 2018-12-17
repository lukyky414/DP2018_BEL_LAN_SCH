package view;

import controller.EpoqueChooser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VueMenuBar extends JMenuBar {

    private JFrame fenetre;
    private EpoqueChooser epoqueChooser;

    private JMenu menuFichier;
        private JMenu menuDemarrer;
            private JMenuItem menuItemContemporain;
            private JMenuItem menuItemFutur;
            private JMenuItem menuItemMedieval;
            private JMenuItem menuItemStarWars;
        private JMenuItem menuItemSauvegarder;
        private JMenuItem menuItemCharger;
        private JMenuItem menuItemQuitter;

    public VueMenuBar(JFrame fenetre, VueJeu vj) {
        this.fenetre=fenetre;
        this.epoqueChooser=new EpoqueChooser(vj);
        menuFichier=new JMenu("Fichier");
            menuDemarrer=new JMenu("Démarrer");
                menuItemContemporain=new JMenuItem("1-Epoque contemporaine");
                menuItemFutur=new JMenuItem("2-Epoque futuriste");
                menuItemMedieval=new JMenuItem("3-Epoque medievale");
                menuItemStarWars=new JMenuItem("4-Epoque starwars");
            menuItemSauvegarder=new JMenuItem("Sauvegarder");
            menuItemCharger=new JMenuItem("Charger");
            menuItemQuitter=new JMenuItem("Quitter");

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

                    menuDemarrer.add(menuItemContemporain);
                    menuDemarrer.add(menuItemFutur);
                    menuDemarrer.add(menuItemMedieval);
                    menuDemarrer.add(menuItemStarWars);
            menuFichier.add(menuDemarrer);
            menuFichier.add(menuItemSauvegarder);
            menuFichier.add(menuItemCharger);
            menuFichier.add(menuItemQuitter);

        //menuItemSauvegarder.setEnabled(false);
        this.add(menuFichier);
    }

}
