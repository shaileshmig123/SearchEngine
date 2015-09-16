package insert;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.IOException;
import java.util.regex.Matcher;

public class Test1 {

    public static void main(String args[]) {

        String zkHost = "localhost:2181";
        int i=0;
        String solrHpst = "http://localhost:8983/solr/";

        //create a solrj connection
        //2 ways - cloud mode and non cloud mode


        //non- cloud mode
        //HttpSolrClient nonCloudClient = new HttpSolrClient(solrHpst);

        //cloud mode
        CloudSolrClient cloudClient = new CloudSolrClient(zkHost);
        cloudClient.connect();
        cloudClient.setDefaultCollection("Searchengine");
        String line,file_name;
        String id_temp;

        //create document
        String pattern = ".*https*\\S*(\\d{0,9}).txt.*";
        //String pattern = ".*";


        try {
            BufferedReader lr = new BufferedReader(new FileReader("/home/shailesh/hello.txt"));
            while ((file_name = lr.readLine()) != null) {
                Pattern r = Pattern.compile(pattern);
                id_temp=file_name.replace(".txt","");

                try {
                    BufferedReader br = new BufferedReader(new FileReader("/home/shailesh/parsed/" + file_name));
                    while ((line = br.readLine()) != null) {
                        //String[] parts = line.split("  ");


                        // pattern object
                        System.out.print(file_name+"  ");
                        Matcher m = r.matcher(file_name);
                        ////System.out.print(m);
                        SolrInputDocument doc1 = new SolrInputDocument();
                        doc1.addField("id",id_temp);
                        doc1.addField("content", line);
                        //doc1.addField("datefl", parts[2]);

                        //insert document
                        try {
                            System.out.println("adding doc");
                            cloudClient.add(doc1);

                            //to make document visible immediately, we need to do a commit

                        } catch (SolrServerException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    }
                    try {
                        br.close();
                    } catch (IOException Y) {

                    }
                } catch (IOException e) {
                    e.printStackTrace();

                }
            }
            try {
                lr.close();
            } catch (IOException Y) {
            }

        }catch (IOException b){

        }



        try {
            cloudClient.commit();
        }catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }//close the connection
        try {
            cloudClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

}



//upload configset
// ./zkcli.sh -cmd upconfig -zkhost localhost:2181 -confdir /home/shailesh/solr-5.2.1/server/solr/configsets/basic_configs/conf/ -confname config1
//create collection
