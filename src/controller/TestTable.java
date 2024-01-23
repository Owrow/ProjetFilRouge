package controller;

import bll.BLLException;
import bll.PlatCarteBLL;
import bo.PlatCarte;

public class TestTable {
public static void main(String[] args) throws BLLException {
//	TableBLL tableBll = new TableBLL();
//	tableBll.insert(1,1,1);
	
	PlatCarteBLL platCarteBll = new PlatCarteBLL();
	PlatCarte platCarte = platCarteBll.selectByPlatCarte(1);
	
	System.out.println(platCarte);
	
}
	
}
