package salwiczek.bartosz.Smithy;

import salwiczek.bartosz.Mine.Carbon;
import salwiczek.bartosz.Mine.Mine;
import salwiczek.bartosz.Mine.Ore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Smithy {
    Mine mine;
    private final List<Sword> swords;
    private final List<Helmet> helmets;
    private final List<Armor> armors;

    private List<Thread> blacksmiths;


    public Smithy(Mine mine)
    {
        this.mine = mine;
        swords = Collections.synchronizedList(new ArrayList<Sword>());
        helmets = Collections.synchronizedList(new ArrayList<Helmet>());
        armors = Collections.synchronizedList(new ArrayList<Armor>());
        blacksmiths = new ArrayList<Thread>();
    }

    public void stop()
    {
        for(Thread t: blacksmiths)
        {
            t.interrupt();
        }
    }

    public void print()
    {
        String format = "%-15s %-15s %-15s %-15s %-15s %n";
        System.out.format(format, "Smithy", "Swords", "Helmets", "Armors", "Blacksmiths");
        System.out.format(format, "", swords.size(),helmets.size(),armors.size(),blacksmiths.size());
    }

    public void createBlacksmiths(int n) {
        for(int i=0; i<n;i++) {
            Blacksmith blacksmith;
            if (i % 3 == 0) {
                blacksmith = new Blacksmith(mine, this, "Armors");
            } else if (i % 3 == 1) {
                blacksmith = new Blacksmith(mine, this, "Swords");
            }else {
                blacksmith = new Blacksmith(mine, this, "Helmets");
            }

            Thread thread = new Thread(blacksmith);
            thread.start();
            blacksmiths.add(thread);
        }
    }

    public Sword takeSword() throws InterruptedException {
        synchronized(swords)
        {
            while (swords.isEmpty()) {
                swords.wait();
            }
            Sword ret = swords.get(0);
            swords.remove(0);
            return ret;
        }

    }

    public Helmet takeHelmet() throws InterruptedException {
        synchronized(helmets)
        {
            while (helmets.isEmpty()) {
                helmets.wait();
            }
            Helmet ret = helmets.get(0);
            helmets.remove(0);
            return ret;
        }
    }

    public Armor takeArmor() throws InterruptedException {
        synchronized(armors)
        {
            while (armors.isEmpty()) {
                armors.wait();
            }
            Armor ret = armors.get(0);
            armors.remove(0);
            return ret;
        }
    }

    public void putSword(Sword sword) {
        synchronized(swords) {
            swords.add(sword);
            swords.notifyAll();
        }
    }

    public void putHelmet(Helmet helmet) {
        synchronized(helmets) {
            helmets.add(helmet);
            helmets.notifyAll();
        }
    }

    public void putArmor(Armor armor) {
        synchronized(armors) {
            armors.add(armor);
            armors.notifyAll();
        }
    }
}
