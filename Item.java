package projectSec1B;

import java.io.*;

public class Item
{
	private String delType, wtString;
	private String  sNumString;
	private double weight;
	private int serialNum;
	
	InputStreamReader isr = new InputStreamReader(System.in);
	BufferedReader br = new BufferedReader(isr);
	public Item(int serialNum, double weight, String delType)
	{
		this.serialNum = serialNum;
		this.weight = weight;
		this.delType = delType;
	}//end constructor pass arguments
	public Item()
	{
		
	}
	private void setSerialNumber(int serialNum)
	{
		this.serialNum = serialNum;
	}//end set method serial number
	private void setWeight(double weight)
	{
		this.weight = weight;
	}//end set method weight
	private void setDelType(String delType)
	{
		this.delType = delType;
	}//end set delivery type
	public int getSerialNumber()
	{
		return serialNum;
	}//end getSerialNumber
	public double getWeight()
	{
		return weight;
	}//end getWeight
	public String getDeliveryType()
	{
		return delType;
	}//end getDeliveryType
	public String getsNumString()
	{
		return sNumString;
	}//end sNumString
	public String getwtString()
	{
		return wtString;
	}//end getwtString
	public void readInputs() throws IOException//method read user enter
	{
		System.out.println("Enter Serial Number(5 digit number) :"); 
		sNumString = br.readLine();//read in as string
		if(sNumString.length()==5)
		{
			serialNum = Integer.valueOf(sNumString).intValue();//convert to int
			setSerialNumber(serialNum);//set serial number
		}//end if statement check length of serial number
		else if (sNumString.equalsIgnoreCase("unload")){}//ok for string to equal unload
		else 
		{
			throw new IllegalArgumentException("serial number must be 5 digits long");
		}//end else check length of serial number
		System.out.println("Enter weight in kg:");
		wtString = br.readLine();//read in as string
		if (weight< 0)
		{
			throw new IllegalArgumentException("weight cannot be negative");
		}//end if statement check if weight neg
		else if (wtString.equalsIgnoreCase("unload")){}//ok for string to equal unload
		else
		{
			weight = Double.valueOf(wtString).doubleValue();//convert to double
			setWeight(weight);//set weight
		}//end else statement check weight neg
		System.out.println("Delivery type(special, immediate, regular"
				+ " or 5 day storage): ");
		delType = br.readLine();
		if(delType.equalsIgnoreCase("special")||
				delType.equalsIgnoreCase("immediate")||
				delType.equalsIgnoreCase("5 day storage")||
				delType.equalsIgnoreCase("regular"))
		{
			setDelType(delType);//set delivery type
		}//end if statement if string doesn't match 3 options
		else if(delType.equalsIgnoreCase("unload")){}//ok for string to equal unload
		else
		{
			throw new IllegalArgumentException("must enter special, immediate, "
					+ "regular or 5 day storage");
		}//end if statement	
	}//end method of reading user entries
	@Override
	public String toString()
	{
		return "Serial number: "+getSerialNumber()+", Weight: " + 
				getWeight() + ", Delivery type: "+getDeliveryType();
	}//end toString method
}//end item class
