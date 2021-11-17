



def print[T <: Product](data: T): Unit = {
  println(data.productIterator.mkString(" "))
}

case class Person(name: String, age: Int)

print(Person("wes", 10))

print((1,2))



case class Person1(name: String, age: Int) extends Ordered[Person1]{
  override def compare(that: Person1) = age - that.age
}

def max[T <: Ordered[T]](t1: T, t2: T) = {
  if (t1 > t2) t1 else t2
}

max(Person1("wes", 12), Person1("kk", 10))

Option