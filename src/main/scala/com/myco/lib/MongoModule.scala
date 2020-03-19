package com.myco.lib
import com.mongodb.casbah.Imports._

object MongoModule {

  val mongoClient: MongoClient = MongoClient()

  val db = mongoClient("mydb")

  val colleciton = db("mycollection")

}
