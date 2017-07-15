package example

import scala.scalanative.native._

object ClosureTest extends App {
  val f = stackalloc[CFloat]
  !f = 1.0f

  def foo {
    // ok
    println(s"f = ${!f}")
    assert(!f == 1.0)
  }
  foo
}
