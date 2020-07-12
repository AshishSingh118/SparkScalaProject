package common

/**
 * Created by Pankaj Gaur on 19-06-2019.
 */
import java.io.File
import com.typesafe.config.{Config, ConfigFactory}

import org.apache.log4j.Logger

object DbConfig {

  val logger = Logger.getLogger(getClass.getName)
  var jdbcUrl :String = _
  var jdbcUsername:String = _
  var jdbcPassword:String = _

  var jdbcdriver:String = _
  var jdbcTableName:String = _

  var applicationConf: Config = _



  def parseArgs(args: Array[String]) ={

    logger.info("reading database configuration")
    // applicationConf = ConfigFactory.parseFile(new File(args(0)))
    applicationConf = ConfigFactory.parseFile(new File("src\\main\\resources\\override.conf"))

    jdbcUrl =  applicationConf.getString("config.database.jdbcUrl")
    jdbcUsername =  applicationConf.getString("config.database.jdbcUsername")
    jdbcPassword =  applicationConf.getString("config.database.jdbcPassword")
    jdbcdriver =  applicationConf.getString("config.database.jdbcdriver")
    jdbcTableName =  applicationConf.getString("config.database.jdbcTableName")
  }




}