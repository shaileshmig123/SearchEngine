package insert;
import java.net.*;
import java.io.*;
/**
 * Created by shailesh on 11/9/15.
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class webcrawler {

    public static void main(String[] args) {
        URL url;
        InputStream is = null;

        URLListHandler ThreadSpecificList = new URLListHandler();
        BufferedReader br;
        String line;
        List<String> links_temp = new ArrayList<String>();
        String pattern = "\"(https*[^\\\"]+)\"";
        int id_doc=0;

        ThreadSpecificList.urlList.add("http://youporn.com/");
        for (int ii = 0; ii < ThreadSpecificList.urlList.size(); ii++)  {
            try {
                url = new URL(ThreadSpecificList.urlList.get(ii));
                System.out.println("hello");
                is = url.openStream();  // throws an IOException
                br = new BufferedReader(new InputStreamReader(is));
                Pattern r = Pattern.compile(pattern); // pattern object
                FileOutputStream fout =new FileOutputStream("/home/shailesh/imagesz/"+id_doc+++".txt");
                while ((line = br.readLine()) != null) {
                    //System.out.println(line);
                    Matcher m = r.matcher(line);  // matcher object..
                    try{
                        String s=line;
                        byte b[]=s.getBytes();//converting string into byte array
                        fout.write(b);
                        //System.out.println("success...");
                    }catch(Exception e){System.out.println(e);}

                    if (m.find()) {
                        //System.out.println(m.group(0).replace('\"', ' '));
                        links_temp.add(m.group(0).replace('\"', ' '));

                    }

                }
                fout.close();
                ThreadSpecificList.setUrls(links_temp);
                for (int i = 0; i < links_temp.size(); i++) {
                    System.out.println("asdsadsad" + ThreadSpecificList.urlList.get(i));
                }

                System.out.println("Size:" + ThreadSpecificList.urlList.size());
            } catch (MalformedURLException mue) {
                mue.printStackTrace();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            } finally {
                try {
                    if (is != null) is.close();
                    continue;
                } catch (IOException ioe) {
                    // nothing to see here
                }
            }
            // System.out.println(links_temp);


            //ThreadSpecificList.setUrls(links_temp);
            // Call to check if the links are present in the Listarray if not then append.
           /* for (int i = 0; i < links_temp.size(); i++) {
                System.out.println("asdsadsad" + ThreadSpecificList.urlList.get(i));
            }*/

        }
    }
}
