package projectSec1B;

import java.io.*;
/* program will simulate a simple robot that will place items
 * into the correct station. There are 10 stations. the robot will
 * place according to delivery type, weight and serial number
 * until user doesn't have any more items to be put away
 * will assume the type of delivery is most important, then temp
 * item needs to be stored in, and lastly weight
 */
public class Robot
{
	private String loopItems;
	private String station[][] = new String[10][8];//10 stations and can hold up to 8 items
	private String otherStation[][] = new String [2][4];//station 12 and 14
	private int sFive=0,sSeven = 0, sNine=0, sTen=0,sFourteen=0, sTwelve=0,
			itemsStored=0,stationLoc=0, itemsEven =0, stationEven=1;
	private int position = 0;
	public Item item = new Item();//creates new item object
	InputStreamReader isr = new InputStreamReader(System.in);
	BufferedReader br = new BufferedReader(isr);
	
	public Robot()
	{
		position = 0; //0 at pickup
	}//end default constructor
	public Robot(int position)
	{
		this.position = position;
	}//end constructor position of robot start at diff position
	private void setPosition(int position)
	{
		this.position = position;
	}//end set position method
	public int getPosition()
	{
		return position;
	}//end get position method
	public void options() throws IOException
	{
		loopItems = "yes";//start with yes to enter loop
		while(loopItems.equalsIgnoreCase("yes"))//while user want to enter items
		{
			setPosition(0);//always going back to pick up
			System.out.println("Robot is at the pick up station");
			System.out.println("Would you like to enter new item for delivery? "
					+ "(yes/no)");
			loopItems = br.readLine();
			if(loopItems.equalsIgnoreCase("no"))//if no want to exit program
			{
				System.out.println("Exit Robotic Delivery Program");
				br.close();
				System.exit(0);//exit system
			}//end if no
			else if (loopItems.equalsIgnoreCase("yes"))//else if yes
			{
				item.readInputs();//calls method that starts reading
				if(item.getsNumString().equalsIgnoreCase("unload")
						||item.getwtString().equalsIgnoreCase("unload")
						||item.getDeliveryType().equalsIgnoreCase("unload"))
				{//if has unload in one of input from items class
					unloadFive();//unload station 5 method
					continue;//back to loop of while yes enter new item
				}//end if unload
				System.out.print("Robot moves backwards and");
				if(item.getSerialNumber() %2 ==0)
				{//if serial number even
					System.out.print(" turns to the left to ");
					station14();//5 day storage method
					station10();//special method
					if(getPosition()==10||getPosition()==14){}//if 10 or 14 do nothing
					else
					{//other than 10 or 14 want to store lowest num to highest
						lowToHighEven();
					}//end else to 10 14 position
				}//end if even
				else
				{//else odd
					System.out.print(" turns to the right to ");
					station12();//immediate
					station9();//special
					if(getPosition()==12||getPosition()==9){}//if 12 or 9 pos do nothing
					else
					{//else if not 12 or 9
						station5();//fridge
						if(getPosition()==5){}//if pos 5 do nothing
						else
						{//else if not pos 12,9,5
							station7();//light weight
							if(getPosition()==7){}//if pos 7 do nothing
							else
							{//else if not pos 12,9,5,7
								lowToHighOdd();
							}//end else if not pos 12,9,5,7
						}//end else not pos 12,9,5
					}//end else not or 9
				}//end else odd
				System.out.println("ITEMS STORED IN STATION " +getPosition()+":\n");
				outPutItemsStored();//print out what items in the station so far
				System.out.print("\nRobot moves forward and ");
			}//end else if yes
			else
			{//else not yes or no enter more items
				throw new IllegalArgumentException("Must enter yes or no");
			}//end else not yes or no enter more items
		}//end while loop that loops entry of items
	}//end method to see if goes through loop
	private void station5()
	{
		String itemString = item.getsNumString();
		if(itemString.indexOf("5")==0)
		{
			setPosition(5);
			if(sFive<8)
			{//if station can still hold items
				System.out.println("store item into Station "+getPosition());
				System.out.println("where it is kept at 22 degree F");
				station[4][sFive]=item.toString();//array[4] b/c index start at 0
				sFive++;
			}//end if station can still hold items
			else 
			{
				System.out.println("store but Station "+getPosition()+" is full");
			}//end else if statement for station full
		}//end if statement id starts with 5
	}//end station 5 method
	private void station7()
	{
		if (item.getWeight()<50)
		{
			if(sSeven<8)
			{//if station can still hold items
				setPosition(7);
				System.out.println("store item into Station "+getPosition());
				System.out.println("Since the weight is less than 50");
				station[6][sSeven]=item.toString();
				sSeven++;
			}//end if station can still hold items
			else 
			{
				System.out.println("store but Station "+getPosition()+" is full");
			}//end else if statement for station full
		}//end if statement weight<50
	}//end station 7 method
	private void station10()
	{
		String itemDType =item.getDeliveryType();
		if(itemDType.equalsIgnoreCase("special"))
		{
			if(sTen<8)
			{
				setPosition(10);
				System.out.println("store item into Station "+getPosition());
				System.out.println("Stored for Special delivery");
				station[9][sTen]=item.toString();
				sTen++;
			}//end if can still hold items
			else 
			{
				System.out.println("store but Station "+getPosition()+" is full");
			}//end else if statement for station full
		}//end if statement immediate
	}//end station 10 method
	private void station9()
	{
		String itemDType =item.getDeliveryType();
		if(itemDType.equalsIgnoreCase("special"))
		{
			if(sNine<8)
			{
				setPosition(9);
				System.out.println("store item into Station "+getPosition());
				System.out.println("Stored for Special delivery");
				station[8][sNine]=item.toString();
				sNine++;
			}//end if station can still items
			else 
			{
				System.out.println("store but Station "+getPosition()+" is full");
			}//end else if statement for station full
		}//end if statement immediate
	}//end station 9 method
	private void station12()
	{
		String itemDType =item.getDeliveryType();
		if(itemDType.equalsIgnoreCase("immediate"))
		{
			if(sTwelve<4)
			{
				setPosition(12);
				System.out.println("store item into Station "+getPosition());
				System.out.println("Stored for immediate delivery");
				otherStation[0][sTwelve]=item.toString();
				sTwelve++;
			}//end if station can still hold items
			else 
			{
				System.out.println("store but Station "+getPosition()+" is full");
			}//end else if statement for station full
		}//end if immediate
	}//end station 12 method
	private void station14()
	{
		String itemDType =item.getDeliveryType();
		if(itemDType.equalsIgnoreCase("5 day storage"))
		{
			if(sFourteen<4)
			{
				setPosition(14);
				System.out.println("store item into Station "+getPosition());
				System.out.println("Stored for 5 day Storage");
				otherStation[1][sFourteen]=item.toString();
				sFourteen++;
			}//end if station can still hold items
			else 
			{
				System.out.println("store but Station "+getPosition()+" is full");
			}//end else if statement for station full
		}//end if 5 day storage
	}//end station 14 method
	private void lowToHighOdd()
	{
		if(itemsStored<8)
		{
			if(stationLoc == 4 || stationLoc == 6 || 
					stationLoc == 8 ||stationLoc == 9){}//if special cases
			else//else if not put in station 5, 7, 9, 10  store in closest station first
			{
				setPosition(stationLoc+1);
				System.out.println("store item into Station "+getPosition());
				station[stationLoc][itemsStored] =item.toString();
				itemsStored++;
			}//end else not 5,7,9,10
		}//end if statement check amount of items for 8-item storage
		else
		{
			itemsStored=0;//start over with amount
			stationLoc = stationLoc+2;//go to next station closest station
			setPosition(stationLoc+1);
			System.out.println("store item into Station "+getPosition());
			station[stationLoc][itemsStored] =item.toString();
			itemsStored++;
		}//end else items go over 8 go to next station
	}//end odd low to high station(closest to further storing)
	private void lowToHighEven()
	{
		if(itemsEven<8)
		{
			if(stationEven == 4 || stationEven == 6 || 
					stationEven == 8 ||stationEven == 9){}//if special cases
			else//else if not station 5, 7, 9, 10  store in closest station first
			{
				setPosition(stationEven+1);
				System.out.println("store item into Station "+getPosition());
				station[stationEven][itemsEven] =item.toString();
				itemsEven++;
			}//end else if not 5,7,9,10
		}//end check amount of items can be stored
		else
		{
			itemsEven=0;//start over with amount
			stationEven= stationEven+2;//go to next station closest station
			setPosition(stationEven+1);
			System.out.println("store item into Station "+getPosition());
			station[stationEven][itemsEven] =item.toString();
			itemsEven++;
		}//end else items>8
	}//end method even low to high station(closest to further)
	private void outPutItemsStored()
	{
		if(getPosition()==14)
		{
			for(int j =0; j<4;j++)
			{
				if(otherStation[1][j]!=null)//gets rid of null be printed
					System.out.println(otherStation[1][j]);
			}//end for loop
		}//end if pos 14
		else if(getPosition() == 12)
		{
			for(int j =0; j<4;j++)
			{
				if(otherStation[0][j]!=null)//rid of null
					System.out.println(otherStation[0][j]);
			}//end for loop
		}//end if pos 12
		else
		{
			for(int j =0; j<4;j++)
			{
				if(station[getPosition()-1][j]!=null)//rid null
					System.out.println(station[getPosition()-1][j]);
			}//end for loop
		}//end else everything other than 12 and 14
	}//end print out items stored method
	public void unloadFive()
	{
		setPosition(5);
		System.out.println("Robot moves backwards and"+
				" turns to the right to Station "+getPosition());
		for(int j =0; j<8;j++)
		{
			station[4][j]=null;//empty to null
		}//end for
		System.out.println("Robot unloaded Station "+getPosition());
		sFive=0;//set amount of itmes for station 5 back to 0
		System.out.println("ITEMS STORED IN STATION " +getPosition()+":\n");
		outPutItemsStored();
	}//end unload station 5 method
}//end class robot
