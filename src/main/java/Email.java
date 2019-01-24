import java.io.*;
import java.util.*;


import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class Email {

    private String username = "dailyreminder324@gmail.com";
    private String password = "Wafflecream123";
    private ArrayList<String> arrli = new ArrayList<String>();

    public void sendEmail(String sign, String location, String subreddit){

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");


        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));


            for(int i =0; i<arrli.size(); i++) {
                message.addRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(arrli.get(i)));
            }



            Horoscope horoscope = new Horoscope();
            Weather weather = new Weather(location);
            Reddit reddit = new Reddit(subreddit);



            message.setSubject("Your Daily Email");
            message.setText("Daily Horoscope for " + sign + ":\n" + "\n" + horoscope.getHoroscope(sign) + "\n" + "\n" + weather.getWeather() + "\n" + "\n" +
                    reddit.getTopPosts() + "\n" + "\n" + Joke.getJoke());

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

 public void addAddress(String address){

        arrli.add(address);


 }









}
