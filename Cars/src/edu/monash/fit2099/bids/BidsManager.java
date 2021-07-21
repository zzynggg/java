package edu.monash.fit2099.bids;

import edu.monash.fit2099.buyers.Buyer;
import edu.monash.fit2099.exceptions.BidException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * The BidsManager class is used to store the buyer and bid into a HashMap
 *
 * @author Yong Zi Ying
 * @version 2.0.0
 * @see Bid
 * @see Buyer
 * @see BidException
 */
public class BidsManager {

    /**
     * Create a HashMap with String key and Bid value
     */
    private HashMap<String, Bid> offer;
    /**
     * Create a new list for Bid
     */
    private ArrayList<Bid> bidList = new ArrayList<>();

    /**
     * A zero parameter constructor of BidsManager class and it initialise the HashMap(offer)
     */
    public BidsManager() {
        // buyer's ID, bid value
        this.offer = new HashMap<String, Bid>();
    }

    /**
     * A method for other classes to invoke it in order to get the HashMap
     *
     * @return A HashMap offer with String key and Bid value
     */
    public HashMap<String, Bid> getOffer() {
        return offer;
    }

    /**
     * A method for other classes to invoke it in order to set the HashMap
     *
     * @param offer A HashMap offer with String key and Bid value
     */
    public void setOffer(HashMap<String, Bid> offer) {
        this.offer = offer;
    }

    /**
     * A method that add new bid by creating Bid class instance and Buyer class instance
     *
     * @param buyerID  A String buyerID that placed the Bid
     * @param bidPrice A Integer bidPrice that placed by the Buyer
     * @param bidDate  A String bidDate that placed by the Buyer
     */
    public void addBid(String buyerID, int bidPrice, String bidDate) {
        Buyer buyer = Buyer.getInstanceID(buyerID);
        try {
            Bid bid = new Bid(buyer, bidPrice, bidDate);
            offer.put(buyerID, bid);
            bidList.add(bid);
        } catch (BidException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * To remove the bid for particular vehicle
     * @param buyerID The buyer that place the bid for the vehicle
     * @param bidID The bid that placed by the buyer
     */
    public void removeBid(String buyerID, String bidID) {
        Boolean flag = false;
        if (!offer.isEmpty()) {
            for (int i = 0; i < bidList.size(); i++) {
                if (bidList.get(i).getBidId().equals(bidID)) {
                    offer.remove(buyerID, bidList.get(i));
                    flag = true;
                }

            }
        }
        if (flag == false) {
            System.out.println("Invalid Bid ID");
        }

    }

    /**
     * To return the best bid for the particular vehicle
     */
    public void bestBid() {
        Integer maxBid = 0;
        if (!offer.isEmpty()) {
            for (Bid newBid : offer.values()) {
                if (newBid.getBidPrice() > maxBid) {
                    maxBid = newBid.getBidPrice();
                }
            }
            System.out.print("The Best Bid is " + maxBid);
        }
    }

    /**
     * To return the worst bid for the particular vehicle
     */
    public void worstBid() {
        Map.Entry<String, Bid> entry = offer.entrySet().iterator().next();  // first value of the hashmap
        Integer minBid = entry.getValue().getBidPrice();
        if (!offer.isEmpty()) {
            for (Bid newBid : offer.values()) {
                if (newBid.getBidPrice() < minBid) {
                    minBid = newBid.getBidPrice();
                }
            }
            System.out.print("The Worst Bid is " + minBid);
        }
    }


    /**
     * To output the description of the added Bid by using the HashMap key and invoke the description method in Bid class
     *
     * @return A String output
     */
    public String description() {
        String output = "";
        if (!offer.isEmpty()) {
            for (String key : offer.keySet()) {
                output = output + offer.get(key).description() + "\n";
            }
        }
        return output;
    }

}
