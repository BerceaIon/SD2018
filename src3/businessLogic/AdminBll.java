package businessLogic;

import java.util.ArrayList;
import java.util.List;

import businessLogic.exception.InvalidCredentialException;
import dao.AdminDao;
import dao.Model.Admin;

public class AdminBll {
	private AdminDao adminDao;
	
	public AdminBll() {
		adminDao = new AdminDao();
	} 
	
	public String login(String username, String password) throws Exception {
		List<Admin> admins= new ArrayList<>();
		if(username ==null && username.trim().length()<=0) {
			throw new InvalidCredentialException();
		}	
		admins = adminDao.searchAdmin(username);
		boolean found = false;
		for(int i=0; i<admins.size(); i++) {
			if((admins.get(i)).getPassword().equals(password)) {
				found = true;
			}
		}
		if(!found) {
			throw new InvalidCredentialException();
		}
		return "Successful login";
	}
}
