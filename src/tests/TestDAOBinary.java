package tests;

import dao.BinaryDAO;
import dao.WrongSaveException;
import dao.XmlDAO;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestDAOBinary {

	@Test
	public void convertion(){
		byte trois = 3;
		String Trois = "00000011";
		byte res = BinaryDAO.stringToBytes(Trois)[0];
		String Res = BinaryDAO.bytesToString(new byte[]{trois});

		assertEquals(trois,res);
		assertEquals(Trois,Res);
	}

	@Test
	public void load(){
		BinaryDAO dao = new BinaryDAO();

		try {
			String chemin = getClass().getResource("/sav/save02.sav").getPath();
			dao.load(chemin);
		} catch (WrongSaveException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void save(){
		BinaryDAO dao = new BinaryDAO();

		try{
			String chemin_load = getClass().getResource("/sav/save02.sav").getPath();
			String chemin_save = getClass().getResource("/sav/").getPath()+"save20.sav";

			dao.load(chemin_load);
			dao.save(chemin_save);
		} catch (WrongSaveException e) {
			e.printStackTrace();
		}
	}
}
