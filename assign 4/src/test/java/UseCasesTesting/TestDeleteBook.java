package UseCasesTesting;

import lnu.resources.*;
import org.junit.Test;
import org.junit.ClassRule;
import static org.junit.Assert.*;
import io.dropwizard.testing.junit.ResourceTestRule;
import javax.ws.rs.NotFoundException;
import static org.mockito.Mockito.*;
import lnu.dao.*;
import lnu.models.*;

public class TestDeleteBook {

    /* Mock  dependencies */
     static booksDAO dao = mock(booksDAO.class);
     catalog cata = mock(catalog.class);
     static RemoveBookResource removeBookResource = new RemoveBookResource(dao);


    @ClassRule
    public static final ResourceTestRule res = ResourceTestRule.builder().addResource(removeBookResource).build();

	/*
	 * Test for removing a book that exists
	 */
    @Test
    public void shouldRemoveAnExistedBook() throws Exception {
    	when(dao.ToCatalog()).thenReturn(cata);
        assertEquals(200, res.client().target("/books/2").request().delete().getStatus());
        verify(dao, atLeast(1)).ToCatalog();
        verify(cata, times(1)).removeBook("2");
        verify(dao, atLeast(1)).ToXML(cata);
    }
	/*
	 * Test deleting (not existed book)
	 */
    @Test
    public void shouldRespond404WhenDeletingNotExistedBook() throws Exception {
    	when(dao.ToCatalog()).thenReturn(cata);
        doThrow(new NotFoundException()).when(cata).removeBook("890");
        assertEquals(404, res.client().target("/books/890").request().delete().getStatus());
        verify(dao, atLeast(1)).ToCatalog();
        verify(cata, times(1)).removeBook("890");
    }
}