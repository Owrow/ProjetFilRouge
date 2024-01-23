package controller;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import bll.BLLException;
import bll.CarteBLL;
import bll.CategorieBLL;
import bll.PlatBLL;
import bll.PlatCarteBLL;
import bll.RestaurantBLL;
import bll.TableBLL;
import bo.Carte;
import bo.Categorie;
import bo.Plat;
import bo.Restaurant;
import bo.Table;

public class TestMenu {
	private static Scanner scan = new Scanner(System.in);
	private static RestaurantBLL restaurantBll;
	private static CarteBLL carteBll;
	private static PlatBLL platBll;
	private static TableBLL tableBll;	
	private static CategorieBLL categorieBll;
	private static int nbTables;

	public static void main(String[] args) {
		int choice;
		do {
			printMenuPrincipal();
			System.out.print("Choix : ");
			choice = scan.nextInt();
			scan.nextLine();

			switch (choice) {
			case 1:
				addResto();
				break;
			case 2:
				modifyResto();
				break;
			case 3:
				deleteResto();
				break;
			case 4:
				createCarte();
				break;
			case 5:
				modifyCarte();
				break;
			case 6:
				System.out.println("Vous avez quitté l'application, bye !");
				break;
			default:
				System.out.println("Choix non valide. Veuillez choisir une option valide (1-6).");
			}
		} while (choice != 6);
	}

	private static void printMenuPrincipal() {
		System.out.println("Menu principal :");
		System.out.println("1. Ajouter un restaurant");
		System.out.println("2. Modifier un restaurant existant");
		System.out.println("3. Supprimer un restaurant existant");
		System.out.println("4. Créer une carte");
		System.out.println("5. Modifier une carte");
		System.out.println("6. Quitter l'application");
	}

	private static void addResto() {
		System.out.println("1. Saisie manuelle");
		System.out.println("2. Saisie automatique");
		System.out.println("3. Annuler");

		System.out.print("Choix : ");
		int choice = scan.nextInt();
		scan.nextLine();

		switch (choice) {
		case 1:
			addRestoManual();
			break;
		case 2:
			addRestoAuto(); // A FAIRE
			break;
		case 3:
			System.out.println("Annulation de l'ajout du restaurant.");
			break;
		default:
			System.out.println("Choix non valide. Veuillez réessayer.");
		}
	}

	private static void addRestoManual() { 
		System.out.println("Vous avez choisi d'ajouter un restaurant");
		System.out.println("Veuillez saisir le nom du restaurant");
		String nom = scan.nextLine();

		System.out.println("Veuillez saisir l'adresse du restaurant ");
		String adresse = scan.nextLine();

		System.out.println("Veuillez saisir les horaires d'ouverture (hh:mm)");
		String horairesOuverture = scan.nextLine();

		System.out.println("Veuillez saisir les horaires de fermeture (hh:mm)");
		String horairesFermeture = scan.nextLine();

		System.out.println("Veuillez saisir le nombre de tables ");
		nbTables = scan.nextInt();
		scan.nextLine();


		try {
			RestaurantBLL restaurantBll = new RestaurantBLL();

			Restaurant restaurant = restaurantBll.insert(nom, adresse, LocalTime.parse(horairesOuverture),LocalTime.parse(horairesFermeture));

			addTable(nbTables,restaurant.getId());

			System.out.println("Ce restaurant à été ajouté : " + restaurant.getNom() + "\n");


		} catch (BLLException e) {
			System.out.println("Une erreur est survenue :");
			for (String erreur : e.getErreurs()) {
				System.out.println("\t" + erreur);
			}
			e.printStackTrace();
		}

	}

	private static void addRestoAuto() { // A FAIRE SI LE TEMPS LE PERMET
	}

	private static void displayListResto(List<Restaurant> restaurants) throws BLLException {

		for (Restaurant current : restaurants) {
			System.out.println("******************");
			System.out.println("       - Id restaurant : "+ current.getId());
			System.out.println("       - Restaurant : "+ current.getNom());
			System.out.println("       - Adresse : "+ current.getAdresse());
			System.out.println("       - Horaire d'ouverture : " + current.getOuverture());
			System.out.println("       - Horaire de fermeture : " + current.getFermeture());

		}

	}

	private static List<Restaurant> getListRestaurants() throws BLLException {
		restaurantBll = new RestaurantBLL();
		List<Restaurant> restaurants = restaurantBll.selectAll();
		return restaurants;
	}

	private static void modifyResto() { 
		System.out.println("Vous avez choisi de modifier un restaurant");
		List<Restaurant> restaurants = null;
		try {
			restaurants = getListRestaurants();
		} catch (BLLException e) 
		{e.printStackTrace();
		}
		if (restaurants.isEmpty()) {
			System.out.println("Il n'existe aucun restaurant à afficher");
			System.out.println(" ");
			System.out.println(" ");
			System.out.println(" ");
		} else {

			System.out.println("Dans la liste suivante, choisissez le restaurant à modifier en saisissant son id : ");
			try {
				displayListResto(restaurants);
			} catch (BLLException e)
			{e.printStackTrace();
			}
			System.out.print("Choix : ");
			int idRestaurantAModifier = scan.nextInt();
			scan.nextLine();

			try {

				Restaurant restoToUpdate = restaurantBll.selectById(idRestaurantAModifier);
				System.out.println("Vous allez modifier le restaurant suivant : " + restoToUpdate.getNom());
				System.out.println("==============");
				System.out.println("Veuillez saisir le nouveau nom du restaurant");
				String nouveauNom = scan.nextLine();
				System.out.println("Veuillez saisir la nouvelle adresse du restaurant ");
				String nouvelleAdresse = scan.nextLine();
				System.out.println("Veuillez saisir les nouveaux horaires d'ouverture du restaurant ");
				String nouveauxHorairesOuverture = scan.nextLine();
				System.out.println("Veuillez saisir les nouveaux horaires de fermeture du restaurant ");
				String nouveauxHorairesFermeture = scan.nextLine();

				restoToUpdate.setNom(nouveauNom);
				restoToUpdate.setAdresse(nouvelleAdresse);
				restoToUpdate.setOuverture(LocalTime.parse(nouveauxHorairesOuverture));
				restoToUpdate.setFermeture(LocalTime.parse(nouveauxHorairesFermeture));

				restaurantBll.update(restoToUpdate);

				System.out.println("Le restaurant "+ restoToUpdate.getNom() + " a bien été modifié." );

			} catch (BLLException e) {
				e.printStackTrace();
			}
		}
	}

	private static void deleteResto() { // FAIT
		System.out.println("Vous avez choisi de supprimer un restaurant");
		List<Restaurant> restaurants = null;
		try {
			restaurants = getListRestaurants();
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (restaurants.isEmpty()) {
			System.out.println("Il n'existe aucun restaurant à afficher");
			System.out.println(" ");
			System.out.println(" ");
			System.out.println(" ");
		} else {
			System.out.println("Veuillez saisir l'id du restaurant à supprimer : ");
			try {
				displayListResto(restaurants);
			} catch (BLLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.print("Choix : ");
			int idRestoToDelete = scan.nextInt();
			scan.nextLine();

			try {

				Restaurant restoToDelete = restaurantBll.selectById(idRestoToDelete);
				System.out.println("Vous êtes sur le point de supprimer le restaurant suivant :");
				System.out.println(restoToDelete);

				System.out.print("Confirmez-vous la suppression ? (O/N) : ");
				String res = scan.nextLine().trim();

				if (res.equalsIgnoreCase("O")) {
					restaurantBll.delete(idRestoToDelete);
					System.out.println("Restaurant supprimé avec succès.");
				} else {
					System.out.println("Suppression annulée.");
				}

			} catch (BLLException e) {
				e.printStackTrace();
			}
		}
	}


	private static void addTable(int nbTables, int id_restaurant) {
		List<Table> tables = new ArrayList<>(nbTables);

		System.out.println("Votre restaurant va contenir "+nbTables+" tables");
		System.out.print("");
		for (int i=0 ; i<nbTables ; i++ ) {
			Table table = new Table();

			System.out.println("Veuillez saisir le numéro de la table " + (i+1) );
			int numeroTable = scan.nextInt();
			scan.nextLine();
			System.out.println("Veuillez saisir le nombre de places de la table n° nouvellement nommée "+ numeroTable);
			int nbPlacesParTable = scan.nextInt();
			scan.nextLine();

			Table nouvelleTable = new Table(numeroTable, nbPlacesParTable);

			tables.add(nouvelleTable);
			try {
				TableBLL tableBll = new TableBLL();
				tableBll.insert(numeroTable,nbPlacesParTable,id_restaurant);
				} catch (BLLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	private static void createCarte() { // A FAIRE
		List<Restaurant> restaurants = null;
		try {
			restaurants = getListRestaurants();
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (!restaurants.isEmpty()) {
		System.out.println("1. Saisie manuelle");
		System.out.println("2. Saisie automatique");
		System.out.println("3. Annuler");

		System.out.print("Choix : ");
		int choice = scan.nextInt();
		scan.nextLine(); 

		switch (choice) {
		case 1:
			createCarteManual(); 
			break;
		case 2:
			createCarteAuto(); 
			break;
		case 3:
			System.out.println("Annulation de la création de la carte.");
			break;
		default:
			System.out.println("Choix non valide. Veuillez réessayer.");
		}

	}
		System.out.println("Veuillez d'abord ajouter un restaurant");
	}

	private static void createCarteManual() {// A FAIRE
	
	
					
		System.out.println("Vous avez choisi de créer la carte d'un restaurant");
		System.out.println("Veuillez saisir le nom de la carte");
		String nomCarte = scan.nextLine();
		List<Plat> plats = new ArrayList<>();

		boolean ajouterAutrePlat = true;

		while (ajouterAutrePlat) {
			System.out.println("Veuillez saisir le nom du plat");
			String nomPlat = scan.nextLine();
			System.out.println("Veuillez saisir le prix du plat");
			float prixPlat = Float.parseFloat(scan.nextLine());
			System.out.println("Veuillez décrire le plat");
			String descriptionPlat = scan.nextLine();
			System.out.println("Veuillez indiquer à quelle catégorie appartient ce plat");
			System.out.println("1. Entrée");
			System.out.println("2. Plat");
			System.out.println("3. Dessert");
			System.out.println("4. Boisson");
			int categoriePlat = scan.nextInt();
			scan.nextLine(); 

			Categorie categorie = new Categorie();
			categorie.setId(categoriePlat);

			
			

			try {
				PlatBLL platBll = new PlatBLL();
				Plat plat =	platBll.insert(nomPlat, descriptionPlat, prixPlat, categorie);
				plats.add(plat);
			} catch (BLLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Souhaitez-vous ajouter un autre plat à cette carte O/N? ");
			String res = scan.nextLine().trim();
			ajouterAutrePlat = res.equalsIgnoreCase("O");
			
			
			
		}

		try {
			
			CarteBLL carteBll = new CarteBLL();
			Carte carte = carteBll.insert(nomCarte);
			PlatCarteBLL platCarteBll = new PlatCarteBLL();
			for (Plat current : plats) {
				platCarteBll.insert(current,carte);
			}
			
			boolean associerAutreRestaurant = true;

			while (associerAutreRestaurant){

				System.out.println("Saissisez l'id du restaurant auquel ajouter cette carte ?");
				List<Restaurant> restaurants = null;
				restaurants = getListRestaurants();
				displayListResto(restaurants);
				int idRestaurant = scan.nextInt();
				scan.nextLine();
				carteBll.insertCarteDansRestaurant(carte.getId(), idRestaurant);
				System.out.println("Souhaitez-vous ajouter cette carte à un autre restaurant O/N? ");
				String res = scan.nextLine().trim();
				associerAutreRestaurant = res.equalsIgnoreCase("O");       	
			}
		}catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}


	private static void createCarteAuto() {// A FAIRE SI LE TEMPS LE PERMET

	}

	private static void modifyCarte() { // A FAIRE

	}
}