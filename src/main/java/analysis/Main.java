package analysis;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
	    FileReader fileReader=new FileReader();
	    List<Double> list= fileReader.GetInfo("E:\\JAVA\\ANALYSIS\\data.txt");
	    System.out.println(list);
    }
}
