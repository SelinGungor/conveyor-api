package controllers.api
import models.{Scores, ScoresForm}
import play.api.libs.json._
import play.api.mvc._
import scala.concurrent.ExecutionContext.Implicits.global
import javax.inject._
import services.ScoreService
import scala.concurrent.Future

class ScoreController @Inject() (
    cc: ControllerComponents,
    scoreService: ScoreService
) extends AbstractController(cc) {
  implicit val scoreFormat = Json.format[Scores]

  def getAll() = Action.async { implicit request: Request[AnyContent] =>
    scoreService.getAll map { records =>
      Ok(Json.toJson(records))
    }
  }

  def getRecord(id: Long) = Action.async {
    implicit request: Request[AnyContent] =>
      scoreService.getRecord(id) map { record =>
        Ok(Json.toJson(record))
      }
  }

  def addRecord() = Action.async { implicit request: Request[AnyContent] =>
    ScoresForm.form.bindFromRequest.fold(
      errorForm => {
        errorForm.errors.foreach(println)
        Future.successful(BadRequest("Error!"))
      },
      data => {
        val newRecord = Scores(0, data.name, data.scores)
        scoreService
          .addRecord(newRecord)
          .map(_ => Redirect(routes.ScoreController.getAll))
      }
    )
  }

  def delete(id: Long) = Action.async { implicit request: Request[AnyContent] =>
    scoreService.deleteRecord(id) map { res =>
      Redirect(routes.ScoreController.getAll)
    }
  }

}
