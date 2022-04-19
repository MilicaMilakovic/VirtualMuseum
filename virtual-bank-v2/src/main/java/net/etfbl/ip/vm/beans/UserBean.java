package net.etfbl.ip.vm.beans;

import java.io.Serializable;

import net.etfbl.ip.vm.dao.CreditCardDAO;
import net.etfbl.ip.vm.dao.UserDAO;
import net.etfbl.ip.vm.dto.CreditCard;
import net.etfbl.ip.vm.dto.User;

public class UserBean implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private User user = new User();
	private CreditCard card;
	private boolean isLoggedIn = false;
	private int cardId;
	
	public UserBean() {
		// TODO Auto-generated constructor stub
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}
	
	
	public CreditCard getCard() {
		return card;
	}

	public void setCard(CreditCard card) {
		this.card = card;
	}
	
	public int getCardId() {
		if(card!=null)
			return card.getId();
		return -1;
	}

	public boolean loginUser(String cardholder, String cardNumber, String password) {
//		System.out.println(cardholder + "  " + cardNumber + "  " + password);
		card = CreditCardDAO.findCard(cardholder, cardNumber, password);
		if(card!= null){
//			System.out.println(card);
			user = UserDAO.getUserById(card.getUserId());
			isLoggedIn = true;
			return true;
		}
		return false;
	}

}
