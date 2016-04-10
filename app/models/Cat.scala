package models

import play.api.libs.functional.syntax._
import play.api.libs.json.{JsPath, Reads, Writes}

/**
  * Created by chris on 3/27/16.
  */
case class Cat(
              name: String,
              desc: String
              )
