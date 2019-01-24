public class Program {

    Email mail;
    Weather weather;
    String location;
    String sign;
    String subreddit;


    public Program(String location, String sign,String email, String subreddit){

        weather = new Weather(location);
        mail = new Email();
        this.location = location;
        this.sign = sign;
        this.subreddit = subreddit;
        addAddress(email);
    }



    public void run(){

        mail.sendEmail(sign,location,subreddit);
    }

    public void addAddress(String email){

        mail.addAddress(email);

    }



}
