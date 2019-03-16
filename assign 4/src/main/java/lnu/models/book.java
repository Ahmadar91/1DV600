package lnu.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "book")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "author", "title", "genre", "price", "publish_date", "description" })
public class book {
	@XmlAttribute
	private String id;
	private String title = "";
	private String author = "";
	private String publish_date = "";
	private String price = "";
	private String description = "";
	private String genre = "";

	/*
	 * Constructors
	 */
	public book() {
	}

	
	public book(String id, String title, String author, String publish_date, String price, String description,
			String genre) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.publish_date = publish_date;
		this.price = price;
		this.description = description;
		this.genre = genre;
	}

	/*
	 * the set and get methods for id,title,author,genre,publish
	 * date,price,Description
	 */
	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
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

	public void setPublish_date(String publishDate) {
		this.publish_date = publishDate;
	}

	public String getPublish_date() {
		return publish_date;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getPrice() {
		return price;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
