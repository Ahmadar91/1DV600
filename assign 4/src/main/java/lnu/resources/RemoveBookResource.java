package lnu.resources;

import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import lnu.dao.*;
import lnu.models.*;

@Produces(MediaType.APPLICATION_JSON)
@Path("/books")
public class RemoveBookResource {

	private booksDAO dao;
	private catalog catalog;

	public RemoveBookResource() {
		dao = new booksDAO();
		catalog = new catalog();
	}
/*
 * Testing Constructor
 */
	public RemoveBookResource(booksDAO dao) {
		this.dao = dao;
	}
	
	/*
	 * A method that deletes a book 
	 */

	@DELETE
	@Path("{book_id}")
	public Response delBook(@PathParam("book_id") String bookId) {
		try {
			catalog = dao.ToCatalog();
			catalog.removeBook(bookId);
			dao.ToXML(catalog);
			return Response.ok().build();
		} catch (Exception e) {
			 return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
}