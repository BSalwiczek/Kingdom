package salwiczek.bartosz.Farm;

import salwiczek.bartosz.Mine.Carbon;
import salwiczek.bartosz.Mine.Miner;
import salwiczek.bartosz.Mine.Ore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Farm {
    private final List<Wheat> wheats;
    private final List<Thread> farmers;


    public Farm() {
        this.wheats = Collections.synchronizedList(new ArrayList<Wheat>());
        farmers = new ArrayList<>();
    }

    public void print()
    {
        String format = "%-15s %-15s %-15s %n";
        System.out.format(format, "Farm", "Wheats", "Farmers");
        System.out.format(format, "", +wheats.size(), farmers.size());
    }

    public void createFarmers(int n)
    {
        for(int i=0; i<n;i++)
        {
            Farmer farmer = new Farmer(this);
            Thread thread = new Thread(farmer);
            thread.start();
            farmers.add(thread);
        }
    }

    public void stop()
    {
        for(Thread t: farmers)
        {
            t.interrupt();
        }
    }

    public Wheat takeWheat() throws InterruptedException {
        synchronized(wheats)
        {
            while (wheats.isEmpty()) {
                wheats.wait();
            }
            Wheat ret = wheats.get(0);
            wheats.remove(0);
            return ret;
        }

    }

    public void putWheat(Wheat wheat) {
        synchronized(wheats) {
            wheats.add(wheat);
            wheats.notifyAll();
        }
    }

}
