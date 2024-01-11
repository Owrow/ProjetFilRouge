package controller;

import java.util.ArrayList;
import java.util.List;

import bll.BLLException;
import bll.CarteBLL;
import bo.Carte;

public class TestInsertionCarte {
	public static void main(String[] args) {
			try {
				
				Carte carte = new Carte();
				List<Carte> cartes = new ArrayList<Carte>();
				CarteBLL carteBLL = new CarteBLL();
				

//				Carte carteaAjouter = carteBLL.insert("ete");

//				cartes = carteBLL.selectAll();
//				System.out.println(cartes);
				
				carte = carteBLL.selectById(2);			
				System.out.println(carte);
				int id = 2;
				String nom = "ete";
				
				Carte carteAUpdate = carteBLL.selectById(id);
				carteAUpdate.setNom(nom);
				
				carteBLL.update(carteAUpdate);
				carte = carteBLL.selectById(2);		
				System.out.println(carte);
				
				carteBLL.delete(1);
				
			} catch (BLLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
