package lnu.models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.ws.rs.NotFoundException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@XmlRootElement(name = "catalog")
@XmlAccessorType(XmlAccessType.FIELD)
public class catalog {

	@XmlElement(name = "book")
	public ArrayList<book> bookslist;
	private static ObjectMapper mapper = new ObjectMapper();;

	/*
	 * Constructor
	 */
	public catalog() {
		bookslist = new ArrayList<book>();
	}

	/*
	 * Testing Constructor
	 */
	public catalog(ArrayList<book> bookslist) {
		this.bookslist = bookslist;
		catalog.mapper = new ObjectMapper();
	}


	/*
	 * conversion method that edit the publish date and convert the list of books
	 * into JSON object
	 * 
	 */
	public String convertToJSON() throws Exception {
		return Publish_dateTopublishDate(catalog.mapper.writeValueAsString(this.bookslist));

	}

	/*
	 * method that removes a book from the list
	 */
	public void removeBook(String id) throws NotFoundException {
		try {
			book bk = getIdReturnbook(id);
			bookslist.remove(bk);
		} catch (Exception e) {
			e.getMessage();
		}
	}

	/*
	 * method that Adds a book to the list
	 */
	public void addBook(String json) throws IOException {
		book bk = catalog.mapper.readValue(json, book.class);
		bk.setId(this.greatestId());
		bookslist.add(bk);
	}

	/*
	 * method that modifies a book
	 */
	public void modifyBook(String id, String json) throws NotFoundException, IOException {

		book bk1 = this.getIdReturnbook(id);
		book bk2 = JSONToBookConversion(json);
		bk1.setAuthor(bk2.getAuthor());
		bk1.setTitle(bk2.getTitle());
		bk1.setGenre(bk2.getGenre());
		bk1.setPrice(bk2.getPrice());
		bk1.setPublish_date(bk2.getPublish_date());
		bk1.setDescription(bk2.getDescription());
	}

	/*
	 * conversion method that edit publishDate to publish_date and Convert JSON
	 * object into book object
	 */
	public book JSONToBookConversion(String json) throws IOException {
		return catalog.mapper.readValue(json.replaceAll("publishDate", "publish_date"), book.class);
	}
	
	public ArrayList<book> getBooks() {
		return bookslist;
	}

	/*
	 * method that gets the book by Id and returns a book Object throw
	 * NotFoundException if the book id is not in the list
	 */
	public book getIdReturnbook(String id) throws NotFoundException {
		for (int i = 0; i < bookslist.size(); i++) {
			if (bookslist.get(i).getId().equals(id)) {
				return (bookslist.get(i));
			}
		}
		throw new NotFoundException();
	}

	/*
	 * method that gets a book by Id and return a String JSON object Throw
	 * NotFoundException if the book is not in the list
	 */
	public String getIdReturnString(String id) throws JsonProcessingException {
		for (int i = 0; i < bookslist.size(); i++) {
			if (bookslist.get(i).getId().equals(id)) {
				return catalog.mapper.writeValueAsString(bookslist.get(i));
			}
		}
		throw new NotFoundException();
	}

	/*
	 * method that Search a book by its Id
	 */
	public String searchById(String id) throws IOException, NotFoundException {
		return Publish_dateTopublishDate(catalog.mapper.writeValueAsString(this.getIdReturnbook(id)));
	}

	/*
	 * a method that convert the publish_date to publishDate because it is written
	 * publishDate in the front-end this is causing a problem to the publish date in
	 * the http://localhost:9090/#/books/id. it is always empty but it can be viewed
	 * in the API
	 */
	private String Publish_dateTopublishDate(String json) {
		return json.replaceAll("publish_date", "publishDate");
	}

	/*
	 * a method that gives a new book a new id. it gives the new book an id of the
	 * total of The Id of the last book in the list + 1 and returns and id "1" if
	 * the list is empty
	 * 
	 */
	public String greatestId() {
		Collections.sort(bookslist, new Comparator<book>() {
			@Override
			public int compare(book book1, book book2) {
				return Integer.parseInt(book1.getId()) - Integer.parseInt(book2.getId());
			}
		});
		if (bookslist.isEmpty()) {
			return "1";
		} else {
			return "" + (Integer.parseInt(bookslist.get(bookslist.size() - 1).getId()) + 1);
		}
	}

}
