package UseCasesTesting;

import lnu.resources.GetBooksResource;
import org.junit.Test;
import org.junit.ClassRule;
import static org.junit.Assert.*;
import io.dropwizard.testing.junit.ResourceTestRule;
import lnu.dao.*;
import lnu.models.*;
import static org.mockito.Mockito.*;

public class TestViewBookList {

	/*
	 * Mock dependencies
	 */
	
	static booksDAO dao = mock(booksDAO.class);
	catalog cata = mock(catalog.class);
	static GetBooksResource getBooksResourceut = new GetBooksResource(dao);


	@ClassRule
	public static final ResourceTestRule res = ResourceTestRule.builder().addResource(getBooksResourceut).build();

	/*
	 * Test for viewing the book list
	 */
	
	@Test
	public void shouldViewABookList() throws Exception {
		when(dao.ToCatalog()).thenReturn(cata);
		String bkTest = "{\"id\":\"4\",\"title\":\"Test\",\"author\":\"Test\","
				+ "\"price\":\"50\",\"genre\":\"Test\",\"publishDate\":\"2018-9-8\",\"description\":\"Test\"}";

		when(cata.convertToJSON()).thenReturn(bkTest);
		assertEquals(bkTest, res.client().target("/books").request().get(String.class));
		assertEquals(200, res.client().target("/books").request().get().getStatus());
	}

	/*
	 * Test for viewing an empty list of books
	 */
	
	@Test
	public void shouldViewAnEmptyBooksList() throws Exception {
		when(dao.ToCatalog()).thenReturn(cata);
		when(cata.convertToJSON()).thenReturn("[]");
		assertEquals("[]", res.client().target("/books").request().get(String.class));
		assertEquals(200, res.client().target("/books").request().get().getStatus());
	}
}
