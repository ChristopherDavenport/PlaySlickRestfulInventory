package dao

import javax.inject.{Inject, Singleton}

import models.Cat
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfig}
import slick.driver.JdbcProfile

/**
  * Created by chris on 4/10/16.
  */
@Singleton
class NewCatsDAO @Inject()(dbConfigProvider: DatabaseConfigProvider) extends AbstractDAO[Cat] with HasDatabaseConfig[JdbcProfile] {

  protected implicit val dbConfig = dbConfigProvider.get[JdbcProfile]
  import driver.api._

  val TableName = "CATS"
  class ClassTable(tag: Tag) extends BaseTable(tag){
    def Name = column[String]("NAME", O.PrimaryKey)
    def Desc = column[String]("DESC")
    def * = (Name, Desc) <> (Cat.tupled, Cat.unapply)

    val pk = Name
  }

  val Query = TableQuery[ClassTable]

}
