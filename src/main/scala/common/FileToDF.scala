package common

import org.apache.spark.sql.{DataFrame, SparkSession}

object FileToDF extends mainMethods {

  def fileReader(filetype: String,path:String) = {
    filetype match {

      case "xlsx"  => xlsxfile(path)
      case _ =>   other(filetype,path)
    }
  }

  def xlsxfile(path:String):DataFrame = {
    spark.read.format("com.crealytics.spark.excel")
      .option("useHeader", "true")
      .option("location", path)
      .option("treatEmptyValuesAsNulls", "false")
      .option("inferSchema", "false")
      .option("addColorColumns", "false")
      .load(path)
  }
  def other(filetype:String,path:String)={
    spark.read.format(filetype).option("Header","True")
      .load(path)
  }
}