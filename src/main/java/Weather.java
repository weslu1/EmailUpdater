import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.Object;
import java.util.*;
import java.net.URL;
import java.net.URLConnection;
import java.text.*;



import com.google.gson.*;
import com.google.gson.reflect.*;


class WeatherObject{

    String id;
    String main;
    String description;
    String icon;



}



//http://api.openweathermap.org/data/2.5/weather?q=San Francisco,US&APPID=e3558ef5a7a482cea1d0873ba9e94c01&units=imperial
public class Weather {

    final String api = "e3558ef5a7a482cea1d0873ba9e94c01";
    private String location;
    private String url;
    private static final DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");



    public Weather(String location){
        this.location = location;
        url = "http://api.openweathermap.org/data/2.5/weather?q=" + location + "&APPID=" + api + "&units=imperial";


    }








    public static Map<String,Object> jsonToMap(String str){

        Map<String,Object> map = new Gson().fromJson(str, new TypeToken<HashMap<String,Object>>() {}.getType());

        return map;
    }


    public String getWeather(){
        String formattedData = "";

        try {
            StringBuilder result = new StringBuilder();
            URL link = new URL(url);
            URLConnection conn = link.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line;
            while((line = br.readLine()) != null) {

                result.append(line);
            }
            br.close();





            String data = result.toString();
            Map<String, Object> map = jsonToMap(data);
            Map<String, Object> mainMap = jsonToMap(map.get("main").toString());
            Map<String, Object> cloudsMap = jsonToMap(map.get("clouds").toString());



            //Do something special for the array
            JsonObject jsonObject = new JsonParser().parse(data).getAsJsonObject();
            WeatherObject[] wea = new Gson().fromJson(jsonObject.get("weather"),WeatherObject[].class);



            Date date = new Date();



            formattedData += "Stats for " + location  + " on " + sdf.format(date) + ":\n" + "\n";
            formattedData += "Temperature: " + mainMap.get("temp") + " F" + "\n";
            formattedData += "Humidity: " + mainMap.get("humidity") + "%" + "\n";
            formattedData += "Cloudiness: " + cloudsMap.get("all") + "%" + "\n";

            formattedData += "Weather: " +"\n";
            for(int i = 0; i<wea.length; i++){

                formattedData+= wea[i].description + "\n";


            }


           return formattedData;






        }
        catch(IOException e){
            System.out.println("Error opening URL");
        }


        return formattedData;

    }








}

