package salwiczek.bartosz;

import salwiczek.bartosz.Army.Barracks;
import salwiczek.bartosz.Bakery.Bakery;
import salwiczek.bartosz.Farm.Farm;
import salwiczek.bartosz.Mine.Mine;
import salwiczek.bartosz.Smithy.Blacksmith;
import salwiczek.bartosz.Smithy.Smithy;

import java.util.ArrayList;

public class Kingdom{
    Mine mine;
    Smithy smithy;
    Barracks barracs;
    Bakery bakery;
    Farm farm;
    String name;

    King king;

    public Kingdom(String name){
        this.name = name;
    }

    public void print(){
        System.out.println("=====================================================================");
        System.out.println(this.name + " Kingdom");
        System.out.println("=====================================================================");
        mine.print();
        farm.print();
        bakery.print();
        smithy.print();
        barracs.print();
        king.print(this.getPoints());
    }

    public void stop(){
        mine.stop();
        smithy.stop();
        farm.stop();
        barracs.stop();
        bakery.stop();
    }

    public double getPoints(){
        return barracs.getWarriorsDefense()*0.5 + barracs.getWarriorsPower();
    }

    public void start() {
        king = new King();
        mine = new Mine();
        mine.createMiners(5);

        farm = new Farm();
        farm.createFarmers(10);

        smithy = new Smithy(mine);
        smithy.createBlacksmiths(5);

        bakery = new Bakery(farm);
        bakery.createBakers(3);

        barracs = new Barracks(smithy, bakery);
        barracs.createWarriors(100);

        System.out.println("Kingdom "+name+" has started");
    }
}
