package sparkUseCase
import common._
import org.apache.log4j.Level
import org.apache.spark.sql.SparkSession

object firstSparkProgram extends mainMethods {
  val spark=setSparkSession("first Program")
setLog(Level.ERROR)
  //val spark= SparkSession.builder().appName("first Program").master("local[*]").getOrCreate()
 val file=spark.read.format("csv").load("/E:/IdeaProject/SourceData/csv/emp.txt")
  file.show()
  spark.close()
}
