package controller;

import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

import bll.BLLException;
import bll.CarteBLL;
import bll.CategorieBLL;
import bll.PlatBLL;
import bll.RestaurantBLL;
import bo.Carte;
import bo.Categorie;
import bo.Plat;
import bo.Restaurant;

public class TestMain {

	private static Scanner scan = new Scanner(System.in);
	private static RestaurantBLL restaurantBll;
	private static CarteBLL carteBll;
	private static PlatBLL platBll;
	private static List<Restaurant> listeRestaurant;
	private static List<Carte> listeCarte;
	private static CategorieBLL categorieBll;

	public static void main(String[] args) {


		creerCarte();


		ajoutRestaurant();






	}


	private static int afficherMenuPrincipal() {

		System.out.println("1. Créer un restaurant");
		System.out.println("2. Modifier un restaurant ");
		System.out.println("3. Supprimer un restaurant");
		System.out.println("4. Créer une carte");
		System.out.println("5. Modifier une carte");
		System.out.println("6. Quitter");
		int choix = scan.nextInt();
		scan.nextLine();
		return choix;
	}


	private static void afficherMenuCreerCarte() {

		System.out.println("Vous avez choisi de créer une carte");

		System.out.println("Veuillez saisir le nom du plat");


		System.out.println("Veuillez saisir le prix du plat ");



		System.out.println("Veuillez saisir la description du plat");




	}

	// methode qui affiche tous les restaurants  enregistrés

	private static List<Restaurant> aficherListRestaurant() {
		try {
			listeRestaurant = restaurantBll.selectAll();
			if (listeRestaurant.size() == 0) {
				System.out.println("Il n'existe aucun restaurant en base"); 
			}
			else {
				for (int i = 0; i < listeRestaurant.size(); i++) {

					System.out.println((i + 1) + ". " + listeRestaurant.get(i));

					System.out.println("Veuillez choisir un restaurant (1-" + listeRestaurant.size() + "):");

				}

			}
		} catch (BLLException e) {

			e.printStackTrace();
		}
		return listeRestaurant;
	}

	//Méthode pour afficher le menu de création de la carte
	private static List<Carte> afficherListCarte() {

		try {
			listeCarte = carteBll.selectAll();
			if (listeCarte.size() == 0) {
				System.out.println("Il n'existe pas de carte correspondant à ce restaurant"); 
			}
			else {
				for (int i = 0; i < listeRestaurant.size(); i++) {
					System.out.println((i + 1) + ". " + listeRestaurant.get(i));
					Scanner scanner = new Scanner(System.in);
					System.out.println("Veuillez choisir un carte (1-" + listeCarte.size() + "):");

				}

			}
		} catch (BLLException e) {

			e.printStackTrace();
		}
		return listeCarte;
	}


	private static void ajoutRestaurant() {

		// TO DO 


		System.out.println("Vous avez choisi d'ajouter un restaurant");

		System.out.println("Veuillez saisir le nom du restaurant");
		String nom = scan.nextLine();

		System.out.println("Veuillez saisir l'adresse du restaurant ");
		String adresse = scan.nextLine();

		System.out.println("Veuillez saisir les horaires d'ouverture ");
		String horairesOuverture = scan.nextLine();

		System.out.println("Veuillez saisir les horaires de fermeture ");
		String horairesFermeture = scan.nextLine();


		try {
			RestaurantBLL restaurantBll = new RestaurantBLL();
			Restaurant restaurant = restaurantBll.insert(nom, adresse, LocalTime.parse(horairesOuverture), LocalTime.parse(horairesFermeture));
			System.out.println("Ce restaurant à été ajouté : " + restaurant + "\n");

			afficherMenuPrincipal();

		} catch (BLLException e) {
			System.out.println("Une erreur est survenue :");
			for (String erreur : e.getErreurs()) {
				System.out.println("\t" + erreur);
			}
			e.printStackTrace();
		}

	}

	// méthode pour ajouter des tables au restaurants

	private static void ajoutTableRestaurant() {


	}

	// méthode pour ajouter des places au tables

	private static void ajoutPlaceTable() {

	}

	private static Categorie ajoutCategoriePlat() {

		System.out.println("Veuillez saisir la catégorie du plat");
		String categoriePlat = scan.nextLine();

		try {
			CategorieBLL catePlat = new CategorieBLL();
			Categorie categorie = catePlat.insert(categoriePlat);
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;


	}



	private static void creerCarte() {

		//List<Categorie> listeCategorie = categorieBll./ completer avec la methode selectAll de categorie

		afficherMenuCreerCarte();
		String nom = scan.nextLine();
		float prix = scan.nextFloat();
		scan.nextLine();
		String description = scan.nextLine();
		Categorie categorie = ajoutCategoriePlat();

		try { 

			PlatBLL newPlat = new PlatBLL();
			Plat plat= newPlat.insert(nom, description , prix, categorie);
			System.out.println(plat + " à été ajouté ");
			Carte newCarte = new Carte();
		} catch (BLLException e) {
			System.out.println("Une erreur est survenue :");
			for (String erreur : e.getErreurs()) {
				System.out.println("\t" + erreur);
			}
			e.printStackTrace();
		}

	}

	//Completer la méthode avec les association table et place restaurant

	private static void modifierRestaurantExistant() {

		Restaurant restaurant;

		System.out.println("Vous avez choisi de modifier un restaurant");			
		System.out.println("Veuillez saisir le nom du restaurant à modifier");
		String nomModifResto = scan.nextLine();

		System.out.println("Veuillez saisir l'adresse du restaurant à modifier ");
		String adresseModifResto = scan.nextLine();

		System.out.println("Veuillez saisir les horaires d'ouverture du restaurant à modifier ");
		String horairesOuvertureModifResto = scan.nextLine();

		System.out.println("Veuillez saisir les horaires de fermeture du restaurant à modifier ");
		String horairesFermetureModifResto = scan.nextLine();





		//			System.out.println("Veuillez saisir le nombres de table pour le restaurant à modifier ");
		//			int nomTableModifResto = scan.nextInt();
		//			
		//				System.out.println("Veuillez saisir les horaires de fermeture du restaurant à modifier ");
		//				int nombrePlaceParTableModifResto = scan.nextInt();


		// TODO méthode update				Restaurant restaurantModif = restaurantBll.update( nomModifResto,adresseModifResto, LocalTime.parse(horairesOuvertureModifResto), LocalTime.parse(horairesFermetureModifResto));


	}

	private static void supprimerRestaurant() {


		System.out.println("Vous avez choisi de supprimer un restaurant" + "\n");

		System.out.println("Quel restaurant souhaitez vous supprimer ?" + "\n" + aficherListRestaurant());

		int saisieUtilisateur = scan.nextInt();
		scan.nextLine();

		System.out.println("ATTENTION : Etes-vous sur de vouloir supprimer  ce : " + saisieUtilisateur + "\n");

		System.out.println("Taper 1 pour OUI ");
		System.out.println("Taper 2 pour NON ");

		int choix = scan.nextInt();

		switch (choix){

		case 1 : try {
			restaurantBll.delete(saisieUtilisateur);
			System.out.println("Le restaurant a bien été supprimé");
		} 
		catch (BLLException e) {
			e.printStackTrace();
		}
		break;

		case 2 : System.out.println("Vous avez annuler la suppression du restaurant." + "\n" +  afficherMenuPrincipal());  

		default : 
			if (scan.hasNextInt()) {

				if (choix < 1 || choix > 2) {
					System.out.println("Choix invalide, veuillez réessayer.");

					// Gérer le choix invalide, par exemple en demandant à nouveau
				}
			} else {
				System.out.println("Entrée invalide, veuillez entrer un nombre.");
				// Gérer l'entrée invalide
			}



		}



	}


	public static void ModifierCarte() {

		PlatBLL platBll = null;
		try {
			listeCarte = carteBll.selectAll();
			if (listeCarte.size() == 0) {
				System.out.println("Il n'existe aucune carte en base");
			}

		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Quelle carte souhaitez vous modifier ?");
		System.out.println(listeCarte);
		int saisieUtilisateur = scan.nextInt();
		System.out.println(platBll);// completer les methodes dans le bll de plat
		// methode pour récuperer les associations plat et boisson

		// Plat plat = platBll / completer avec la methode update de plat
	}













}
