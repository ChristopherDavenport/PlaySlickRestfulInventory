package dao

import javax.inject.Inject

import models.Building
import play.api.db.slick.DatabaseConfigProvider
import slick.driver.JdbcProfile
import tables.BuildingTable

/**
  * Created by chris on 4/11/16.
  */
class BuildingDAO @Inject()(dbConfigProvider: DatabaseConfigProvider) extends
  AbstractDAO[Building, BuildingTable]{

  protected implicit val dbConfig = dbConfigProvider.get[JdbcProfile]
  import driver.api._
  val Query = TableQuery[BuildingTable]
}
