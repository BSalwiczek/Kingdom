package salwiczek.bartosz.Mine;

import lombok.ToString;

import java.util.concurrent.ThreadLocalRandom;

@ToString
public class Miner implements Runnable {
    private String produces;
    Mine mine;
    private int production_time = 1000;

    public Miner(String produces, Mine mine)
    {
        this.produces = produces;
        this.mine = mine;
    }

    @Override
    public void run() {
        while (true)
        {
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(production_time-200,production_time+200));
            } catch (InterruptedException e) {
                return;
            }

            if(produces.equals("Carbon"))
            {
                mine.putCarbon(new Carbon());
            }
            if(produces.equals("Ore"))
            {
                mine.putOre(new Ore());
            }
        }
    }
}
