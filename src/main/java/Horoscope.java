import com.sun.mail.imap.protocol.ID;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;
import java.time.*;
import java.io.IOException;


public class Horoscope {

    public String getHoroscope(String sign) {
        String result = "";
        try {


            Document doc = Jsoup.connect("https://astrostyle.com/daily-horoscopes/" + sign + "-daily-horoscope/").userAgent("Mozilla/17.0").get();

            String currentDay;
            if(LocalDate.now().getDayOfWeek().name().toLowerCase().equals("sunday")){

                currentDay = "saturday";
            }
            else {

                currentDay = LocalDate.now().getDayOfWeek().name().toLowerCase();
            }

            Elements words = doc.select("div#"+ currentDay);


            for (Element horo : words) {

                result+=horo.getElementsByTag("p").first().text();
            }
        } catch (IOException e) {

            e.printStackTrace();

        }
return result;

    }
}