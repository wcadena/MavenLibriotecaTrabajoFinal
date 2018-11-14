/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testin;

import com.github.javafaker.Faker;
import static java.lang.ProcessBuilder.Redirect.from;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import junit.framework.TestCase;
import model.Anio;
import model.Libros;
import model.Mes;

/**
 *
 * @author wcade
 */
public class LibroJUnitTest extends TestCase {

    public LibroJUnitTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    // TODO add test methods here. The name must begin with 'test'. For example:
    public void testFaker() {
        Faker faker = new Faker();

        String name = faker.name().fullName(); // Miss Samanta Schmidt
        String firstName = faker.name().firstName(); // Emory
        String lastName = faker.name().lastName(); // Barton

        String streetAddress = faker.address().streetAddress(); // 60018 Sawayn Brooks Suite 449
        System.out.println("name" + name + firstName + lastName + streetAddress);
    }

    public void testLLenarLibros() {
        Faker faker = new Faker();

    }

    public void testAnios() {

        ArrayList<Anio> as;
        as = new ArrayList();
        for (int i = 0; i < 20; i++) {
            int anio = 2018 - i;
            Anio a = new Anio();
            a.setAnio(anio);
            ArrayList<Mes> ms;
            ms = new ArrayList();
            for (int j = 1; j <= 12; j++) {
                Mes m = new Mes();
                m.setMes(j);

                ms.add(m);
            }
            a.setMess(ms);
            as.add(a);
        }
        System.out.println("Test Anios--->" + as);
    }

    public void testLibros() throws ParseException {

        ArrayList<Anio> as;
        as = new ArrayList();
        for (int i = 0; i < 20; i++) {
            int anio = 2018 - i;
            Anio a = new Anio();
            a.setAnio(anio);
            ArrayList<Mes> ms;
            ms = new ArrayList();
            for (int j = 1; j <= 12; j++) {
                Mes m = new Mes();
                m.setMes(j);

                ms.add(m);
            }
            a.setMess(ms);
            as.add(a);
        }
        ArrayList<Libros> ls = null;
        ls = new ArrayList();
        Faker faker = new Faker();
        for (int i = 0; i < 200; i++) {
            Libros l = new Libros();
            l.setAuthor(faker.book().author());
            l.setTitle(faker.book().title());
            l.setGenre(faker.book().genre());
            l.setPublisher(faker.book().publisher());
            l.setId(i);
            l.setNumero(faker.idNumber().hashCode());
            Date dNow = new Date();

            String date_s = "1985-01-18 00:00:00.0";

            // *** note that it's "yyyy-MM-dd hh:mm:ss" not "yyyy-mm-dd hh:mm:ss"  
            SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date dateFrom = dt.parse(date_s);

            l.setCreated_at(faker.date().between(dateFrom, dNow));
            l.setUpdate_at(faker.date().between(dateFrom, dNow));
            Anio a_util = as.get(Utilitario.randInt(0, 20));
            Mes m = a_util.getMess().get(Utilitario.randInt(1, 12));
            l.setMes(m);
            ls.add(l);
            
        }
        System.out.println("---->"+ls);
    }
}
