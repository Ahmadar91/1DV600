package lnu.dao;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import lnu.models.*;

public class booksDAO {

	private File file = new File("books.xml");

	/*
	 * Constructor
	 */
	public booksDAO() {

	}

	/*
	 * Testing Constructor
	 */

	public booksDAO(File file) {
		this.file = file;
	}

	/*
	 * Conversion Method from XML object to catalog object
	 */
	public catalog ToCatalog() throws Exception {
		JAXBContext cont = JAXBContext.newInstance(catalog.class);
		Unmarshaller unmar = cont.createUnmarshaller();
		return (catalog) unmar.unmarshal(file);
	}

	/*
	 * Conversion Method from catalog object to XML object
	 */

	public void ToXML(catalog Catolog) throws Exception {
		JAXBContext cont = JAXBContext.newInstance(catalog.class);
		Marshaller marsh = cont.createMarshaller();
		marsh.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marsh.marshal(Catolog, file);
	}
}