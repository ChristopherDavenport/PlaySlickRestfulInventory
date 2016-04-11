package tables

import models.Asset_Group
import org.joda.time.DateTime
import slick.driver.PostgresDriver.api._
import com.github.tototoshi.slick.PostgresJodaSupport._
/**
  * Created by chris on 4/10/16.
  */
class Asset_GroupTable (tag: Tag) extends
  BaseTable[Asset_Group](tag, "ASSET_GROUPS"){
  def Asset_Group_PK = column[String]("ASSET_GROUP_PK", O.PrimaryKey)
  def Asset_Group_Desc = column[String]("ASSET_GROUP_DESC")
  def Status_Check = column[String]("STATUS_CK")
  def Status_Date = column[DateTime]("STATUS_DATE")
  def Activity_User = column[String]("ACTIVITY_USER")
  def Activity_Date = column[DateTime]("ACTIVITY_DATE")
  def Ip_Rpt_Check = column[Option[String]]("IP_RPT_CHECK")

  def * = (Asset_Group_PK, Asset_Group_Desc, Status_Check, Status_Date,
    Activity_User, Activity_Date, Ip_Rpt_Check) <> (Asset_Group.tupled, Asset_Group.unapply)

  val pk = Asset_Group_PK
}
