package dao;

public interface DAO {
    void save(String path);
    void load(String path) throws WrongSaveException;
}