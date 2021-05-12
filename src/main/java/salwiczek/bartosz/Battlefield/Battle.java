package salwiczek.bartosz.Battlefield;

import salwiczek.bartosz.Army.Warrior;
import salwiczek.bartosz.Kingdom;

import java.util.ArrayList;
import java.util.List;

public class Battle implements Runnable{
    Warrior w;
    Battlefield bf;

    public Battle(Warrior w, Battlefield bf)
    {
        this.w = w;
        this.bf = bf;
    }

    @Override
    public void run() {
        try {
            while (w.getHealth() > 0)
            {
                Warrior opponent = bf.takeOpponent();
                if(opponent == null)
                    return;

                while(opponent.getHealth() > 0 && w.getHealth() > 0)
                {
                    if(opponent.getDefense() > 0)
                    {
                        opponent.setDefense(opponent.getDefense() - w.getPower());
                    }else{
                        opponent.setHealth(opponent.getHealth() - w.getPower());
                    }

                    if(w.getDefense() > 0)
                    {
                        w.setDefense(w.getDefense() - opponent.getPower());
                    }else{
                        w.setHealth(w.getHealth() - opponent.getPower());
                    }
                }

                if(opponent.getHealth() > 0)
                {
                    bf.putOpponent(opponent);
                    bf.removeWarrior(w);

                    return;
                }

                if(w.getHealth() <= 0)
                {
                    bf.removeWarrior(w);

                    return;
                }
            }
        } catch (InterruptedException e) {
            return;
        }
    }
}
