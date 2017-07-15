package org.ekrich.ml
package blas

import scalanative.native._
import Nat._
import blas._
import blasOps._

object BlasTest extends App {
  println("Starting blas test...")

  val N = 3
  val alpha = 0
  val incX = 1
  val incY = 1

  {
    val X = stackalloc[CFloat](3)
    X(0) = 1
    X(1) = 3
    X(2) = -5
    val Y = stackalloc[CFloat](3)
    Y(0) = 4
    Y(1) = -2
    Y(2) = -1

    val res = cblas_sdsdot(N, alpha, X,
      incX, Y, incY)
    println(s"cblas_sdsdot: $res")

    val res1 = cblas_dsdot(N, X,
      incX, Y, incY)
    println(s"cblas_dsdot: $res1")

    val res2 = cblas_sdot(N, X,
      incX, Y, incY)
    println(s"cblas_sdot: $res2")
  }
  {
    val X = stackalloc[CDouble](3)
    X(0) = 1
    X(1) = 3
    X(2) = -5
    val Y = stackalloc[CDouble](3)
    Y(0) = 4
    Y(1) = -2
    Y(2) = -1
    def foo {
      val res3 = cblas_ddot(N, X,
        incX, Y, incY)
      println(s"cblas_ddot: $res3")
    }
    foo
  }

  val f = stackalloc[CFloat]
  !f = 1.0f

  def foo {
    // ok
    println(s"f = ${!f}")
    assert(!f == 1.0)
  }
  foo

  println("Done.")
}
