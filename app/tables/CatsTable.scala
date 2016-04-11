package tables

import slick.driver.PostgresDriver.api._

import models.Cat

/**
  * Created by chris on 4/10/16.
  */
class CatsTable(tag: Tag) extends
  BaseTable[Cat](tag, "CATS"){

  def Name = column[String]("NAME", O.PrimaryKey)
  def Desc = column[String]("DESC")
  def * = (Name, Desc) <> (Cat.tupled, Cat.unapply)

  val pk = Name
}
