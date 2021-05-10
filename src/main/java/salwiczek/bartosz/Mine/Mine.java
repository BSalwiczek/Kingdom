package salwiczek.bartosz.Mine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Mine {
    private final List<Ore> ores;
    private final List<Carbon> carbons;

    private List<Thread> miners;

    public Mine()
    {
        ores = Collections.synchronizedList(new ArrayList<Ore>());
        carbons = Collections.synchronizedList(new ArrayList<Carbon>());
        miners = new ArrayList<Thread>();
    }

    public void print()
    {
        String format = "%-15s %-15s %-15s %-15s %n";
        System.out.format(format, "Mine", "Ores", "Carbons", "Miners");
        System.out.format(format, "", +ores.size(),carbons.size(),miners.size());
    }

    public void stop()
    {
        for(Thread miner: miners)
        {
            miner.interrupt();
        }
    }

    public void createMiners(int n) {
        for(int i=0; i<n;i++)
        {
            Miner miner;
            if(i%2==0)
            {
                miner = new Miner("Ore", this);
            }else {
                miner = new Miner("Carbon", this);
            }

            Thread thread = new Thread(miner);
            thread.start();
            miners.add(thread);
        }

//        for(int i=0;i<n;i++)
//        {
//            try {
//                miners.get(i).join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

    }

    public Ore takeOre() throws InterruptedException {
        synchronized(ores)
        {
            while (ores.isEmpty()) {
                ores.wait();
            }
            Ore ret = ores.get(0);
            ores.remove(0);
            return ret;
        }
    }

    public Carbon takeCarbon() throws InterruptedException {
        synchronized(carbons)
        {
            while (carbons.isEmpty()) {
                carbons.wait();
            }
            Carbon ret = carbons.get(0);
            carbons.remove(0);
            return ret;
        }

    }

    public void putOre(Ore ore) {
        synchronized(ores) {
            ores.add(ore);
            ores.notifyAll();
        }
    }

    public void putCarbon(Carbon carbon) {
        synchronized(carbons) {
            carbons.add(carbon);
            carbons.notifyAll();
        }
    }

}
