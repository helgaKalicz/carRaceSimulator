import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Car
 // Since cars are so fast there is a 30% chance that they can go only with 70km/h speed.
 static setSpeedLimit(int limit) // Call this from the Main class!
 normalSpeed // the normal speed of the car. Set to a random number in the constructor between 80-110km/h.
 name // Make a list from the words here: http://www.fantasynamegenerators.com/car-names.php and pick 2 randomly for each instance.
 distanceTraveled // holds the current distance traveled.
 moveForAnHour() // The vehicle travels for an hour. It increases the distance traveled. Call this from the main class only!
 */

public class Car {

    private static List<String> takenCarNames = new ArrayList<>();
    private static List<String> carNames = new ArrayList<>(Arrays.asList("Scorpion", "Renegade", "Blitz", "Bullet",
            "Blade", "Hydra", "Patron", "Empyrean", "Eternity", "Millenium", "Specter", "Cobra", "Legend", "Vortex"));
    public String name;
    private int normalSpeed;
    public int distanceTravelled;
    private static int speedLimit;

    public Car(int minSpeed, int maxSpeed) {
        Random randomSpeed = new Random();
        this.name = setCarName();
        this.normalSpeed = randomSpeed.nextInt(maxSpeed - minSpeed) + minSpeed;
        this.distanceTravelled = 0;
    }

    private String setCarName() {
        Random randomName = new Random();
        String firsName = "";
        String lastName = "";
        String fullName = "";
        boolean isNameTaken = true;
        while (isNameTaken) {
            while (firsName.equals(lastName)) {
                firsName = carNames.get(randomName.nextInt(carNames.size()));
                lastName = carNames.get(randomName.nextInt(carNames.size()));
                fullName = firsName.concat(" ").concat(lastName);
            }
            if (!takenCarNames.contains(fullName)) {
                takenCarNames.add(fullName);
                isNameTaken = false;
            }
        }
        return fullName;
    }

    public static void setSpeedLimit(int limit) {
        speedLimit = limit;
    }

    public void moveForAnHour() {
        distanceTravelled += speedLimit == 0 ? normalSpeed : speedLimit;
    }
}
