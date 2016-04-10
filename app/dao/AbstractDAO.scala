package dao
import javax.inject.{Inject, Singleton}

import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfig}
import slick.driver.JdbcProfile
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import scala.concurrent.Future


/**
  * Created by chris on 4/10/16.
  */
abstract class AbstractDAO[A, C] @Inject()(dbConfigProvider: DatabaseConfigProvider) extends
  HasDatabaseConfig[JdbcProfile]{

  protected implicit val dbConfig = dbConfigProvider.get[JdbcProfile]
  import driver.api._

  def classPk(a: A): C

  val TableName: String
  type ClassTable <: BaseTable
  abstract class BaseTable(tag: Tag) extends Table[A](tag , TableName ) {

    def pk: Rep[C]
  }

  val Query : TableQuery[ClassTable]

  def insertOrUpdate(row: A) = {
    db.run(Query.insertOrUpdate(row))
  }



}
