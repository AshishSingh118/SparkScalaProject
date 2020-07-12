package spark.core

import common.mainMethods
import org.apache.spark.sql._
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
object MySQLPractice  extends mainMethods {



    override val spark = SparkSession.builder.master("local[*]").appName("mysqlpractice")
      .config("spark.sql.warehouse.dir", "/home/hadoop/work/warehouse")
      .enableHiveSupport().getOrCreate()

    val sc = spark.sparkContext
    val conf = new SparkConf().setAppName("mysqlpractice").setMaster("local[*]")
    //    val sc = new SparkContext(conf)
    //val sqlContext = new org.apache.spark.sql.SQLContext(sc)
    val sqlContext = spark.sqlContext
    import spark.implicits._
    import spark.sql

    //val mySQLurl = "jdbc:mysql://192.168.182.1:3306/db100"

    val jdbcHostname = "192.168.182.1"
    val jdbcPort = 3306
    val jdbcDatabase ="techieventures"
    val jdbcUsername = "root"
    val jdbcPassword = "root"

    // Create the JDBC URL without passing in the user and password parameters.
    val jdbcUrl =  s"jdbc:mysql://${jdbcHostname}:${jdbcPort}/${jdbcDatabase}"

    //val mySQLurl = "jdbc:mysql://mysqldbibm.ctx5qjxp7j6n.ap-south-1.rds.amazonaws.com:3306/mysqldb"
    val connProp = new java.util.Properties()
    connProp.setProperty("user", s"${jdbcUsername}")
    connProp.setProperty("password", s"${jdbcPassword}")
    connProp.setProperty("driver", "com.mysql.jdbc.Driver")

    connProp.setProperty("partitionColumn","orderNumber")
    connProp.setProperty("lowerBound","10100")
    connProp.setProperty("upperBound","10425")
    connProp.setProperty("numPartitions","4")


    // val mytable = "(select * from cus where id > 111) as t"

    val mytable = "(SELECT o.orderNumber,o.orderDate,o.status,od.productCode,od.quantityOrdered,od.priceEach " +
      "FROM orders o JOIN orderDetails od ON o.orderNumber = od.orderNumber) as t"


    val mySqldf = spark.read.jdbc(jdbcUrl,mytable,connProp)
    mySqldf.printSchema()

    mySqldf.show()
    println(mySqldf.distinct().count())
    println(mySqldf.rdd.getNumPartitions)

    // writing the same fdata to in mytable
    //mySqldf.write.mode(saveMode = "append") jdbc(jdbcUrl,"mytable",connProp)
    //     mySqldf.write.format("CSV").save("E:\\work\\datasets\\result")

    savetoOracle(mySqldf)

    spark.stop()


  def savetoOracle(dataFrame: DataFrame): Unit =
  {
    val url = "jdbc:oracle:thin://@oracledb.ctx5qjxp7j6n.ap-south-1.rds.amazonaws.com:1521/ORCL"
    val myprop = new java.util.Properties()
    myprop.setProperty("driver","oracle.jdbc.OracleDriver")
    myprop.setProperty("user","ousername")
    myprop.setProperty("password","opassword")
    myprop.setProperty("partitionColumn","orderNumber")
    myprop.setProperty("lowerBound","10100")
    myprop.setProperty("upperBound","10425")
    myprop.setProperty("numPartitions","4")

    dataFrame.write.jdbc(url,"tab_pankajjoin",myprop )
  }
  }
