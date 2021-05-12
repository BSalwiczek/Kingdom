package salwiczek.bartosz.Battlefield;

import lombok.Getter;
import salwiczek.bartosz.Army.Warrior;
import salwiczek.bartosz.Bakery.Baker;
import salwiczek.bartosz.Bakery.Bread;
import salwiczek.bartosz.Kingdom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Battlefield {
    private final List<Warrior> opponents;
    private final ArrayList<Thread> battles;
    private final List<Warrior> warriors2;
    private final List<Warrior> dead;

    @Getter
    String winner;

    public Battlefield(Kingdom k1, Kingdom k2)
    {
        ArrayList<Warrior> warriors1 = k1.getBarracs().getWarriors();
        battles = new ArrayList<>();

        opponents = Collections.synchronizedList(new ArrayList<Warrior>(warriors1));
        warriors2 = Collections.synchronizedList(new ArrayList<Warrior>(k2.getBarracs().getWarriors()));
        dead = Collections.synchronizedList(new ArrayList<Warrior>());

        for(Warrior w: warriors2)
        {
            Battle battle = new Battle(w, this);
            Thread thread = new Thread(battle);
            thread.start();
            battles.add(thread);
        }


        for(Thread t: battles)
        {
            try {
                t.join();
            } catch (InterruptedException e) {
                return;
            }
        }

        for(Warrior d: dead)
        {
            warriors2.remove(d);
        }



        if(opponents.size() == 0)
        {
            this.winner = k2.getName();
        }else{
            this.winner = k1.getName();
        }

        k1.getBarracs().setWarriors(new ArrayList<Warrior>(opponents));
        k2.getBarracs().setWarriors(new ArrayList<Warrior>(warriors2));

    }

    public Warrior takeOpponent() throws InterruptedException {
        synchronized(opponents)
        {
            while (opponents.isEmpty()) {
                opponents.wait(1000);
                if(opponents.isEmpty())
                    return null;
            }
            Warrior ret = opponents.get(0);
            opponents.remove(0);
            return ret;
        }

    }

    public void removeWarrior(Warrior w) {
        synchronized (dead)
        {
            dead.add(w);
        }
    }

    public void putOpponent(Warrior warrior) {
        synchronized(opponents) {
            opponents.add(warrior);
            opponents.notifyAll();
        }
    }
}
