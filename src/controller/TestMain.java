package controller;

import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

import bll.BLLException;
import bll.CarteDLL;
import bll.CategorieBLL;
import bll.PlatBLL;
import bll.RestaurantBLL;
import bo.Carte;
import bo.Categorie;
import bo.Restaurant;

public class TestMain {
	
	private static Scanner scan;
	private static RestaurantBLL restaurantBll;
	private static CarteDLL carteBll;
	private static List<Restaurant> listeRestaurant;
	private static List<Carte> listeCarte;
	private static CategorieBLL categorieBll;

	public static void main(String[] args) {
		
	
		
		
		
		
	}
		
	
		 private static int afficherMenu() {
				System.out.println("1. Créer un restaurant");
				System.out.println("2. Modifier un restaurant ");
				System.out.println("3. Supprimer un restaurant");
				System.out.println("4. Créer une carte");
				System.out.println("4. Modifier une carte");
				System.out.println("4. Quitter");
				int choix = scan.nextInt();
				scan.nextLine();
				return choix;
			}
		
		 private static void ajoutRestaurant() {
				
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
					Restaurant restaurant = restaurantBll.insert(nom, adresse, LocalTime.parse(horairesOuverture), LocalTime.parse(horairesFermeture));
					System.out.println("Composant ajouté " + restaurant);
				} catch (BLLException e) {
					System.out.println("Une erreur est survenue :");
					for (String erreur : e.getErreurs()) {
						System.out.println("\t" + erreur);
					}
					e.printStackTrace();
				}
				
			}
			
		 private static void creerCarte() {
			//List<Categorie> listeCategorie = categorieBll./ completer avec la methode selectAll de categorie
			 System.out.println("Vous avez choisi de créer une carte");
			 
			 System.out.println("Veuillez saisir le nom du plat");
			 String nom = scan.nextLine();
			 
			 System.out.println("Veuillez saisir le prix du plat ");
			 float prix = scan.nextFloat();
			 
			 System.out.println("Veuillez saisir la description du plat");
			 String description = scan.nextLine();
			 
			 System.out.println("Veuillez saisir la catégorie du plat");
			 
			 String categoriePlat = scan.nextLine();
			 
			 
			 try {
				 Carte carte = carteBll.insert(nom);
				 System.out.println("Composant ajouté " + carte);
			 } catch (BLLException e) {
				 System.out.println("Une erreur est survenue :");
				 for (String erreur : e.getErreurs()) {
					 System.out.println("\t" + erreur);
				 }
				 e.printStackTrace();
			 }
			 
		 }
		 
		 //Completer la méthode avec les association table et place restaurant
		 
//		 private static void modifierRestaurant() {
//			 
//			 System.out.println("Vous avez choisi de modifier un restaurant");
//				
//				System.out.println("Veuillez saisir le nom du restaurant à modifier");
//				String nomModifResto = scan.nextLine();
//				
//				System.out.println("Veuillez saisir l'adresse du restaurant à modifier ");
//				String adresseModifResto = scan.nextLine();
//				
//				System.out.println("Veuillez saisir les horaires d'ouverture du restaurant à modifier ");
//				String horairesOuvertureModifResto = scan.nextLine();
//				
//				System.out.println("Veuillez saisir les horaires de fermeture du restaurant à modifier ");
//				String horairesFermetureModifResto = scan.nextLine();
//			 
//				System.out.println("Veuillez saisir le nombres de table pour le restaurant à modifier ");
//				int nomTableModifResto = scan.nextInt();
//				
//				System.out.println("Veuillez saisir les horaires de fermeture du restaurant à modifier ");
//				int nombrePlaceParTableModifResto = scan.nextInt();
//				
//				
//				Restaurant restaurantModif = restaurantBll.update(nomModifResto,adresseModifResto, LocalTime.parse(horairesOuvertureModifResto), LocalTime.parse(horairesFermetureModifResto));
//				
//			 
//		 }
		 
		 private static void supprimerRestaurant() {
				try {
					listeRestaurant = restaurantBll.selectAll();
					if (listeRestaurant.size() == 0) {
						System.out.println("Il n'existe aucun restaurant en base");
						return;
					}
				} catch (BLLException e1) {
					e1.printStackTrace();
				}
				
				System.out.println("Vous avez choisi de supprimer un composant" + "\n");
				System.out.println("Veuillez saisir l'id du composant à supprimer");
				int id = scan.nextInt();
				scan.nextLine();
				
				try {
					restaurantBll.delete(id);
					System.out.println("Le composant a bien été supprimé");
				} catch (BLLException e) {
					System.out.println("L'id saisi n'existe pas en base de données");
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
