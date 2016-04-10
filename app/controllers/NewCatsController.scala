package controllers

import javax.inject.Inject

import dao.NewCatsDAO
import models.{Cat, JsonModule}


/**
  * Created by chris on 4/10/16.
  */
class NewCatsController @Inject()(catsDAO: NewCatsDAO, json: JsonModule) extends
  AbstractController[Cat, NewCatsDAO](catsDAO)(json.catsWrites, json.catsReads)