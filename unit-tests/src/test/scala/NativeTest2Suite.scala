package example

import utest._
import scala.scalanative.native._

object NativeTest2Suite extends TestSuite {
  val tests = this {
    val f = stackalloc[CFloat]
    !f = 1.0f
    val ff = !f
    val d = 3.0

    'test {
      // ok
      assert(d == 3.0)
      // ok
      assert(ff == 1.0)
      // fails here - Tag[T] required?
      //assert(!f == 1.0)
    }
  }
}
