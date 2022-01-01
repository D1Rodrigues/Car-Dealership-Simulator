/**
 * ElectricCar class extends Car, describes the behaviour of an electric car, and
 * has new instance variables connected to minutiae of an electric car.
 */
public class ElectricCar extends Car {
    //instance variables
    private int rechargeTime; //time the electric car needs to charge
    private String batteryType; //battery type of the electric car

    /**
     * Constructor method for the ElectricCar class, uses certain parameters and
     * makes a super call to the Car class to access Car class constructor.
     * Parameters unique to Electric class listed below.
     * @param rechargeTime initializes rechargeTime variable
     * @param batteryType initializes batteryType varibale
     */
    public ElectricCar(String mfr, String color,  String model, String power,  double safetyRating, int maxRange, boolean AWD, double price, int rechargeTime, String batteryType) {
        super(mfr, color, model, power, safetyRating, maxRange, AWD, price);
        this.rechargeTime = rechargeTime;
        this.batteryType = batteryType;
    }

    /**
     * @return the batteryType
     */
    public String getBatteryType() {
        return batteryType;
    }

    /**
     * sets the batteryType variable
     * @param batteryType the batteryType to set
     */
    public void setBatteryType(String batteryType) {
        this.batteryType = batteryType;
    }

    /**
     * @return the rechargeTime
     */
    public int getRechargeTime() {
        return rechargeTime;
    }

    /**
     * sets the rechargeTime variable
     * @param rechargeTime the rechargeTime to set
     */
    public void setRechargeTime(int rechargeTime) {
        this.rechargeTime = rechargeTime;
    }

    /**
     * displays string containing values of instance variables, information about ElectricCar object
     * @return a String containing instance variables
     */
    public String display() {
        return super.display() + " EL, BAT: " + batteryType + " RCH " + rechargeTime;
    }
}
