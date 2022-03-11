package models

import play.api.data.Form
import play.api.data.Forms.mapping
import play.api.data.Forms._
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile
import slick.jdbc.MySQLProfile.api._
import scala.concurrent.{ExecutionContext, Future}
import com.google.inject.Inject

case class Scores (id: Long, name: String, scores: String)

case class ScoresFormData(name: String, scores: String)

object ScoresForm {
  val form = Form(
    mapping(
      "name" -> nonEmptyText,
      "scores" -> nonEmptyText
    )(ScoresFormData.apply)(ScoresFormData.unapply)
  )
}

class ScoresTableDef(tag: Tag) extends Table[Scores](tag, "scores") {

  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")
  def scores = column[String]("scores")

  override def * = (id, name, scores) <> (Scores.tupled, Scores.unapply)
}

class ScoresList @Inject()(
                          protected val dbConfigProvider: DatabaseConfigProvider
                        )(implicit executionContext: ExecutionContext)
  extends HasDatabaseConfigProvider[JdbcProfile] {

  var scoresList = TableQuery[ScoresTableDef]

  def get(id: Long): Future[Option[Scores]] = {
    dbConfig.db.run(scoresList.filter(_.id === id).result.headOption)
  }

  def listAll: Future[Seq[Scores]] = {
    dbConfig.db.run(scoresList.result)
  }

  def add(record: Scores): Future[String] = {
    dbConfig.db
      .run(scoresList += record)
      .map(score => "Score successfully added")
      .recover {
        case ex: Exception => {
          printf(ex.getMessage())
          ex.getMessage
        }
      }
  }

  def delete(id: Long): Future[Int] = {
    dbConfig.db.run(scoresList.filter(_.id === id).delete)
  }

  def update(record: Scores): Future[Int] = {
    dbConfig.db
      .run(scoresList.filter(_.id === record.id)
        .map(x => (x.name, x.scores))
        .update(record.name, record.scores)
      )
  }
}