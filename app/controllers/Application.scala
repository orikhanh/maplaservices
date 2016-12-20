package controllers

import play.api.mvc._
import play.api.libs.json._

class Application extends Controller {

  case class Place(id: Int, name: String)
  var places = List(
    Place(1,"DN1"),
    Place(2,"DN2"),
    Place(3,"DN3"),
    Place(4,"DN4")
  )
  implicit val placesWrites = Json.writes[Place]

  def listPlaces = Action {
    val json = Json.toJson(places)
    Ok(json)
  }
  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

}