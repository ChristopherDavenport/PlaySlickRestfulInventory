package controllers

import javax.inject.Inject

import dao.BuildingDAO
import models.{Building, JsonModule}
import tables.BuildingTable

/**
  * Created by chris on 4/11/16.
  */
class BuildingController @Inject()(dao: BuildingDAO, json: JsonModule) extends
  AbstractController[Building, BuildingTable, BuildingDAO](dao)(json.buildingWrites, json.buildingReads)
