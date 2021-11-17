import scala.reflect.ClassTag
import scala.reflect.runtime.universe._

/**
  * 验证scala中类型擦除的问题，并且ClassTag和TypeTag是可以保存一阶及高阶类型的验证
  *
  * @param data
  * @tparam T
  */
case class Box[T](data: T)

val b = new Box(10)

println(b.isInstanceOf[Box[Int]])

println(b.isInstanceOf[Box[String]])

println(b.isInstanceOf[Box[_]])

/**
  * typeTag
  * @param data
  * @param `typeTag$T`
  * @tparam T
  */

class Box1[T: TypeTag](val data:T) {

  def printTypeOfparameter() = {
    typeOf[T] match {
      case t if t =:= typeOf[Int] => println("int")
      case t if t =:= typeOf[String] => println("string")
      case t if t =:= typeOf[Seq[Int]] => println("seq[int]")
      case _ => println("unknown")
    }
  }
}

val boxes = Seq(new Box1("33"), new Box1(12), new Box1(Seq(20)))

boxes.foreach(_.printTypeOfparameter())

/**
  * classTag
  * @param data
  * @param `classTag$T`
  * @tparam T
  */

class Box2[T](val data:T) {

  def printTypeOfparameter() = {
    // TODO 获取参数的运行时类型
    /**
      *
      * A `ClassTag[T]` stores the erased class of a given type `T`, accessible via the `runtimeClass`
      * field. This is particularly useful for instantiating `Array`s whose element types are unknown
      * at compile time.
      *
      * `ClassTag`s are a weaker special case of [[scala.reflect.api.TypeTags.TypeTag]]s, in that they
      * wrap only the runtime class of a given type, whereas a `TypeTag` contains all static type
      * information. That is, `ClassTag`s are constructed from knowing only the top-level class of a
      * type, without necessarily knowing all of its argument types. This runtime information is enough
      * for runtime `Array` creation.
      *
      * For example:
      * {{{
      *   scala> def mkArray[T : ClassTag](elems: T*) = Array[T](elems: _*)
      *   mkArray: [T](elems: T*)(implicit evidence\$1: scala.reflect.ClassTag[T])Array[T]
      *
      *   scala> mkArray(42, 13)
      *   res0: Array[Int] = Array(42, 13)
      *
      *   scala> mkArray("Japan","Brazil","Germany")
      *   res1: Array[String] = Array(Japan, Brazil, Germany)
      * }}}
      *
      * See [[scala.reflect.api.TypeTags]] for more examples, or the
      * [[http://docs.scala-lang.org/overviews/reflection/typetags-manifests.html Reflection Guide: TypeTags]]
      * for more details.
      *
      */
    val tag = implicitly[ClassTag[T]].runtimeClass
    tag match {
      case t if t == classOf[Int] => println("int")
      case t if t == classOf[String] => println("string")
      case t if t == classOf[Seq[Int]] => println("seq[int]")
      case _ => println("unknown")
    }
  }
}

val boxes2 = Seq(new Box2("33"), new Box2(12), new Box2(Seq("hello")))

boxes2.foreach(_.printTypeOfparameter())
