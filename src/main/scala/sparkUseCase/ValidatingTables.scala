package sparkUseCase

case class tables(name:String,db:String)
object ValidatingTables extends App{

  val ref_tables: Seq[tables] =Seq(tables("cust","oracle"),tables("loan","oracle"),tables("defaulter","oracle"),tables("account","oracle"),
    tables("transaction","oracle"),tables("cashflow","oracle"),tables("rates","oracle"))
val inputTables:Option[String]=Some("cust ,loan ,account,transaction,cashflow1")

  val core: Seq[tables] =
    inputTables match {
    case Some(tablesname) =>
      tablesname.split(",").map(_.trim).filter(_.nonEmpty).flatMap{ hive =>
      ref_tables.find( _.name == hive)}.toSeq
    case _ =>  ref_tables
  }

  core.foreach(println)
}
