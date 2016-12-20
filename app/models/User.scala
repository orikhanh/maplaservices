package models

import models.dao.UserDAO
import play.api.libs.json._
import play.api.libs.functional.syntax._

/**
  * Created by tttien on 12/18/2016.
  */

object User {
  //implicit val favouriteStudioWrites = Json.writes[FavouriteStudio]
  implicit val userWrites: Writes[User] = (
    (JsPath \ "userEmail").write[String] and
      (JsPath \ "password").write[String]
    )(unlift(User.unapply))

  def addUser(userEmail: String, password: String): User = {
    val user = User(userEmail, password)
    UserDAO.create(user)
    user
  }

  def delete(userEmail: String, password: String) =
    UserDAO.delete(User(userEmail, password))

  def findAllByUser(userEmail: String): List[User] =
    UserDAO.index(userEmail)

  def find(userEmail: String, password: String): Option[User] = {
    val user = User(userEmail, password)

    if (UserDAO.exists(user))
      Some(user)
    else
      None
  }
}
case class User(userEmail: String, password: String)