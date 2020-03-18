package com.myco.lib
import com.mongodb.casbah.Imports._

object MongoModule {

  def getMongoclient: MongoClient = MongoClient();

}
