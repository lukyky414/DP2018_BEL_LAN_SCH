package model;

public class Jeu {

    public static Jeu instance;

    private boolean enCours;


    private Jeu () {
        enCours=true;
    }

    public static Jeu getInstance() {
        if (instance == null) {
            instance=new Jeu();
        }
        return instance;
    }


    public boolean verificationTir(Coup c) {

    }

    public void tirHumain() {

    }

    public void


}
