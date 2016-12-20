package controllers

import models.User
import play.api.mvc._
import play.api.libs.json.Json
/**
  * Created by tttien on 12/18/2016.
  */

class User extends Controller {
  def add(userEmail: String, password: String) = Action {
    val user = User.addUser(userEmail, password)
    Ok(Json.obj("result" -> user))
    //Ok(Json.obj("result"->user(userEmail,password)))
  }
  def remove(userEmail: String, password: String) = Action {
    User.delete(userEmail, password)

    Ok(Json.obj("result" -> Json.obj()))
  }

  def find(userEmail: String, password: String) = Action {
    val oUser = User.find(userEmail, password)

    oUser match {
      case None => NotFound(Json.obj("error" -> "NOT_FOUND"))
      case Some(user) => Ok(Json.obj("result" -> user))
    }
  }

  def findAll(userEmail: String) = Action {
    val allUser = User.findAllByUser(userEmail)

    Ok(Json.obj("result" -> allUser))
  }

}

