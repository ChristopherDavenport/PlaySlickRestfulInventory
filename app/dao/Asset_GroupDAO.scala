package dao

import javax.inject.{Inject, Singleton}

import models.Asset_Group
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfig}
import slick.driver.JdbcProfile
import tables.Asset_GroupTable

/**
  * Created by chris on 4/9/16.
  */
@Singleton()
class Asset_GroupDAO  @Inject()(dbConfigProvider: DatabaseConfigProvider) extends
  AbstractDAO[Asset_Group, Asset_GroupTable] with HasDatabaseConfig[JdbcProfile] {

  protected implicit val dbConfig = dbConfigProvider.get[JdbcProfile]
  import driver.api._

  val Query = TableQuery[Asset_GroupTable]

}
