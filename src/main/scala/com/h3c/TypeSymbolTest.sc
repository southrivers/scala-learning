class A {}
import scala.reflect.runtime.universe._
val tpe = typeOf[A]
val TypeRef(pre, sym, List()) = tpe





import scala.reflect.runtime.universe._
val tpe1 = typeOf[List[Seq[Int]]]
val TypeRef(pre1, sym1, List(tp))=tpe1





val tpe2 = typeOf[Map[String, Int]]
val TypeRef(pre2, sym2,List(k, v)) = tpe2



case class Person(name: String, age: Int, salary: Int) {
  def raiseSalary(salary: Int) = this.salary + salary
}

val loader = classOf[Person].getClassLoader
val mirror: Mirror = runtimeMirror(loader)

// 对Class反射获取ClassMirror
val classSymbol = typeOf[Person].typeSymbol.asClass
val cm: ClassMirror = mirror.reflectClass(classSymbol)

// 根据ClassMirror获取构造函数Mirror，构造函数Mirror是一个MethodMirror
// 不过这个MethodMirror是与类相关的
val cstSymbol = typeOf[Person].decl(termNames.CONSTRUCTOR).asMethod
val ctrom: MethodMirror = cm.reflectConstructor(cstSymbol)


// 使用构造方法Mirror创建对象
val p = ctrom("wes", 12, 1000).asInstanceOf[Person]

// 对对象进行反射，得到InstanceMirror
val im: InstanceMirror = mirror.reflect(p)

// 反射调用函数
val methodRaiseSalary = typeOf[Person].decl(TermName("raiseSalary")).asMethod
val rm = im.reflectMethod(methodRaiseSalary)

val salary = rm.apply(100)

println(salary)

// 反射获取对象的成员对象，可以反射调用
val fieldSalary = typeOf[Person].decl(TermName("salary")).asTerm
val fm: FieldMirror = im.reflectField(fieldSalary)

println(fm.get)
fm.set(100)
println(fm.get)