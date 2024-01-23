//package controller;
//
//import java.util.List;
//
//import bll.BLLException;
//import bll.CategorieBLL;
//import bo.Categorie;
//
//public class TestCategorie {
//	public static void main(String[] args) {
//		try {
//			CategorieBLL bll = new CategorieBLL();
//
//			System.out.println("*************************************");
//			System.out.println("tentative ajout d'une categorie de plat :");
//			System.out.println("Boisson");
//
//			bll.insert("Entree");
//			bll.insert("Plat");
//			bll.insert("Dessert");
//			bll.insert("Boisson");
//			System.out.println("*************************************");
//			System.out.println("tentative de selection de toutes les categories");
//			List<Categorie> categories = bll.selectAll();
//			for (Categorie current : categories) {
//				System.out.println(current.getId() + " : " + current.getNom());
//			}
//
//			System.out.println("*************************************");
//			System.out.println("Tentative de récupération d'Entrée par son id");
//			Categorie recup = bll.selectById(2);
//			System.out.println(recup);
//
//			System.out.println("*************************************");
//			System.out.println("Tentative de mise à jour de la categorie de l'id 3");
//			Categorie catToModify = bll.selectById(3);
//			catToModify.setNom("Dessert");
//			bll.update(catToModify);
//			System.out.println(categories);
//			
//			System.out.println("*************************************");
//			System.out.println("Tentative suppresion d'une categorie");
//			 bll.delete(3);
//			 System.out.println(categories);
//
//			
//		
//		
//			
//			
//
//		} catch (BLLException blle) {
//			blle.printStackTrace();
//		}
//
//	}
//
//}
