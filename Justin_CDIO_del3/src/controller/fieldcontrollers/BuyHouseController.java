/**
 * 
 */
package controller.fieldcontrollers;

import model.PlayerDTO;
import model.fieldclasses.GameBoardDTO;
import model.fieldclasses.StreetDTO;
import model.language.LanguageHandler;
import view.GUIHandler;

/**
 * Kun relevant for streetcontroller
 * @author Justin
 *
 */
public enum BuyHouseController { ASKBUY, EXIT, CHECKBUY, BUY, NOMONEY,TOOMANYHOUSES,ERROR;
	
	public static void BuyHouseState(GUIHandler GUIh, LanguageHandler language, PlayerDTO player, GameBoardDTO gb) {
		BuyHouseController state = ASKBUY;
		int streetnumber = -1;

		while (state != EXIT) {
			switch (state) {
			case ASKBUY:
				state = CHECKBUY;
				if (GUIh.getYesNo(language.askBuyHouse(player.getName()), language.move(), language.buy()))
					state = EXIT; 
				break;
			case CHECKBUY:
				streetnumber = GUIh.buyHouse(gb, language.promptGroup(), player);
				switch (streetnumber) {
				case -1: state = ERROR;				break;
				case -2: state = NOMONEY;			break;
				case -3: state = TOOMANYHOUSES;		break;
				default: state = BUY;				break;
				}
				break;
			case BUY:
				GUIh.setBalance(player.getName(), player.getBalance());
				final StreetDTO sfield = (StreetDTO) gb.getField(streetnumber);
				GUIh.setHouses(streetnumber, sfield.getHouses());
				GUIh.getButtonPressed(language.SuccessfulHousePurchase(sfield.getRent(), sfield.getName()), language.Ok());
				state = ASKBUY;
				break;
			case NOMONEY:
				GUIh.getButtonPressed(language.failedHouseNoMoney(), language.Ok());
				state = ASKBUY;
				break;
			case TOOMANYHOUSES:
				GUIh.getButtonPressed(language.failedHouseNoHouses(), language.Ok());
				state = ASKBUY;
				break;
			case ERROR:
				final Exception e = new RuntimeException();
				e.printStackTrace();
				
			default: state= ASKBUY;
			}
		}
	}
}
