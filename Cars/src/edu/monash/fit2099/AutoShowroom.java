package edu.monash.fit2099;

import edu.monash.fit2099.bids.BidsManager;
import edu.monash.fit2099.buyers.Buyer;
import edu.monash.fit2099.exceptions.BidException;
import edu.monash.fit2099.exceptions.SedanException;
import edu.monash.fit2099.exceptions.TruckException;
import edu.monash.fit2099.exceptions.VehicleException;
import edu.monash.fit2099.vehicles.Sedan;
import edu.monash.fit2099.vehicles.Truck;
import edu.monash.fit2099.vehicles.Vehicle;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The AutoShowroom class have 2 edu.monash.fit2099.vehicles which are Sedan and Truck.
 * Buyer can place Bid for any edu.monash.fit2099.vehicles
 *
 * @author Yong Zi Ying
 * @version 2.0.0
 * @see Vehicle
 * @see Truck
 * @see Sedan
 * @see Buyer
 * @see BidsManager
 * @see IAutoShowroom
 */
public class AutoShowroom implements IAutoShowroom {
    //   create an global object array list in order to allow several method to use.
    /**
     * A vehicle ArrayList
     */
    private ArrayList<Vehicle> vehicleArrayList = new ArrayList<>();
    /**
     * A Vehicle ArrayList to store the sold vehicle
     */
    private ArrayList<Vehicle> sellVehicleArrayList = new ArrayList<>();

    /**
     * A buyer ArrayList
     */
    private ArrayList<Buyer> buyerArrayList = new ArrayList<>();
    /**
     * A String ID for vehicle, buyer and bid
     */
    private String vehicleID, buyerID, bidID;
    // sedan
    /**
     * A String Sedan's maker and model, in the range of 3..15
     */
    private String sedanMaker, sedanModel;
    /**
     * A String Sedan's seats, in the range of 4..5
     */
    private String sedanSeats;
    // truck
    /**
     * A String truck's maker and model, in the range of 3..15
     */
    private String truckMaker,truckModel ;
    /**
     * A String truck's capacity, in the range of 1..15
     */
    private String truckCapacity;
    /**
     * A String truck's wheels, in the range of 4..16
     */
    private String truckWheels;
    // buyer
    /**
     * A String buyer's given name and family name, in the range of 2..15
     */
    private String buyerGivenName,buyerFamilyName;      // given name
    // bid
    /**
     * A String bidPrice that placed by buyer, price must be more than zero
     */
    private String bidPrice;
    /**
     * A String bidDate that the buyer placed the bid, day must be in the range of 1..31, month must be in the range of 1..12, year must be in the range of 1930..2021 and the format for date must be 1/1/2021
     */
    private String date;
    /**
     * Create boolean check for valid best bid, worst bid, new bid and remove bid
     */
    private Boolean bestBid, worstBid, newBid, removeBid = false;

    /**
     * A method that output the current status of the showroom
     */
    public void printStatus() {
        System.out.println("Welcome to FIT2099 Showroom");
        createCars();
        System.out.println("\nThank you for visiting FIT2099 Showroom");
    }

    /**
     * A method that scan and output user input based on the option chosen by user
     */
    @Override
    public void createCars() {
        int choice;
        Scanner scan = new Scanner(System.in);
        do {
            removeBid = false;
            newBid = false;
            bestBid = false;
            worstBid = false;
            choice = inputMenu();
            switch (choice) {
                case 0:
                    System.out.println("Invalid Option!");
                    break;
                case 1:
                    // newSedan
                    System.out.println("Option 1 is selected to add new sedan!");
                    System.out.println("Please enter sedan maker: ");
                    sedanMaker = scan.next();

                    System.out.println("Please enter sedan model: ");
                    sedanModel = scan.next();

                    System.out.println("Please enter sedan seats: ");
                    sedanSeats = scan.next();
                    // fail-fast
                    assert Integer.parseInt(sedanSeats) >= 4 && Integer.parseInt(sedanSeats)<=5:"seats should be either 4 or 5";
                    createSedan();
                    break;

                case 2:
                    // new truck
                    System.out.println("Option 2 is selected to add new truck!");
                    System.out.println("Please enter truck maker: ");
                    truckMaker = scan.next();

                    System.out.println("Please enter truck model: ");
                    truckModel = scan.next();

                    System.out.println("Please enter truck capacity: ");
                    truckCapacity = scan.next();

                    System.out.println("Please enter truck wheels: ");
                    truckWheels = scan.next();
                    createTruck();
                    break;

                case 3:
                    // display fleet
                    System.out.println("Option 3 is selected to display fleet!");
                    displayFleet();
                    break;

                case 4:
                    // Add Buyer
                    System.out.println("Option 4 is selected to add buyer!");
                    System.out.println("Please enter buyer's first/given name: ");
                    buyerGivenName = scan.next();// reads string

                    System.out.println("Please enter buyer's last/family name: ");
                    buyerFamilyName = scan.next();
                    createBuyer();
                    break;

                case 5:
                    // display buyers
                    System.out.println("Option 5 is selected to display buyers!");
                    displayBuyers();
                    break;

                case 6:
                    // Add bid
                    System.out.println("Option 6 is selected to add bid!");
                    System.out.println("Please enter vehicle ID: ");
                    vehicleID = scan.next();

                    System.out.println("Please enter buyer ID: ");
                    buyerID = scan.next();

                    System.out.println("Please enter bid price: ");
                    bidPrice = scan.next();
                    // fail-fast
                    assert Integer.parseInt(bidPrice) > 0:"bid price should be larger than equal to 0";

                    System.out.println("Please enter date: ");
                    date = scan.next();

                    newBid = true;
                    try {
                        createBid();
                    } catch (NumberFormatException e) {
                        System.out.println(e.getMessage());
                        System.out.println("Price must be Integer only and larger than 0");
                    } catch (BidException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 7:
                    //delete bid
                    System.out.println("Option 7 is selected to delete bid!");
                    System.out.println("Please enter vehicle ID: ");
                    vehicleID = scan.next();

                    System.out.println("Please enter buyer ID: ");
                    buyerID = scan.next();

                    System.out.println("Please enter bid ID to delete it: ");
                    bidID = scan.next();

                    removeBid = true;
                    try {
                        createBid();
                    } catch (BidException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 8:
                    //display best bid
                    System.out.println("Option 8 is selected to return BEST bid!");
                    System.out.println("Please enter vehicle ID: ");
                    vehicleID = scan.next();

                    bestBid = true;
                    try {
                        createBid();
                    } catch (BidException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 9:
                    //display worst bid
                    System.out.println("Option 9 is selected to return WORST bid!");
                    System.out.println("Please enter vehicle ID: ");
                    vehicleID = scan.next();

                    worstBid = true;
                    try {
                        createBid();
                    } catch (BidException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 10:
                    //sell vehicle
                    System.out.println("Option 10 is selected to sell vehicle!");
                    System.out.println("Please enter vehicle ID: ");
                    vehicleID = scan.next();

                    sellVehicle();
                    break;


                case 11:
                    //display sold vehicle
                    System.out.println("Option 11 is selected to display sold vehicle!");
                    displaySoldVehicle();
                    break;

                case 12:
                    System.out.println("Exit");
                    break;
            }
        } while (choice != 12);
    }

    /**
     * An input menu for user reference
     *
     * @return An Integer option
     */
    @Override
    public Integer inputMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n1) New Sedan");
        System.out.println("2) New Truck");
        System.out.println("3) Display Fleet");
        System.out.println("4) Add Buyer");
        System.out.println("5) List Buyers");
        System.out.println("6) Add Bid");
        System.out.println("7) Delete Bid");
        System.out.println("8) Display Best Bid");
        System.out.println("9) Display Worst Bid");
        System.out.println("10) Sell Vehicles");
        System.out.println("11) Display Sold Vehicles");
        System.out.println("12) Exit");
        System.out.println("--------------------");
        System.out.println("Select your option: ");

        Integer checkedOption = 0;
        try {
            Integer option = scanner.nextInt();
            if (option >= 1 && option <= 12) {
                checkedOption = option;
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return checkedOption;
    }

    /**
     * A method that create Sedan based on user input sedan's maker, model and seats
     */
    @Override
    public void createSedan() {
        // read from user maker, model, seats
        try {
            Sedan createdSedan = new Sedan(sedanMaker, sedanModel, Integer.parseInt(sedanSeats));
            vehicleArrayList.add(createdSedan);
            System.out.println(vehicleArrayList.get(vehicleArrayList.size() - 1).description());
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            System.out.println("Please input valid seats within 4-5");
            System.out.println("Vehicle failed to add!");
        } catch (SedanException e) {
            System.out.println(e.getMessage());
            System.out.println("Vehicle failed to add!");
        } catch (VehicleException e) {
            System.out.println(e.getMessage());
            System.out.println("Vehicle failed to add!");
        }
    }

    /**
     * A method that create Truck based on user input truck's maker, model, capacity and wheels
     */
    @Override
    public void createTruck() {
        // read from user maker, model, capacity, wheels
        try {
            Truck createdTruck = new Truck(truckMaker, truckModel, Integer.parseInt(truckCapacity), Integer.parseInt(truckWheels));
            vehicleArrayList.add(createdTruck);
            System.out.println(vehicleArrayList.get(vehicleArrayList.size() - 1).description());
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            System.out.println("Truck capacity and truck wheel must be in Integer");
            System.out.println("Vehicle failed to add!");
        } catch (TruckException e) {
            System.out.println(e.getMessage());
            System.out.println("Vehicle failed to add!");
        } catch (VehicleException e) {
            System.out.println(e.getMessage());
            System.out.println("Vehicle failed to add!");
        }
    }

    /**
     * A method that create Buyer based on user input buyer's given name and family name
     */
    @Override
    public void createBuyer() {
        Buyer createdBuyer = Buyer.getInstance(buyerGivenName, buyerFamilyName);
        if (createdBuyer != null) {
            buyerArrayList.add(createdBuyer);
            System.out.println(buyerArrayList.get(buyerArrayList.size() - 1).description());
        } else
            System.out.println("Buyer name should be within 2-15 letters");
    }

    /**
     * A method that create Bid based on user input vehicleID, BuyerID, Bid price and Bid date
     * To search for the best bid, worst bid and remove the bid
     * @throws BidException          To catch any unexpected exception
     * @throws NumberFormatException When user input String datatype for bid price
     */
    @Override
    public void createBid() throws BidException, NumberFormatException {
        // check invalid vehicle ID inputted
        boolean vehicleExist = false;
        boolean buyerExist = false;
        for (int i = 0; i < vehicleArrayList.size(); i++) {
            // vehicle exist
            if (vehicleArrayList.get(i).getVehicleID().equals(vehicleID)) {
                vehicleExist = true;
                for (int j = 0; j < buyerArrayList.size(); j++) {
                    // buyer exist
                    if (buyerArrayList.get(j).getBuyerId().equals(buyerID)) {
                        buyerExist = true;
                        // remove bid
                        if (removeBid) {
                            vehicleArrayList.get(i).getBidsManager().removeBid(buyerID, bidID);
                        }
                        if (newBid) {
                            // vehicle and buyer exist just add bid
                            vehicleArrayList.get(i).getBidsManager().addBid(buyerID, Integer.parseInt(bidPrice), date);
                        }
                        if (bestBid) {
                            vehicleArrayList.get(i).getBidsManager().bestBid();
                            System.out.println(" for this vehicle with ID " + vehicleArrayList.get(i).getVehicleID() + " and maker " + vehicleArrayList.get(i).getMaker());
                        }
                        if(worstBid){
                            vehicleArrayList.get(i).getBidsManager().worstBid();
                            System.out.println(" for this vehicle with ID " + vehicleArrayList.get(i).getVehicleID() + " and maker " + vehicleArrayList.get(i).getMaker());
                        }
                    }
                }
                if ((bestBid || worstBid) && !buyerExist) {
                    // no buyer = No bid
                    System.out.println("No bid for this vehicle!");
                }
            }
        }
        if (!bestBid || !worstBid) {
            if (!buyerExist && buyerArrayList.size() > 0) {
                System.out.println("The Buyer ID is not exist!");
                System.out.println("Failed to add Bid");
            }
        }

        if (!vehicleExist && vehicleArrayList.size() > 0) {
            System.out.println("The Vehicle ID is not exist!");
            System.out.println("Failed to add Bid");
        }
        if (vehicleArrayList.isEmpty()) {
            System.out.println("The Vehicle list is empty!");
            System.out.println("Please add vehicle before add Bid to it");
        }
        if (!bestBid && !worstBid) {
            if (buyerArrayList.isEmpty()) {
                System.out.println("The Buyer list is empty!");
                System.out.println("Please add buyer before add Bid");
            }
        }

    }

    /**
     * A method that sell the vehicle (remove the vehicle)
     */
    public void sellVehicle(){
        Boolean sold = false;
        for(int i = 0; i < vehicleArrayList.size(); i++){
            if (vehicleID.equals(vehicleArrayList.get(i).getVehicleID())){
                sold = true;
                Vehicle removedVehicle = vehicleArrayList.remove(i);
                sellVehicleArrayList.add(removedVehicle);
            }
        }
        if(!sold){
            System.out.println("Invalid vehicle");
        }
    }

    /**
     * A method that display the sold vehicle ArrayList
     */
    public void displaySoldVehicle(){
        if (sellVehicleArrayList.isEmpty()) {
            System.out.println("The Vehicle sold list is empty!");
        }
        for (int i = 0; i < sellVehicleArrayList.size(); i++) {
            System.out.println(sellVehicleArrayList.get(i).description());
        }
    }

    /**
     * A method to display the vehicle ArrayList and the Bid that added for the vehicle
     */
    public void displayFleet() {
        // print list of vehicle
        if (vehicleArrayList.isEmpty()) {
            System.out.println("The Vehicle list is empty!");
        }
        for (int i = 0; i < vehicleArrayList.size(); i++) {
            System.out.println(vehicleArrayList.get(i).description());
            System.out.println(vehicleArrayList.get(i).getBidsManager().description());
        }
    }

    /**
     * A method that display the buyer ArrayList
     */
    public void displayBuyers() {
        // print list of buyer
        if (buyerArrayList.isEmpty()) {
            System.out.println("The Buyer list is empty!");
        }
        for (int j = 0; j < buyerArrayList.size(); j++) {
            System.out.println(buyerArrayList.get(j).description());
        }
    }

}