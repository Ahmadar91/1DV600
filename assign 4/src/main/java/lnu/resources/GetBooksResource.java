package lnu.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import lnu.dao.*;
import lnu.models.*;

;
@Produces(MediaType.APPLICATION_JSON)
@Path("/books")
public class GetBooksResource {

	private booksDAO dao;
	private catalog catalog;


	
	/*
	 * Constructor
	 */
	
	public GetBooksResource() {
		dao = new booksDAO();
		catalog = new catalog();
	}
	/*
	 * Testing constructor
	 */

	public GetBooksResource(booksDAO dao) {
		this.dao = dao;
	}

	/*
	 *  if the provided json ,Get list of book(s) and respond with 200 OKis valid
	 *  else respond with 404 Not Found
	 */
	@GET
	public String getBooks() throws Exception {
		try {
			catalog = dao.ToCatalog();
			return catalog.convertToJSON();
		} catch (Exception e) {
			return catalog.convertToJSON();
		}
	}
	
}