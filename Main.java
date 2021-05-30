package graphs;

import java.util.List;


public class Main 
{
    public static void main( String[] args )
    
    {   DClass d=new DClass();
    	  List<Dataset> pass= d.getPassengersFromJsonFile();
    	
    	d.graphPassengerClass(pass);
    	d.graphsurvived(pass);
    	d.graphgender(pass);
        
    }
}
