import java.util.*;

/**
 * Main
 static isRaining //30% chance of rain every hour
 createVehicles() // creates 10 cars, 10 trucks and 10 motorcycles
 simulateRace() // simulates the race by calling moveForAnHour() on every vehicle 50 times and setting whether its raining.
 printRaceResults() // prints each vehicle's name, distance traveled ant type.
 */

public class Main {

    private static int hoursOfRace = 50;

    // Cars details.
    private static int carNumber = 10;
    private static int carMinSpeed = 80;
    private static int carMaxSpeed = 110;
    private static int carSpeedLimit = 70;
    private static int carSpeedLimitPercentage = 30;
    private static ArrayList<Car> cars = new ArrayList<>();

    // MotorCycles details.
    private static int motorCycleNumber = 10;
    private static int motorCycleSpeed = 100;
    private static int changeSpeedOnRainMin = 5;
    private static int changeSpeedOnRainMax = 50;
    private static int rainPercentage = 30;
    private static boolean isRaining;
    private static ArrayList<MotorCycle> motorCycles = new ArrayList<>();

    //Truck details.
    private static int truckNumber = 10;
    private static int truckSpeed = 100;
    private static int chanceToBreakDown = 5;
    private static int breakdownHours = 2;
    private static ArrayList<Truck> trucks = new ArrayList<>();

    private static void changeCarSpeed(int percentage) {
        Random chanceToSpeedLimit = new Random();
        if (chanceToSpeedLimit.nextInt(100) <= percentage) {
            Car.setSpeedLimit(carSpeedLimit);
        } else {
            Car.setSpeedLimit(0);
        }
    }

    private static boolean checkRaining(int percentage) {
        Random chanceToRain = new Random();
        return chanceToRain.nextInt(100) <= percentage;
    }

    private static void createVehicles(int carNum, int motorNum, int truckNum) {
        for (int i = 0; i < carNum; i++) {
            cars.add(new Car(carMinSpeed, carMaxSpeed));
        }
        for (int i = 0; i < motorNum; i++) {
            motorCycles.add(new MotorCycle(motorCycleSpeed));
        }
        for (int i = 0; i < truckNum; i++) {
            trucks.add(new Truck(truckSpeed));
        }
    }

    private static void simulateRace(int hours) {
        for (int i = 0; i < hours; i++) {
            for (Car element : cars) {
                changeCarSpeed(carSpeedLimitPercentage);
                element.moveForAnHour();
            }
            for (MotorCycle element : motorCycles) {
                isRaining = checkRaining(rainPercentage);
                element.moveForAnHour(isRaining, changeSpeedOnRainMax, changeSpeedOnRainMin);
            }
            for (Truck element : trucks) {
                element.moveForAnHour(chanceToBreakDown, breakdownHours);
            }
        }
    }


    private static void printRaceResults() {
        LinkedList<HashMap<Integer, HashMap<String, String>>> raceResult = new LinkedList<>();
        for (Car element : cars) {
            HashMap<Integer, HashMap<String, String>> carDistance = new HashMap<>();
            HashMap<String, String> carDetails = new HashMap<>();
            carDistance.put(element.distanceTravelled, carDetails);
            carDetails.put("Type", "Car");
            carDetails.put("Name", element.name);
            raceResult.add(carDistance);
        }
        for (MotorCycle element : motorCycles) {
            HashMap<Integer, HashMap<String, String>> motorDistance = new HashMap<>();
            HashMap<String, String> motorDetails = new HashMap<>();
            motorDistance.put(element.distanceTravelled, motorDetails);
            motorDetails.put("Type", "Motorcycle");
            motorDetails.put("Name", element.name);
            raceResult.add(motorDistance);
        }
        for (Truck element : trucks) {
            HashMap<Integer, HashMap<String, String>> truckDistance = new HashMap<>();
            HashMap<String, String> truckDetails = new HashMap<>();
            truckDistance.put(element.distanceTravelled, truckDetails);
            truckDetails.put("Type", "Truck");
            truckDetails.put("Name", element.name);
            raceResult.add(truckDistance);
        }
        raceResult = sortingResult(raceResult);
        for (int i = raceResult.size()-1; i >= 0; i--){
            String distance = (raceResult.get(i).keySet().toArray()[0]).toString();
            String type = raceResult.get(i).get(raceResult.get(i).keySet().toArray()[0]).get("Type");
            String name = raceResult.get(i).get(raceResult.get(i).keySet().toArray()[0]).get("Name");
            System.out.printf("Vehicle type:\t\t%s\nVehicle name:\t\t%s\nDistance travelled:\t%s\n\n", type, name, distance);
        }
    }

    private static LinkedList<HashMap<Integer, HashMap<String, String>>> sortingResult(LinkedList<HashMap<Integer, HashMap<String, String>>> resultList) {
        for (int i = 0; i < resultList.size(); i++) {
            for (int j = 0; j < resultList.size() - 1 ; j++) {
                if (Integer.valueOf((resultList.get(j).keySet().toArray()[0]).toString()) > Integer.parseInt((resultList.get(j + 1).keySet().toArray()[0]).toString())) {
                    HashMap<Integer, HashMap<String, String>> elementToChange = resultList.get(j);
                    resultList.set(j, resultList.get(j + 1));
                    resultList.set(j + 1, elementToChange);
                }
            }
        }
        return resultList;
    }

    public static void main(String[] args) {
        createVehicles(carNumber, motorCycleNumber, truckNumber);
        simulateRace(hoursOfRace);
        printRaceResults();
    }
}
