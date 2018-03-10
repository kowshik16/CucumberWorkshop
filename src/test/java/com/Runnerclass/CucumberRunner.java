package com.Runnerclass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
		strict = true,
		
		monochrome = true,
		features = "Features",
		glue = "com.TestCases",
		plugin = {"pretty", "html:target/cucumber-html-report"}
		)

public class CucumberRunner extends AbstractTestNGCucumberTests {
	
	
	public static WebDriver driver;
	public static Fillo fillo;
	public static Connection connection;
	public static String crntclass;
	public static Recordset recordset;
	public static String URL="";
	
	
	@BeforeSuite
	public static void initiazeReport()
	{
		connectToExcel();
	}
	
	
//##################################
	
	/*
	 * Method Name: waitforElement
	 * Purpose: This method is developed to maintain a constant wait across all the pages and elements
	 * Input Parameters: user must send a webelement as an argument
	 * Output Parameters: NA
	 * Designed by: Kowshik
	 * Created by:
	 * Reviewed by:
	 * Comments:
	 * Modified Date:
	 * 
	 */
//##################################	
public static void waitForElement(WebElement element)
	{
		for(int i=1;i<=15;i++)
		{
			// try to perform mouse hovering actions on Webelement
			try
			{
				System.out.println("wait is executing");
				Actions acc=new Actions(driver);
				acc.moveToElement(element).build().perform();
				System.out.println("wait is completed and element is found");
				// If an element is found then break the execution loop
				break;
			}
			catch(Exception e)
			{
				// If an element is not found then wait for 1 sec
				try
				{
					Thread.sleep(1000);
					
				}
				catch(Exception e1)
				{
					System.out.println("element is not found");
				}
				
			}
		}
		
	}

//##################################
	
		/*
		 * Method Name: connectToExcel
		 * Purpose: For fast retrieval of data, we are converting excel as database
		 * Input Parameters: NA
		 * Output Parameters: NA
		 * Designed by: Kowshik
		 * Created by:
		 * Reviewed by:
		 * Comments:
		 * Modified Date:
		 * Plugin Dependency: Dependency is added in POM.xml
		 */
//##################################	

	public static void connectToExcel()
	{
		fillo=new Fillo();
		try {
			String crntdir= System.getProperty("user.dir");
			connection=fillo.getConnection(crntdir+"\\Testdata\\Testdata.xlsx");
			System.out.println("*********Connection Established***************");
		} catch (FilloException e) {
			
			e.printStackTrace();
		}
		
	}
	
	//##################################
	
			/*
			 * Method Name: getData
			 * Purpose: get the data for a particular test case with respective to the iteration
			 * Input Parameters: Testcase name, Iteration
			 * Output Parameters: NA
			 * Designed by: Kowshik
			 * Created by:
			 * Reviewed by:
			 * Comments:
			 * Modified Date:
			 * Plugin Dependency: Dependency is added in POM.xml
			 */
	//##################################
	
	public static String getData(String sheetname,String Testcasename,String fieldname, int itr)
	{
		String data="";
		try
		{
			
		
		String strQuery="Select "+fieldname+" from "+sheetname+" where Tc_Name='"+Testcasename+"' and Iteration="+itr;
		System.out.println(strQuery);
		recordset=connection.executeQuery(strQuery);
		
		 
		while(recordset.next())
		{
		System.out.println(recordset.getField(fieldname));
		data=recordset.getField(fieldname);
		break;
		}
		}
		catch(Exception e)
		{
			System.out.println("No records found");
			
		}
		finally
		{
			recordset.close();	
		}
		
		return data;
		
	}
	
	
	public static int getIterationCount(String sheetname, String Testcasename)
	{
		int data=1;
		try
		{
			String strQuery="Select "+Testcasename+" from "+sheetname;
			recordset=connection.executeQuery(strQuery);
			while(recordset.next()){
				data++;
			}
			
		}
		catch(Exception e)
		{
			System.out.println("No records found");
		}
		finally
		{
			recordset.close();	
		}
		
		return data;
		
	}
	
	public static String getEnv(String sheetname)
	{
		
		try
		{
			//String strQuery="Select * from "+sheetname+" where Execute='Yes'";
			                  
			String strQuery="select Url from "+sheetname;
			System.out.println(strQuery);
			recordset=connection.executeQuery(strQuery);
			System.out.println("-------- record from common data sheet is fetched-------");
			while(recordset.next())
			{
			System.out.println(recordset.getField("Url"));
			URL=recordset.getField("Url");
			break;
			}
			
		}
		catch(Exception e)
		{
			System.out.println("No records found");
			
		}
		finally
		{
			recordset.close();
		}
		return URL;
	}
	
	}
	

