package com.iaditya.testng.providersample;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * TestNG sample to load unique test data based on test method name with one dataProvider
 * 
 * @author adityai
 *
 */
public class NewTest {
	/**
	 * Simple test method that loads test data from the master data provider
	 * 
	 * @param a
	 * @param b
	 */
  @Test(dataProvider="masterDataProvider")
  public void testMethod1(int a, int b) {
	  Assert.assertEquals(a, 1);
	  Assert.assertEquals(b, 1);
  }

	/**
	 * Simple test method that loads test data from the master data provider
	 * 
	 * @param a
	 * @param b
	 */
  @Test(dataProvider="masterDataProvider")
  public void testMethod2(int a, int b) {
	  Assert.assertEquals(a, 2);
	  Assert.assertEquals(b, 2);
  }
  
  /**
   * Sample test method to demonstrate execution of multiple iterations of the test method for
   * each row of test data
   * 
   * @param dataMap
   */
  @Test(dataProvider="mapDataProvider")
  public void testMethod3(Map<String, String> dataMap) {
	  Assert.assertEquals(dataMap.get("dataKey"), "dataValue1");
  }

  /**
   * Sample test method to demonstrate execution of multiple iterations of the test method for
   * each row of test data
   * 
   * @param dataMap
   */
//  @Test(dataProvider="mapDataProvider")
//  public void testMethod4(Map<String, String> dataMap) {
//	  Assert.assertEquals(dataMap.get("dataKey"), "dataValue1");
//  }

  
  /**
   * Single data provider for providing unique data to each test method
   * @param method
   * @return
   */
  @DataProvider(name="masterDataProvider")
  private Object[][] getData(Method method) {
	  Object[][] data = null;
	  
	  if ("testMethod1".equals(method.getName())) {
		  data = new Object[][] {{1,1}};
	  }
	  else if ("testMethod2".equals(method.getName())) {
		  //Two rows of data fed to one test method. 
		  //Equivalent to two iterations of the same test executed with different data
		  data = new Object[][] {{2,2}, {2, 2}};
	  }
	return data;
  }
  
  /**
   * Sample data provider for executing multiple iterations of the same test with different rows of data
   * 
   * @param method
   * @return
   */
  @SuppressWarnings("null")
@DataProvider(name="mapDataProvider")
  private Object[][] getMapData(Method method) {
	  Object[][] data = null;
	  Map<String, String> dataMap = new HashMap<String, String>();
	  Map<String, String> dataMap1 = new HashMap<String, String>();
	  dataMap.put("dataKey", "dataValue1");
	  dataMap1.put("dataKey", "dataValue1");

	  //Two rows of data fed to one test method. 
	  //Equivalent to two iterations of the same test executed with different data
	  if ("testMethod3".equals(method.getName())) {
		  data = new Object[][] {{dataMap}, {dataMap1}};
	  }
	  //Example for putting three data maps in the object array in a loop
	  else if ("testMethod4".equals(method.getName())) {
		  for (int i=0;i<3;i++) {
			  data[i][0] = dataMap;
		  }
	  }
	return data;
  }
  
}
