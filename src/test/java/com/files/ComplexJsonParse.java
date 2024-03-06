package com.files;

import io.restassured.path.json.JsonPath;

public class ComplexJsonParse
{
	public static void main(String[] args) 
	{
		
		JsonPath js=new JsonPath(Payload.CoursePice());
		
		//print the number of courses
		int count =js.getInt("courses.size()");
		System.out.println("The total count of the courses is : "+count);
		
		//print the total purchaseAmount
		int totalPriceAmount=js.getInt("dashboard.purchaseAmount");
		System.out.println("The total Price Amount is :"+totalPriceAmount);
		
		//print all the course titles and respective price
		for(int i=0;i<count;i++)
		{
			String coursetitle=js.get("courses["+i+"].title");
			int coursePrice=js.get("courses["+i+"].price");
			
			//System.out.println("The course title is "+coursetitle+" its price is "+coursePrice);
		}
		System.out.println("Print Numer of Copies sold by RPA");
		//print the number of copies cold by RPA
		for(int i=0;i<count;i++)
		{
			String coursetitle=js.get("courses["+i+"].title");
			if(coursetitle.equalsIgnoreCase("RPA"))
			{
				int copiescount=js.get("courses["+i+"].copies");
				System.out.println(copiescount);
				break;
			}
		}
		
		
		
	}
	
	
}
