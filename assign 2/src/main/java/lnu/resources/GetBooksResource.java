package lnu.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import lnu.dao.*;
import lnu.models.*;

@Produces(MediaType.APPLICATION_JSON)
@Path("/books")
public class GetBooksResource {
private catalog catalog = new catalog();
	private booksDAO dao = new booksDAO();

	@GET
	public String getBooks() throws Exception {
		catalog = dao.Tocatalog();
		return  catalog.ConvertToJSON();
	}
}
