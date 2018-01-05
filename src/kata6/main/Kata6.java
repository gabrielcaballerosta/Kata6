package kata6.main;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import kata6.model.Histogram;
import kata6.model.Person;
import kata6.view.DataBaseList;
import kata6.view.HistogramDisplay;
import kata6.view.HistogramBuilder;

public class Kata6 {
    
    private String fileName = "";
    private List<Person> listPerson;
    private HistogramBuilder<Person> builderPerson;

    private Histogram<Character> gender;
    private Histogram<Float> weight;            
    
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        Kata6 kata4 = new Kata6();
        kata4.execute();
    }
    
    private void input() throws IOException, ClassNotFoundException, SQLException {
        fileName = "C:\\Users\\Gabriel\\Downloads\\KATA.sDB"; 
        listPerson = DataBaseList.read(fileName);
    }
    
    private void process() throws IOException {
        builderPerson = new HistogramBuilder<>(listPerson);

        gender = builderPerson.build(new Attribute<Person, Character>() {

            @Override
            public Character get(Person item) {
                return item.getGender();
            }
        });

        weight = builderPerson.build(new Attribute<Person, Float>() {

            @Override
            public Float get(Person item) {
                return item.getWeight();
            }
        });
    }

    
    private void output() {
        new HistogramDisplay(gender, "Genero").excute();
        new HistogramDisplay(weight, "Peso").excute();
    }
    
    private void execute() throws IOException, ClassNotFoundException, SQLException {
        input();
        process();
        output();       
    }
}
