package com.btc.transformation

/*This job calculates
the standard deviation and
also stores result into hive table
*/

import com.btc.schema.Btc
import org.apache.spark.sql.{DataFrame, SaveMode}
import org.apache.spark.sql.functions._

object Pipeline {

  val outputTableName = "daily_volatility_table"

  def standardDeviationCalculation(btcTableDf:DataFrame): Unit ={

    val btcDf=   btcTableDf.withColumn(Btc.date, col(Btc.timePeriodStart).substr(0, 10))

    val df_with_avgPriceClose =   btcDf.groupBy(Btc.date).agg(
      avg(Btc.priceClose).as(Btc.avgPriceClose))


    val btcDf_Join_AvgPriceCloseDf = btcDf.join(df_with_avgPriceClose,btcDf(Btc.date) === df_with_avgPriceClose(Btc.date),"inner")
      .withColumn(Btc.avgDiff, col(Btc.priceClose)-col(Btc.avgPriceClose))
      .withColumn(Btc.avgDiffSq, pow(col(Btc.avgDiff), 2))
      .drop(df_with_avgPriceClose(Btc.date))

    val VarianceDf  = btcDf_Join_AvgPriceCloseDf
      .groupBy(Btc.date).agg(
      avg(Btc.avgDiffSq).as(Btc.variance))
      .withColumn(Btc.standardDeviation,pow(col(Btc.variance), 0.5))

    VarianceDf.write.format("csv").mode(SaveMode.Overwrite).saveAsTable(outputTableName)
  }
}
