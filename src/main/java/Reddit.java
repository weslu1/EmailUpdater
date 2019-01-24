import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import net.dean.jraw.RedditClient;
import net.dean.jraw.oauth.*;
import net.dean.jraw.http.*;
import net.dean.jraw.models.*;
import net.dean.jraw.pagination.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class Reddit {


    private String subreddit;
    private String url;

    class Data extends Object{

        Object title;
        Object permalink;
    }
    class Child extends Object{

        Data data;
    }
    class Post{

        String dist;
        String title;
       Child[] children;

    }


  public Reddit(String subreddit){

       this.subreddit = subreddit;
       url = "https://www.reddit.com/r/"+ subreddit +"/hot.json?limit=3";
    }

    public String getTopPosts(){



        try {

            StringBuilder result = new StringBuilder();
            URL link = new URL(url);
            URLConnection conn = link.openConnection();
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while((line = br.readLine()) != null) {

                result.append(line);
            }
            br.close();

            String data = result.toString();






            JsonObject jsonObject = new JsonParser().parse(data).getAsJsonObject();
            Post posts = new Gson().fromJson(jsonObject.get("data"),Post.class);
            //JsonObject jsonObject2 = new JsonParser().parse(posts.children).getAsJsonObject();
           //Child c = new Gson().fromJson(jsonObject.get("data"),Child.class);

            String returnString = "Current top 5 posts for /r/" + subreddit + ":\n";

           // Map<String, Object> dataMap = jsonToMap(posts.children[0].toString());
           // System.out.println(dataMap.get("subreddit"));
            for(int i = 0; i< posts.children.length; i++) {
                returnString += (i+1) + ") " + posts.children[i].data.title + "\n" + posts.children[i].data.permalink + "\n" + "\n";
                //System.out.println(posts.children[i].data.title);
                //System.out.println(posts.children[i].data.permalink);


           }


            return returnString;

        } catch(IOException e) {

            e.printStackTrace();

        }

        return "ERROR";

    }
}
