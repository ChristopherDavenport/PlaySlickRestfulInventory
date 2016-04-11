package models
import org.joda.time.DateTime

/**
  * Created by chris on 4/11/16.
  */
case class Building(
                   Building_Code: String,
                   Building_Desc: String,
                   Activity_User: String,
                   Activity_Date: DateTime
                   )
