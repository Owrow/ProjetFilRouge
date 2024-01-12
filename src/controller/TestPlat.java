package controller;

import java.util.List;
import java.util.Scanner;

import bll.BLLException;
import bll.PlatBLL;
import bo.Categorie;
import bo.Plat;

public class TestPlat {
	private static Scanner scan;
	private static PlatBLL bll;

	public static void main(String[] args) {

		scan = new Scanner(System.in);
		try {
			bll = new PlatBLL();
		} catch (BLLException e) {
			e.printStackTrace();
		}

		System.out.println("Veuillez choisir l'action à réaliser");

		int choix;
		do {
			choix = afficherMenu();

			switch (choix) {
			case 1:
				creerPlat();
				break;
			case 2:
				listerPlats();
				break;
			case 3:
				supprimerPlat();
				break;
			case 4:
				System.out.println("Byebye");
				break;
			default:
				System.out.println("Saisie non valide");
				break;
			}
		} while (choix != 4);

		scan.close();

	}

	private static void supprimerPlat() {
		try {
			List<Plat> plats = bll.selectAll();
			if (plats.size() == 0) {
				System.out.println("Il n'existe aucun plat en base");
				return;
			}
		} catch (BLLException e1) {
			e1.printStackTrace();
		}

		System.out.println("Vous avez choisi de supprimer un plat");
		System.out.println("Veuillez saisir l'id du plat à supprimer");
		int id = scan.nextInt();
		scan.nextLine();

		try {
			bll.delete(id);
			System.out.println("Le plat a bien été supprimé");
		} catch (BLLException e) {
			System.out.println("L'id saisi n'existe pas en db");
		}
	}

	private static void listerPlats() {
		try {
			List<Plat> plats = bll.selectAll();
			for (Plat current : plats) {
				System.out.println("\t" + current.getId() + ". " + current);
			}
		} catch (BLLException e) {
			e.printStackTrace();
		}
	}

	private static void creerPlat() {
		System.out.println("Vous avez choisi d'ajouter un plat");

		System.out.println("Veuillez saisir son nom");
		String nom = scan.nextLine();

		System.out.println("Veuillez une description");
		String description_plat = scan.nextLine();

		System.out.println("Veuillez saisir le prix du plat");
		Float prix = Float.parseFloat(scan.nextLine());

		System.out.println("Veuillez saisir l'ID de la catégorie du plat");
		String input = scan.nextLine();
		Categorie categorie = null;

		int catId = Integer.parseInt(input);
		categorie = new Categorie();
		categorie.setId(catId);

		try {

			Plat platAjoute = bll.insert(nom, description_plat, prix, categorie);
			System.out.println("Composant ajouté " + platAjoute);
		} catch (BLLException e) {

			e.printStackTrace();
		}
	}

	private static int afficherMenu() {
		System.out.println("1. Créer un plat");
		System.out.println("2. Consulter les plats");
		System.out.println("3. Supprimer un plat");
		System.out.println("4. Quitter");
		int choix = scan.nextInt();
		scan.nextLine();
		return choix;
	}
}
