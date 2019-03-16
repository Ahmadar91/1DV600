package lnu.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import lnu.dao.*;

@Produces(MediaType.APPLICATION_JSON)
@Path("/books")
public class GetBookResource {

	private booksDAO dao;

	/*
	 * Constructor
	 */

	public GetBookResource() {
		dao = new booksDAO();
	}

	/*
	 * Testing Constructor
	 */

	public GetBookResource(booksDAO dao) {
		this.dao = dao;
	}

	/*
	 * 
	 * A method that gets a book and respond with 200 OK if the provided bookId is valid 
	 * 
	 * 
	 * else respond with 404 Not Found
	 */

	@GET
	@Path("{book_id}")
	public Response getBook(@PathParam("book_id") String bookId) {
		try {
			return Response.ok(dao.ToCatalog().searchById(bookId), MediaType.APPLICATION_JSON).build();

		} catch (Exception e) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
}