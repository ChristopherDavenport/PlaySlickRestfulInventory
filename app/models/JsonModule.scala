package models

import play.api.libs.functional.syntax._
import play.api.libs.json.{JsPath, Json, Reads, Writes}

/**
  * Created by chris on 4/10/16.
  */

class JsonModule {

  implicit val catsWrites: Writes[Cat] = (
    (JsPath \ "name").write[String] and
      (JsPath \ "desc").write[String]
    )(unlift(Cat.unapply))

  implicit val catsReads: Reads[Cat] = (
    (JsPath \ "name").read[String] and
      (JsPath \ "desc").read[String]
    )(Cat.apply _)

  implicit val assetGroupWrites = Json.writes[Asset_Group]
  implicit val assetGroupReads = Json.reads[Asset_Group]

  implicit val printerWrites = Json.writes[Printer]
  implicit val printerReads = Json.reads[Printer]

  implicit val assetTypeWrites = Json.writes[Asset_Type]
  implicit val assetTypeReads = Json.reads[Asset_Type]

}
