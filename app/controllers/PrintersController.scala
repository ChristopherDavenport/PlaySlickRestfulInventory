package controllers

import javax.inject.Inject

import dao.PrintersDAO
import models.{JsonModule, Printer}

/**
  * Created by chris on 3/26/16.
  */
class PrintersController @Inject()(dao: PrintersDAO, json: JsonModule) extends
  AbstractController[Printer, PrintersDAO](dao)(json.printerWrites, json.printerReads)

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




