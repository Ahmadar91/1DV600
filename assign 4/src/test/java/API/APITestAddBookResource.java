package API;

import org.junit.Test;
import org.junit.ClassRule;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import io.dropwizard.testing.junit.ResourceTestRule;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import lnu.dao.booksDAO;
import lnu.models.*;
import lnu.resources.AddBookResource;

public class APITestAddBookResource {
	/*
	 * Mock dependencies
	 */
	static booksDAO dao = mock(booksDAO.class);
	catalog cata = mock(catalog.class);
	static AddBookResource addBookResource = new AddBookResource(dao);


	@ClassRule
	public static final ResourceTestRule res = ResourceTestRule.builder().addResource(addBookResource).build();

	/*
	 * A test for adding a new book that doesn't exist should return 200 OK.
	 */
	@Test
	public void shouldAddABook() throws Exception {
		when(dao.ToCatalog()).thenReturn(cata);
		String bkTest = "{\"id\":\"4\",\"title\":\"Test\",\"author\":\"Test\","
				+ "\"price\":\"50\",\"genre\":\"Test\",\"publishDate\":\"2018-9-8\",\"description\":\"Test\"}";

		assertThat(res.client().target("/books").request().put(Entity.entity(bkTest, MediaType.APPLICATION_JSON))
				.getStatus()).isEqualTo(200);
	}

}