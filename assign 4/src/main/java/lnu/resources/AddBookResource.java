package lnu.resources;

import javax.ws.rs.*;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//import bookDAOTest.booksDAOTest;
import lnu.dao.booksDAO;
import lnu.models.catalog;

@Produces(MediaType.APPLICATION_JSON)
@Path("/books")
public class AddBookResource {
	booksDAO dao;
	catalog catalog;

	/*
	 * Constructor
	 */

	public AddBookResource() {
		dao = new booksDAO();
	}

	/*
	 * Testing Constructor
	 */

	public AddBookResource(booksDAO dao) {
		this.dao = dao;
	}

	/*
	 * a method that adds a book if the provided json is valid and response with 200
	 * OK
	 * 
	 * else response with 404 Not Found
	 */

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addBook(String json) {
		try {
			catalog catalog = dao.ToCatalog();
			catalog.addBook(json);
			dao.ToXML(catalog);
			return Response.ok().build();
		} catch (Exception e) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
}
