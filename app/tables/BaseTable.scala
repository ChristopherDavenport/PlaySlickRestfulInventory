package tables

import slick.driver.PostgresDriver.api._

/**
  * Created by chris on 4/10/16.
  */
abstract class BaseTable[A](tag: Tag, TableName: String) extends
  Table[A](tag , TableName ) {
  val pk: Rep[String]
}