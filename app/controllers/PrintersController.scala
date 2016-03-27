package controllers

import javax.inject.Inject
import dao.PrintersDAO
import play.api.mvc._
import play.api.libs.json._
import play.api.libs.functional.syntax._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import models.Printer


/**
  * Created by chris on 3/26/16.
  */
class PrintersController @Inject()(printersDao: PrintersDAO) extends Controller {

//  implicit val printerWrites: Writes[Printer] = (
//  (JsPath \ "id").write[Long] and
//    (JsPath \ "printer_pk").write[String] and
//    (JsPath \ "printer_desc").write[String] and
//    (JsPath \ "pages_per_min").writeNullable[Int] and
//    (JsPath \ "charge_back").writeNullable[Double] and
//    (JsPath \ "status_ck").write[String] and
//    (JsPath \ "status_date").write[DateTime] and
//    (JsPath \ "activity_user").write[String] and
//    (JsPath \ "activity_date").write[DateTime] and
//    (JsPath \ "created_user").write[String] and
//    (JsPath \ "created_date").write[DateTime]
//
//    )( unlift(Printer.unapply))

//  implicit val printerFormat = Json.format[Printer]

  implicit val jsonWrites = Json.writes[Printer]


  def index = Action { request => Ok("Hi from Printer Controller!")}


  def list = Action.async {
    request =>
      printersDao.all().map{ printers => Ok(Json.toJson(printers))}
  }
}

//def list = Action.async { request =>
//  catsDAO.all().map{ cats => Ok(Json.toJson(cats))}
//}
