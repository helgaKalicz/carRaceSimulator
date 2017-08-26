import java.util.Random;

/**
 * Motorcycle // speed is 100km/h. If rains, travels with 5-50km/h slower (randomly).
 static nameNumber // The number of the instance created. Used for its name.
 name // Are called "Motorcycle 1", "Motorcycle 2", "Motorcycle 3",... Unique.
 distanceTraveled
 moveForAnHour()
 */

public class MotorCycle {

    private static int nameNumber = 0;
    public String name;
    private int normalSpeed;
    public int distanceTravelled;

    public MotorCycle(int speed) {
        this.name = String.format("Motorcycle %d", ++nameNumber);
        this.normalSpeed = speed;
        this.distanceTravelled = 0;
    }

    public void moveForAnHour(boolean isRaining, int changeSpeedOnRainMax, int changeSpeedOnRainMin) {
        if (isRaining) {
            Random speed = new Random();
            int speedLossPercentage = speed.nextInt(changeSpeedOnRainMax - changeSpeedOnRainMin) + changeSpeedOnRainMin;
            distanceTravelled += normalSpeed * (100 - speedLossPercentage) / 100;
        } else {
            distanceTravelled += normalSpeed;
        }
    }
}
