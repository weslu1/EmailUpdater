import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Template {
    private JButton Submit;
    private JTextField emailTextField;
    private JTextField locationTextField;
    private JPanel panelMain;
    private JComboBox comboBox1;
    private JLabel horoscopeLabel;
    private JLabel emailLabel;
    private JLabel locationLabel;
    private JTextField subredditTextField;
    private JLabel subredditLabel;

    private String location;
    private String email;
    private String subreddit;
    private String sign;

    public Template() {
        Submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                location = locationTextField.getText();
                email = emailTextField.getText();
                subreddit = subredditTextField.getText();
                sign = (String) comboBox1.getSelectedItem();

                Program program = new Program(location,sign,email,subreddit);
                program.run();



            }
        });
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("Template");
        frame.setContentPane(new Template().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        //String signs[] = {"Aries", "Taurus", "Gemini", "Cancer", "Leo", "Virgo", "Libra", "Scorpio", "Sagittarius", "Capricorn", "Aquarius", "Pisces"};


        //Program program = new Program("Santa Barbara,US", "aquarius", "wesleychenlu@gmail.com","leagueoflegends");
        //program.addAddress("ocampoashleyy@gmail.com");
       // program.run();

        // Reddit reddit = new Reddit("Leagueoflegends");
        //System.out.println(reddit.getTopPosts());

        // Horoscope horoscope = new Horoscope();
        //System.out.println(horoscope.getHoroscope("aquarius"));


    }
}
