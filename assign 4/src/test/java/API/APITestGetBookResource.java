package API;

import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import io.dropwizard.testing.junit.ResourceTestRule;
import javax.ws.rs.NotFoundException;
import lnu.dao.booksDAO;
import lnu.models.*;
import lnu.resources.GetBookResource;

public class APITestGetBookResource {

	/*
	 * Mock dependencies 
	 */
	static booksDAO dao = mock(booksDAO.class);
	catalog cata = mock(catalog.class);
	static GetBookResource getBookResource = new GetBookResource(dao);

	
	@ClassRule
	public static final ResourceTestRule res = ResourceTestRule.builder().addResource(getBookResource).build();

	/*
	 * A test for getting a book that doesn't exist in the list should respond 404 Not Found.
	 */
	
	@Test
	public void shouldRespond404WhenBookIsNotExisted() throws Exception {

		when(cata.searchById("2")).thenThrow(new NotFoundException());

		assertEquals(404, res.client().target("/books/999").request().get().getStatus());

	}

}