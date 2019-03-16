package Suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import UseCasesTesting.*;

@RunWith(Suite.class)
@SuiteClasses({ TestAddABook.class, TestDeleteBook.class, TestEditABook.class,
	TestViewBookList.class, TestViewABook.class })
public class UseCasesSuit {
	
}
