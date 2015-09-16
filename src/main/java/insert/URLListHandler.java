package insert;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by shailesh on 12/9/15.
 */
public class URLListHandler {
    public  List<String> urlList=new ArrayList<String>();
    public  void setUrls(List<String> urls){
        for(String url:urls){
            //if(!duplicateCheck(url)){

                urlList.add(url);
            //}
        }
    }

    private  boolean duplicateCheck(String urlAppend){
        for(String url:urlList){
            if(url.equals(urlAppend.trim())){
                return true;
            }
        }
        return false;
    }

    public static String getUrl(){
        return "";
    }

}
