package dao
import javax.inject.{Inject, Singleton}

import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfig}
import slick.driver.JdbcProfile
import play.api.libs.concurrent.Execution.Implicits.defaultContext

import scala.concurrent.Future
import com.github.tototoshi.slick.PostgresJodaSupport._
import tables.BaseTable


/**
  * Created by chris on 4/10/16.
  */
abstract class AbstractDAO[A, T <: BaseTable[A]] extends
  HasDatabaseConfig[JdbcProfile]{

  import driver.api._

  // These Are The Values That Need To Be Supplied In The Implemented DAO



//  abstract class BaseTable(tag: Tag, TableName: String) extends Table[A](tag , TableName ) {
//    val pk: Rep[String]
//  }

  def Query : TableQuery[T]


  // This is where the magic methods happen
  def insertOrUpdate(row: A): Future[Int] = {
    db.run(Query.insertOrUpdate(row))
  }

  def insertOrUpdate(rows: Seq[A]): Future[Unit] = {
    Future { rows.foreach(insertOrUpdate(_)) }
  }

  def delete(pk: String): Future[Int] = {
    delete(Seq(pk))
  }

  def delete(pks: Seq[String]) : Future[Int] = {
    db.run(Query.filter(_.pk.inSet(pks)).delete)
  }

  def findAll() : Future[Seq[A]] = db.run(Query.result)

  def find(string: String): Future[Seq[A]] = {
    db.run( Query.filter(_.pk like s"%$string%").result )
  }

  def findOne(string: String): Future[Option[A]] = {
    db.run( Query.filter(_.pk === string).result.map(r => r.headOption))
  }

  def createTable() : Future[Unit] = db.run(DBIO.seq(Query.schema.create))

  def dropTable() : Future[Unit] = db.run(DBIO.seq(Query.schema.drop))



}
