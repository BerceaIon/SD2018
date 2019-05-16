package businessLogic.Factory;

import java.util.List;

import dao.Model.Book;

public class Genre implements Recommandation{

	@Override
	public Book getRecommandation() {
		System.out.println("Genre recommandation");
		return null;
	}

}
