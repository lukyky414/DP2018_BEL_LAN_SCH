package dao;

import model.*;
import textureFactory.*;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class BinaryDAO implements DAO {

	@Override
	public void save(String path){
		try{
			String str = "";
			StringBuilder strBuilder = new StringBuilder(str);

			Jeu jeu = Jeu.getInstance();
			{
				//actuellement 4 epoques. Dans le cas d'ajout d'epoque: 3bits
				int epoque = getNumberByEpoqueName(jeu.getEpoque().toString());
				String res = byteToString((byte)epoque);
				//8-3 = 5
				String toput = res.substring(5);
				strBuilder.append(toput);
			}


			for(int i = 0; i < 2; i++){
				ChampTir champ;
				List<Bateau> bateaux;
				if(i == 0){
					champ = jeu.getTerrainJ1().getChampTir();
					bateaux = jeu.getTerrainJ1().getBateaux();
				}
				else{
					champ = jeu.getTerrainJ2().getChampTir();
					bateaux = jeu.getTerrainJ2().getBateaux();
				}

				//5 bateaux
				for(Bateau b : bateaux){
					{
						//Id: 5 max -> 3bits.
						int id = b.getId();
						String res = byteToString((byte)id);
						//8-3 = 5
						String toput = res.substring(5);
						strBuilder.append(toput);
					}
					{
						//mun: 100max (tout le terrain) -> 7bits
						int mun = b.getMunitions();
						String res = byteToString((byte)mun);
						//8-7 = 1
						String toput = res.substring(1);
						strBuilder.append(toput);
					}
					{
						//posX | posY: 10 possibilite -> 5bits
						int posX = b.getPosition().x;
						int posY = b.getPosition().y;

						//8-5 = 3
						String res = byteToString((byte)posX);
						String toput = res.substring(3);
						strBuilder.append(toput);

						res = byteToString((byte)posY);
						toput = res.substring(3);
						strBuilder.append(toput);
					}
					{
						//direction: 4 -> 2bits
						int dir = b.getDirection();
						String res = byteToString((byte)dir);
						//8-4 = 4
						String toput = res.substring(4);
						strBuilder.append(toput);
					}
				}

				//champ tir
				for(boolean b : champ){
					strBuilder.append((b?"1":"0"));
				}
			}

			str = strBuilder.toString();
			byte[] save = stringToBytes(str);
			writeSmallBinaryFile(save, path);
		}catch(IOException e){
			//this should not happen
		}
	}

	@Override
	public void load(String path) throws WrongSaveException {
		try{
			byte[] bytes = readSmallBinaryFile(path);
			String str = bytesToString(bytes);
			int begin = 0;

			Jeu jeu = Jeu.getInstance();
			SingletonEpoque epoque;
			{
				//actuellement 4 epoques. Dans le cas d'ajout d'epoque: 3bits
				String e = str.substring(begin,begin+3);
				int ep = stringToBytes(e)[0];
				e = getEpoqueNaleByNumber(ep);

				epoque = XmlDAO.getEpoque(e);

				begin+=3;
			}


			for(int i = 0; i < 2; i++){
				ChampTir champ;
				List<Bateau> bateaux = epoque.generateFleet();
				Terrain terrain = new Terrain();

				//5 bateaux
				for(int j = 0; j < 5; j++){
					//Id: 5 max -> 3bits.
					String res = str.substring(begin,begin + 3);
					int id = stringToBytes(res)[0];
					begin+=3;

					//mun: 100max (tout le terrain) -> 7bits
					res = str.substring(begin,begin+7);
					int mun = stringToBytes(res)[0];
					begin+=7;

					//posX | posY: 10 possibilite -> 5bits
					res = str.substring(begin,begin+5);
					int posX = stringToBytes(res)[0];
					begin+=5;

					res = str.substring(begin,begin+5);
					int posY = stringToBytes(res)[0];
					begin+=5;

					//direction: 4 -> 2bits
					res = str.substring(begin,begin+2);
					int dir = stringToBytes(res)[0];
					begin+=2;

					Bateau b = XmlDAO.transformBateau(bateaux, id,mun,posX,posY,dir);
					terrain.ajouterBateau(b);
					Coup c = new Coup(new Point(posX, posY), b);
					if(!terrain.verificationPlacer(c))
						throw new WrongSaveException("Position impossible du bateau (id:"+id+")");
					terrain.placer(c);
				}

				//champ_tir
				for(int k = 0; k < 10; k++){
					for(int j = 0; j < 10; j++){
						if(str.substring(begin,++begin).equals("1")){
							terrain.tirer(new Point(j,k));
						}
					}
				}

				if(i == 0){
					jeu.setTerrainJ1(terrain);
				}
				else{
					jeu.setTerrainJ2(terrain);
				}
			}

			byte[] save = stringToBytes(str);
			writeSmallBinaryFile(save, path);
		}catch (WrongSaveException e){
			throw e;
		}catch (Exception e){
			e.printStackTrace();
			throw new WrongSaveException(e.getMessage());
		}
	}

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

	public static String byteToString(byte octet){
		String str = "";
		StringBuilder strBuilder = new StringBuilder(str);

		for(int i = 0; i < 8; i++){
			if((octet & (1<<(7-i))) > 0)
				strBuilder.append("1");
			else
				strBuilder.append("0");
		}

		str = strBuilder.toString();

		return str;
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
