package com.myco.model

import com.myco.lib.{Common, MongoModule, Stock}
import net.liftweb.http.OkResponse
import net.liftweb.http.rest.RestHelper
import net.liftweb.json.JValue
import net.liftweb.util.Helpers.AsInt


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

  def saveProcess = {
    val apple = Stock("apple", 500)
    val google = Stock("goog", 400)
    val netflix = Stock("Nflx", 600)

    Common.saveStock(apple)
    new OkResponse
  }
//{"symbol":"mytest", "price": 4000}
  def saveByPostReq(jsonData: JValue): JValue= {
    Common.saveStock(
      Stock.addStock(
        symbol = ( jsonData \ "symbol").extract[String],
        price = (jsonData \ "price").extract[Double]
      )
    )
    Stock.addStock(
      symbol = ( jsonData \ "symbol").extract[String],
      price = (jsonData \ "price").extract[Double]
    ).toStockJson
  }

  //  def saveOrder = {
  //
  //    import net.liftweb.json.JsonDSL._
  //    implicit val system: ActorSystem = ActorSystem()
  //    implicit val materializer: Materializer = ActorMaterializer()
  //
  //    implicit val executionContext = system.dispatcher
  //
  //    val uri = "http://localhost:9090/orders"
  //    val body = new Order("jay@yahoo.com", 10d).toOrderJson.toString
  //    val entity = HttpEntity(ContentTypes.`application/json`, body)
  //    val request = HttpRequest(method = HttpMethods.POST, uri = uri, entity = entity)
  //
  //    val responseFuture = Http().singleRequest(request)
  //
  //    val j = responseFuture.map {
  //      case response @HttpResponse(_, _, _, _) => println(s"successfull")
  //      case _ => sys.error("something went wrong.")
  //    }
  //
  //    val k = ("name" -> "jay") ~ ("lastname" -> "Bhavsar")
  //    k
  //  }

  serve {
    case "api" :: "blog" :: AsInt(postID) :: Nil JsonGet req => getArticleJSON(postID)
    case "api" :: "blog" :: AsInt(postID) :: Nil JsonDelete req => deleteArticle(postID)
    case "api" :: "blog" :: Nil JsonPost ((jsonData, req)) => postArticle(jsonData)
    case "api" :: "mongocol" :: Nil JsonGet req => printCollection
    case "api" :: "saveone" :: Nil JsonGet req => saveProcess
    case "api" :: "savestock" :: Nil JsonPost((jsonData, req)) => saveByPostReq(jsonData)
  }

}
