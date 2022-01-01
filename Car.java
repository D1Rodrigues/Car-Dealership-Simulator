import java.util.Comparator;
/**
 * Car class extends the Vehicle class, implements the Comparable interface,
 * and describes an object that behaves like a car.
 */
public class Car extends Vehicle implements Comparable <Car>
{
    //instance variables
    private int model; //model of the car
    private int maxRange; //maximum range of the car
    private double safetyRating; //safety rating of the car
    private boolean AWD; //All Wheel Drive - does the car have it?
    private double price; //price of the car
    //next four lines are the different Car models designated by final int values
    public static final int SEDAN = 0;
    public static final int SUV = 1;
    public static final int SPORTS = 2;
    public static final int MINIVAN = 3;

    /** 
     * Constructor method for the car class
     * constructor method makes a super call to Vehicle constructor
     * paramaters include values needed for the super call
     * @param safetyRating safety rating of the class
     * @param model car model
     * @param maxRange maximum range of the car
     * @param AWD boolean variable indicating if car has AWD
     * @param price price of car
    */
    public Car(String mfr, String color, String model, String i, double safetyRating, int maxRange, boolean AWD, double price) {
        super(mfr, color, i, 4); //super call for the Vehicle constructor method
        setModelType(model); //this and following methods initializes variables with given parameters
        this.maxRange = maxRange;
        this.safetyRating = safetyRating;
        this.AWD = AWD;
        this.price = price;
    }

    /**
     * Sets the model variable according to the input
     * @param input the model type to set the model variable to
     */

    public void setModelType(String input) 
    {
        if(input.equalsIgnoreCase("SEDAN")) 
        {
            this.model = SEDAN;
        }
        else if(input.equalsIgnoreCase("SUV"))
        {
            this.model = SUV;
        }
        else if (input.equalsIgnoreCase("SPORTS"))
        {
            this.model = SPORTS;
        }
        else 
        {
            this.model = MINIVAN;
        }
    }

    /**
     * Returns the car's model based on the int value stored in model variable
     * @return String containing model name
     */
    public String getModelType()
    {
        int i = getModel();
        if(i == SEDAN) 
        {
            return "SEDAN";
        }
        else if(i == SUV)
        {
            return "SUV";
        }
        else if (i == SPORTS)
        {
            return "SPORTS";
        }
        else 
        {
            return "MINIVAN";
        }
    }

    /**
     * returns the model variable
     * @return the model 
     */
    public int getModel() {
        return model;
    }

    /**
     * display() method displays information about the car, such as price, range and safety rating, etc.,
     * overrides the method from Vehicle class
     * @return model, price, safetyRating, maxRange and information from superclass display() method
     */
    public String display() {
        return super.display() + " " + getModelType() + " " + price + "$ SF: " + safetyRating + " RNG:" + maxRange;
    }

    /**
     * Checks if the vehicle/car contained in parameter is equal to this car/vehicle object
     * @param other another car/vehicle 
     */
    public boolean equals(Object other) {
        Car otherCar = (Car) other;
        if (super.equals(otherCar)) {
            if (this.model == otherCar.model && AWD == (otherCar.AWD)) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }
    
    /**
     * Uses the Comparable interface to compare the prices of two cars:
     * compares vehicle given in parameter and this car based on their price,
     * sorts according which is more expensive, in ascending order.
     * @param other the other car object
     * @return -1 if other car object's price is greater,
     *          1 if this car object's price is greater,
     *          0 if both cars' price is equal
     */
    public int compareTo(Car other) {
        if (price < other.price) {return -1;} 
        if (price > other.price) {return 1;}
        return 0;
    }

    /**
     * returns the price of car
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @return the AWD varibale
     */
    public boolean getAWD() {
        return AWD;
    }

    /**
     * @return the safetyRating variable
     */
    public double getSafetyRating() {
        return safetyRating;
    }

    /**
     * @return the maxRange variable
     */
    public int getMaxRange() {
        return maxRange;
    }
}

/**
 * Class CarSafetyRatingComparator implements the Comparator interface,
 * compares two Car objects based on their SafetyRating
 * @return 1 if safetyRating of car A  is greater,
 *         -1 if it's the opposite case,
 *          0 if they're equal.
 */
class CarSafetyRatingComparator implements Comparator<Car>
{
	public int compare(Car a, Car b)
	{
		if (a.getSafetyRating() < b.getSafetyRating()) {return 1;}
		if (a.getSafetyRating() > b.getSafetyRating()) {return  -1;}
		else {return 0;}
	}
}

/**
 * Class CarMaxRangingComparator implements the Comparator interface,
 * compares two Car objects based on their MaxRange
 * @return 1 if car B's maxRange is greater,
 *         -1 if car A's maxRange is greater,
 *         0 if they are equal.
 */
class CarMaxRangeComparator implements Comparator<Car>
{
	public int compare(Car a, Car b)
	{
		if (a.getMaxRange() < b.getMaxRange()) {return 1;}
		if (a.getMaxRange() > b.getMaxRange()) {return  -1;}
		else {return 0;}
	}
}
