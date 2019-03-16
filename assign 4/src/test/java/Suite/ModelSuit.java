package Suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import ModelTesting.*;

@RunWith(Suite.class)
@SuiteClasses({ TestbooksDAO.class, TestBook.class })
public class ModelSuit {
}