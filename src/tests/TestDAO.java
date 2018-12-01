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
}
