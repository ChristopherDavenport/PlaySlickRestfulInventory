package controllers

import javax.inject.Inject

import play.api.db.slick.DatabaseConfigProvider
import play.api.mvc._
import slick.driver.JdbcProfile
import play.api.libs.concurrent.Execution.Implicits.defaultContext

import scala.concurrent.Future
import dao.PrintersDAO
import models.Printer
import play.api.libs.json.{Json, Writes}
import play.api.libs.json.Json.toJson

/**
  * Created by chris on 3/26/16.
  */
class PrintersController @Inject()(printersDao: PrintersDAO) extends Controller {


  def index = Action { request => Ok("Hi!")}

  def list = Action.async( request =>
  printersDao.all().map(printers => Ok(printers.toString())))
}
