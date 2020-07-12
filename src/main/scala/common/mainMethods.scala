package common
import org.apache.spark.sql.SparkSession
import org.apache.log4j._
trait mainMethods extends App {
  def setSparkSession(appName: String):SparkSession={
    SparkSession.builder().appName(appName).master("local[*]").getOrCreate()
      }
  def setLog(level: Level)={
    Logger.getRootLogger.setLevel(level)
  }

}
