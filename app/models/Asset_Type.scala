package models

import org.joda.time.DateTime

/**
  * Created by chris on 4/10/16.
  */
case class Asset_Type(
                     Asset_Type_PK: String,
                     Asset_Type_Desc: String,
                     Status_Check: String = "A",
                     Status_Date: DateTime = DateTime.now(),
                     Activity_User: String,
                     Activity_Date: DateTime = DateTime.now(),
                     Asset_Group_FK: String
                     )
