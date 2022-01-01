import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.Random;
/**
 * CarDealership class creates an object that simulates the behaviour of a car dealership.
 **/
public class CarDealership {
    //instance variables
    private ArrayList<Car> cars; //a collection of cars
    //filters that decide what cars are displayed
    private boolean electricFilter; 
    private boolean AWDfilter;
    private boolean priceFilter;
    //min and max price, helps with the price filter
    private double maxPrice;
    private double minPrice;
    SalesTeam salesTeam; //SalesTeam object
    AccountingSystem accountingSystem; //AccountingSystem object
    Random rand; //Random object
 
    /**
     * Constructor method for the CarDealership class/object,
     * initializes the arraylist of cars, the SalesTeam, AccountingSystem and Random objects.
     */
    public CarDealership() {
        cars = new ArrayList<Car>();
        salesTeam = new SalesTeam();
        accountingSystem = new AccountingSystem();
        rand = new Random();
    }

    /**
     * Takes cars from the arraylist of cars provided as an input value,
     * and adds it to the CarDealership object's arraylist of cars
     * @param newCars the input array list of cars 
     */
	public void addCars(ArrayList<Car> newCars) {
        for (int i = 0; i < newCars.size(); i++)
        {
            Car element = newCars.get(i);
            cars.add(element);
        }
    }
    
    /**
     * "Buys" a car with a certain VIN: method goes through arraylist of cars to find one with matching VIN. 
     * If one is found, it is removed. A GregorianCalendar date is created
     * using Random number generators, a salesperson is retrieved and a record is made 
     * of the transaction using the AccountingSystem object.
     * If one is not found, an exception is thrown. An exception is also thrown when there are no cars in the 
     * arraylist and this command is called.
     * @param VIN the VIN of the car to bought
     * @return record of the transaction
     */
    public String buyCar(int VIN) 
    {
        if(cars.size() == 0) //if there are no cars in the arraylist, an exception is thrown
        {
            throw new IndexOutOfBoundsException("The arraylist of cars is empty.");
        }

        boolean found = false; //to check if Car with the certain VIN is found
        Car boughtCar = null;
        for(int i = 0; i < cars.size(); i++)
        {
            Car car = cars.get(i);
            if(car.getVIN() == VIN)
            {
                boughtCar = car;
                found = true;
                cars.remove(i);
            }
        }
        
        if(!found) //car has not been found (VIN input could also have been illogical)
        {
            throw new IllegalArgumentException("No car with given VIN found. Enter valid VIN."); //exception is thrown
        }
        
        String sp = salesTeam.getAnySalesPerson(); //fetching salesperson
        int day = rand.nextInt(31) + 1, month = rand.nextInt(11); //day and month is randomly generated
        Calendar date = new GregorianCalendar(2019, month, day); //date created
        return accountingSystem.add(date, boughtCar, sp, "BUY", boughtCar.getPrice()); //record of transaction is made

    }
    

    /**
     * Takes the ID of a transaction where a car was bought
     * and puts the car associated with that transaction back into the arraylist of cars. 
     * Another Transaction object of the RET type is created.
     * @param transaction the ID of the transaction in which the car was bought
     */
    public void returnCar(int transaction)
    {
        Transaction purchaseTransaction = accountingSystem.getTransaction(transaction);
        if(purchaseTransaction != null)//
        {
            Car car = purchaseTransaction.getCar();
            int month = purchaseTransaction.getDate().get(Calendar.MONTH);
            int day = purchaseTransaction.getDate().get(Calendar.DAY_OF_MONTH);
            int newDay = 0, increment = 28 - day;
            if(increment >= 0)
            {
                newDay = day + increment;
            }
            else 
            {
                newDay = day;
            }
            Calendar date = new GregorianCalendar(2019, month, newDay);
            System.out.println(accountingSystem.add(date, car, purchaseTransaction.getSalesPerson(), "RET", purchaseTransaction.getSalesPrice()));
            cars.add(car);
        }
        else 
        {
            throw new NullPointerException("Invalid transaction ID. Try again.");
        }
    }

    /**
     * Sets the electric filter.
     */
    public void filterByElectric() {
        this.electricFilter = true;
    }

    /**
     * Sets the AWD filter.
     */
    public void filterByAWD() {
        this.AWDfilter = true;
    }

    /**
     * Sets the price filter, as well as the maximum and minimum price: later on, when this filter is set, only
     * cars with prices in the max and min range can be displayed.
     * @param minPrice the minimum price
     * @param maxPrice the maximum price
     */
    public void filterByPrice(double minPrice, double maxPrice) {
        this.priceFilter = true;
        this.maxPrice = maxPrice;
        this.minPrice = minPrice;
    }

    /**
     * Clears all set filters by setting all filter variables to false.
     */
    public void filterClear(){
        this.priceFilter = false;
        this.AWDfilter = false;
        this.electricFilter = false;
    }

    /**
     * displayInventory() displays all cars in the arraylist according to the filters, as in which of them are set or are not. 
     * Method goes through each car in arraylist using a for loop. Uses if statement to work with the filters.
     * If no filters are set, all cars are displayed. If one, two or more filters are set, 
     * only cars with the qualities specified by the filters are displayed.
     */
    public void displayInventory() 
    {
        int count  = 0; //keeps count of all cars displayed
        for (int i = 0; i < cars.size(); i++) 
        {
            Car car = cars.get(i);
            if (priceFilter && AWDfilter && electricFilter)
            {
                double carPrice = car.getPrice();
                int carPower = car.getPower();
                if (carPrice >= minPrice && carPrice <= maxPrice && car.getAWD() && carPower == Vehicle.ELECTRIC_ENGINE) 
                {
                    System.out.println(cars.indexOf(car) + " " + car.display());
                    count++;
                }
            }
            else if (AWDfilter && !priceFilter && !electricFilter) 
            {
                if (car.getAWD()) 
                {
                    System.out.println(cars.indexOf(car) + " " + car.display());
                    count++;
                }
            }
            else if(electricFilter && !AWDfilter && !priceFilter)
            {
                int carPower = car.getPower();
                if (carPower == Vehicle.ELECTRIC_ENGINE)
                {

                    System.out.println(cars.indexOf(car) + " " + car.display());
                    count++;
                }
            }
            else if(AWDfilter && priceFilter && !electricFilter) 
            {
                double carPrice = car.getPrice();
                if (carPrice >= minPrice && carPrice <= maxPrice && car.getAWD()) {
                    System.out.println(cars.indexOf(car) + " " + car.display());
                    count++;
                }
            }
            else if(AWDfilter && electricFilter && !priceFilter)
            {
                int carPower = car.getPower();
                if (car.getAWD() && carPower == Vehicle.ELECTRIC_ENGINE) {
                    System.out.println(cars.indexOf(car) + " " + car.display());
                    count++;
                }
            }  
            else if(electricFilter && priceFilter && !AWDfilter) 
            {
                double carPrice = car.getPrice();
                int carPower = car.getPower();
                if (carPrice >= minPrice && carPrice <= maxPrice && carPower == Vehicle.ELECTRIC_ENGINE) 
                {
                    System.out.println(cars.indexOf(car) + " " + car.display());
                    count++;
                }
            }
            else if(priceFilter && !electricFilter && !AWDfilter) 
            {
                double carPrice = car.getPrice();
                if (carPrice >= minPrice && carPrice <= maxPrice) 
                {
                    System.out.println(cars.indexOf(car) + " " + car.display());
                    count++;
                }
            }
            else 
            {
                System.out.println(cars.indexOf(car)+ " " + car.display());
                count++;
                
            }
        }
        if(count == 0) //if count is zero, no car is displayed and thus a message to this effect is displayed
        {
            System.out.println("No cars found.");
        }
    
    }

    //following methods use the Collections.sort method
    /**
     * Sorts the cars in the CarDealership arraylist according to price.
     */
    public void sortByPrice() {
        Collections.sort(cars);
    }
    
    /**
     * Sorts the cars in the CarDealership arraylist according to safety rating.
     */
    public void sortBySafetyRating() {
        Collections.sort(cars, new CarSafetyRatingComparator());
    }

    /**
     * Sorts the cars in the CarDealership arraylist according to maximum range of car.
     */
    public void sortByMaxRange() {
        Collections.sort(cars, new CarMaxRangeComparator());
    }
}
