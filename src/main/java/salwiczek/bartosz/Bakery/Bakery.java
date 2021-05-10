package salwiczek.bartosz.Bakery;

import salwiczek.bartosz.Farm.Farm;
import salwiczek.bartosz.Farm.Farmer;
import salwiczek.bartosz.Farm.Wheat;
import salwiczek.bartosz.Mine.Carbon;
import salwiczek.bartosz.Mine.Ore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Bakery {
    Farm farm;
    private final List<Bread> breads;
    private final List<Thread> bakers;

    public Bakery(Farm farm)
    {
        this.farm = farm;
        this.breads = Collections.synchronizedList(new ArrayList<Bread>());
        bakers = new ArrayList<>();

    }

    public void print()
    {
        String format = "%-15s %-15s %-15s %n";
        System.out.format(format, "Bakery", "Breads", "Bakers");
        System.out.format(format, "", breads.size(), bakers.size());
    }

    public void createBakers(int n)
    {
        for(int i=0; i<n;i++)
        {
            Baker baker = new Baker(farm, this);
            Thread thread = new Thread(baker);
            thread.start();
            bakers.add(thread);
        }
    }

    public void stop()
    {
        for(Thread t: bakers)
        {
            t.interrupt();
        }
    }

    public Bread takeBread() throws InterruptedException {
        synchronized(breads)
        {
            while (breads.isEmpty()) {
                breads.wait();
            }
            Bread ret = breads.get(0);
            breads.remove(0);
            return ret;
        }

    }

    public void putBread(Bread bread) {
        synchronized(breads) {
            breads.add(bread);
            breads.notifyAll();
        }
    }
}
