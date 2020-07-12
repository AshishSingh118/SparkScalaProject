package Zomato

import common.mainMethods

import org.apache.log4j.Level
object LocalMain extends mainMethods  {
   setLog(Level.ERROR)
//  val s1= new DataRefinementCountryCode(setSparkSession("Processing Zomato CSV"),args(0))
  // /E:/IdeaProject/SourceData/Kaggle/Zomato/CountryCode.xlsx
  val s2= new DataRefinementZomato(spark,args(0))
// args(1)= /E:/IdeaProject/SourceData/Kaggle/Zomato/Zomato.csv
}
