package com.btc.configuration


/*Contains parameters for
 spark session and  postgres connection
*/

import java.util.Properties

object Context {

  //Spark Parameters
  val inputDataAppName   =  "StoreIntoPostgres"
  val appName            =  "BTC_StandardDeviation"
  val sparkMaster        =  "spark.master"
  val mode               =  "local"


  //Postgres Parameters
  val dbName             = "postgres"
  val dbPassword         = "Carrom@123"
  val pgUrl              = "jdbc:postgresql://localhost:5432/postgres"

  val pgConnectionProperties = new Properties()
  pgConnectionProperties.put("user",dbName)
  pgConnectionProperties.put("password",dbPassword)

}
