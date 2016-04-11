package tables

import models.Asset_Type
import org.joda.time.DateTime
import slick.driver.PostgresDriver.api._
import com.github.tototoshi.slick.PostgresJodaSupport._

/**
  * Created by chris on 4/10/16.
  */
class Asset_TypeTable (tag: Tag) extends
  BaseTable[Asset_Type](tag, "ASSET_TYPES"){
  def Asset_Type_PK = column[String]("ASSET_TYPE_PK", O.PrimaryKey)
  def Asset_Type_Desc = column[String]("ASSET_TYPE_DESC")
  def Status_Check = column[String]("STATUS_CK")
  def Status_Date = column[DateTime]("STATUS_DATE")
  def Activity_User = column[String]("ACTIVITY_USER")
  def Activity_Date = column[DateTime]("ACTIVITY_DATE")
  def Asset_Group = column[String]("ASSET_GROUP_FK")

  def Asset_Group_FK = foreignKey("ASSET_GROUP_FK", Asset_Group, TableQuery[Asset_GroupTable])(_.Asset_Group_PK,
    onUpdate = ForeignKeyAction.Restrict)

  def * = (Asset_Type_PK, Asset_Type_Desc, Status_Check,
    Status_Date, Activity_User, Activity_Date, Asset_Group
    ) <> (Asset_Type.tupled, Asset_Type.unapply)

  val pk = Asset_Type_PK
}
