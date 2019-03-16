package API;

import org.junit.ClassRule;
import org.junit.Test;
import static org.mockito.Mockito.*;
import javax.ws.rs.NotFoundException;
import io.dropwizard.testing.junit.ResourceTestRule;
import lnu.dao.booksDAO;
import lnu.models.catalog;
import lnu.resources.*;
import static org.junit.Assert.*;

public class APITestRemoveBookResource {

	/*
	 * Mock dependencies 
	 */
	static booksDAO mockedDao = mock(booksDAO.class);
    catalog mockedCata = mock(catalog.class);
    static RemoveBookResource removeBookResource = new RemoveBookResource(mockedDao);
	

    @ClassRule
    public static final ResourceTestRule res = ResourceTestRule.builder().addResource(removeBookResource).build();

	/*
	 * A test for removing a book that exists with an id of 2 should respond 200 OK
	 */
    @Test
    public void shouldRemoveABook() throws Exception {
        when(mockedDao.ToCatalog()).thenReturn(mockedCata);
        assertEquals(200, res.client().target("/books/2").request().delete().getStatus());
    }
	/*
	 * A test for deleting a book that doesn't exist with an id of 999 should respond 404 Not Found
	 */
    @Test
    public void shouldThrowExceptionwith404WhenRemovingAbookNotInTheList() throws Exception {
        when(mockedDao.ToCatalog()).thenReturn(mockedCata);
        doThrow(new NotFoundException()).when(mockedCata).removeBook("999");
        assertEquals(404, res.client().target("/books/999").request().delete().getStatus());

    }
}