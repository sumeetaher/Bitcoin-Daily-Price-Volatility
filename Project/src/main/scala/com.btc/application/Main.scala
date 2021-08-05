package com.btc.application

/*This job loads postgres table
into a dataframe, calls calculation
function which also stores result into hive table
*/

import org.apache.spark.sql.SparkSession
import com.btc.configuration.Context._
import com.btc.schema.Btc
import com.btc.transformation.Pipeline

object Main {

  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder.appName(appName).config(sparkMaster,mode).enableHiveSupport().getOrCreate()

    val btcTableDf = Btc.loadBtcTableData(spark)

    Pipeline.standardDeviationCalculation(btcTableDf)

  }
}
