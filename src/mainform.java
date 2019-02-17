import java.io.*;
import java.util.ArrayList;

public class mainform {

    public static void main(String[] args) {
        Parser p=new Parser();
//        buffer=new ArrayList();
//
//        readFile();
//        writeToFile();
        p.readFile();
        p.readFiles("c:/ix/header.html",p.header);
        p.readFiles("c:/ix/footer.html",p.footer);
        try {
            p.writeToFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
