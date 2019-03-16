package Suite;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import API.*;

@RunWith(Suite.class)
@SuiteClasses({ APITestAddBookResource.class, APITestEditBookResource.class, APITestGetBookResource.class,
	APITestGetBooksResource.class, APITestRemoveBookResource.class })
public class APISuit {
}
