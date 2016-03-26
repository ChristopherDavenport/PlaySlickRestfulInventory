package models

import org.joda.time.DateTime

/**
  * Created by chris on 3/26/16.
  */
case class Printer(
                    id: Long,
                    printer_pk: String,
                    printer_desc: String,
                    pages_per_min: Option[Int],
                    charge_back: Option[Double],
                    status_ck: Option[Char],
                    status_date: Option[DateTime],
                    activity_user: Option[String],
                    activity_date: Option[DateTime],
                    created_user: Option[String],
                    created_date: Option[DateTime]
                  )