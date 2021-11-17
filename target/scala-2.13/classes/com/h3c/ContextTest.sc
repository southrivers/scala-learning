import scala.concurrent.{ExecutionContext, Future}


// Future中的方法声明

def apply[T](body: => T)(implicit executor: ExecutionContext): Future[T] = ???

def foreach[U, T](f: T)(implicit executor: ExecutionContext): Unit = ???


def max[A: Ordering](a1: A, a2: A): A = {
  val cmp = implicitly[Ordering[A]]
  if (cmp.gt(a1, a2)) a1 else a2
}

max(12, 13)



def max1[T <% Ordered[T]](a: T, b: T) = {
  if (a > b) a else b
}

max1(122, 24)