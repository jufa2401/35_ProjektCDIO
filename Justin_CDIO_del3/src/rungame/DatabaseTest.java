package rungame;

import java.util.ArrayList;

import boundary.dao.BreweryDAO;
import boundary.dao.PlayerDAO;
import boundary.dao.StreetDAO;
import daoimplementation.MySQLBreweryDAO;
import daoimplementation.MySQLPlayerDAO;
import daoimplementation.MySQLStreetDAO;
import entity.PlayerDTO;
import entity.fieldclasses.BreweryDTO;
import entity.fieldclasses.StreetDTO;

public class DatabaseTest {

	public static void main(String[] args) {
		new DatabaseTest().go();
	}
	
	public void go(){
		StreetDAO dao = new MySQLStreetDAO();
		ArrayList<StreetDTO> streetlist= dao.getStreet();
		System.out.println(streetlist + "\n");
		
		PlayerDAO Playerdao = new MySQLPlayerDAO();
	    int playercount = Playerdao.getPlayerCount();
		System.out.println(playercount + "\n");
		
		BreweryDAO Brewerydao = new MySQLBreweryDAO();
		ArrayList<BreweryDTO> brewerylist= Brewerydao.getBrewery();
		System.out.println(brewerylist + "\n");
		
		StreetDAO dao1 = new MySQLStreetDAO();
		StreetDTO street = dao.get1Street();
		System.out.println(street);
		
		
	}
}
