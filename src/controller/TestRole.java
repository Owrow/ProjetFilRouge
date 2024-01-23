package controller;

import bll.BLLException;
import bll.RoleBLL;
import bo.Role;

public class TestRole {
public static void main(String[] args) {
	
	try {
		RoleBLL rolebll = new RoleBLL();
		Role role = new Role();

		rolebll.insert("USER");
		
	} catch (BLLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	

}
}
