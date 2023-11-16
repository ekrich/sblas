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

  /** L2 norm (Euclidian length) of a vector (double-precision).
   *
   *  @param X
   *    Vector X.
   *  @return
   *    See description above.
   */
  def dnrm2(X: Array[Double]): Double = dnrm2(X, incX = 1)

  /** L2 norm (Euclidian length) of a vector (double-precision).
   *
   *  @param X
   *    Vector X.
   *  @param incX
   *    Stride within X.
   *  @return
   *    See description above.
   */
  def dnrm2(X: Array[Double], incX: Int): Double =
    dnrm2(X, incX, X.length)

  /** @param X
   *  @param incX
   *  @param N
   *  @return
   */
  def dnrm2(X: Array[Double], incX: Int, N: Int): Double =
    unsafe.blas.cblas_dnrm2(N, X.at(0), incX)

  /** Dot product of two single-precision vectors plus an initial
   *  single-precision value.
   *
   *  @param N
   *    The number of elements in the vectors.
   *  @param alpha
   *    The initial value to add to the dot product.
   *  @param X
   *    Vector X.
   *  @param incX
   *    Stride within X. For example, if incX is 7, every 7th element is used.
   *  @param Y
   *    Vector Y.
   *  @param incY
   *    Stride within Y. For example, if incY is 7, every 7th element is used.
   *  @return
   *    See description above.
   */
  def sdsdot(
      N: Int,
      alpha: Float,
      X: Array[Float],
      incX: CInt,
      Y: Array[Float],
      incY: Int
  ): Float =
    unsafe.blas.cblas_sdsdot(N, alpha, X.at(0), incX, Y.at(0), incY = incY)
}
