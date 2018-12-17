package controller;

import main.Main;
import textureFactory.*;
import view.VueJeu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EpoqueChooser implements ActionListener {

    private VueJeu vj;

    public EpoqueChooser(VueJeu vj) {
        this.vj = vj;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String test=e.getActionCommand();
        int numEpoque=Integer.parseInt(test.split("-")[0]);
        SingletonEpoque se;
        switch (numEpoque) {
            case 1:
                se= SingletonContemporain.getInstance();
                break;
            case 2:
                se= SingletonFutur.getInstance();
                break;
            case 3:
                se= SingletonMedieval.getInstance();
                break;
            case 4:
                se= SingletonStarWars.getInstance();
                break;
            default:
                se=null;
        }
        vj.ajouterBateaux(se.generateFleet(),se.generateFleet());
		Main.setEnabled(vj, true);
    }
}
