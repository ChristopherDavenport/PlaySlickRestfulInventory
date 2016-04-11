package controllers

import dao.AbstractDAO
import play.api.libs.json.{JsError, Json, Reads, Writes}
import play.api.mvc.{Action, BodyParsers, Controller}
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import tables.BaseTable

import scala.concurrent.Future

/**
  * Created by chris on 4/10/16.
  *
  * Requires Implicit json Converter
  */
abstract class AbstractController[C, T <: BaseTable[C], D <: AbstractDAO[C, T]](dao: D)(
  implicit val jsonWrites : Writes[C], implicit val jsonReads: Reads[C]) extends Controller {


  def createTable() = Action.async{ request =>
    dao.createTable().map(_ => Created(Json.obj("status" -> "OK", "message" -> "Table Created")))
  }

  def dropTable() = Action.async{ request =>
    dao.dropTable().map(_ => Ok("Table Dropped"))
  }

  def findAll = Action.async { request =>
    dao.findAll().map{ value => Ok(Json.toJson(value))}
  }

  def find(string: String) = Action.async { request =>
    dao.find(string).map { value => Ok(Json.toJson(value)) }
  }

  def findOne(string: String) = Action.async { request =>
    dao.findOne(string).map {value => Ok(Json.toJson(value))}
  }

  def insertOrUpdate() = Action.async(BodyParsers.parse.json) { request =>
    val cats = request.body.validate[Seq[C]]
    cats.fold(
      errors => {
        Future(BadRequest(Json.obj("status" -> "OK", "message" -> JsError.toJson(errors))))
      },
      cats => {
        dao.insertOrUpdate(cats).map(_ => Created(Json.obj("status" -> "OK", "message" -> "Entities added")))
      }
    )
  }

  def delete(string: String) = Action.async { request =>
    dao.delete(string).map {value => Ok(Json.toJson(value))}
  }

}
