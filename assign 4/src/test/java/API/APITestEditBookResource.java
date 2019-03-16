package API;

import org.junit.ClassRule;
import org.junit.Test;
import static org.mockito.Mockito.*;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import io.dropwizard.testing.junit.ResourceTestRule;
import lnu.dao.booksDAO;
import lnu.models.catalog;
import lnu.resources.*;
import static org.fest.assertions.Assertions.assertThat;

public class APITestEditBookResource {

	/*
	 * Mock dependencies 
	 */
	static booksDAO dao = mock(booksDAO.class);
	catalog cata = mock(catalog.class);
	static EditBookResource editBookResource = new EditBookResource(dao);


	@ClassRule
	public static final ResourceTestRule res = ResourceTestRule.builder().addResource(editBookResource).build();

	/*
	 *A test for modifying a book should return 200 OK.
	 */
	@Test
	public void shouldModifyABook() throws Exception {
		when(dao.ToCatalog()).thenReturn(cata);
		String bkTest = "{\"id\":\"4\",\"title\":\"Test\",\"author\":\"Test\","
				+ "\"price\":\"50\",\"genre\":\"Test\",\"publishDate\":\"2018-9-8\",\"description\":\"Test\"}";
		
		assertThat(res.client().target("/books/2").request().post(Entity.entity(bkTest, MediaType.APPLICATION_JSON))
				.getStatus()).isEqualTo(200);

		verify(dao, atLeast(1)).ToCatalog();
		verify(cata, times(1)).modifyBook("2", bkTest);
		verify(dao, atLeast(1)).ToXML(cata);
	}

}