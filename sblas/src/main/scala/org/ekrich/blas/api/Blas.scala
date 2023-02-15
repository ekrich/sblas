package org.ekrich.blas
package api

/** C Blas API
 *
 *  Higher level Scala to call the underlying C API. The input Array gets
 *  directly past to the C function so there is no copying.
 *
 *  Adding optimizer options during the build will allow for function inlining
 *  `mode = releaseFast or release` and `lto = thin or full` for link time
 *  optimization. Refer to the docs for `sbt` settings: [Scala
 *  Native](https://scala-native.org/en/stable/user/sbt.html#sbt-settings-and-tasks).
 */
object Blas {
  import scala.scalanative.unsafe._

  def dnrm2(vector: Array[Double]): Double = dnrm2(vector, stride = 1)

  def dnrm2(vector: Array[Double], stride: Int): Double =
    dnrm2(vector, stride, vector.length)

  def dnrm2(vector: Array[Double], stride: Int, n: Int): Double =
    unsafe.blas.cblas_dnrm2(n, vector.at(0), stride)
}
