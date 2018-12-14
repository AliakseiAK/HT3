import entities.TestsSuite;
import entities.TestOperation;
import entities.TestTask;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import services.FileService;

import java.io.IOException;

public class FileServiceTest {

    private FileService fileService;

    private TestTask testTask;

    @BeforeMethod
    public void setUp() {
        fileService = new FileService("testinput.txt");
        testTask = new TestTask();

        testTask.setUrl("http://www.google.com");
        testTask.setTimeoutInMillis(3000);

        TestOperation testOpOpen = new TestOperation("open", "http://www.google.com");
        TestOperation testOpCheckTitle = new TestOperation("checkPageTitle", "Google Search Page");
        TestOperation testOpPageContains = new TestOperation("checkPageContains", "The best search engine");

        testTask.getOperations().add(testOpOpen);
        testTask.getOperations().add(testOpCheckTitle);
        testTask.getOperations().add(testOpPageContains);
    }

    @Test
    public void testReadFile() throws IOException {
        TestsSuite suite = fileService.readFile();
        Assert.assertEquals(suite.getTasks().size(), 2);
        Assert.assertEquals(suite.getTasks().get(0).getOperations().size(), 3);
        Assert.assertEquals(suite.getTasks().get(0).generate(), testTask.generate());
    }
}