package tests;

import main.RMI;
import org.junit.Test;

public class RMITest {
	@Test
	public void co(){
		RMI.ETAT=RMI.JOIN;
		RMI.connect();
	}

	@Test
	public void host(){
		RMI.ETAT=RMI.HOST;
		//RMI.connect();
	}
}
