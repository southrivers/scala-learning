import scala.reflect.runtime.universe._

class A {

  val x = 10
  def print() = println(x)
}

val x: Symbol = typeOf[A].member(TermName("x"))
val print: Symbol = typeOf[A].member(TermName("print"))
println(x.asTerm.isVal)
println(x.asTerm.isVar)
println(print.asMethod.isPublic)
