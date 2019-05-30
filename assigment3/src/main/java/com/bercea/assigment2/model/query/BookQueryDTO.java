package com.bercea.assigment2.model.query;

public class BookQueryDTO {
	private int id;
	private String author;
	private String releaseDate;
	private String genre;
	private int price;
	private boolean available;

	public BookQueryDTO() {}
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public BookQueryDTO(int id, String author, String releaseDate, String genre, int price, boolean available) {
		super();
		this.id = id;
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
