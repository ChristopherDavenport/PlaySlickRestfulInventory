package controllers

import javax.inject.Inject

import dao.NewCatsDAO
import models.{Cat, JsonModule}
import tables.CatsTable


/**
  * Created by chris on 4/10/16.
  */
class NewCatsController @Inject()(catsDAO: NewCatsDAO, json: JsonModule) extends
  AbstractController[Cat, CatsTable, NewCatsDAO](catsDAO)(json.catsWrites, json.catsReads)