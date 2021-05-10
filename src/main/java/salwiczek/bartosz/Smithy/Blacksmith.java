package salwiczek.bartosz.Smithy;

import salwiczek.bartosz.Mine.Carbon;
import salwiczek.bartosz.Mine.Mine;
import salwiczek.bartosz.Mine.Ore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Blacksmith implements Runnable{
    Mine mine;
    Smithy smithy;
    String produces;
    private int production_time = 1000;

    public Blacksmith(Mine mine, Smithy smithy,String produces) {
        this.mine = mine;
        this.smithy = smithy;
        this.produces = produces;
    }

    @Override
    public void run() {
        while(true)
        {
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(production_time-200,production_time+200));
            } catch (InterruptedException e) {
                return;
            }

            try {
                mine.takeCarbon();
                mine.takeOre();
                if(produces.equals("Swords"))
                {
                    smithy.putSword(new Sword());
                }
                if(produces.equals("Helmets"))
                {
                    smithy.putHelmet(new Helmet());
                }
                if(produces.equals("Armors"))
                {
                    smithy.putArmor(new Armor());
                }
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
