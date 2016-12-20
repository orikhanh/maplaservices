package controllers

import models.User
import play.api.mvc._
import play.api.libs.json.Json
/**
  * Created by tttien on 12/18/2016.
  */

class User extends Controller {
  def add(userEmail: String, password: String) = Action {
    // TODO: Add favourite
    val favourite = User.addUser(userEmail, password)
    Ok(Json.obj("result" -> favourite))
    //Ok(Json.obj("result"->user(userEmail,password)))
  }
  def remove(userEmail: String, password: String) = Action {
    User.delete(userEmail, password)

    Ok(Json.obj("result" -> Json.obj()))
  }

  def find(userEmail: String, password: String) = Action {
    val oFavourite = User.find(userEmail, password)

    oFavourite match {
      case None => NotFound(Json.obj("error" -> "NOT_FOUND"))
      case Some(favourite) => Ok(Json.obj("result" -> favourite))
    }
  }

  def findAll(userEmail: String) = Action {
    val allFavourites = User.findAllByUser(userEmail)

    Ok(Json.obj("result" -> allFavourites))
  }

}

