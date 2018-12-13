package tests;

import dao.BinaryDAO;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestDAOBinary {

	@Test
	public void convertion(){
		String binaire = "0000001100000000111111110000000010100000";

		byte[] bs = BinaryDAO.stringToBytes(binaire);

		String res = BinaryDAO.bytesToString(bs);

		System.out.println(binaire);
		System.out.println(res);
		assertEquals(binaire, res);
	}
}
