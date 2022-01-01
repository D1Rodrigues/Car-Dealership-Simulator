//import java.util.LinkedList;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

/**
 * Dylan Rodrigues, student number: 500881797, Mar. 6, 2019. Vehicle class
 * implements an object that behaves like a vehicle.
 */
public class Vehicle {
    // instance variables
    private String mfr; // name of manufacturer
    private String color; // color of vehicle
    private int power; // variable holds engine types
    // next three final constant variables defines the different engine types
    public static final int GAS_ENGINE = 0;
    public static final int DIESEL_ENGINE = 1;
    public static final int ELECTRIC_ENGINE = 2;
    private int numWheels; // number of wheels the vehicle has
    private int VIN; // vehicle identification number
    Random rand = new Random(); // a Random object that generates a VIN
    private static Set<Integer> vinNumbers = new TreeSet<Integer>(); //Set that stores all generated VIN numbers

    /**
     * Constructor method initializes the instance variables in Vehicle class.
     * The VIN is created by a Random Number Generator.
     * @param mfr       is the manufacturer name
     * @param color     is the color of vehicle
     * @param i         describes engine of vehicle using the PowerSource enum class
     * @param numWheels the number of wheels of the vehicle
     */
    public Vehicle(String mfr, String color, String i, int numWheels) {
        this.mfr = mfr;
        this.color = color;
        setEngineType(i);
        this.numWheels = numWheels;
        this.VIN = rand.nextInt(400) + 100;
        while(vinNumbers.contains(VIN)) //checks if new VIN number isn't equal to one already given to other vehicle
        {
            this.VIN = rand.nextInt(400) + 100; //if it is, a new one is generated...
            vinNumbers.add(VIN); //and added to Set of VINs
        }
    }

    /**
     * An accessor method that returns the vehicle idenitifcation number (VIN)
     * @return the VIN 
     */
    public int getVIN() {
        return VIN;
    }

    /**
     * Sets the engine type of the vehicle based on the String input
     * @param input the type of engine the car is set to have
     */
    public void setEngineType(String input) 
    {
        if(input.equalsIgnoreCase("GAS_ENGINE")) 
        {
            this.power = GAS_ENGINE;
        }
        else if(input.equalsIgnoreCase("DIESEL_ENGINE"))
        {
            this.power = DIESEL_ENGINE;
        }
        else 
        {
            this.power = ELECTRIC_ENGINE;
        }
    }

    /**
     * Based on the value of the power variable and which of the FINAL constant values it holds,
     * this method returns the engine type of the vehicle.
     * @return the engine type as a String
     */
    public String getEngineType() 
    {
        int i = getPower();
        
        if(i == 0) 
        {
            return "GAS_ENGINE";
        }
        else if(i == 1)
        {
            return "DIESEL_ENGINE";
        }
        else
        {
            return "ELECTRIC_ENGINE";
        } 
    }

    /**
     * sets the power to given parameter
     * @param power engine type of the vehicle
     */
    public void setPower(int power) {
        this.power = power;
    }

    /**
     * returns the power variable
     * @return the power 
     */
    public int getPower() {
        return power;
    }


    /**
     * @return the numWheels variable
     */
    public int getNumWheels() {
        return numWheels;
    }

    /**
     * sets the numWheels variable to given parameter
     * @param numWheels the number of wheels of the vehicle
     */
    public void setNumWheels(int numWheels) {
        this.numWheels = numWheels;
    }

    /**
     * returns the name of the manufacturer
     * @return the mfr
     */
    public String getMfr() {
        return mfr;
    }

    /**
     * sets the name of the manufacturer
     * @param mfr the mfr to set
     */
    public void setMfr(String mfr) {
        this.mfr = mfr;
    }

    /**
     * returns the color of the vehicle
     * @return the color 
     */
    public String getColor() {
        return color;
    }

    /**
     * sets the color to the given parameter
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Method compares this vehicle object to the provided vehicle for equality. 
     * Method checks if given object is not null:
     * if it is, returns false
     * @return true if the two vehicles are equal,
     *         false if the two vehicles are different,
     *         false if object parameter is null.
     */
    public boolean equals(Object other) {
        if (other != null) 
        {
            Vehicle otherVehicle = (Vehicle) other;
            if (mfr.equals(otherVehicle.mfr) && color.equals(otherVehicle.color) && numWheels == otherVehicle.numWheels && VIN == otherVehicle.VIN)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else 
        {
            return false;
        }

    }

    /**
     * returns string with VIN, name of manufacturer and the color of the vehicle
     * @return VIN, mfr and color
     */
    public String display() {
        return "VIN: " + VIN + " " + mfr + " " + color;
    }

}