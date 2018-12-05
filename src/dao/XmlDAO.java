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
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;

public class XmlDAO implements DAO {
	@Override
    public void save(String path){
		try {
			Jeu jeu = Jeu.getInstance();
			final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			final DocumentBuilder builder = factory.newDocumentBuilder();
			final Document document= builder.newDocument();

			final Element racine = document.createElement("jeu");
			document.appendChild(racine);

			final Element epoque = document.createElement("epoque");
			racine.appendChild(epoque);

			epoque.appendChild(document.createTextNode(jeu.getEpoque().toString()));

			for(int i = 0; i < 2; i++){
				final Element joueur = document.createElement("joueur");
				racine.appendChild(joueur);

				final Element terrain = document.createElement("terrain");
				joueur.appendChild(terrain);

				terrain.appendChild(document.createTextNode((i==0?jeu.getTerrainJ1() : jeu.getTerrainJ2()).getChampTir().toString()));

				for(Bateau b : (i==0?jeu.getTerrainJ1() : jeu.getTerrainJ2()).getBateaux()){
					final Element bateau = document.createElement("bateau");
					joueur.appendChild(bateau);

					final Element id = document.createElement("id");
					bateau.appendChild(id);
					id.appendChild(document.createTextNode(Integer.toString(b.getId())));

					final Element mun = document.createElement("munitions");
					bateau.appendChild(mun);
					mun.appendChild(document.createTextNode(Integer.toString(b.getMunitions())));

					final Element pos = document.createElement("position");
					bateau.appendChild(pos);
					final Element posX = document.createElement("x");
					pos.appendChild(posX);
					posX.appendChild(document.createTextNode(Integer.toString(b.getPosition().x)));
					final Element posY = document.createElement("y");
					pos.appendChild(posY);
					posY.appendChild(document.createTextNode(Integer.toString(b.getPosition().y)));

					final Element dir = document.createElement("direction");
					bateau.appendChild(dir);
					dir.appendChild(document.createTextNode(Integer.toString(b.getDirection())));
				}
			}





			final TransformerFactory transformerFactory = TransformerFactory.newInstance();
			final Transformer transformer = transformerFactory.newTransformer();
			final DOMSource source = new DOMSource(document);
			final StreamResult sortie = new StreamResult(new File(path));
			//final StreamResult sortie = new StreamResult(System.out);

			transformer.setOutputProperty(OutputKeys.VERSION, "1.0");
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");

			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

			transformer.transform(source, sortie);

		} catch (ParserConfigurationException | TransformerException e) {
			e.printStackTrace();
		}
	}

	//TODO effacer sout
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

			//System.out.println(NomEpoque + "=" + epoque.toString());

			Jeu game = Jeu.getInstance();


			for(int i = 0; i < 2; i++){
				//System.out.println(System.getProperty("line.separator") + "TERRAIN:");
				//System.out.println("fichier:");
				final Element ElementJoueur = (Element) NodeJoueurs.item(i);

				String champ = ElementJoueur.getElementsByTagName("terrain").item(0).getTextContent();
				//System.out.println(champ);

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

					//System.out.println(bateau);

					terrain.ajouterBateau(bateau);
					Coup c = new Coup(new Point(posX, posY), bateau);
					if(!terrain.verificationPlacer(c))
						throw new WrongSaveException("Position impossible du bateau (id:"+id+")");
					terrain.placer(c);
				}

				//Les tirs joués
				//System.out.println("----------");
				//System.out.println("resultat:");

				String[] lignes = champ.split(System.getProperty("line.separator"));
				for(int k = 0; k < 10; k++){
					String l = lignes[k];
					for(int j = 0; j < 10; j++){
						if(l.charAt(j) == '1'){
							terrain.Tirer(new Point(j,k));
						}
					}
				}
				//System.out.println(terrain.getChampTir());

				for(Bateau b : terrain.getBateaux()){
					//System.out.println(b);
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