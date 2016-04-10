package controllers

import javax.inject.Inject
import dao.CatsDAO
import models.Cat
import play.api.mvc._
import play.api.libs.json._
import play.api.libs.functional.syntax._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import scala.concurrent.Future
import scala.concurrent.duration._

/**
  * Created by chris on 3/27/16.
  */
class CatsController @Inject()(catsDAO: CatsDAO) extends Controller {

//  implicit object CatWrites extends Writes[Cat] {
//    def writes(c: Cat) = Json.obj(
//      "name" -> Json.toJson(c.name),
//      "desc" -> Json.toJson(c.desc)
//    )
//  }

//  implicit val jsonWrites = Json.writes[Cat]

  implicit val catsWrites: Writes[Cat] = (
    (JsPath \ "name").write[String] and
      (JsPath \ "desc").write[String]
    )(unlift(Cat.unapply))

  implicit val catsRead: Reads[Cat] = (
    (JsPath \ "name").read[String] and
      (JsPath \ "desc").read[String]
    )(Cat.apply _)


  def index = Action { request => Ok("Hi from the Cats!")}

  def create() = Action.async{ request =>
    catsDAO.createTable().map(_ => Ok("Created Cats Table"))
  }

  def list = Action.async { request =>
    catsDAO.findAll().map{ cats => Ok(Json.toJson(cats))}
  }

//  def add() = Action.async(BodyParsers.parse.json) { request =>
//    val cats = request.body.validate[Seq[Cat]]
//    cats.fold(
//      errors => {
//        Future(BadRequest(Json.obj("status" -> "OK", "message" -> JsError.toJson(errors))))
//      },
//      cats => {
//        catsDAO.insert(cats).map(result => Ok(Json.obj("status" -> "OK", "message" -> "Cats added")))
//      }
//    )
//  }
//
//  def update() = Action.async(BodyParsers.parse.json) { request =>
//    val cats = request.body.validate[Seq[Cat]]
//    cats.fold(
//      errors => {
//        Future(BadRequest(Json.obj("status" -> "OK", "message" -> JsError.toJson(errors))))
//      },
//      cats => {
//        catsDAO.update(cats).map(result => Ok(Json.obj("status" -> "OK", "message" -> "Cats added")))
//      }
//    )
//  }

  def find(string: String) = Action.async { request =>
    catsDAO.find(string).map { cats => Ok(Json.toJson(cats)) }
  }

  def insertOrUpdate() = Action.async(BodyParsers.parse.json) { request =>
    val cats = request.body.validate[Seq[Cat]]
    cats.fold(
      errors => {
        Future(BadRequest(Json.obj("status" -> "OK", "message" -> JsError.toJson(errors))))
      },
      cats => {
        catsDAO.insertOrUpdate(cats).map(_ => Ok(Json.obj("status" -> "OK", "message" -> "Cats added")))
      }
    )
  }



//
//  def save = Action { request =>
//    val catJson = request.body.asJson
//    catJson.as
////    catJson.map{
////      json =>
////        val myCat = json.as[Cat]
////        catsDAO.add(json).map{_ => Ok("Cat Created")}
////    }
//
//  }

}
