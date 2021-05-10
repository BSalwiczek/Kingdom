package salwiczek.bartosz.Army;

import lombok.Getter;
import salwiczek.bartosz.Bakery.Bakery;
import salwiczek.bartosz.Farm.Farm;
import salwiczek.bartosz.Smithy.Armor;
import salwiczek.bartosz.Smithy.Helmet;
import salwiczek.bartosz.Smithy.Smithy;
import salwiczek.bartosz.Smithy.Sword;

import java.util.concurrent.ThreadLocalRandom;

public class Warrior implements Runnable{
    @Getter
    int power;

    @Getter
    int defense;
    Smithy smithy;
    Bakery bakery;

    public Warrior(Smithy smithy, Bakery bakery)
    {
        this.smithy = smithy;
        this.bakery = bakery;
        power = 10;
        defense = 0;
    }

    @Override
    public void run() {
        try {
            Sword sword = smithy.takeSword();
            power += sword.getPower();
            Armor armor = smithy.takeArmor();
            defense += armor.getArmor();
            Helmet helmet = smithy.takeHelmet();
            defense += helmet.getArmor();

            while (true)
            {
                try {
                    Thread.sleep(ThreadLocalRandom.current().nextInt(200, 1000));
                } catch (InterruptedException e) {
                    return;
                }

                bakery.takeBread();
                power += 1;
            }
        } catch (InterruptedException e) {
//            return;
        }

    }
}
