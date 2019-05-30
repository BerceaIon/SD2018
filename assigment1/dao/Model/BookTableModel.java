package dao.Model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import dao.Model.Book;

public class BookTableModel extends AbstractTableModel {

	private static final int TITLE_COL = 0;
	private static final int RELEASE_DATE_COL = 1;
	private static final int AUTHOR_COL = 2;
	private static final int GENRE_COL = 3;
	private static final int NOOFBOOKS_COL = 4;
	private static final int PRICE_COL = 5;
	
	private String[] columnNames = {"title","releaseDate", "author",
			"genre", "noOfBooks","price"};
	private List<Book> books;
	
	public BookTableModel(List<Book> theBook) {
		books = theBook;
	}
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public int getRowCount() {
		return books.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		Book tempBook = books.get(row);
		
		switch(col) {
		case TITLE_COL:
			return tempBook.getTitle();
		case RELEASE_DATE_COL:
			return tempBook.getReleaseDate();
		case AUTHOR_COL:
			return tempBook.getAuthor();
		case GENRE_COL:
			return tempBook.getGenre();
		case NOOFBOOKS_COL:
			return tempBook.getNoOfBooks();
		case PRICE_COL:
			return tempBook.getPrice();
		default:
			return tempBook.getAuthor();
		}
	}

}
