package lnu.models;


public class Book {
	private int id = 0;
	private String title = "";
	private String author = "";
	private String publishDate = "";
	private int price = 0;
	private String description = "";
	private String genre = "";

	public Book() {
	}

	public Book(int id, String author, String title, String genre, int price, String publishDate, String description) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.publishDate = publishDate;
		this.price = price;
		this.description = description;
		this.genre = genre;
	}



	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getAuthor() {
		return author;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getGenre() {
		return genre;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	public String getPublishDate() {
		return publishDate;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getPrice() {
		return price;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}


}
