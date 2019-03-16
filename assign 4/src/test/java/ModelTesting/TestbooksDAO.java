package ModelTesting;

import static org.junit.Assert.*;
import org.junit.Test;
import lnu.dao.booksDAO;
import lnu.models.catalog;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class TestbooksDAO {

	booksDAO dao;
	catalog cata;

	File file = new File("TestbooksDAO.xml");
	final String JSONBkTest = "[{\"id\":\"400\",\"title\":\"Test\",\"author\":\"Test\",\"publishDate\":\"2018-9-8\",\"price\":\"33\",\"description\":\"Test\",\"genre\":\"Test\"}]";
	final String XMLTestObj = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><catalog>"
			+ "<book id=\"400\"><author>Test</author><title>Test</title><genre>Test</genre><price>33</price><publish_date>2018-9-8</publish_date><description>Test</description></book></catalog>";

	/*
	 *A method to write the XML String created above into TestbooksDAO.xml
	 */

	void writeFile(String XMLObjTest) {
		try {
			PrintWriter pw = new PrintWriter(file);
			pw.write(XMLObjTest);
			pw.close();
		} catch (IOException e) {
			e.getMessage();
		}
	}

	/*
	 *A method to scans and returns the content of the file TestbooksDAO.xml in to a string 
	 */
	String scanFile() {
		String str = "";
		try {
			Scanner sc = new Scanner(file);
			while (sc.hasNextLine()) {
				str += sc.nextLine().trim();
			}
			sc.close();
		} catch (IOException e) {
			e.getMessage();
		}
		return str;
	}

	/*
	 *A Test for Converting XML objects which are in the file TestbooksDAO.xml into catalog
	 * objects
	 */
	@Test
	public void shouldConvertXMLObjTocatalogObj() throws Exception {
		dao = new booksDAO(file);
		writeFile(XMLTestObj);
		cata = dao.ToCatalog();
		assertNotNull(cata);
		assertEquals(JSONBkTest, cata.convertToJSON());
	}

	/*
	 * A test for Converting catalog objects into XML objects
	 */
	@Test
	public void shouldConvertcatalogObjToXMLObj() throws Exception {
		dao = new booksDAO(file);
		writeFile(XMLTestObj);
		cata = dao.ToCatalog();
		dao.ToXML(cata);
		assertEquals(XMLTestObj, scanFile());
	}
	
	/*
	 *A test for converting a file that doesn't exist 
	 */

	@Test(expected = Exception.class)
	public void shouldNotConvertAFileThatDoesntExistAndThrowAnError() throws Exception {
		dao = new booksDAO(file);
		writeFile(XMLTestObj);
		cata = dao.ToCatalog();
		file.delete();
		dao.ToCatalog();
	}
}