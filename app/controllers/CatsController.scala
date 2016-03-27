package controllers

import javax.inject.Inject
import dao.CatsDAO
import models.Cat
import play.api.mvc._
import play.api.libs.json._
import play.api.libs.functional.syntax._
import play.api.libs.concurrent.Execution.Implicits.defaultContext


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


  def index = Action { request => Ok("Hi from the Cats!")}

  def create = Action.async{ request =>
    catsDAO.create().map(_ => Ok("Created Cats Table"))
  }

  def list = Action.async { request =>
    catsDAO.all().map{ cats => Ok(Json.toJson(cats))}
  }

}
