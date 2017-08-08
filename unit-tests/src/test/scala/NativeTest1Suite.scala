package example

import utest._
import scala.scalanative.native._

object NativeTest1Suite extends TestSuite {
  def tests = this {
    //Zone { implicit z =>
    // do stuff in Zone here
    'test {
      // ok for Zone here
      Zone { implicit z =>
        val f = alloc[CFloat]
        !f = 1.0f
        assert(!f == 1.0)
      }
    }
    //}
  }
}
