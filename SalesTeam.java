import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;
/**
 * The SalesTeam class contaims an arraylist of salesperson names.
 * Contains methods that manipulate this arraylist.
 */
public class SalesTeam 
{
    LinkedList<String> salespersons; //the arraylist that will contain the name of salespeople
    Random rand; //Random object

    /**
     * Constructor method that initializes the LinkedList and the Random object,
     * the latter with a Random number generator.
     * Furthermore, 5-6 names are added to the arraylist.
     */
    public SalesTeam()
    {
        salespersons = new LinkedList<String>();
        salespersons.add("Rajiv");
        salespersons.add("Schweta");
        salespersons.add("Manish");
        salespersons.add("Salim");
        salespersons.add("Dhanush");
        salespersons.add("Joe Strummer");
        rand = new Random();
    }

    /**
     * Returns a salesperson from the arraylist based on a randomly generated index number.
     * @return the salesperson's name in a String
     */
    public String getAnySalesPerson()
    {
        int i = rand.nextInt(6);
        return salespersons.get(i);
    }
    
    /**
     * Returns the sales person with associated index in SalesTeam arraylist.
     * @param i the index value
     * @return the salesperson with the index value
     */
    public String getSpecificSalesPerson(int i) 
    {
        return salespersons.get(i);
    }

    /**
     * Returns all the salespeople in the LinkedList.
     * Goes through the salespeople LinkedList using an iterator and adds to an accumulator String, 
     * and returns the latter.
     * @return all the people in the sales team
     */
    public String displayAll() 
    {
        String salespeople = "Team: ";
        ListIterator<String> iter = salespersons.listIterator();
        for (int i = 0; i < salespersons.size(); i++) 
        {
            String element = iter.next();
            salespeople = salespeople + element + " ";
        }
        return salespeople;
    }

    /**
     * Returns the number of people on the sales team
     * @return size of the sales team
     */
    public int teamSize() 
    {
        return salespersons.size();
    }

}
