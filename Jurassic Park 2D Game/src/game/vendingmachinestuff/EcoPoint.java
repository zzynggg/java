package game.vendingmachinestuff;

public class EcoPoint {
    /**
     * eco points
     */
    private Integer ecoPoint;
    private Integer maxEcoPoints;

    /**
     * EcoPoint constructor
     */
    public EcoPoint() {
        ecoPoint = 0;
        maxEcoPoints =0;
    }

    public Integer getMaxEcoPoints() {
        return maxEcoPoints;
    }

    public void setMaxEcoPoints(Integer maxEcoPoints) {
        this.maxEcoPoints = maxEcoPoints;
    }

    /**
     * to get eco point
     * @return value of ecopoints
     */
    public Integer getEcoPoint() {
        return ecoPoint;
    }

    /**
     * to increment ecopoint
     * @param newEcoPoint new ecopoint to add
     */
    public void incEcoPoint(Integer newEcoPoint) {
        ecoPoint += newEcoPoint;
    }

    /**
     * to set the ecopoint
     * @param ecoPoint new eco point to add
     */
    public void setEcoPoint(Integer ecoPoint) {
        this.ecoPoint = ecoPoint;
    }

    public String menuDescription() {
        return ("Player Eco Points: " + getEcoPoint());
    }
}
