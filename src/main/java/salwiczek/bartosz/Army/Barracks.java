package salwiczek.bartosz.Army;

import lombok.Getter;
import lombok.Setter;
import salwiczek.bartosz.Bakery.Bakery;
import salwiczek.bartosz.Farm.Farm;
import salwiczek.bartosz.Mine.Miner;
import salwiczek.bartosz.Smithy.Smithy;

import java.util.ArrayList;
import java.util.List;

public class Barracks {
    private List<Thread> warriorsT;

    @Getter
    @Setter
    private ArrayList<Warrior> warriors;
    Smithy smithy;
    Bakery bakery;

    Recruiter recruiter;
    Thread recruiterThread;

    public Barracks(Smithy smith, Bakery bakery)
    {
        this.smithy = smith;
        this.bakery = bakery;
        warriorsT = new ArrayList<>();
        warriors = new ArrayList<>();
    }

    public void print()
    {
        String format = "%-15s %-15s %-15s %-15s %n";
        System.out.format(format, "Barracs", "Warriors", "Total power", "Total defense");
        System.out.format(format, "", warriors.size(),getWarriorsPower(),Math.max(0,getWarriorsDefense()));
    }

    public void stop()
    {
        for(Thread t: warriorsT)
        {
            t.interrupt();
        }
        recruiterThread.interrupt();
    }

    public void createWarriors(int n)
    {
        for(int i=0; i<n;i++)
        {
            createWarrior();
        }

        recruiter = new Recruiter(this);
        recruiterThread = new Thread(recruiter);
        recruiterThread.start();
    }

    public void createWarrior()
    {
        Warrior warrior = new Warrior(smithy, bakery);
        Thread thread = new Thread(warrior);
        thread.start();
        warriorsT.add(thread);
        warriors.add(warrior);
    }

    public int getWarriorsPower()
    {
        int power = 0;
        for(Warrior w: warriors)
        {
            power += w.getPower();
        }
        return power;
    }

    public int getWarriorsDefense()
    {
        int defense = 0;
        for(Warrior w: warriors)
        {
            defense += w.getDefense();
        }
        return defense;
    }
}
