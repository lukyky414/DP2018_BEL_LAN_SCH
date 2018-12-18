package controller;

import main.Main;
import textureFactory.*;
import view.VueJeu;
import view.VueMenuBar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EpoqueChooser implements ActionListener {

    private VueJeu vj;
    private VueMenuBar vmb;

    public EpoqueChooser(VueJeu vj, VueMenuBar vmb) {
        this.vj = vj;
        this.vmb=vmb;
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

        vmb.peutSauvegarder(true);
        vj.ajouterBateauxEtEpoque(se);
        VueJeu.setEnabled(vj, true);
    }
}
