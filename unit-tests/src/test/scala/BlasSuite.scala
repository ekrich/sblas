package org.ekrich.ml
package blas

import scala.scalanative.native._
import utest._
import blas._
import blasOps._

import scala.collection.mutable.ListBuffer

object BlasSuite extends TestSuite {

  val tests = this {

    val N = 3
    val alpha = 0
    val incX = 1
    val incY = 1

    def FloatArray(elems: Float*)(implicit z: Zone): Ptr[CFloat] = {
      val size = elems.size
      val ptr = alloc[CFloat](size)
      for (i <-  0 until size) ptr(i) = elems(i)
      ptr
    }

    def DoubleArray(elems: Double*)(implicit z: Zone): Ptr[CDouble] = {
      val size = elems.size
      val ptr = alloc[CDouble](size)
      for (i <-  0 until size) ptr(i) = elems(i)
      ptr
    }

    def FloatComplexArray(elems: (Float, Float)*)
                         (implicit z: Zone): Ptr[CFloatComplex] = {
      val size = elems.size
      val ptr = alloc[CFloatComplex](size)
      var i = 0
      while (i < size) {
        val e = ptr + i
        !e._1 = elems(i)._1
        !e._2 = elems(i)._2
        i += 1
      }
      ptr
    }

    def printFloatComplex(ptr: Ptr[CFloatComplex], size: Int): Unit = {
      var i = 0
      val lb = new ListBuffer[String]()
      while (i < size) {
        val e = ptr + i
        lb += s"{${!e._1}, ${!e._2}}"
        i += 1
      }
      println(lb.mkString("{", ", ", "}"))
    }

    def DoubleComplexArray(elems: (Double, Double)*)
                         (implicit z: Zone): Ptr[CDoubleComplex] = {
      val size = elems.size
      val ptr = alloc[CDoubleComplex](size)
      var i = 0
      while (i < size) {
        val e = ptr + i
        !e._1 = elems(i)._1
        !e._2 = elems(i)._2
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

    def Xfc(implicit z: Zone): Ptr[CFloatComplex] = FloatComplexArray((1.0f, 1.0f), (1.0f, 1.0f), (1.0f, 1.0f))

    def Yfc(implicit z: Zone): Ptr[CFloatComplex] = FloatComplexArray((0.0f, 1.0f), (1.0f, 0.0f), (0.0f, 1.0f))

    val zdc = (0.0, 0.0)

    def dotudc(implicit z: Zone): Ptr[CDoubleComplex] = DoubleComplexArray(zdc)

    def Xdc(implicit z: Zone): Ptr[CDoubleComplex] = DoubleComplexArray((1.0, 1.0), (1.0, 1.0), (1.0, 1.0))

    def Ydc(implicit z: Zone): Ptr[CDoubleComplex] = DoubleComplexArray((0.0, 1.0), (1.0, 0.0), (0.0, 1.0))

    'cblas_sdsdot {
      Zone { implicit z =>
        val res = cblas_sdsdot(N, alpha, X,
          incX, Y, incY)
        assert(res == 3.0)
      }
    }

    'cblas_dsdot {
      Zone { implicit z =>
        val res = cblas_dsdot(N, X,
          incX, Y, incY)
        assert(res == 3.0)
      }
    }

    'cblas_sdot {
      Zone { implicit z =>
        val res = cblas_sdot(N, X,
          incX, Y, incY)
        assert(res == 3.0)
      }
    }

    'cblas_ddot {
      Zone { implicit z =>
        val res = cblas_ddot(N, Xd,
          incX, Yd, incY)
        assert(res == 3.0)
      }
    }

    'cblas_cdotu_sub {
      Zone { implicit z =>
        val dotu = dotufc
        cblas_cdotu_sub(N, Xfc, incX,
          Yfc, incY, dotu)
        printFloatComplex(dotu, 1)
        assert(!dotu._1 == -1.0)
        assert(!dotu._2 == 3.0)
      }
    }

    'cblas_cdotc_sub {
      Zone { implicit z =>
        val dotc = dotufc
        cblas_cdotc_sub(N, Xfc, incX,
          Yfc, incY, dotc)
        printFloatComplex(dotc, 1)
        assert(!dotc._1 == 3.0)
        assert(!dotc._2 == 1.0)
      }
    }

    'cblas_zdotu_sub {
      Zone { implicit z =>
        val dotu = dotudc
        cblas_zdotu_sub(N, Xdc, incX,
          Ydc, incY, dotu)
        assert(!dotu._1 == -1.0)
        assert(!dotu._2 == 3.0)
      }
    }

    'cblas_zdotc_sub {
      Zone { implicit z =>
        val dotc = dotudc
        cblas_zdotc_sub(N, Xdc, incX,
          Ydc, incY, dotc)
        assert(!dotc._1 == 3.0)
        assert(!dotc._2 == 1.0)
      }
    }
  }
}
