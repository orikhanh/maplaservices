package controllers

import play.api.mvc._

class Application extends Controller {

  def index = Action {
    Ok("Welcome to mapla service")
  }

}