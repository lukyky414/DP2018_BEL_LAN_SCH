package controller;


import main.RMI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReseauListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		String test=e.getActionCommand();

		switch (test) {
			case "Host":
				RMI.ETAT = RMI.HOST;
				break;
			case "Join":
				RMI.ETAT = RMI.JOIN;
				break;
			default: //local
				RMI.ETAT = RMI.LOCAL;
				break;
		}
	}
}
