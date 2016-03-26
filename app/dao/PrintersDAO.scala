package dao

import javax.inject.{Inject, Singleton}

import org.joda.time.DateTime
import com.github.tototoshi.slick.PostgresJodaSupport._
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfig}
import slick.driver.JdbcProfile
import models.Printer
import scala.concurrent.Future



/**
  * Created by chris on 3/26/16.
  */
trait PrintersComponent { self: HasDatabaseConfig[JdbcProfile] =>
  import driver.api._

  class PrintersTable(tag: Tag) extends Table[Printer](tag, "printers"){
    def id = column[Long]("id", O.AutoInc, O.PrimaryKey)
    def printer_pk = column[String]("printer_pk")
    def printer_desc = column[String]("printer_desc")
    def pages_per_min = column[Option[Int]]("pages_per_min")
    def charge_back = column[Option[Double]]("charge_back")
    def status_ck = column[Option[Char]]("status_ck")
    def status_date = column[Option[DateTime]]("status_date")
    def activity_user = column[Option[String]]("activity_user")
    def activity_date = column[Option[DateTime]]("activity_date")
    def created_user = column[Option[String]]("created_user")
    def created_date = column[Option[DateTime]]("created_date")

    def * = ( id , printer_pk, printer_desc,
      pages_per_min , charge_back ,
      status_ck , status_date,
      activity_user , activity_date ,
      created_user , created_date ) <> ( Printer.tupled , Printer.unapply)
  }

}

@Singleton()
class PrintersDAO @Inject()(dbConfigProvider: DatabaseConfigProvider) extends
  HasDatabaseConfig[JdbcProfile] with PrintersComponent {

  protected implicit val dbConfig = dbConfigProvider.get[JdbcProfile]
  import driver.api._

  val Printers = TableQuery[PrintersTable]

  def all() : Future[Seq[Printer]] = db.run(Printers.result)
}