package com.files;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class SumOfValidation 
{
	@Test
	public void sumOfValidation()
	{
		JsonPath js=new JsonPath(Payload.CoursePice());
		int sum=0;
		int count=js.get("courses.size()");
		for(int i=0;i<count;i++)
		{
			int price=js.get("courses["+i+"].price");
			int copies=js.get("courses["+i+"].copies");
			int amount=price*copies;
			sum=sum+amount;
			
		}
		System.out.println("the Sum is  "+sum);	
		int purchaseAmount=js.getInt("dashboard.purchaseAmount");
		System.out.println("Purchase AMount is :"+purchaseAmount);
		Assert.assertEquals(sum,purchaseAmount);
	}
}
