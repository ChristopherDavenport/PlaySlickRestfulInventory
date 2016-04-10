package controllers

import dao.Asset_GroupDAO
import javax.inject.Inject

import models.Asset_Group
import play.api.libs.json.Json
import play.api.mvc._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.functional.syntax._

/**
  * Created by chris on 4/10/16.
  */
class Asset_GroupController @Inject()(dao: Asset_GroupDAO) extends Controller {

  implicit val jsonWrites = Json.writes[Asset_Group]
  implicit val jsonReads = Json.reads[Asset_Group]

  def index = Action { request => Ok("Hi from the Asset_Groups!")}

  def list = Action.async { request =>
    dao.all().map{ value => Ok(Json.toJson(value))}
  }

  def create = Action.async{ request =>
    dao.create().map(_ => Ok("Created Cats Table"))
  }

  def Asset_Group(pk: String) = Action.async{
    dao.get(pk).map(value => Ok(Json.toJson(value)))
  }

}
