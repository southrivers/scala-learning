


class MyOption[+A](val x: A) {

//  def getOrElse0(default: => A): A = ???

  def getOrElse1[B >: A](default: => B): B = ???

//  def getOrElse2[B <: A](default: => B): A = ???
}

//List[T] forSome {type T}