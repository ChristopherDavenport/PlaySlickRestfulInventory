package dao

import javax.inject.{Inject, Singleton}

import models.Cat
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfig}
import slick.driver.JdbcProfile
import tables.CatsTable

/**
  * Created by chris on 4/10/16.
  */
@Singleton
class NewCatsDAO @Inject()(dbConfigProvider: DatabaseConfigProvider) extends
  AbstractDAO[Cat, CatsTable] with HasDatabaseConfig[JdbcProfile] {

  protected implicit val dbConfig = dbConfigProvider.get[JdbcProfile]
  import driver.api._

  val Query = TableQuery[CatsTable]

}
