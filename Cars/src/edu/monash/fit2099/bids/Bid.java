package edu.monash.fit2099.bids;

import edu.monash.fit2099.buyers.Buyer;
import edu.monash.fit2099.exceptions.BidException;

import java.util.Random;
import java.util.StringTokenizer;

/**
 * The Bid class is for buyer to add Bid based on vehicleID, buyerID, Bid price and date
 *
 * @author Yong Zi Ying
 * @version 1.0.0
 * @see Buyer
 * @see BidException
 * @see Random
 * @see StringTokenizer
 */
public class Bid {
    /**
     * A String bidID for the Bid
     */
    private String bidId;

    /**
     * The buyer that place the Bid
     */
    private Buyer buyer;

    /**
     * The Bid price that place by buyer, price must be more than zero
     */
    private int bidPrice;

    /**
     * The Bid date that buyer place the Bid, day must be in the range of 1..31, month must be in the range of 1..12, year must be in the range of 1930..2021 and the format for date must be 1/1/2021
     */
    private String bidDate;

    /**
     * The constructor of Bid class with 4 input parameters
     *
     * @param newBidId    A String bidID for the Bid
     * @param newBuyer    The buyer that place the Bid
     * @param newBidPrice The Bid price that place by buyer, price must be more than zero
     * @param newBidDate  The Bid date that buyer place the Bid, day must be in the range of 1..31, month must be in the range of 1..12, year must be in the range of 1930..2021 and the format for date must be 1/1/2021
     * @throws BidException          When the setBidPrice return false catch the exception
     * @throws NumberFormatException When user input String datatype for BidPrice catch the exception
     */
    public Bid(String newBidId, Buyer newBuyer, int newBidPrice, String newBidDate) throws BidException, NumberFormatException {
        bidId = newBidId;
        buyer = newBuyer;
        if (!setBidPrice(newBidPrice)) throw new BidException("Invalid bid price, it must be more than equal to 0");
        if (!setBidDate(newBidDate))
            throw new BidException("Invalid bid date, day must within 1-31, month must within 1-12, year must within 1930-2021");
    }

    /**
     * The constructor of Bid class with 3 input parameters, a random BidID will generate
     *
     * @param newBuyer    The buyer that place the Bid
     * @param newBidPrice The Bid price that place by buyer, price must be more than zero
     * @param newBidDate  The Bid date that buyer place the Bid, day must be in the range of 1..31, month must be in the range of 1..12, year must be in the range of 1930..2021 and the format for date must be 1/1/2021
     * @throws BidException          When the setBidPrice return false catch the exception
     * @throws NumberFormatException When user input String datatype for BidPrice catch the exception
     */
    public Bid(Buyer newBuyer, int newBidPrice, String newBidDate) throws BidException, NumberFormatException {
        Random random = new Random();  // to generate random ID
        int randomID = random.nextInt(1000);
        bidId = "Bid" + randomID;
        buyer = newBuyer;
        if (!setBidPrice(newBidPrice)) throw new BidException("Invalid bid price, it must be more than equal to 0");
        if (!setBidDate(newBidDate))
            throw new BidException("Invalid bid date, day must within 1-31, month must within 1-12, year must within 1930-2021");
    }

    /**
     * A method for other classes to invoke it in order to get the BidID
     *
     * @return A string bidID
     */
    public String getBidId() {
        return bidId;
    }

    /**
     * A method for other classes to invoke it in order to set the BidID
     *
     * @param bidId A string bidID
     */
    public void setBidId(String bidId) {
        this.bidId = bidId;
    }

    /**
     * A method for other classes to invoke it in order to get the Buyer that place the Bid
     *
     * @return An instance of Buyer class
     */
    public Buyer getBuyer() {
        return buyer;
    }

    /**
     * A method for other classes to invoke it in order to set the Buyer that place the Bid
     *
     * @param buyer An instance of Buyer class
     */
    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    /**
     * A method for other classes to invoke it in order to get the BidPrice that placed by the Buyer
     *
     * @return An Integer bidPrice
     */
    public int getBidPrice() {
        return bidPrice;
    }

    /**
     * A method for other classes to invoke it in order to set the BidPrice that placed by the Buyer
     *
     * @param bidPrice An Integer bidPrice
     * @return A boolean flag, to make sure the data inputted is accurate
     */
    public boolean setBidPrice(int bidPrice) {
        boolean flag = false;
        if (bidPrice >= 0) {
            this.bidPrice = bidPrice;
            flag = true;
        }
        return flag;
    }

    /**
     * A method for other classes to invoke it in order to get the BidDate that Buyer place the Bid
     *
     * @return A String bidDate
     */
    public String getBidDate() {
        return bidDate;
    }

    /**
     * A method for other classes to invoke it in order to set the BidDate that Buyer place the Bid
     *
     * @param newBidDate A String bidDate
     * @return A boolean flag, to make sure the data inputted is accurate
     */
    public boolean setBidDate(String newBidDate) {
        boolean flag = false;
        int day, month, year;
        // check for invalid date format input
        try {
            StringTokenizer stringTokenizer = new StringTokenizer(newBidDate, "/");
            day = Integer.parseInt(stringTokenizer.nextToken());
            month = Integer.parseInt(stringTokenizer.nextToken());
            year = Integer.parseInt(stringTokenizer.nextToken());
            // check for the range of dd/mm/yyyy
            if ((day >= 1 && day <= 31) && (month >= 1 && month <= 12)
                    && (year >= 1930 && year <= 2021)) {
                flag = true;
                this.bidDate = newBidDate;
            }
        } catch (NumberFormatException e) {
            System.out.println("Please input valid date, the following format will be accepted only 1/1/2021");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return flag;
    }

    /**
     * To output the description of the Buyer and the Bid that placed by the buyer
     *
     * @return A String output
     */
    public String description() {
        String output = "{BuyerID: " + buyer.getBuyerId() + "} = {BidID: " + bidId + " | BuyerID: " + buyer.getBuyerId() + " | Bid Price:  " + bidPrice + " | Bid Date: " + bidDate + "}";
        return output;
    }
}
