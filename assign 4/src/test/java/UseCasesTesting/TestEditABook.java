package UseCasesTesting;

import lnu.resources.*;
import org.junit.Test;
import org.junit.ClassRule;
import static org.junit.Assert.*;
import io.dropwizard.testing.junit.ResourceTestRule;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import static org.mockito.Mockito.*;
import lnu.dao.*;
import lnu.models.*;

public class TestEditABook {

	/*
	 * Mock dependencies
	 */
    static booksDAO dao = mock(booksDAO.class);
    catalog cata = mock(catalog.class);
    static EditBookResource editBookResource = new EditBookResource(dao);


    @ClassRule
    public static final ResourceTestRule res = ResourceTestRule.builder().addResource(editBookResource).build();

    /*
     * Test for modifying a book that exists.
     */
    @Test
    public void shouldModifyAnExistingBook() throws Exception {
    	when(dao.ToCatalog()).thenReturn(cata);
    	String bkTest = "{\"id\":\"4\",\"title\":\"Test\",\"author\":\"Test\","
				+ "\"price\":\"50\",\"genre\":\"Test\",\"publishDate\":\"2018-9-8\",\"description\":\"Test\"}";


        assertEquals(200, res.client().target("/books/11").request().post(Entity.entity(bkTest, MediaType.APPLICATION_JSON)).getStatus());
        verify(cata, times(1)).modifyBook("11", bkTest);
        verify(dao, atLeast(1)).ToXML(cata);
        verify(dao, atLeast(1)).ToCatalog();
    }
    /*
     * Test for modifying a book that doesn't exist
     */
    @Test
    public void shouldntModifyABookthatDoesntExist() throws Exception {
    	when(dao.ToCatalog()).thenReturn(cata);
    	String id = "999";
    	String bkTest = "{\"id\":\"999\",\"title\":\"Test\",\"author\":\"Test\","
				+ "\"price\":\"50\",\"genre\":\"Test\",\"publishDate\":\"2018-9-8\",\"description\":\"Test\"}";
        doThrow(new NotFoundException()).when(cata).modifyBook(id, bkTest);
        assertEquals(404, res.client().target("/books/"+id).request().post(Entity.entity(bkTest, MediaType.APPLICATION_JSON)).getStatus());
        verify(dao, atLeast(1)).ToCatalog();
        verify(cata, times(1)).modifyBook(id, bkTest);
    }

}
