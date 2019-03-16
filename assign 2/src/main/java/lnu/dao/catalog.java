package lnu.models;

import java.io.IOException;
import java.util.ArrayList;

import javax.ws.rs.NotFoundException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.databind.ObjectMapper;


@XmlRootElement(name = "catalog")
@XmlAccessorType(XmlAccessType.FIELD)
public class catalog {

	@XmlElement(name = "book")
	private ArrayList<book> booklist = new ArrayList<book>();
	private static ObjectMapper mapper = new ObjectMapper();;


	public catalog() {
	}



  public String ConvertToJSON() throws IOException {
    return mapper.writeValueAsString(booklist);
  }


	public void removeBook(String id) throws NotFoundException {
		for (int i = 0; i < booklist.size(); i++) {
			if (booklist.get(i).getId().equals(id))
				booklist.remove(i);
		}
	}
}
