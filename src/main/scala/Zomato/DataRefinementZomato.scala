package Zomato

import org.apache.spark.sql.{DataFrame, SparkSession}
import common._

import common.FileToDF._
class DataRefinementZomato(spark:SparkSession,path:String) extends Node[DataFrame] {

  override def execute: DataFrame = {

    fileReader("csv",path)
  }

 execute.show(5)
}
