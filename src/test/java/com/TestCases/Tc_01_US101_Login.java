package com.TestCases;

import java.util.List;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import com.Runnerclass.CucumberRunner;
import com.ScreenFunctions.Login;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tc_01_US101_Login extends CucumberRunner {
	
	@Given("Launch the firefox browser")
	
	public static void launchBrowser()
	{
		driver = new FirefoxDriver();
	}
	
	
	
	@And("Enter URL")
	
	public static void enterURL()
	{
		//driver.get("http://www.testingmasters.com/hrm/symfony/web/index/php/auth/login");
		driver.get(getEnv("Commondetails"));
	}
	
	@Then("maximize the browser")
	public static void maximizeBrowser()
	{
		driver.manage().window().maximize();
	}
	
	@When("username is available enter the admin credentials")
	
	public static void enterAdminCredentials() //DataTable credentials
	{
	
		//String username= getData("Testcases","Tc_01_US101_Login","Username",1);
		//String password= getData("Testcases","Tc_01_US101_Login","Password",1);
		//System.out.println(username);
		//System.out.println(password);
		Login lgn = PageFactory.initElements(driver, Login.class);
		
		int itrcount=getIterationCount("Testcases","Tc_01_US101_Login");
		System.out.println(itrcount);
		
		for(int i=1;i<=itrcount-1;i++)
		{
			String username= getData("Testcases","Tc_01_US101_Login","Username",i);
			String password= getData("Testcases","Tc_01_US101_Login","Password",i);
			System.out.println(username);
			System.out.println(password);
			lgn.login(username, password);
			
		}
		
		
		
		//List<List<String>> cred= credentials.raw();
		
		//System.out.println(cred.size());
		
	/*	for(int i=0;i<=cred.size()-1;i++)
		{
			
		System.out.println(cred.get(i).get(0));
		System.out.println(cred.get(i).get(1));
		Login lgn = PageFactory.initElements(driver, Login.class);
		lgn.login(cred.get(i).get(0),cred.get(i).get(1));
		}
		*/
		
	}
	
	/*@And("verify the user is navigated to hompage")
	public static void verifyHomepage()
	{
		
	}
	*/
	
	
	

	
	
}
