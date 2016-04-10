package models

import play.api.libs.functional.syntax._
import play.api.libs.json.{JsPath, Reads, Writes}

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

}
