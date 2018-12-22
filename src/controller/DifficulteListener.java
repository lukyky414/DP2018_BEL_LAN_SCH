package controller;

import model.IA;
import model.Jeu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DifficulteListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        String test=e.getActionCommand();

        switch (test) {
            case "Facile":
                IA.difficulte=IA.FACILE;
                break;
            case "Normal":
                IA.difficulte=IA.NORMAL;
                break;
            case "Hardcore":
                IA.difficulte=IA.HARDCORE;
                break;
            default:
        }
    }
}
