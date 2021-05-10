package salwiczek.bartosz;

public class King{
    public King()
    {

    }

    public void print(double points)
    {
        String format = "%-15s %-15s %n";
        System.out.format(format, "King", "Military power");
        System.out.format(format, "", points);
    }
}
