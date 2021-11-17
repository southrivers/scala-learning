

/**
  * TODO scala type 关键字如何理解？
  */
abstract class Computer {

  def memory: String

  def CPU: String

  def disk: String

  override def toString: String = s"memory: $memory, cpu: $CPU, disk: $disk"
}


abstract class ComputerFactory {


  type C <: Computer

  def create: C
}


def create(factory: ComputerFactory): factory.C = factory.create


class DellFactory extends ComputerFactory {

  class DellComputer extends Computer {
    override def memory = "dell memory"

    override def CPU = "dell cpu"

    override def disk = "dell disk"
  }

  override type C = DellComputer

  override def create = new DellComputer
}


