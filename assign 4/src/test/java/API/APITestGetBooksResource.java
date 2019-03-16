package API;

import org.junit.ClassRule;
import org.junit.Test;
import static org.mockito.Mockito.*;
import io.dropwizard.testing.junit.ResourceTestRule;
import lnu.dao.booksDAO;
import lnu.models.catalog;
import lnu.resources.GetBooksResource;
import static org.junit.Assert.*;

public class APITestGetBooksResource {

	/*
	 * Mock dependencies 
	 */
    static booksDAO dao = mock(booksDAO.class);
    catalog cata = mock(catalog.class);
    static GetBooksResource getBooksResource = new GetBooksResource(dao);


    
    @ClassRule
    public static final ResourceTestRule res = ResourceTestRule.builder().addResource(getBooksResource).build();

	/*
	 * A test for getting a list of books should respond 200 OK
	 */

    @Test
    public void shouldReturnAllBooksToJSON() throws Exception {
		String JSON = "{\"id\":\"4\",\"title\":\"Test\",\"author\":\"Test\","
				+ "\"price\":\"50\",\"genre\":\"Test\",\"publishDate\":\"2018-9-8\",\"description\":\"Test\"}";
		
        when(dao.ToCatalog()).thenReturn(cata);
        when(cata.convertToJSON()).thenReturn(JSON);

        assertEquals(JSON, res.client().target("/books").request().get(String.class));
        assertEquals(200, res.client().target("/books").request().get().getStatus());
    }

	/*
	 * A test for getting an empty list of books should respond 200 OK
	 */
    @Test
    public void shouldReturnAnEmptyArrayInJSON200OK() throws Exception {
        when(dao.ToCatalog()).thenReturn(cata);
        when(cata.convertToJSON()).thenReturn("[]");

        assertEquals("[]", res.client().target("/books").request().get(String.class));
        assertEquals(200, res.client().target("/books").request().get().getStatus());
    }




}
