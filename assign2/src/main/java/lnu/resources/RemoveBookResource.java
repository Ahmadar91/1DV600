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

    private booksDAO dao = new booksDAO();

    @DELETE
    @Path("{book_id}")
    public Response removeBook(@PathParam("book_id") String book_id) throws Exception {
            catalog cata = dao.Tocatalog();
            cata.removeBook(book_id);
            dao.ToXML(cata);
            return Response.ok().build();
    }
}
