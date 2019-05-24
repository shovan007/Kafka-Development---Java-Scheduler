package simplescheduler;

import java.util.Date;
import java.util.TimerTask;

public class ScheduledTask extends TimerTask
{
	Date now; // to display current time
	multipleQuery MQuery; // Object created for class Multiple Query

	// Add your task here
	public void run() 
	{
		MQuery=new multipleQuery(); // Created a object of Multiple Query class so 
								//that it can call method present inside Multiple Query
		MQuery.Query(); // Method Query has been called using MQuery object
		now = new Date(); // initialize date
		System.out.println("Time is :" + now); // Display current time
	}
}