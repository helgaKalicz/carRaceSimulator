import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Truck // speed: 100km/h. 5% chance of breaking down for 2 hours.
 // Truck drivers are boring. They call all their trucks a random number between 0 and 1000.
 breakdownTurnsLeft // holds how long its still broken down.
 distanceTraveled
 moveForAnHour()
 */

public class Truck {

    private static List<String> takenTruckNames = new ArrayList<>();
    public String name;
    private int normalSpeed;
    public int distanceTravelled;
    private int breakdownTurnsLeft = 0;

    public Truck(int speed) {
        this.name = setTruckName(0, 1000);
        this.normalSpeed = speed;
        this.distanceTravelled = 0;
    }

    private String setTruckName(int nameFrom, int nameTo) {
        Random randomName = new Random();
        String newName = "";
        boolean isNameTaken = true;
        while (isNameTaken) {
            newName = Integer.toString(randomName.nextInt(nameTo - nameFrom) + nameFrom); // It works if it is not from 0
            if (!takenTruckNames.contains(newName)) {
                takenTruckNames.add(newName);
                isNameTaken = false;
            }
        }
        return newName;
    }

    public void moveForAnHour(int chanceToBreakDown, int breakDownHour) {
        Random isBreakDownChance = new Random();
        if (breakdownTurnsLeft == 0) {
            if (isBreakDownChance.nextInt(100) <= chanceToBreakDown) {
                breakdownTurnsLeft = breakDownHour - 1;
            } else {
                distanceTravelled += normalSpeed;
            }
        } else {
            breakdownTurnsLeft--;
        }
    }
}
