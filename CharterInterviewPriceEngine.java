package com.micro.interview;

import java.util.HashMap;
import java.util.Scanner;

public class CharterInterviewPriceEngine {
  private static HashMap<String, HashMap<String, Double>> carData;

  public static void main(String[] args) {
    boolean isProgramStop = false;
    while (!isProgramStop) {
      isProgramStop = startTheProgram();
    }
  }

  private static boolean startTheProgram() {
    if (carData == null) {
      System.out.println("Initializing Car data.");
      createCarData();
    }

    Scanner scanner = new Scanner(System.in);
    String vehicleType = null;
    Integer vehicleCountInt = 0;
    String priceMethod = null;
    Double pricingMethodUnitsF = 0.0;
    boolean findVehicle = false;
    while (!findVehicle) {
      System.out.print("Please type the vehicle type or exit: ");
      vehicleType =  getValueOrExit(scanner);
      if (carData.get(vehicleType) != null) {
        findVehicle = true;
      } else {
        findVehicle = false;
        System.out.println("Please enter valid car type.");
      }
    }
    boolean vaildNumber = false;
    while (!vaildNumber) {
      try {
        System.out.print("Please type the vehicle count or exit: ");
        String vehicleCount = getValueOrExit(scanner);
        vehicleCountInt = Integer.parseInt(vehicleCount);
        vaildNumber = true;
      } catch (Exception e) {
        System.out.println("Please input valid number.");
        vaildNumber = false;
      }
    }
    boolean vaildPriceMethod = false;
    while (!vaildPriceMethod) {
      System.out.print("Please type the price method or exit: ");
      priceMethod = getValueOrExit(scanner);
      if (carData.get(vehicleType).get(priceMethod) != null) {
        vaildPriceMethod = true;
      } else {
        vaildPriceMethod = false;
        System.out.println("Please enter valid payment type.");
      }
    }

    boolean vaildNumberOfMethodUnits = false;
    while (!vaildNumberOfMethodUnits) {
      try {
        System.out.print("Please type the pricing unit or exit: ");
        String pricingMethodUnits = getValueOrExit(scanner);
        pricingMethodUnitsF = Double.parseDouble(pricingMethodUnits);
        vaildNumberOfMethodUnits = true;
      } catch (Exception e) {
        System.out.println("Please input valid pricing units.");
        vaildNumberOfMethodUnits = false;
      }
    }

    double finalPrice = vehicleCountInt.doubleValue() * carData.get(vehicleType).get(priceMethod) * pricingMethodUnitsF;
    System.out.println("Here is your final price: " + finalPrice);

    System.out.print("Would you like to run again Y/N? or exit");
    String answer = getValueOrExit(scanner);
    return answer.equalsIgnoreCase("n");
  }

  private static String getValueOrExit(Scanner scanner) {
    String value = scanner.nextLine();
    if (value.equalsIgnoreCase("exit")) {
      System.exit(0);
    }
    return value;
  }

  private static void createCarData() {
    HashMap<String, HashMap<String, Double>> carData = new HashMap<>();
    // Charter Price
    addPriceModel(carData, "Charter", 1000.00, 400.00, 3.50);
    addPriceModel(carData, "Mini Bus", 925.00, 360.00, 3.25);
    addPriceModel(carData, "Sprinter", 850.00, 320.00, 3.00);
    addPriceModel(carData, "Party Bus", 775.00, 280.00, 2.75);
    addPriceModel(carData, "Sedan", 700.00, 240.00, 2.50);
    addPriceModel(carData, "SUV", 625.00, 200.00, 2.25);
    addPriceModel(carData, "Limousine", 550.00, 160.00, 2.00);
    addPriceModel(carData, "Trolley", 475.00, 120.00, 1.75);

    CharterInterviewPriceEngine.carData = carData;
  }

  private static void addPriceModel(HashMap<String, HashMap<String, Double>> carData, String carType, Double dailyPrice, Double hourlyPrice, Double distancePrice) {
    HashMap<String, Double> charterPrices = new HashMap<>() {{
      put("Daily", dailyPrice);
      put("Hourly", hourlyPrice);
      put("Distance", distancePrice);
    }};
    carData.put(carType, charterPrices);
  }
}
