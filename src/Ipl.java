import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Ipl {
    public static List<String> data=new ArrayList<>();
    public static void main(String[] args) throws IOException {
        String line="";
        int skip=0;

        BufferedReader bf = new BufferedReader(new FileReader("matches.csv"));
        while((line=bf.readLine())!=null){
            if(skip == 0){
                skip++;
                continue;
            }
            String[] match = line.split(",");
            System.out.println(match[0]);
        }
    }
}


