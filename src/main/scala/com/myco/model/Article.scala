package com.myco.model

case class Article(
                  id: Int,
                  title: String,
                  content: String) {

  def toJSON = {
    import net.liftweb.json.JsonDSL._
    ("id" -> id) ~ ("title" -> title) ~ ("content" -> content)
  }
}

case class Order(
                email: String,
                money: Double
                ) {

  def toJSON = {
    import net.liftweb.json.JsonDSL._
    ("email" -> email) ~ ("money" -> money)
  }
}

object Article {
  var store: List[Article] = Article(12, "qqq", "sss") :: Nil

  def addArticle(title: String,
                 content: String): Article = {
    val nextID = store.map(_.id).max + 1
    val newArticle = new Article(nextID, title, content)

    store ::= newArticle
    newArticle
  }

  def getArticle(id: Int): Option[Article] = store.filter( _.id == id).headOption
  def deleteArticle(id: Int) { store = store.filterNot(_.id == id)}
}



object Test {

  val divid2: PartialFunction[Int, Int] = {
    case x: Int if(x % 2 == 0)  => x
  }

  val divide : PartialFunction[Int, Int] = {
    case d: Int if d != 0 => 42/d
  }

  val k = List(42, "cat")

  val runPar = k.collect {
    case i: Int => Some(i + 1)
    case i: String => None
  }

  val isOdd: PartialFunction[Int, String] = {
    case x if x % 2 == 1 => x + "is odd"
  }

  val isEven: PartialFunction[Int, String] = {
    case x if x % 2 == 0 => x + "is even"
  }

  val numbers = List(1,2,3,4,5,6,7,8,9)

  numbers map ( isOdd orElse isEven)

}



















