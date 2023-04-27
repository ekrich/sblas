package org.ekrich.blas
package unsafe

import scala.collection.mutable.ListBuffer
import scala.scalanative.libc.complex.{CDoubleComplex, CFloatComplex}
import scala.scalanative.libc.complexOps._
import scala.scalanative.unsafe.{CDouble, CFloat, Ptr, Zone, alloc}

import org.ekrich.blas.unsafe.blas._

import org.junit.Test
import org.junit.Assert._
import org.ekrich.blas.api.Blas

class BlasTest {
  val N = 3
  val alpha = 0.0f
  val incX = 1
  val incY = 1

  def FloatArray(elems: Float*)(implicit z: Zone): Ptr[CFloat] = {
    val size = elems.size
    val ptr = alloc[CFloat](size)
    for (i <- 0 until size) ptr(i) = elems(i)
    ptr
  }

  def DoubleArray(elems: Double*)(implicit z: Zone): Ptr[CDouble] = {
    val size = elems.size
    val ptr = alloc[CDouble](size)
    for (i <- 0 until size) ptr(i) = elems(i)
    ptr
  }

  def FloatComplexArray(
      elems: (Float, Float)*
  )(implicit z: Zone): Ptr[CFloatComplex] = {
    val size = elems.size
    val ptr = alloc[CFloatComplex](size)
    var i = 0
    while (i < size) {
      val c = ptr + i
      c.re = elems(i)._1
      c.im = elems(i)._2
      i += 1
    }
    ptr
  }

  def printFloatComplex(ptr: Ptr[CFloatComplex], size: Int): Unit = {
    var i = 0
    val lb = new ListBuffer[String]()
    while (i < size) {
      val c = ptr + i
      lb += s"{${c.re}, ${c.im}}"
      i += 1
    }
    println(lb.mkString("{", ", ", "}"))
  }

  def DoubleComplexArray(
      elems: (Double, Double)*
  )(implicit z: Zone): Ptr[CDoubleComplex] = {
    val size = elems.size
    val ptr = alloc[CDoubleComplex](size)
    var i = 0
    while (i < size) {
      val c = ptr + i
      c.re = elems(i)._1
      c.im = elems(i)._2
      i += 1
    }
    ptr
  }

  def X(implicit z: Zone): Ptr[Float] = FloatArray(1, 3, -5)

  def Y(implicit z: Zone): Ptr[Float] = FloatArray(4, -2, -1)

  def Xd(implicit z: Zone): Ptr[Double] = DoubleArray(1, 3, -5)

  def Yd(implicit z: Zone): Ptr[Double] = DoubleArray(4, -2, -1)

  val zfc = (0.0f, 0.0f)

  def dotufc(implicit z: Zone): Ptr[CFloatComplex] = FloatComplexArray(zfc)

  def Xfc(implicit z: Zone): Ptr[CFloatComplex] =
    FloatComplexArray((1.0f, 1.0f), (1.0f, 1.0f), (1.0f, 1.0f))

  def Yfc(implicit z: Zone): Ptr[CFloatComplex] =
    FloatComplexArray((0.0f, 1.0f), (1.0f, 0.0f), (0.0f, 1.0f))

  val zdc = (0.0, 0.0)

  def dotudc(implicit z: Zone): Ptr[CDoubleComplex] = DoubleComplexArray(zdc)

  def Xdc(implicit z: Zone): Ptr[CDoubleComplex] =
    DoubleComplexArray((1.0, 1.0), (1.0, 1.0), (1.0, 1.0))

  def Ydc(implicit z: Zone): Ptr[CDoubleComplex] =
    DoubleComplexArray((0.0, 1.0), (1.0, 0.0), (0.0, 1.0))

  @Test def test_cblas_sdsdot(): Unit = {
    Zone { implicit z =>
      val res = cblas_sdsdot(N, alpha, X, incX, Y, incY)
      assertEquals(res, 3.0, 0.0)
    }
  }

  @Test def test_cblas_dsdot(): Unit = {
    Zone { implicit z =>
      val res = cblas_dsdot(N, X, incX, Y, incY)
      assertEquals(res, 3.0, 0.0)
    }
  }

  @Test def test_cblas_sdot(): Unit = {
    Zone { implicit z =>
      val res = cblas_sdot(N, X, incX, Y, incY)
      assertEquals(res, 3.0, 0.0)
    }
  }

  @Test def test_cblas_ddot(): Unit = {
    Zone { implicit z =>
      val res = cblas_ddot(N, Xd, incX, Yd, incY)
      assertEquals(res, 3.0, 0.0)
    }
  }

  @Test def test_cblas_cdotu_sub(): Unit = {
    Zone { implicit z =>
      val dotu = dotufc
      cblas_cdotu_sub(N, Xfc, incX, Yfc, incY, dotu)
      // printFloatComplex(dotu, 1)
      assertEquals(dotu._1, -1.0, 0.0)
      assertEquals(dotu._2, 3.0, 0.0)
    }
  }

  @Test def test_cblas_cdotc_sub(): Unit = {
    Zone { implicit z =>
      val dotc = dotufc
      cblas_cdotc_sub(N, Xfc, incX, Yfc, incY, dotc)
      // printFloatComplex(dotc, 1)
      assertEquals(dotc._1, 3.0, 0.0)
      assertEquals(dotc._2, 1.0, 0.0)
    }
  }

  @Test def test_cblas_zdotu_sub(): Unit = {
    Zone { implicit z =>
      val dotu = dotudc
      cblas_zdotu_sub(N, Xdc, incX, Ydc, incY, dotu)
      assertEquals(dotu._1, -1.0, 0.0)
      assertEquals(dotu._2, 3.0, 0.0)
    }
  }

  @Test def test_cblas_zdotc_sub(): Unit = {
    Zone { implicit z =>
      val dotc = dotudc
      cblas_zdotc_sub(N, Xdc, incX, Ydc, incY, dotc)
      assertEquals(dotc._1, 3.0, 0.0)
      assertEquals(dotc._2, 1.0, 0.0)
    }
  }

  @Test def test_cblas_snrm2(): Unit = {
    Zone { implicit z =>
      val X = FloatArray(1, 2, -2)
      val res = cblas_snrm2(N, X, incX)
      assertEquals(res, 3.0, 0.0)
    }
  }

  @Test def test_cblas_sasum(): Unit = {
    Zone { implicit z =>
      val X = FloatArray(1, 2, -2)
      val res = cblas_sasum(N, X, incX)
      assertEquals(res, 5.0, 0.0)
    }
  }

  @Test def test_cblas_dnrm2(): Unit = {
    Zone { implicit z =>
      val X = DoubleArray(1, 2, -2)
      val res = cblas_dnrm2(N, X, incX)
      assertEquals(res, 3.0, 0.0)
    }
  }

  @Test def test_cblas_dasum(): Unit = {
    Zone { implicit z =>
      val X = DoubleArray(1, 2, -2)
      val res = cblas_dasum(N, X, incX)
      assertEquals(res, 5.0, 0.0)
    }
  }

  @Test def test_cblas_scnrm2(): Unit = {
    Zone { implicit z =>
      val X = FloatComplexArray((1, 0), (0, -2), (2, 0))
      val res = cblas_scnrm2(N, X, incX)
      assertEquals(res, 3.0, 0.0)
    }
  }

  @Test def test_cblas_scasum(): Unit = {
    Zone { implicit z =>
      val X = FloatComplexArray((1, 0), (0, -2), (2, 0))
      val res = cblas_scasum(N, X, incX)
      assertEquals(res, 5.0, 0.0)
    }
  }

  @Test def test_cblas_dznrm2(): Unit = {
    Zone { implicit z =>
      val X = DoubleComplexArray((1, 0), (0, -2), (2, 0))
      val res = cblas_dznrm2(N, X, incX)
      assertEquals(res, 3.0, 0.0)
    }
  }

  @Test def test_cblas_dzasum(): Unit = {
    Zone { implicit z =>
      val X = DoubleComplexArray((1, 0), (1, -2), (2, 0))
      val res = cblas_dzasum(N, X, incX)
      assertEquals(res, 6.0, 0.0)
    }
  }

  @Test def test_cblas_isamax(): Unit = {
    Zone { implicit z =>
      val res = cblas_isamax(N, X, incX)
      assertEquals(res, 2)
    }
  }

  @Test def test_cblas_idamax(): Unit = {
    Zone { implicit z =>
      val res = cblas_idamax(N, Xd, incX)
      assertEquals(res, 2)
    }
  }

  @Test def test_cblas_icamax(): Unit = {
    Zone { implicit z =>
      val X = FloatComplexArray((1.0f, 0.0f), (1.0f, 1.0f), (3.0f, -2.0f))
      val res = cblas_icamax(N, X, incX)
      assertEquals(res, 2)
    }
  }

  @Test def test_cblas_izamax(): Unit = {
    Zone { implicit z =>
      val X = DoubleComplexArray((1.0, 2.0), (2.0, 2.0), (3.0, 0.0))
      val res = cblas_izamax(N, X, incX)
      assertEquals(res, 1)
    }
  }
}
