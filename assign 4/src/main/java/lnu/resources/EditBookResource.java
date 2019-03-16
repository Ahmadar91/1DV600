package lnu.resources;

import lnu.models.*;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import lnu.dao.*;

@Produces(MediaType.APPLICATION_JSON)
@Path("/books")
public class EditBookResource {

	private booksDAO dao;

	/*
	 * Constructor
	 */
	public EditBookResource() {
		dao = new booksDAO();
	}

	/*
	 * Testing Constructor
	 */

	public EditBookResource(booksDAO dao) {
		this.dao = dao;
	}

	/*
	 * a method that Edits a book if the provided json and bookId are valid and
	 * respond with 200 OK
	 * 
	 * 
	 * else respond with 404 Not Found
	 */
	@POST
	@Path("{book_id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response modifyBook(@PathParam("book_id") String bookId, String json) {
		try {
			catalog catalog = dao.ToCatalog();
			catalog.modifyBook(bookId, json);
			dao.ToXML(catalog);
			return Response.ok().build();

		} catch (Exception e) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
}