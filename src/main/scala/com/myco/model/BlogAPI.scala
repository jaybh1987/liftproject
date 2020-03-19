package com.myco.model

import com.myco.lib.MongoModule
import net.liftweb.http.rest.RestHelper
import net.liftweb.http.OkResponse
import net.liftweb.json.JValue
import net.liftweb.util.Helpers.AsInt
import org.bson.types.ObjectId


object BlogAPI extends RestHelper {

  def getArticleJSON(postID: Int): Option[JValue] = {
    Article.getArticle(postID).map(_.toJSON)
  }

  def deleteArticle(postID: Int):OkResponse = {
    Article.deleteArticle(postID)
    new OkResponse
  }

  def postArticle(jsonData: JValue): JValue = {
    Article.addArticle(
      title = ( jsonData \ "title").extract[String],
      content = ( jsonData \ "content").extract[String]).toJSON
  }

  def printCollection = {
    println("this is you method call. "+MongoModule.colleciton.map(r => println(r)))
    new OkResponse
  }


  serve {
    case "api" :: "blog" :: AsInt(postID) :: Nil JsonGet req => getArticleJSON(postID)
    case "api" :: "blog" :: AsInt(postID) :: Nil JsonDelete req => deleteArticle(postID)
    case "api" :: "blog" :: Nil JsonPost ((jsonData, req)) => postArticle(jsonData)
    case "api" :: "mongocol" :: Nil JsonGet req => printCollection
  }
}
