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

  class CatsTable(tag: Tag) extends Table[Cat](tag, "CATS") {
    def name = column[String]("NAME", O.PrimaryKey)
    def desc = column[String]("DESC")

    def * = (name, desc) <> (Cat.tupled, Cat.unapply)
    val pk = name
  }

  private val Cats = TableQuery[CatsTable]

  def insertOrUpdate(row: Cat): Future[Int] = {
    db.run(Cats.insertOrUpdate(row))
  }

  def insertOrUpdate(rows: Seq[Cat]): Future[Unit] = {
    Future { rows.foreach(insertOrUpdate(_)) }
  }

  def delete(pk: String): Future[Int] = {
    delete(Seq(pk))
  }

  def delete(pks: Seq[String]) : Future[Int] = {
    db.run(Cats.filter(_.pk.inSet(pks)).delete)
  }

  def findAll() : Future[Seq[Cat]] = db.run(Cats.result)

  def find(string: String): Future[Seq[Cat]] = {
    val filterName = Cats.filter { cat =>
        cat.name like s"%$string%"
    }
    db.run( filterName.result )
  }

  def createTable() : Future[Unit] = db.run(DBIO.seq(Cats.schema.create))

  def dropTable() : Future[Unit] = db.run(DBIO.seq(Cats.schema.drop))

}
