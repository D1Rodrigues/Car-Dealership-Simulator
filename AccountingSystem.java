import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
/**
 * Dylan Rodrigues, student id: 500881797. April 4, 2019. The Accounting System
 * class receives and holds an arraylist of Transaction objects. It includes
 * various other methods that are needed when working with Transaction objects.
 */
public class AccountingSystem
{
    private Map<Transaction, Integer> transactions; //collection of Transaction objects
    private Set<Transaction> keySet; //a keyset used in iterating through the above Map
    Random rand; //Random object needed to create a random number generator
    private int tID; //the transaction ID number
    private Map<String, Integer> spSales; //HashMap stores the name of salesperson as a key and the number of cars they have sold as the value of the key

    /**
     * Constructor method for the AccountingSystem class. Initializes reference
     * variable for the collection of Transaction objects, as well as the Random object,
     * Map of salesperson and their sales, and the keySet for the Transactions map .
     */
    public AccountingSystem() {
        transactions = new HashMap<Transaction, Integer>();
        rand = new Random();
        spSales = new HashMap<String, Integer>();
        keySet = transactions.keySet();
    }

    /**
     * Takes in transaction details, as parameters, needed to create a Transaction object. 
     * A Random number generator is used to create an ID number for the transaction.
     * Using the parameters and the newly generated ID integer, 
     * it creates a Transaction object and returns its details.
     * Finally, the increments the amount of cars sold by a salesperson by calling the updateSPRanking() method.
     * @param date a Calendar object that contains date of transaction
     * @param car the car being bought or sold
     * @param salesPerson the salesperson involved
     * @param type the type of transaction
     * @param salesPrice the salesprice of the car
     * @return a String containing all transaction details using the display() method of Transaction class
     */
    public String add(Calendar date, Car car, String salesPerson, String type, double salesPrice) 
    {
        tID = rand.nextInt(99) + 1;
        Transaction transaction = new Transaction(date, car, salesPerson, type, salesPrice, tID);
        if (type.equalsIgnoreCase("BUY")) //amount of sales made by a salesperson is incremented...
        {
            updateSPRanking(salesPerson); //... only if it is a purchase
        }
        transactions.put(transaction, tID);
        updateSPRanking(salesPerson);
        return transaction.display();
        
    }

    /**
     * Accessor method returns the transaction ID
     * @return tID
     */
    public int gettID() 
    {
        return tID;
    }

    /**
     * Takes in a salesperson's name. If they already are in the spSales HashMap,
     * the amount of cars associated with them is incremented.
     * If not, the name is added to the HashMap with the value of 1, 
     * signifying it is the first sale of the person.
     * @param salesperson the person who made the sale
     */
    public void updateSPRanking(String salesperson)
    {
        int count = 0;
        if(spSales.containsKey(salesperson)) //if salesperson is on the map, the count of cars they've sold is incremented.
        {
            count = spSales.get(salesperson) + 1;
            spSales.put(salesperson, count);
        }
        else
        {
            spSales.put(salesperson, 1); //if salesperson isn't on the map, they are placed on the map with the # of cars they have sold (1) 

        }

    }

    /**
     * Goes through the Map of salespeople and the amount of cars they've sold.
     * First, the largest value is found in the HashMap. Then, all salespersons
     * with that value of cars sold are displayed along with the aforementioned number.
     */
    public void getHighestEarningSP()
    {
        if(transactions.size() != 0)
        {
            int mostCarsSold = 0;
            Set<String> keySet = spSales.keySet(); 
            for (String key : keySet)
            {
                int carsSold = spSales.get(key);
                if (carsSold > mostCarsSold)
                {
                    mostCarsSold = carsSold;
                }
            }
            for (String key : keySet)
            {
                int carsSold = spSales.get(key);
                if (carsSold == mostCarsSold)
                {
                    System.out.println("Top SP: " + key + " " + carsSold);
                }
            }
        }
        else 
        {
            System.out.println("There have been no transactions.");
        }

    }


    /**
     * Takes in a Transaction ID and searches the arraylist of Transaction objects. 
     * If any of the Transaction objects have the same ID,
     * that Transaction is returnes to the calling method. Otherwise, null is returned.
     * @param id the ID that is to be looked for in the arraylist of Transaction objects
     * @return the Transaction object with the ID provided as a parameter
     */
    public Transaction getTransaction(int id) 
    {
        boolean found = false;
        Transaction wantedTransaction = null; //Transaction object initialized
        for (Transaction key : keySet)
        {
            int ID = transactions.get(key);
            if (id == ID) 
            {
                wantedTransaction = key;
                found = true;
            }
        }
        if(!found)
        {
            return null;
        }
        else 
        {
            return wantedTransaction;
        }
    }

    /**
     * Displays all the transactions stored in the AccountingSystem object.
     */
    public void displayAllTransactions()
    {
        if(transactions.size() != 0)
        {
            for (Transaction key : keySet)
            {
                Transaction transaction = key;
                System.out.println(transaction.display());
            }
        }
        else 
        {
            System.out.println("There are no transactions.");
        }
    }

    /**
     * Displays all the transactions in a given month
     * @param m the month
     */
    public void displayTransactionsByMonth(int m)
    {
        boolean check = false;
        for(Transaction key : keySet)
        {
            Transaction t = key;
            int month = t.getDate().get(Calendar.MONTH);
            if(month == m)
            {
                System.out.println(t.display());
                check = true;
            }
        }
        if(!check)
        {
            System.out.println("There were no transactions made in that month. Try again.");
        }
    }
    
    /**
     * Finds the transactions that happened in given month in the arraylist 
     * and sums up the toral of all cars sold
     * @param m the month
     * @return the total # of cars sold
     */
    public int getMonthlyTotal(int m)
    {
        int carsSold = 0;
        for(Transaction key : keySet)
        {
            Transaction t = key;
            int month = t.getDate().get(Calendar.MONTH);
            if(month == m && (t.getTransactionType().equalsIgnoreCase("BUY")))
            {
                carsSold++;
            }
        }
        return carsSold;
    }

    /**
     * Takes an int value and returns the month associated with it
     * e.g. int month = 11 would give December
     * @param i an int value
     * @return Strin that contains the month assoicated with that given int value
     */
    public String getMonth(int i) {
        if(i >= 0 && i < 12)
        {
            return new DateFormatSymbols().getMonths()[i];
        }
        else 
        {
            return null;
        }
    }

    /**
     * Using a for-loop, goes through all the months and and compares the amount of cars sold in each,
     * With the largest amount of cars sold and the month with most cars sold stored and returned.
     * @return String containing month with the most cars sold and the number of cars sold in that month.
     */
    public String getBestMonth()
    {
        double mostCarsSold = 0; 
        String bestMonth = "";
        for (int i = 0; i < 12; i++)
        {
            double totalCarsSold = getMonthlyTotal(i);
            if (totalCarsSold > mostCarsSold)
            {
                mostCarsSold = totalCarsSold;
                bestMonth = getMonth(i);
            }
        }
        return "Best month: " + bestMonth + ": - " + mostCarsSold;

    }

    /**
     * Goes through arraylist of transactions in order to compare various sales information,
     * in order to get the total sales, total cars sold and returned, etc.
     * @return String containg total sales, total cars sold, average sales and total cars returned.
     */
    public String getSalesInfo()
    {
        double totalSales = 0;
        int carsSold = 0, carsReturned = 0;
        for (Transaction key : keySet)
        {
            Transaction transaction = key;
            if(transaction.getTransactionType().equalsIgnoreCase("BUY"))
            {
                totalSales += transaction.getSalesPrice();
                carsSold++;
            }
            else 
            {
                carsReturned++;
            }
        }
        double avgSales = totalSales / 12;
        return "Total sales: " + totalSales + " Total sold: " + carsSold + " Avg sales: "
        + avgSales + " Total returned: " + carsReturned;
    }

    /**
     * Calls relevant methods in order to get information about sales in a certain year
     * @return the sales information for a year in a String
     */
    public String getSalesStats()
    {
        if(transactions.size() != 0)
        {
            return getSalesInfo() + " " + getBestMonth();

        }
        else
        {
            return "There have been no transactions yet.";
        }
    }
}