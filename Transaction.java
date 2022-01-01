import java.text.SimpleDateFormat;
import java.util.Calendar;
/**
 * The Transaction object stores information about the purchase 
 * of an car or its return.
 */
public class Transaction 
{
    private int id; //the transaction ID
    private Calendar date; //the date of transaction
    private Car car; //the car being bought or returned
    private String salesperson; //the salesperson involved in transaction
    private String type; //type of transaction
    private double salesPrice; //price of car being bought

    /** 
     * Constructor method intializes all the instance variables of the Transaction object.
     * @param date  initializes the date variable, date of the transaction
     * @param car   initializes the car variable, the car in the transaction
     * @param salesPerson initializes the salesperson variable, the salesperson involved
     * @param type initializes the type variable, type of transaction, e.g. BUY or RET 
     * @param salePrice initializes the salesPrice variable, the sale price
     * @param ID intializes the id variable, the transaction variable
    */
    public Transaction(Calendar date, Car car, String salesPerson, String type, double salePrice, int ID) {
        this.date = date;
        this.setCar(car);
        this.salesperson = salesPerson;
        this.type = type;
        this.setSalesPrice(salePrice);
        this.id = ID;
    }

    /**
     * Accessor methord returns the sale price of car.
     * @return the salesPrice
     */
    public double getSalesPrice() {
        return salesPrice;
    }
    /**
     * Accessor methord returns the type of the transaction, i.e. BUY or RET.
     * @return the type of transaction
     */
    public String getTransactionType() {
        return type;
    }

    /**
     * Mutator method changes the sale price of car.
     * @param salesPrice the salesPrice to set
     */
    public void setSalesPrice(double salesPrice) {
        this.salesPrice = salesPrice;
    }

    /**
     * returns the salesperson associated with transaction
     * @return the salespersom
     */
    public String getSalesPerson() {
        return salesperson;
    }

    /**
     * Acessor method returns the Car object
     * @return the car
     */
    public Car getCar() {
        return car;
    }

    /**
     * Mutator method changes the Car object
     * @param car the car to set
     */
    public void setCar(Car car) {
        this.car = car;
    }

    /**
     * Acessor method returns the transaction ID
     * @return the ID
     */
    public int getID() {
        return id;
    }

    /**
     * Acessor method returns the date
     * @return the date
     */
    public Calendar getDate() {
        return date;
    }

    /**
     * display() method returns all of the transaction details in a String.
     * @return id, date, type, salesperson, and car details
     */
    public String display() 
    {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
        return  ("ID: " + id + " " + sdf.format(date.getTime()) + " " + type + " Salesperson: " + salesperson + " Car: " + car.display());
    }
    
}
