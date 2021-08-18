import java.util.Random;
import java.util.Scanner;

public class Controller {

    public Controller() {
        double average = 0.0;
        double iterations = 200;
        for(int i = 0; i <iterations; i++) {
            int n = 100;
            Precolation uf = new Precolation(n);
            Random rand = new Random();
            Scanner scan = new Scanner(System.in);

            while (uf.precolated() == false){
                uf.openSite(rand.nextInt(n*n));
                //uf.printConnections();
                //System.out.println("");
            }
           // System.out.println(uf.openPercentage());
            average = average + uf.openPercentage();
        }
        average = (average) /iterations;

        System.out.println(average);
    }
}
