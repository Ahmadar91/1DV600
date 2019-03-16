package lnu.resources;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lnu.models.Book;

// Response will be JSON
@Produces(MediaType.APPLICATION_JSON)

// This is the class that will be called when "localhost:9090/api/books" get
// called by the browser.
// FOR THE CURIOUS: Take a look in the config.yml to find out why "/api" is
// present in the path.
@Path("/books")
public class GetBooksResource {

	@GET
	public String getBooks() throws JsonProcessingException {

		ArrayList<Book> list = new ArrayList<Book>();
		Book book1 = new Book();
		
		book1.setTitle("a song of ice and fire");
		book1.setId(120021);
		book1.setAuthor("Geroge R.R");
		book1.setGenre("Fantasy");
		book1.setDescription("Nice book");
		book1.setPublishDate("1991");
		book1.setPrice(600);
		
	
		Book book2 = new Book();
		
		book2.setTitle("Illidan: World of Warcraft");
		book2.setId(2);
		book2.setAuthor("William King");
		book2.setGenre("fantasy");
		book2.setDescription("world of warcraft");
		book2.setPublishDate("2016");
		book2.setPrice(100);

		
		Book book3 = new Book();
		
		book3.setTitle("World of Warcraft: War Crimes");
		book3.setId(3);
		book3.setAuthor("Christie Golden");
		book3.setGenre("fantasy");
		book3.setDescription("world of warcraft");
		book3.setPublishDate("2014");
		book3.setPrice(230);
	
	
		
		

		list.add(book1);
		list.add(book2);
		list.add(book3);

		 for (Book book : list) {
		 System.out.println(book);
		 }


		 ObjectMapper tojson = new ObjectMapper();
		 for (Book book : list) {
		 System.out.println(tojson.writeValueAsString(book));
		 }


		String str = "";


		 for (Book b : list) {
		 str += tojson.writeValueAsString(b);
		 }




		return "[" + str + "]";

	}
}



