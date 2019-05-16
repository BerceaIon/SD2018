package businessLogic.Factory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import dao.BookDao;
import dao.Model.Book;

public class Price implements Recommandation {
	private static final int PRICE = 30;
	@Override
	public Book getRecommandation() {
		
		List<Book> books = new ArrayList<>();
		List<Book> booksFiltered = new ArrayList<>();
		Book randomTrend = null;
		Random random = new Random();
		try {
			BookDao bookDao = new BookDao();
			books = bookDao.getBooksByPrice(PRICE);
			for(Book b:books) {
				int randomIndex = random.nextInt(books.size());
				randomTrend = books.get(randomIndex);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(randomTrend.toString());
		return randomTrend;

	}

}
