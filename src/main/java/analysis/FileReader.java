package analysis;

import java.io.FileNotFoundException;
import java.util.*;
import java.io.File;

public class FileReader {
    public static List<Double> GetInfo(String filename) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(filename));

        List<Double> list =new ArrayList<>();
        while (sc.hasNextDouble())//逐行读取文件内容
        {
                list.add(sc.nextDouble());
        }
        return list;
    }
}
