

// TODO scala _ 需要理解一下

abstract class A {
  val x: Int
}

class B extends A {

  override val x: Int = 10
}






abstract class Box {

  type In

  def get: In
}

class Cat

class CatBox extends Box {
  override type In = Cat

  override def get = new Cat
}




class Cat1

class Box1[T >: Cat1] {
  def get: T = new Cat1
}

new Box1().get




//////////////

abstract class Reader {

  type S

  val source: S

  def read(): String
}


class StringReader extends Reader {
  override type S = StringBuilder
  override val source = new StringBuilder("hello world")

  override def read() = source.toString()
}



class ByteArrayReader extends Reader {
  override type S = Array[Byte]
  override val source = "hello".getBytes()

  override def read() = new String(source)
}


trait X[S, T, U]

trait Y[S, T, U] extends X[S, T, U]

trait Z[S, U] extends Y[S, String, U]

class XYZ[S] extends Z[S, Int]

new XYZ[Boolean]



trait X1 {
  type S
  type T
  type U
}

trait Y1 extends X1

trait Z1 extends Y1 {
  type T = String
}

class XYZ1 extends Y1 {
  type U = Int
}

class XYZ2[S] extends Y1 {
  type U = Int
}

new XYZ1

new XYZ2[Boolean]



















