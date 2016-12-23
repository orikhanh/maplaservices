package services

import domains.User
import repositories.UserRepositoryImpl

/**
  * Created by tttien on 12/18/2016.
  */

trait UserService {
  def addUser(userEmail: String, password: String): User
  def delete(userEmail: String, password: String)
  def findAll(userEmail: String): List[User]
  def checkExits(userEmail: String, password: String): Option[User]
}

class UserServiceImp extends UserService{
  var userRepository = new UserRepositoryImpl()
  override def addUser(userEmail: String, password: String): User = {
    val user = User(userEmail, password)
    userRepository.create(user)
    user
  }

  override def delete(userEmail: String, password: String) =
    userRepository.delete(User(userEmail, password))

  override def findAll(userEmail: String): List[User] =
    userRepository.findAll(userEmail)

  override def checkExits(userEmail: String, password: String): Option[User] = {
    val user = User(userEmail, password)

    if (userRepository.checkExits(user))
      Some(user)
    else
      None
  }
}
