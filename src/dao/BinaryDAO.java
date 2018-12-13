package dao;

import model.Jeu;
import textureFactory.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class BinaryDAO implements DAO {

	public static byte[] stringToBytes(String str){

		//de base y'a le while() str += "0"; IntelliJ conseille ca pour eviter de reconstruire un String a chaque iteration de la boucle.
		StringBuilder strBuilder = new StringBuilder(str);
		while(strBuilder.length() % 8 != 0)
			strBuilder.append("0");
		str = strBuilder.toString();

		ArrayList<Byte> _bytes = new ArrayList<>();
		byte[] bytes;

		for(int i = 0; i < str.length(); i+=8){
			byte b = 0;
			for(int j = 0; j < 8; j++){
				if(str.charAt(i+j) == '1')
					b+=1<<(7-j);
			}
			_bytes.add(b);
			System.out.println(b);
		}

		bytes = new byte[_bytes.size()];

		for(int i = 0; i < _bytes.size(); i++){
			bytes[i] = _bytes.get(i);
		}

		return bytes;
	}

	public static String bytesToString(byte[] bytes){
		String str = "";
		StringBuilder strBuilder = new StringBuilder(str);

		for(byte b : bytes){
			for(int i = 0; i < 8; i++){
				if((b & (1<<(7-i))) > 0)
					strBuilder.append("1");
				else
					strBuilder.append("0");
			}
		}
		str = strBuilder.toString();

		return str;
	}

	@Override
	public void save(String path){
		try{
			String str = "";
			StringBuilder strBuilder = new StringBuilder(str);

			Jeu jeu = Jeu.getInstance();
			int epoque = getNumberByEpoqueName(jeu.getEpoque().toString());
			for(int i = 2; i > 0; i--) {
				if ((epoque >> i) % 2 == 1)
					strBuilder.append("1");
				else
					strBuilder.append("0");
			}

			//J1
			//champ tir
			//5 bateaux

			//J2
			//champ tir
			//5 bateaux



			str = strBuilder.toString();
			byte[] save = stringToBytes(str);
			writeSmallBinaryFile(save, path);
		}catch(IOException e){
			//this should not happen
		}
	}

	@Override
	public void load(String path) throws WrongSaveException {

	}

	public static byte[] readSmallBinaryFile(String fileName) throws IOException {
		Path path = Paths.get(fileName);
		return Files.readAllBytes(path);
	}

	public static void writeSmallBinaryFile(byte[] bytes, String fileName) throws IOException {
		Path path = Paths.get(fileName);
		Files.write(path, bytes); //creates, overwrites
	}

	public static String getEpoqueNaleByNumber(int i){
		switch(i){
			case 3:
				return "StarWars";
			case 1:
				return "Futuriste";
			case 2:
				return "Mediévale";
			default:
				return "Contemporaine";
		}
	}
	public static int getNumberByEpoqueName(String e_name){
		switch(e_name){
			case "Futuriste":
				return 1;
			case "Mediévale":
				return 2;
			case "StarWars":
				return 3;
			default:
				return 0;
		}
	}
}
