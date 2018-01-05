package kata6.main;

import java.io.IOException;
import java.util.List;
import kata6.model.Histogram;
import kata6.model.Mail;
import kata6.view.HistogramDisplay;
import kata6.view.HistogramBuilder;
import kata6.view.MailListReader;

public class Kata6 {
    
    private String fileName = "";
    private List<Mail> listMail;
    private HistogramBuilder<Mail> builder;

    private Histogram<String> domains;
    private Histogram<Character> letters;            
    
    public static void main(String[] args) throws IOException {
        Kata6 kata4 = new Kata6();
        kata4.execute();
    }
    
    private void input() throws IOException {
        fileName = "C:\\Users\\Gabriel\\Documents\\NetBeansProjects\\Kata6\\emailsfile.txt"; 
        listMail = MailListReader.read(fileName);
    }
    
    private void process() throws IOException {
        builder = new HistogramBuilder<>(listMail);

        domains = builder.build(new Attribute<Mail, String>() {

            @Override
            public String get(Mail item) {
                return item.getMail().split("@")[1];
            }
        });

        letters = builder.build(new Attribute<Mail, Character>() {

            @Override
            public Character get(Mail item) {
                return item.getMail().charAt(0);
            }
        });
    }

    
    private void output() {
        new HistogramDisplay(domains, "Dominios").excute();
        new HistogramDisplay(letters, "Primer Caracter").excute();
    }
    
    private void execute() throws IOException {
        input();
        process();
        output();       
    }
}
