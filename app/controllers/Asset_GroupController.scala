package controllers

import dao.Asset_GroupDAO
import javax.inject.Inject

import models.{Asset_Group, JsonModule}
import tables.Asset_GroupTable

/**
  * Created by chris on 4/10/16.
  */
class Asset_GroupController @Inject()(dao: Asset_GroupDAO, json: JsonModule) extends
  AbstractController[Asset_Group, Asset_GroupTable, Asset_GroupDAO](dao)(json.assetGroupWrites, json.assetGroupReads)
