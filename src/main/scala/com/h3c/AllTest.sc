case class Box[T](data: T) {

}

val box = new Box(100)


println(box.isInstanceOf[Box[Int]])

println("======")
println(box.isInstanceOf[Box[String]])

println(box.isInstanceOf[Box[_]])

List(1, 2, 3).sum


class Outter {

  class Inner {
    def printIn = {
      println("inner is called")
    }
  }

}

val o1 = new Outter
val o2 = new Outter
val i1 = new o1.Inner
val i2 = new o2.Inner

def work(inner: Outter # Inner): Unit = {
  inner.printIn
}

work(i1)