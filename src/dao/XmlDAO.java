package dao;

import model.Bateau;
import model.Coup;
import model.Jeu;
import model.Terrain;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import textureFactory.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.io.File;
import java.util.List;

public class XmlDAO implements DAO {
	@Override
    public void save(Jeu j){

    }

	@Override
	public void load(String path) throws WrongSaveException {
		try {
			final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			final DocumentBuilder builder = factory.newDocumentBuilder();
			final Document document = builder.parse(new File(path));

			final Element racine = document.getDocumentElement();

			String NomEpoque = racine.getElementsByTagName("epoque").item(0).getTextContent();
			final SingletonEpoque epoque = this.getEpoque(NomEpoque);

			final NodeList NodeJoueurs = racine.getElementsByTagName("joueur");

			System.out.println(NomEpoque + "=" + epoque.toString());

			Jeu game = Jeu.getInstance();


			for(int i = 0; i < 2; i++){
				System.out.println(System.getProperty("line.separator") + "TERRAIN:");
				System.out.println("fichier:");
				final Element ElementJoueur = (Element) NodeJoueurs.item(i);

				String champ = ElementJoueur.getElementsByTagName("terrain").item(0).getTextContent();
				System.out.println(champ);

				final NodeList NodeBateaux = ElementJoueur.getElementsByTagName("bateau");
				int taille = NodeBateaux.getLength();

				List<Bateau> bateaux = epoque.generateFleet();

				Terrain terrain = new Terrain();

				//Les bateaux
				for(int j = 0; j < taille; j++){
					final Element ElementBateau = (Element) NodeBateaux.item(j);

					int id = Integer.valueOf(ElementBateau.getElementsByTagName("id").item(0).getTextContent());
					int mun = Integer.valueOf(ElementBateau.getElementsByTagName("id").item(0).getTextContent());

					final Element pos = (Element) ElementBateau.getElementsByTagName("position").item(0);

					int posX = Integer.valueOf(pos.getElementsByTagName("x").item(0).getTextContent());
					int posY = Integer.valueOf(pos.getElementsByTagName("y").item(0).getTextContent());

					int dir = Integer.valueOf(ElementBateau.getElementsByTagName("direction").item(0).getTextContent());

					Bateau bateau = transformBateau(bateaux, id, mun, posX, posY, dir);

					System.out.println(bateau);

					//terrain.ajouterBateau(bateau);
					Coup c = new Coup(new Point(posX, posY), bateau);
					if(!terrain.verificationPlacer(c))
						throw new WrongSaveException("Position impossible du bateau (id:"+id+")");
					terrain.placer(c);
				}

				//Les tirs joués
				System.out.println("----------");
				System.out.println("resultat:");

				String[] lignes = champ.split(System.getProperty("line.separator"));
				for(int k = 0; k < 10; k++){
					String l = lignes[k];
					for(int j = 0; j < 10; j++){
						if(l.charAt(j) == '1'){
							terrain.Tirer(new Point(j,k));
						}
					}
				}
				System.out.println(terrain.getChampTir());

				for(Bateau b : terrain.getBateaux()){
					System.out.println(b);
				}





				if(i ==0)
					game.setTerrainJ1(terrain);
				else
					game.setTerrainJ2(terrain);
			}
		} catch (Exception e) {
			throw new WrongSaveException(e.getMessage());
		}
	}

	private Bateau transformBateau(List<Bateau> bateaux, int id, int munitions, int posX, int posY, int dir) throws WrongSaveException {
		Bateau bateau = null;
		for(Bateau b : bateaux){
			if(b.getId() == id)
				bateau = b;
		}
		if(bateau == null)
			throw new WrongSaveException("ID du bateau ne correspond a aucun bateau de l'epoque (id:"+id+")" );

		if(munitions > bateau.getMunitions())
			throw new WrongSaveException("Munitions impossible pour le bateau de l'epoque (id:"+id+")" );
		while(munitions < bateau.getMunitions())
			bateau.utiliserMunition();

		bateau.setPosition(new Point(posX, posY));
		bateau.setDirection(dir);

		return bateau;
	}

	private SingletonEpoque getEpoque(String epoque) throws WrongSaveException {
		switch(epoque){
			case "Contemporaine":
				return SingletonContemporain.getInstance();
			case "Futuriste":
				return SingletonFutur.getInstance();
			case "Mediévale":
				return SingletonMedieval.getInstance();
			case "StarWars":
				return SingletonStarWars.getInstance();
				default:
					throw new WrongSaveException("Epoque non reconnue: '" + epoque + "'");
		}
	}
}