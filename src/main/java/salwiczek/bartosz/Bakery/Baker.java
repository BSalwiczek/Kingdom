package salwiczek.bartosz.Bakery;

import salwiczek.bartosz.Farm.Farm;
import salwiczek.bartosz.Farm.Wheat;

import java.util.concurrent.ThreadLocalRandom;

public class Baker implements Runnable{
    private Farm farm;
    private Bakery bakery;
    private int production_time = 500;

    public Baker(Farm farm, Bakery bakery) {
        this.farm = farm;
        this.bakery = bakery;
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
                farm.takeWheat();
                farm.takeWheat();
                farm.takeWheat();

                bakery.putBread(new Bread());
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
