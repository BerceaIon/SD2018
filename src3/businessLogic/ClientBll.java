package businessLogic;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import businessLogic.exception.InvalidCredentialException;
import dao.ClientDao;
import dao.Model.Client;

public class ClientBll {
	private ClientDao clientDao;
		
	public ClientBll(){
		clientDao = new ClientDao();
	} 
	
	public String login(String username, String password) throws Exception {
		List<Client> clients = new ArrayList<>();
		if(username ==null && username.trim().length()<=0) {
			throw new InvalidCredentialException();
		}	
		clients = clientDao.searchClient(username);
		boolean found = false;
		for(int i=0; i<clients.size(); i++) {
			if((clients.get(i)).getPassword().equals(password)) {
				found = true;
			}
		} 
		if(!found) {
			throw new InvalidCredentialException();
		}
		return "Successful login";
	}
	
	public String createAccount(String firstName, String lastName, String password, String username, int wallet) throws Exception {
		if(firstName==null && firstName.trim().length()<=0 &&
				   lastName==null && lastName.trim().length()<=0 &&
				   username == null && username.trim().length()<=0 &&
				   password==null && password.trim().length()<=0 
				    && wallet <=0) {
			throw new InvalidCredentialException();
		}
		clientDao.insertClient(new Client(firstName, lastName, 0, username, password, wallet));
		return "Account created";
	}
}
