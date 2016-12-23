package repositories

import anorm._
import play.api.db.{DB}
import domains.User
import play.api.Play.current

/**
  * Created by tttien on 12/18/2016.
  */

trait UserRepository{

  def create(user: User)

  def delete(user: User)

  def checkExits(user: User): Boolean

  def findAll(email: String):List[User]

}

class UserRepositoryImpl extends UserRepository{
  override def create(user: User) =
    DB.withConnection { implicit c =>
      SQL(
        """
          | INSERT IGNORE INTO `user` (`user_email`, `password`)
          | VALUES
          |   ({user_email}, {password});
        """.stripMargin).on(
        "user_email" -> user.userEmail,
        "password" -> user.password
      ).executeInsert()
    }

  override def delete(user: User) = {
    DB.withConnection { implicit c =>
      SQL(
        """
          | DELETE FROM `user`
          | WHERE `user_email`={user_email} AND `password`={password}
          | LIMIT 1;
        """.stripMargin).on(
        "user_email" -> user.userEmail,
        "password" -> user.password
      ).executeUpdate()
    }
  }

  override def checkExits(user: User): Boolean = {
    DB.withConnection { implicit c =>
      val result = SQL(
        """
          | SELECT COUNT(*) as numMatches
          | FROM `user`
          | WHERE `user_email`={user_email} AND `password`={password};
        """.stripMargin).on(
        "user_email" -> user.userEmail,
        "password" -> user.password
      ).apply().head

      result[Int]("numMatches") != 0
    }
  }

  override def findAll(userEmail: String): List[User] = {
    DB.withConnection { implicit c =>
      val results = SQL(
        """
          | SELECT `user_email`, `password`
          | FROM `user`
          | WHERE `user_email`={user_email};
        """.stripMargin).on(
        "user_email" -> userEmail
      ).apply()

      results.map { row =>
        User(row[String]("user_email"), row[String]("password"))
      }.force.toList
    }
  }
}
