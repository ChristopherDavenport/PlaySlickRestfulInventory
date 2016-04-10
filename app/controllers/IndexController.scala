package controllers

import play.api.mvc._
import play.api.libs.concurrent.Execution.Implicits.defaultContext

/**
  * Created by chris on 4/10/16.
  */
class IndexController extends Controller{
  def index = Action {
    request => Ok("Hello From Index")
  }
}
