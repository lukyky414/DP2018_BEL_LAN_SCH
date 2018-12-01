package dao;

import model.Jeu;

public interface DAO {
    void save(Jeu j);
    Jeu load(String path) throws WrongSaveException;
}