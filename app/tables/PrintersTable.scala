package tables

import slick.driver.PostgresDriver.api._
import models.Printer
import org.joda.time.DateTime
import com.github.tototoshi.slick.PostgresJodaSupport._
/**
  * Created by chris on 4/10/16.
  */
class PrintersTable(tag: Tag) extends
  BaseTable[Printer](tag, "PRINTERS") {
  def printer_pk = column[String]("printer_pk", O.PrimaryKey)
  def printer_desc = column[String]("printer_desc")
  def pages_per_min = column[Option[Int]]("pages_per_min")
  def charge_back = column[Option[Double]]("charge_back")
  def status_ck = column[Option[String]]("status_ck")
  def status_date = column[Option[DateTime]]("status_date")
  def activity_user = column[Option[String]]("activity_user")
  def activity_date = column[Option[DateTime]]("activity_date")
  def created_user = column[Option[String]]("created_user")
  def created_date = column[Option[DateTime]]("created_date")

  def * = ( printer_pk, printer_desc,
    pages_per_min , charge_back ,
    status_ck , status_date,
    activity_user , activity_date ,
    created_user , created_date ) <> ( Printer.tupled , Printer.unapply )

  val pk = printer_pk
}
