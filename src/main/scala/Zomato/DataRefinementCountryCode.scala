package Zomato

import common.FileToDF._
import common.Node
import org.apache.spark.sql.{DataFrame, SparkSession}
class DataRefinementCountryCode(spark:SparkSession, path:String) extends Node[DataFrame]  {
  def execute = {
//    val file = spark.read.format("com.crealytics.spark.excel")
//      .option("useHeader", "true")
//      .option("location","/E:/IdeaProject/SourceData/Kaggle/Zomato/CountryCode.xlsx")
//      .option("treatEmptyValuesAsNulls", "false")
//      .option("inferSchema", "false")
//      .option("addColorColumns", "false")
//      .load("/E:/IdeaProject/SourceData/Kaggle/Zomato/CountryCode.xlsx")

    val df = fileReader("xlsx",path).toDF("Code","Country")
   df.groupBy("Code","Country").count().drop("count")
//     filter(col("Code")==="1")


//      "/E:/IdeaProject/SourceData/Kaggle/Zomato/CountryCode.xlsx")
  }

  execute.show(10)
}