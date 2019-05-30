package com.bercea.assigment2.model.command;

public class BookCommandDTO {

	private String author;
	private String releaseDate;
	private String genre;
	private int price;
	private boolean available;

	public BookCommandDTO() {}
	
	public BookCommandDTO(String author, String releaseDate, String genre, int price, boolean available) {
		super();
		this.author = author;
		this.releaseDate = releaseDate;
		this.genre = genre;
		this.price = price;
		this.available = available;
	}



	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	

}
