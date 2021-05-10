package salwiczek.bartosz.Army;

import java.util.concurrent.ThreadLocalRandom;

public class Recruiter implements Runnable {

    Barracks barracks;
    private int recruiting_time = 1000;

    public Recruiter(Barracks barracks)
    {
        this.barracks = barracks;
    }

    @Override
    public void run() {

        while(true)
        {
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(recruiting_time-200,recruiting_time+2000));
            } catch (InterruptedException e) {
                return;
            }

            barracks.createWarrior();
        }

    }
}
