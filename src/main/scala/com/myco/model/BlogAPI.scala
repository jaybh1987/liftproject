package com.myco.model

import net.liftweb.http.rest.RestHelper
import net.liftweb.http.OkResponse
import net.liftweb.json.JValue
import net.liftweb.util.Helpers.AsInt

object BlogAPI extends RestHelper {

  def getArticleJSON(postID: Int): Option[JValue] = {
    Article.getArticle(postID).map(_.toJSON)
  }

  def deleteArticle(postID: Int) = {
    Article.deleteArticle(postID)
    new OkResponse
  }

  def postArticle(jsonData: JValue): JValue = {
    Article.addArticle(
      title = (jsonData \ "title").extract[String],
      content = (jsonData \ "content").extract[String]
    ).toJSON
  }

  serve {
    case "api" :: "blog" :: AsInt(postID) :: Nil JsonGet req => getArticleJSON(postID)
    case "api" :: "blog" :: AsInt(postID) :: Nil JsonDelete req => deleteArticle(postID)
    case "api" :: "blog" :: Nil JsonPost ((jsonData, req)) => postArticle(jsonData)
  }
}
