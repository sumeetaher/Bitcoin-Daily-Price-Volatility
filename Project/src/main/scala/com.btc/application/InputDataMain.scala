package com.btc.application
/*This job loads json data from URL
 to a postgres table
*/

import com.btc.configuration.Context._
import com.btc.schema.Btc
import org.apache.spark.sql.{SaveMode, SparkSession}

object InputDataMain {

  val dataUrl      =  "http://cf-code-challenge-40ziu6ep60m9.s3-website.eu-central-1.amazonaws.com/ohlcv-btc-usd-history-6min-2020.json"
  val pgTableName  =  "public.input_btc"

  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder.appName(inputDataAppName).config(sparkMaster,mode).enableHiveSupport().getOrCreate()

    val btcDf = Btc.loadFromUrl(dataUrl)(spark)

    btcDf.write.mode(SaveMode.Overwrite).jdbc(pgUrl,pgTableName,pgConnectionProperties)

  }
}
