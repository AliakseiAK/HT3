import entities.TestsSuite;
import services.FileService;
import services.ParseService;
import utils.AppConstants;

import java.io.IOException;

/**
 * Запускающий класс приложения.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length != 2){
            System.out.println(AppConstants.USAGE);
        } else {
        String inputFile = args[0];
        String outputFile = args[1];
        FileService fileService = new FileService(inputFile);
        TestsSuite suite = fileService.readFile();
        ParseService parseService = new ParseService();
        parseService.processTestSuite(suite);
        fileService.writeLogFile(suite, outputFile);
        }
    }
}
