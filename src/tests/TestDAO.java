package tests;

import dao.WrongSaveException;
import dao.XmlDAO;
import org.junit.Test;

public class TestDAO {

	@Test
	public void load(){
		XmlDAO dao = new XmlDAO();

		try {
			String chemin = getClass().getResource("/sav/save01.xml").getPath();
			dao.load(chemin);


		} catch (WrongSaveException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void save(){
		XmlDAO dao = new XmlDAO();
		try{
			String chemin_load = getClass().getResource("/sav/save01.xml").getPath();
			String chemin_save = getClass().getResource("/sav/").getPath()+"save10.xml";

			dao.load(chemin_load);
			dao.save(chemin_save);
		} catch (WrongSaveException e) {
			e.printStackTrace();
		}
	}
}
