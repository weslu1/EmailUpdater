import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.*;

import java.io.IOException;
import java.time.LocalDate;

public class Joke {

    public static String getJoke(){

        String result = "";

        try {


            Document doc = Jsoup.connect("https://www.rd.com/jokes/daily-life/").userAgent("Chrome").get();

            Elements joke = doc.getElementsByClass("joke entry");

            ArrayList<String> list = new ArrayList<String>();
            //System.out.println(doc.text());
            for (Element horo : joke) {

                list.add(horo.getElementsByTag("p").first().text());
            }

            int random = (int )(Math.random() * list.size() + 0);

            result += list.get(random);


        } catch (IOException e) {

            e.printStackTrace();

        }
        return result;

    }

}
