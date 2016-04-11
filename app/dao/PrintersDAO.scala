package dao

import javax.inject.{Inject, Singleton}

import org.joda.time.DateTime
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfig}
import slick.driver.JdbcProfile
import models.Printer
import com.github.tototoshi.slick.PostgresJodaSupport._
import tables.PrintersTable

/**
  * Created by chris on 3/26/16.
  */

@Singleton()
class PrintersDAO @Inject()(dbConfigProvider: DatabaseConfigProvider) extends
  AbstractDAO[Printer, PrintersTable] with HasDatabaseConfig[JdbcProfile] {

  protected implicit val dbConfig = dbConfigProvider.get[JdbcProfile]
  import driver.api._

  val Query = TableQuery[PrintersTable]
}