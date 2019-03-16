package UseCasesTesting;

import lnu.resources.GetBookResource;
import org.junit.Test;
import org.junit.ClassRule;
import static org.junit.Assert.*;
import io.dropwizard.testing.junit.ResourceTestRule;
import static org.mockito.Mockito.*;
import javax.ws.rs.NotFoundException;
import lnu.dao.*;
import lnu.models.*;

public class TestViewABook {

	/*
	 * Mocking the dependencies
	 */
	static booksDAO dao = mock(booksDAO.class);
	catalog cata = mock(catalog.class);
	static GetBookResource getBookResource = new GetBookResource(dao);


	@ClassRule
	public static final ResourceTestRule res = ResourceTestRule.builder().addResource(getBookResource).build();

    /*
     * Test for viewing a book that exists
     */
	@Test
	public void shouldViewAnExistingBook() throws Exception {
		when(dao.ToCatalog()).thenReturn(cata);
    	String bkTest = "{\"id\":\"4\",\"title\":\"Test\",\"author\":\"Test\","
				+ "\"price\":\"50\",\"genre\":\"Test\",\"publishDate\":\"2018-9-8\",\"description\":\"Test\"}";

		when(cata.searchById("2")).thenReturn(bkTest);
		assertEquals(bkTest, res.client().target("/books/2").request().get(String.class));
		assertEquals(200, res.client().target("/books/2").request().get().getStatus());
	}
    /*
     * Test for viewing a book that doesn't exist
     */
	@Test
	public void shouldntViewABookThatDoesntExist() throws Exception {
		when(dao.ToCatalog()).thenReturn(cata);
		String id = "999";
		when(cata.searchById(id)).thenThrow(new NotFoundException());
		assertEquals(404, res.client().target("/books/"+id).request().get().getStatus());
	}
}