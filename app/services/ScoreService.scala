package services

import com.google.inject.Inject
import models.{Scores, ScoresList}
import scala.concurrent.Future

class ScoreService @Inject() (records: ScoresList) {

  def getAll: Future[Seq[Scores]] = {
    records.listAll
  }

  def getRecord(id: Long): Future[Option[Scores]] = {
    records.get(id)
  }

  def addRecord(record: Scores): Future[String] = {
    records.add(record)
  }

  def deleteRecord(id: Long): Future[Int] = {
    records.delete(id)
  }

  def updateRecord(record: Scores): Future[String] = {
    records.add(record)
  }
}
