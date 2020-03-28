package com.myco.lib
import com.mongodb.casbah.Imports._

object MongoFactory {

  private val SERVER = "localhost"
  private val PORT = 27017
  private val DATABASE = "mydb"
  private val COLLECTION = "stock"
  val connection = MongoConnection(SERVER)
  val collection = connection(DATABASE)(COLLECTION)
}

object MongoModule {

  val mongoClient: MongoClient = MongoClient()

  val db = mongoClient("mydb")

  val colleciton = db("mycollection")


  def insertMethod = {

    colleciton += MongoDBObject(
      "title" -> "Proramming in scala",
      "auther" -> "Martin",
      "tags" -> ("scala", "functional", "jvm"),
      "body" -> "..."
    )
  }

}


case class Stock(symbol: String, price: Double)

object Common {

  def buildMongoDBObject(stock: Stock) : MongoDBObject = {
    val builder = MongoDBObject.newBuilder
    builder += "symbol" -> stock.symbol
    builder += "price" -> stock.price
    builder.result
  }

  def saveStock(stock: Stock): Unit = {
    val mongoObj = buildMongoDBObject(stock)
    MongoFactory.collection.save(mongoObj)
  }
}