package salwiczek.bartosz;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Kingdom kingdom1 = new Kingdom("Horde");
        Kingdom kingdom2 = new Kingdom("Alliance");

        kingdom1.start();
        kingdom2.start();

        long fightTime = System.currentTimeMillis() + 1000*20;

        while (fightTime - System.currentTimeMillis() > 0)
        {
            clearConsole();

            System.out.println("Time left to fight: "+((fightTime - System.currentTimeMillis())/1000)+"s");
            System.out.println();
            kingdom1.print();
            System.out.println();
            kingdom2.print();

            try
            {
                Thread.sleep(200);
            }
            catch(InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("=====================================================================");
        System.out.println("FIGHT!");
        System.out.println(getWinner(kingdom1, kingdom2) + " wins");
        System.out.println("=====================================================================");
        kingdom1.stop();
        kingdom2.stop();
    }

    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
            else {
                System.out.print("\033\143");
            }
        } catch (IOException | InterruptedException ex) {}
    }

    public static String getWinner(Kingdom k1, Kingdom k2) {
        if(k1.getPoints() == k2.getPoints())
            return "No kingdom";
        if(k1.getPoints() > k2.getPoints())
            return k1.name;
        return k2.name;
    }
}
