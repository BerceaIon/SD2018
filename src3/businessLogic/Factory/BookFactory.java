package businessLogic.Factory;

import java.util.List;

import dao.Model.Book;

public class BookFactory {
	public Recommandation createRecommandation(String type) {
		if(type.equals("genre")) {
			return new Genre();
		}
		if(type.equals("price")) {
			return new Price();
		}
		return null;
	}
	public static void main(String args[]) {
		BookFactory bookFactory = new BookFactory();
		Recommandation recomandation1 = bookFactory.createRecommandation("trends");
		Recommandation recomandation2 = bookFactory.createRecommandation("price");
		//recomandation1.getRecommandation();
		//Book b = recomandation1.getRecommandation();
		//recomandation2.getRecommandation();
	}
}
