package net.etfbl.ip.vm.beans;

import java.io.Serializable;
import java.util.ArrayList;

import net.etfbl.ip.vm.dao.TransactionDAO;
import net.etfbl.ip.vm.dto.Transaction;

public class TransactionBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private int cardID;
	
	public TransactionBean() {
		// TODO Auto-generated constructor stub
	}
	
	
	public int getCardID() {
		return cardID;
	}


	public void setCardID(int cardID) {
		this.cardID = cardID;
	}


	public ArrayList<Transaction> getTransactions(int cardId){
		return TransactionDAO.getCardTransactions(cardId);
	}

}
