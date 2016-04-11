package controllers

import javax.inject.Inject

import dao.PrintersDAO
import models.{JsonModule, Printer}
import tables.PrintersTable

/**
  * Created by chris on 3/26/16.
  */
class PrintersController @Inject()(dao: PrintersDAO, json: JsonModule) extends
  AbstractController[Printer, PrintersTable, PrintersDAO](dao)(json.printerWrites, json.printerReads)





