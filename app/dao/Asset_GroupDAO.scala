package dao

import javax.inject.{Inject, Singleton}

import models.Asset_Group
import org.joda.time.DateTime
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfig}
import slick.driver.JdbcProfile
import com.github.tototoshi.slick.PostgresJodaSupport._
import scala.concurrent.Future

/**
  * Created by chris on 4/9/16.
  */
@Singleton()
class Asset_GroupDAO  @Inject()(dbConfigProvider: DatabaseConfigProvider) extends
  HasDatabaseConfig[JdbcProfile] {

  protected implicit val dbConfig = dbConfigProvider.get[JdbcProfile]
  import driver.api._

  class Asset_GroupTable(tag: Tag) extends Table[Asset_Group](tag, "ASSET_GROUPS") {
    def Asset_Group_PK = column[String]("ASSET_GROUP_PK")
    def Asset_Group_Desc = column[String]("ASSET_GROUP_DESC")
    def Status_Check = column[String]("STATUS_CK")
    def Status_Date = column[DateTime]("STATUS_DATE")
    def Activity_User = column[String]("ACTIVITY_USER")
    def Activity_Date = column[DateTime]("ACTIVITY_DATE")
    def Ip_Rpt_Check = column[Option[String]]("IP_RPT_CHECK")

    def * = (Asset_Group_PK, Asset_Group_Desc, Status_Check, Status_Date,
      Activity_User, Activity_Date, Ip_Rpt_Check) <> (Asset_Group.tupled, Asset_Group.unapply)
  }

  private val Asset_Groups = TableQuery[Asset_GroupTable]

  def create() = db.run(DBIO.seq(Asset_Groups.schema.create))
  def all() : Future[Seq[Asset_Group]] = db.run(Asset_Groups.result)
  def get(PK: String):Future[Seq[Asset_Group]] = db.run(Asset_Groups.filter(_.Asset_Group_PK === PK).result)

  def add(myCat: Asset_Group) = db.run(Asset_Groups.insertOrUpdate(myCat))
}
