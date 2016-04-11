package dao

import javax.inject.{Inject, Singleton}

import models.Asset_Type
import org.joda.time.DateTime
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfig}
import slick.driver.JdbcProfile
import tables.Asset_TypeTable

/**
  * Created by chris on 4/10/16.
  */
@Singleton()
class Asset_TypeDAO @Inject()(dbConfigProvider: DatabaseConfigProvider) extends
  AbstractDAO[Asset_Type, Asset_TypeTable] with HasDatabaseConfig[JdbcProfile] {

  protected implicit val dbConfig = dbConfigProvider.get[JdbcProfile]
  import driver.api._

 val Query = TableQuery[Asset_TypeTable]
}
