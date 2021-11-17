class Outer {

  self =>

  def sum(a: Int, b: Int) = a + b

  class Inner {

    class Inner1 {
      def sum(a: Int) = self.sum(a, 0)
    }

  }

}

val outer = new Outer
// 这里区别于java的语法，java中是outer.new Inner
val inner1: outer.Inner = new outer.Inner
val inner11 = new inner1.Inner1

println(inner11.sum(10))


trait IUserDao {
  def validateUser(name: String, passwd: String): Boolean = ???
}

trait IUserService {
  self: IUserDao =>

  def isvalidUser(name: String, passwd: String): Boolean = {
    self.validateUser(name, passwd)
  }

  println("====")
  println(self.isInstanceOf[IUserDao])
  println(self.isInstanceOf[IUserService])
}

val userService = new IUserService with IUserDao {
  override def validateUser(name: String, passwd: String): Boolean = {
    name.equals("wes") && passwd.equals("wes")
  }
}

println(userService.isvalidUser("kk", "wes"))