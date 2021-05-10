package salwiczek.bartosz.Farm;

import salwiczek.bartosz.Mine.Ore;
import salwiczek.bartosz.Smithy.Armor;
import salwiczek.bartosz.Smithy.Helmet;
import salwiczek.bartosz.Smithy.Sword;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Farmer implements Runnable{

    private Farm farm;
    private int production_time = 600;

    public Farmer(Farm farm) {
        this.farm = farm;
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

            farm.putWheat(new Wheat());
        }
    }
}
