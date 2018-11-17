package dao;

import model.Jeu;

public interface DAO {

    void save(Jeu j);
    void load(Jeu j);
}