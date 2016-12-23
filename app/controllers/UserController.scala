package controllers

import domains.User
import play.api.mvc._
import play.api.libs.json.{Json, Writes}
import services.{UserService, UserServiceImp}
/**
  * Created by tttien on 12/18/2016.
  */

class UserController extends Controller {

  var userService = new UserServiceImp()
  implicit val userWrites: Writes[User] = new Writes[User] {
    def writes(user: User) = Json.obj(
      "userEmail" -> user.userEmail,
      "password" -> user.password
    )
  }

  def add(userEmail: String, password: String) = Action {
    userService.addUser(userEmail, password)
    var user = User(userEmail,password)
    Ok(Json.toJson(user))
    //Ok(Json.obj("result"->user(userEmail,password)))
  }
  def remove(userEmail: String, password: String) = Action {
    userService.delete(userEmail, password)

    Ok(Json.obj("success" -> true))
  }

  def find(userEmail: String, password: String) = Action {
    val oUser = userService.checkExits(userEmail, password)

    oUser match {
      case None => NotFound(Json.obj("error" -> "NOT_FOUND"))
      case Some(user) => Ok(Json.toJson(user))
    }
  }

  def findAll(userEmail: String) = Action {
    val allUser = userService.findAll(userEmail)

    Ok(Json.toJson(allUser))
  }

}

