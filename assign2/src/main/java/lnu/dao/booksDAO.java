// Use this file to write and read the xml file.
// http://www.journaldev.com/1234/jaxb-tutorial-example-to-convert-object-to-xml-and-xml-to-object
// javax.xml.bind is added as a part of the sdk from java7 and forward.

package lnu.dao;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import lnu.models.*;

public class booksDAO {

	private File file = new File("books.xml");

	public booksDAO() {
	}

	public catalog Tocatalog() throws Exception {
		JAXBContext  cont = JAXBContext.newInstance(catalog.class);
		Unmarshaller unmar = cont.createUnmarshaller();
		return (catalog) unmar.unmarshal(file);
	}


	public void ToXML(catalog Catolog) throws Exception {
		JAXBContext cont = JAXBContext.newInstance(catalog.class);
		Marshaller marsh = cont.createMarshaller();
		marsh.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marsh.marshal(Catolog, file);
	}
}
