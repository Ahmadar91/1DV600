package UseCasesTesting;

import lnu.resources.*;
import org.junit.Test;
import org.junit.ClassRule;
import static org.junit.Assert.*;
import io.dropwizard.testing.junit.ResourceTestRule;
import java.io.IOException;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import static org.mockito.Mockito.*;
import lnu.dao.*;
import lnu.models.*;

public class TestAddABook {

	/*
	 * Mock dependencies
	 */
	static booksDAO dao = mock(booksDAO.class);
	catalog cata = mock(catalog.class);
	static AddBookResource addBookResource = new AddBookResource(dao);



	@ClassRule
	public static final ResourceTestRule res = ResourceTestRule.builder().addResource(addBookResource).build();

	/*
	 * Test for adding a book
	 */
	@Test
	public void shouldAddABook() throws Exception {
		when(dao.ToCatalog()).thenReturn(cata);
    	String bkTest = "{\"id\":\"4\",\"title\":\"Test\",\"author\":\"Test\","
				+ "\"price\":\"50\",\"genre\":\"Test\",\"publishDate\":\"2018-9-8\",\"description\":\"Test\"}";


		assertEquals(200, res.client().target("/books").request().put(Entity.entity(bkTest, MediaType.APPLICATION_JSON)).getStatus());		
		verify(dao, atLeast(1)).ToCatalog();
		verify(cata, times(1)).addBook(bkTest);
		verify(dao, atLeast(1)).ToXML(cata);
	}
	
	/*
	 * Test for adding a book without any details.
	 */
	@Test
	public void shouldntAddABookWithNoDetails() throws Exception {
		when(dao.ToCatalog()).thenReturn(cata);
		doThrow(new IOException()).when(cata).addBook("");
		assertEquals(404, res.client().target("/books").request().put(Entity.entity("", MediaType.APPLICATION_JSON)).getStatus());
		verify(dao, atLeast(1)).ToCatalog();
		verify(cata, times(1)).addBook("");
	}
}

