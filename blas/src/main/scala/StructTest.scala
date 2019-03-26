import scalanative.native._

object StructTest extends App {
  println("Starting struct test...")

//  def printMatrix[T, N <: Nat](rows: Ptr[CArray[T, N]]*)(implicit tag: Tag[T]/*, field: CField1[CArray[T, N], T]*/): Unit = {
//    //{//(implicit tag:  Tag[T]): Unit = {
//      //(implicit tag: Tag[CArray[T, N]], field: CField1[CArray[T, N], T]): Unit = {
//    val l = List()
//    var i = 0
//    for(row <- rows) {
//      val v = !(row._1 + i)
//      l.::(v)
//      i += 1
//      //println(s"${fp + 0}, ${fp + 1}, ${fp + 2}")
//    }
//    val str = l.mkString("{ ", ", ", " }")
//    println(str)
//  }

  type Vec = CStruct3[Float, Float, Float]

  val mat: Ptr[Vec] = stackalloc[Vec](3) // allocate c struct on stack

  val r1 = mat + 0
  val r2: Ptr[Vec] = mat + 1
  val r3 = mat + 2
  !(r1._1 + 0) = 1.0f
  !r1._2 = 3.0f
  !r1._3 = 5.0f
  !r2._1 = 0.0f
  !r2._2 = 1.0f
  !r2._3 = 0.0f
  !r3._1 = 3.0f
  !r3._2 = 5.0f
  !r3._3 = 1.0f

  val r11 = !r1._1

  stdio.printf(c"Mat: %p\n", mat)
  stdio.printf(c"Row1: %p %f, %f, %f\n", r1, r11, r1(1), r1(2))
  stdio.printf(c"Row2: %p\n", r2)
  stdio.printf(c"Row3: %p\n", r3)

  println(s"r1: ${!r1._1}, ${!r1._2}, ${!r1._3}")
  println(s"r2: ${!r2._1}, ${!r2._2}, ${!r2._3}")
  println(s"r3: ${!r3._1}, ${!r3._2}, ${!r3._3}")

  //val v1: Ptr[Vec] = mat(0)
  //println(s"v1: ${v1}")

  //printMatrix[CFloat, Nat._3](r1, r2, r3)(tag, field)

  println("Done.")
}
