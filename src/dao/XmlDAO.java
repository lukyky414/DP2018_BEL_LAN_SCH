package dao;

import model.Bateau;
import model.Jeu;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import textureFactory.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.File;
import java.util.List;

public class XmlDAO implements DAO {
	@Override
    public void save(Jeu j){

    }

	@Override
	public Jeu load(String path) throws WrongSaveException {
		try {
			final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			final DocumentBuilder builder = factory.newDocumentBuilder();
			final Document document = builder.parse(new File(path));

			final Element racine = document.getDocumentElement();

			String NomEpoque = racine.getElementsByTagName("epoque").item(0).getTextContent();
			final SingletonEpoque epoque = this.getEpoque(NomEpoque);

			final NodeList NodeJoueurs = racine.getElementsByTagName("joueur");

			System.out.println(NomEpoque + ":" + epoque.toString());

			for(int i = 0; i < 2; i++){
				final Element ElementJoueur = (Element) NodeJoueurs.item(i);
				System.out.println("Terrain:");
				System.out.println(ElementJoueur.getElementsByTagName("terrain").item(0).getTextContent());

				final NodeList NodeBateaux = ElementJoueur.getElementsByTagName("bateau");
				int taille = NodeBateaux.getLength();

				List<Bateau> bateaux = epoque.generateFleet();

				for(int j = 0; j < taille; j++){
					final Element ElementBateau = (Element) NodeBateaux.item(j);

					int id = Integer.valueOf(ElementBateau.getElementsByTagName("id").item(0).getTextContent());
					int mun = Integer.valueOf(ElementBateau.getElementsByTagName("id").item(0).getTextContent());

					System.out.println("Bateau id:" + id);
					System.out.println("mun:" + mun);

					final Element pos = (Element) ElementBateau.getElementsByTagName("position").item(0);

					int posX = Integer.valueOf(pos.getElementsByTagName("x").item(0).getTextContent());
					int posY = Integer.valueOf(pos.getElementsByTagName("y").item(0).getTextContent());
					System.out.println("posX:" + posX + " posY:" + posY);

					int dir = Integer.valueOf(ElementBateau.getElementsByTagName("direction").item(0).getTextContent());
					System.out.println("dir:" + dir);

					Bateau bateau = transformBateau(bateaux, id, mun, posX, posY, dir);

					//TODO PLACER LES BATEAUX
				}
			}

		} catch (Exception e) {
			throw new WrongSaveException(e.getMessage());
		}
		return null;
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