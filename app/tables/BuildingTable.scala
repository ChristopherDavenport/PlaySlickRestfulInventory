package tables

import models.Building
import slick.driver.PostgresDriver.api._
import org.joda.time.DateTime
import com.github.tototoshi.slick.PostgresJodaSupport._

/**
  * Created by chris on 4/11/16.
  */
class BuildingTable(tag: Tag) extends
  BaseTable[Building](tag, "BUILDINGS"){

  def BuildingCode = column[String]("BUILDING_CODE")
  def BuildingDesc = column[String]("BUILDING_DESC")
  def ActivityUser = column[String]("ACTIVITY_USER")
  def ActivityDate = column[DateTime]("ACTIVITY_DATE")

  def * = (
    BuildingCode,
    BuildingDesc,
    ActivityUser,
    ActivityDate
    ) <> (Building.tupled, Building.unapply)

  val pk = BuildingCode

}
