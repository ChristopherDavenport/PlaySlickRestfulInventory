package dao

import javax.inject.{Inject, Singleton}

import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfig}
import slick.driver.JdbcProfile
import models.Cat
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import scala.concurrent.Future

/**
  * Created by chris on 3/27/16.
  */

@Singleton()
class CatsDAO @Inject()(dbConfigProvider: DatabaseConfigProvider) extends
  HasDatabaseConfig[JdbcProfile] {

  protected implicit val dbConfig = dbConfigProvider.get[JdbcProfile]
  import driver.api._

  private class CatsTable(tag: Tag) extends Table[Cat](tag, "cats") {
    def name = column[String]("name")
    def desc = column[String]("desc")

    def * = (name, desc) <> (Cat.tupled, Cat.unapply)
  }

  private val Cats = TableQuery[CatsTable]

  def create() = db.run(DBIO.seq(Cats.schema.create))
  def all() : Future[Seq[Cat]] = db.run(Cats.result)
}
