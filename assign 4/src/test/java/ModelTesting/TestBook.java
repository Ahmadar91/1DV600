package ModelTesting;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import lnu.models.*;

public class TestBook {

	private book book;

	@Before
	public void setUp() {
		book = new book();
	}

	@Test
	public void TestSetandGetId() {
		book.setId("1");
		assertEquals("1", book.getId());
	}

	@Test
	public void TestSetandGetTitle() {
		book.setTitle("Area X");
		assertEquals("Area X", book.getTitle());
	}

	@Test
	public void TestSetandGetAuthor() {
		book.setAuthor("Jeff Vandermeer");
		assertEquals("Jeff Vandermeer", book.getAuthor());
	}

	@Test
	public void TestSetandGetDescription() {
		book.setDescription("Southern Reach trilogy");
		assertEquals("Southern Reach trilogy", book.getDescription());
	}

	@Test
	public void TestSetandGetGenre() {
		book.setGenre("Science Fiction");
		assertEquals("Science Fiction", book.getGenre());
	}

	@Test
	public void TestSetandGetPrice() {
		book.setPrice("259");
		assertEquals("259", book.getPrice());
	}

	@Test
	public void TestSetandGetPublishDate() {
		book.setPublish_date("2017-3-13");
		assertEquals("2017-3-13", book.getPublish_date());
	}
}


