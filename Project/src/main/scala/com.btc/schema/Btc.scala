package com.btc.schema
/* Contains functions to
(1) to load data into postgres --called in InputMain
(2) to load postgres data into a datarame --called in Main
*/

import org.apache.spark.sql.{DataFrame, SparkSession}
import com.btc.configuration.Context._

object Btc {

  //given columns
  val timePeriodStart    =  "time_period_start"
  val timePeriodEnd      =  "time_period_end"
  val timeOpen           =  "time_open"
  val timeClose          =  "time_close"
  val priceOpen          =  "price_open"
  val priceHigh          =  "price_high"
  val priceLow           =  "price_low"
  val priceClose         =  "price_close"
  val volumeTraded       =  "volume_traded"
  val tradesCount        =  "trades_count"

  //calculated columns
  val date               =  "date"
  val avgPriceClose      =  "avg_price_close"
  val avgDiff            =  "avg_diff"
  val avgDiffSq          =  "avg_diff_sq"
  val variance           =  "variance"
  val perDayRecords      =  "per_day_records"
  val standardDeviation  =  "standard_deviation"

  val selectColumnList:  List[String] = List(date, priceOpen,priceHigh, priceLow ,priceClose, standardDeviation)

  val pgTableName  =  "public.input_btc"

  def loadBtcTableData(implicit spark: SparkSession): DataFrame ={

    spark.read.jdbc(pgUrl,pgTableName,pgConnectionProperties)
      .select(Btc.priceClose,Btc.timePeriodStart)
  }


  def loadFromUrl(url: String)(implicit spark: SparkSession): DataFrame ={
    val result = scala.io.Source.fromURL(url).mkString

    val jsonResponseOneLine = result.toString().stripLineEnd

    val jsonRdd = spark.sparkContext.parallelize(jsonResponseOneLine :: Nil)
    val jsonDf = spark.read.json(jsonRdd)
    jsonDf

  }

}


