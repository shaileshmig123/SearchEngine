package insert;

/**
 * Created by shailesh on 10/9/15.
 *
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class testjava {

    public static void main(String args[]) {
            String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader("/home/shailesh/temp.txt"));
            while ((line=br.readLine()) != null) {
                System.out.print(line + "\n");
                String[] parts = line.split("  ");
                String part1 = parts[0]; // 004
                String part2 = parts[1];
                String part3 = parts[2];
                System.out.print(parts[0]+parts[1]+parts[2]+"\n");

            }
            try{
                br.close();
            }catch(IOException Y){

            }

        }catch(IOException e){
            e.printStackTrace();

        }


    }
}
