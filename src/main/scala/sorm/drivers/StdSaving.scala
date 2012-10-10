package sorm.drivers

import sext.Sext._

import sorm._

trait StdSaving {
  import sql.Sql._

  def statement ( sql : Sql ) : jdbc.Statement
  def connection : jdbc.JdbcConnection
  def update 
    ( table : String, values : Iterable[(String, Any)], pk : Iterable[(String, Any)] )
    {
      val exprs = values.toStream.map{case (c, v) => SetExpression(Column(c), v)}
      Update(table, exprs, pk $ where) $ statement $ connection.executeUpdate
    }

  def insert
    ( table : String, values : Iterable[(String, Any)] )
    : Seq[Any]
    = {
      val (cs, vs) = values.toStream.unzip
      Insert(table, cs, vs) $ statement $ connection.executeUpdateAndGetGeneratedKeys
    }

  def delete
    ( table : String, pk : Iterable[(String, Any)] )
    {
      Delete(table, pk $ where) $ statement $ connection.executeUpdate
    }

  private def where ( pk : Iterable[(String, Any)] )
    = pk.view map (_ $$ (Column(_) -> Value(_)) $$ (Comparison(_, _, Equal) : Condition[WhereObject])) reduceOption (CompositeCondition(_, _, And)) map (Where(_))
}
