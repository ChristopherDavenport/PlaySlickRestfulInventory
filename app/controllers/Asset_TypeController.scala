package controllers

import javax.inject.Inject

import dao.Asset_TypeDAO
import models.{Asset_Type, JsonModule}
import tables.Asset_TypeTable

/**
  * Created by chris on 4/10/16.
  */
class Asset_TypeController @Inject()(dao: Asset_TypeDAO, json: JsonModule) extends
  AbstractController[Asset_Type, Asset_TypeTable, Asset_TypeDAO](dao)(json.assetTypeWrites, json.assetTypeReads)
