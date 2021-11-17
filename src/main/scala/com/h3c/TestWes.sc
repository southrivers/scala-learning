import scala.reflect.runtime.universe._

import scala.reflect.ClassTag

class Box[T: ClassTag](val t: T) {

  val ct = implicitly[ClassTag[T]]

  def impPrint() = println(ct)
}


val box = new Box(100)

val boxes = Seq(new Box(List(122)), new Box(Map(1 -> "test")))

boxes.foreach(_.impPrint())


class Box1[T: TypeTag](val data: T) {
  //直接获取type
  val t = typeOf[T]
  val t1 = implicitly[TypeTag[T]]

  def pp = {
    println(t + "====" + t1)
  }
}

val b1 = new Box1(Seq(12))

b1.pp




def max1[T](a: T, b: T)(implicit cmp: Ordering[T]): T = {

  if (cmp.gt(a, b)) a else b
}

case class P(val name: String, val age: Int)

val p1 = P("wes", 12)
val p2 = P("kk", 13)

implicit val cc = new Ordering[P] {
  override def compare(x: P, y: P) = if (x.age > y.age) 1 else -1
}

max1(p1, p2)

def max2[T: Ordering](a: T, b: T): Boolean = {
  val cmp = implicitly[Ordering[T]]
  cmp.gt(a, b)
}


max2(16, 13)


def max3[T <% Ordered[T]](a: T, b: T): T = {

  if (a > b) a else b
}


max3(123, 343)

case class Tn(name: String, age: Int)

val t1 = Tn("wes", 12)
val t2 = Tn("kk", 78)



implicit val a = (t: Tn) => new Ordered[Tn]{
  override def compare(that: Tn) = t.age - that.age
}


max3(t1, t2)















