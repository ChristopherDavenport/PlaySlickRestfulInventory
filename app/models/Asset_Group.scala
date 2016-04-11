package models

import org.joda.time.DateTime
/**
  * Created by chris on 4/9/16.
  */
case class Asset_Group(
                      Asset_Group_PK: String,
                      Asset_Group_Desc: String,
                      Status_Check: String = "A",
                      Status_Date: DateTime = DateTime.now(),
                      Activity_User: String,
                      Activity_Date: DateTime = DateTime.now(),
                      Ip_Rpt_Check: Option[String]
                      )
