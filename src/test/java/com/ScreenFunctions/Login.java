package com.ScreenFunctions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.Runnerclass.CucumberRunner;

public class Login extends CucumberRunner {

	@FindBy(how=How.XPATH,using="//input[@id='txtUsername']")
	public static WebElement Edi_UserName;
	
	@FindBy(how=How.ID,using="txtPassword")
	public static WebElement Edi_Password;
	
	@FindBy(how=How.NAME,using="Submit")
	public static WebElement btn_Submit;
	
	@FindBy(how=How.ID,using="welcome")
	public static WebElement lnk_Welcome;
	
	@FindBy(how=How.XPATH,using="//a[text()='Logout']")
	public static WebElement lnk_Logout;
	
	public static boolean login(String usrName, String passWord)
	{
		boolean status = true;
		try
		{
			Edi_UserName.sendKeys(usrName);
			
		}
		catch(Exception e)
		{
			System.out.println("Username field is not found");
			status = false;
			
		}
		if(status)
		{
		   try
		    {
				
				Edi_Password.sendKeys(passWord);
			}
			catch(Exception e)
			
			{
				System.out.println("password field is not found");
				status = false;
			}
			
		   if(status)
		   {
			   try {
				btn_Submit.click();
				waitForElement(lnk_Welcome);
				lnk_Welcome.click();
				waitForElement(lnk_Logout);
				lnk_Logout.click();
				waitForElement(Edi_UserName);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Button is not found");
				status=false;
			}
		   }
		}
		return status;
		
		
	}
	
}
