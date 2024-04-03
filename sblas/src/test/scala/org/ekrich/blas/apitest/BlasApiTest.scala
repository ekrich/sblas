package org.ekrich.blas
package apitest

import scala.collection.mutable.ListBuffer
import scala.scalanative.libc.complex.{CDoubleComplex, CFloatComplex}
import scala.scalanative.libc.complexOps._
import scala.scalanative.unsafe.{CDouble, CFloat, Ptr, Zone, alloc}

import org.ekrich.blas.unsafe.blas._
import org.ekrich.blas.unsafe.blasEnums._

import org.junit.Test
import org.junit.Assert._
import org.ekrich.blas.api.Blas

class BlasApiTest {

  @Test def test_blas_dnrm2(): Unit = {
    val x = Array[Double](1, 2, -2)
    val res = Blas.dnrm2(x)
    assertEquals(res, 3.0, 0.0)
  }

  // Level 3 Tests

  /*
   [ 0.11 0.12 0.13 ]  [ 1011 1012 ]     [ 367.76 368.12 ]
   [ 0.21 0.22 0.23 ]  [ 1021 1022 ]  =  [ 674.06 674.72 ]
                       [ 1031 1032 ]
   */
  @Test def test_cblas_sgemm(): Unit = {
    // here for implicit at(0)
    // until function is added to API
    import scala.scalanative.unsafe._
    val lda = 3

    val A = Array(
      0.11f, 0.12f, 0.13f,
      0.21f, 0.22f, 0.23f)

    val ldb = 2

    val B = Array(
      1011f, 1012f,
      1021f, 1022f,
      1031f, 1032f)

    val ldc = 2

    val C = Array(
      0.00f, 0.00f,
      0.00f, 0.00f)

    /* Compute C = A B */

    cblas_sgemm(
      CblasRowMajor,
      CblasNoTrans, CblasNoTrans, 2, 2, 3,
      1.0f, A.at(0), lda, B.at(0), ldb, 0.0f, C.at(0), ldc)

    printf("[ %g, %g\n", C(0), C(1))
    printf("  %g, %g ]\n", C(2), C(3))
    assertArrayEquals(Array(367.760f, 368.120f, 674.060f, 674.720f), C, 0.01f)
  }
}
