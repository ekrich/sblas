package org.ekrich.blas
package api

/** C Blas API
 *
 *  Higher level Scala to call the underlying C API.
 */
object Blas {
  import scala.scalanative.unsafe._

  def dnrm2(vector: Array[Double]): Double =
    unsafe.blas.cblas_dnrm2(vector.length, vector.at(0), 1)

  def dnrm2(vector: Array[Double], stride: Int): Double =
    unsafe.blas.cblas_dnrm2(vector.length, vector.at(0), stride)

  def dnrm2(vector: Array[Double], stride: Int, n: Int): Double =
    unsafe.blas.cblas_dnrm2(n, vector.at(0), stride)
}
