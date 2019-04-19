/** Copyright 2017-2019 Eric K Richardson
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ekrich.blas.snic

import scala.scalanative.native._
import scala.scalanative.native.complex.{CDoubleComplex, CFloatComplex}

/**
 * Used for Level 2 and 3 BLAS
 */
object blasEnums {
  //enums
  type CBLAS_ORDER = CInt
  final val CblasRowMajor: CBLAS_ORDER = 101
  final val CblasColMajor: CBLAS_ORDER = 102

  type CBLAS_TRANSPOSE = CInt
  final val CblasNoTrans: CBLAS_TRANSPOSE   = 111
  final val CblasTrans: CBLAS_TRANSPOSE     = 112
  final val CblasConjTrans: CBLAS_TRANSPOSE = 113

  type CBLAS_UPLO = CInt
  final val CblasUpper: CBLAS_UPLO = 121
  final val CblasLower: CBLAS_UPLO = 122

  type CBLAS_DIAG = CInt
  final val CblasNonUnit: CBLAS_DIAG = 131
  final val CblasUnit: CBLAS_DIAG    = 132

  type CBLAS_SIDE = CInt
  final val CblasLeft: CBLAS_SIDE  = 141
  final val CblasRight: CBLAS_SIDE = 142
}

import blasEnums._

/**
 * Scala Native extern C interface to CBLAS Version 3.8.0
 *
 */
@link("cblas")
@extern
object blas {
  /* This may vary between platforms. CBLAS_INDEX in C */
  type CblasIndex = CSize // size_t

  /*
   * ===========================================================================
   * Prototypes for level 1 BLAS functions (complex are recast as routines)
   * ===========================================================================
   */

  /**
   * Dot product of two single-precision vectors plus
   * an initial single-precision value.
   *
   * @param N The number of elements in the vectors.
   * @param alpha The initial value to add to the dot product.
   * @param X Vector X.
   * @param incX Stride within X. For example, if incX is 7, every 7th element is used.
   * @param Y Vector Y.
   * @param incY Stride within Y. For example, if incY is 7, every 7th element is used.
   * @return See description above.
   */
  def cblas_sdsdot(N: CInt,
                   alpha: CFloat,
                   X: Ptr[CFloat],
                   incX: CInt,
                   Y: Ptr[CFloat],
                   incY: CInt): CFloat = extern

  /**
   * Double-precision dot product of a pair of single-precision vectors.
   */
  def cblas_dsdot(N: CInt,
                  X: Ptr[CFloat],
                  incX: CInt,
                  Y: Ptr[CFloat],
                  incY: CInt): CDouble = extern

  /**
   * Dot product of two vectors (single-precision).
   */
  def cblas_sdot(N: CInt,
                 X: Ptr[CFloat],
                 incX: CInt,
                 Y: Ptr[CFloat],
                 incY: CInt): CFloat = extern

  /**
   * Dot product of two vectors (double-precision).
   */
  def cblas_ddot(N: CInt,
                 X: Ptr[CDouble],
                 incX: CInt,
                 Y: Ptr[CDouble],
                 incY: CInt): CDouble = extern

  /*
   * Functions having prefixes Z and C only
   */

  /**
   * Dot product of two single-precision complex vectors.
   *
   * @param dotu - The result vector.
   */
  def cblas_cdotu_sub(N: CInt,
                      X: Ptr[CFloatComplex],
                      incX: CInt,
                      Y: Ptr[CFloatComplex],
                      incY: CInt,
                      dotu: Ptr[CFloatComplex]): Unit = extern

  /**
   * Dot product of the complex conjugate of a single-precision
   * complex vector with a second single-precision complex vector.
   *
   * @param dotc - The result vector. Computes conjg(X) * Y.
   */
  def cblas_cdotc_sub(N: CInt,
                      X: Ptr[CFloatComplex],
                      incX: CInt,
                      Y: Ptr[CFloatComplex],
                      incY: CInt,
                      dotc: Ptr[CFloatComplex]): Unit = extern

  /**
   * Dot product of two double-precision complex vectors.
   *
   * @param dotu - The result vector.
   */
  def cblas_zdotu_sub(N: CInt,
                      X: Ptr[CDoubleComplex],
                      incX: CInt,
                      Y: Ptr[CDoubleComplex],
                      incY: CInt,
                      dotu: Ptr[CDoubleComplex]): Unit = extern

  /**
   * Dot product of the complex conjugate of a double-precision
   * complex vector with a second double-precision complex vector.
   *
   * @param dotc - The result vector. Computes conjg(X) * Y.
   */
  def cblas_zdotc_sub(N: CInt,
                      X: Ptr[CDoubleComplex],
                      incX: CInt,
                      Y: Ptr[CDoubleComplex],
                      incY: CInt,
                      dotc: Ptr[CDoubleComplex]): Unit = extern

  /*
   * Functions having prefixes S D SC DZ
   */

  /**
   * L2 norm (Euclidian length) of a vector (single-precision).
   */
  def cblas_snrm2(N: CInt, X: Ptr[CFloat], incX: CInt): CFloat = extern

  /**
   * Sum of the absolute values of elements in a vector (single-precision).
   */
  def cblas_sasum(N: CInt, X: Ptr[CFloat], incX: CInt): CFloat = extern

  /**
   * L2 norm (Euclidian length) of a vector (double-precision).
   */
  def cblas_dnrm2(N: CInt, X: Ptr[CDouble], incX: CInt): CDouble = extern

  /**
   * Sum of the absolute values of elements in a vector (double-precision).
   */
  def cblas_dasum(N: CInt, X: Ptr[CDouble], incX: CInt): CDouble = extern

  /**
   * Unitary norm of a vector (single-precision complex).
   */
  def cblas_scnrm2(N: CInt, X: Ptr[CFloatComplex], incX: CInt): CFloat = extern

  /**
   * Sum of the absolute values of real and imaginary parts
   * of elements in a vector (single-precision complex).
   */
  def cblas_scasum(N: CInt, X: Ptr[CFloatComplex], incX: CInt): CFloat = extern

  /**
   * Unitary norm of a vector (double-precision complex).
   */
  def cblas_dznrm2(N: CInt, X: Ptr[CDoubleComplex], incX: CInt): CDouble =
    extern

  /**
   * Sum of the absolute values of real and imaginary parts
   * of elements in a vector (double-precision complex).
   */
  def cblas_dzasum(N: CInt, X: Ptr[CDoubleComplex], incX: CInt): CDouble =
    extern

  /*
   * Functions having standard 4 prefixes (S D C Z)
   */

  /**
   * @return Index of the element with the largest absolute value
   * in a vector (single-precision).
   */
  def cblas_isamax(N: CInt, X: Ptr[CFloat], incX: CInt): CblasIndex = extern

  /**
   * @return Index of the element with the largest absolute value
   * in a vector (double-precision).
   */
  def cblas_idamax(N: CInt, X: Ptr[CDouble], incX: CInt): CblasIndex = extern

  /**
   * @return Index of the element with the largest absolute value
   * in a vector (single-precision complex).
   */
  def cblas_icamax(N: CInt, X: Ptr[CFloatComplex], incX: CInt): CblasIndex =
    extern

  /**
   * @return Index of the element with the largest absolute value
   * in a vector (double-precision complex).
   */
  def cblas_izamax(N: CInt, X: Ptr[CDoubleComplex], incX: CInt): CblasIndex =
    extern

  /*
   * ===========================================================================
   * Prototypes for level 1 BLAS routines
   * ===========================================================================
   */

  /*
   * Routines with standard 4 prefixes (s, d, c, z)
   */

  /**
   * Exchanges the elements of two vectors (single precision).
   *
   * Parameters for the following functions:
   *
   * @param N The number of elements in the vectors.
   * @param X Vector X.
   * @param incX Stride within X. For example, if incX is 7, every 7th element is used.
   * @param Y Vector Y.
   * @param incY Stride within Y. For example, if incY is 7, every 7th element is used.
   *
   * @return See description above.
   */
  def cblas_sswap(N: CInt,
                  X: Ptr[CFloat],
                  incX: CInt,
                  Y: Ptr[CFloat],
                  incY: CInt): Unit = extern

  /**
   * Copies a vector to another vector (single-precision).
   */
  def cblas_scopy(N: CInt,
                  X: Ptr[CFloat],
                  incX: CInt,
                  Y: Ptr[CFloat],
                  incY: CInt): Unit = extern

  /**
   * A constant times a vector plus a vector (single-precision).
   *
   * @param alpha The initial value to add to the dot product.
   */
  def cblas_saxpy(N: CInt,
                  alpha: CFloat,
                  X: Ptr[CFloat],
                  incX: CInt,
                  Y: Ptr[CFloat],
                  incY: CInt): Unit = extern

  /**
   * Exchanges the elements of two vectors (double precision).
   */
  def cblas_dswap(N: CInt,
                  X: Ptr[CDouble],
                  incX: CInt,
                  Y: Ptr[CDouble],
                  incY: CInt): Unit = extern

  /**
   * Copies a vector to another vector (double-precision).
   */
  def cblas_dcopy(N: CInt,
                  X: Ptr[CDouble],
                  incX: CInt,
                  Y: Ptr[CDouble],
                  incY: CInt): Unit = extern

  /**
   * Computes a constant times a vector plus a vector (double-precision).
   */
  def cblas_daxpy(N: CInt,
                  alpha: CDouble,
                  X: Ptr[CDouble],
                  incX: CInt,
                  Y: Ptr[CDouble],
                  incY: CInt): Unit = extern

  /**
   * Exchanges the elements of two vectors (single-precision complex).
   */
  def cblas_cswap(N: CInt,
                  X: Ptr[CFloatComplex],
                  incX: CInt,
                  Y: Ptr[CFloatComplex],
                  incY: CInt): Unit = extern

  /**
   * Copies a vector to another vector (single-precision complex).
   */
  def cblas_ccopy(N: CInt,
                  X: Ptr[CFloatComplex],
                  incX: CInt,
                  Y: Ptr[CFloatComplex],
                  incY: CInt): Unit = extern

  /**
   * A constant times a vector plus a vector (single-precision complex).
   */
  def cblas_caxpy(N: CInt,
                  alpha: Ptr[CFloatComplex],
                  X: Ptr[CFloatComplex],
                  incX: CInt,
                  Y: Ptr[CFloatComplex],
                  incY: CInt): Unit = extern

  /**
   * Exchanges the elements of two vectors (double-precision complex).
   */
  def cblas_zswap(N: CInt,
                  X: Ptr[CDoubleComplex],
                  incX: CInt,
                  Y: Ptr[CDoubleComplex],
                  incY: CInt): Unit = extern

  /**
   * Copies a vector to another vector (double-precision complex).
   */
  def cblas_zcopy(N: CInt,
                  X: Ptr[CDoubleComplex],
                  incX: CInt,
                  Y: Ptr[CDoubleComplex],
                  incY: CInt): Unit = extern

  /**
   * A constant times a vector plus a vector (double-precision complex).
   */
  def cblas_zaxpy(N: CInt,
                  alpha: Ptr[CDoubleComplex],
                  X: Ptr[CDoubleComplex],
                  incX: CInt,
                  Y: Ptr[CDoubleComplex],
                  incY: CInt): Unit = extern

  /*
   * Routines with S and D prefix only
   */

  /**
   * Constructs a Givens rotation matrix.
   *
   * @param a Single-precision value a. Overwritten on return with result r.
   * @param b Single-precision value b. Overwritten on return with result z (zero).
   * @param c Unused on entry. Overwritten on return with the value cos(θ).
   * @param s Unused on entry. Overwritten on return with the value sin(θ).
   */
  def cblas_srotg(a: Ptr[CFloat],
                  b: Ptr[CFloat],
                  c: Ptr[CFloat],
                  s: Ptr[CFloat]): Unit = extern

  /**
   * Generates a modified Givens rotation matrix.
   *
   * @param d1 Scaling factor D1.
   * @param d2 Scaling factor D2.
   * @param b1 Scaling factor B1.
   * @param b2 Scaling factor B2.
   * @param P  A 5-element vector:
   *           P[0] Flag value that defines the form of matrix H.
   *           -2.0: matrix H contains the identity matrix.
   *           -1.0: matrix H is identical to matrix SH (defined by the remaining values in the vector).
   *            0.0: H[1,2] and H[2,1] are obtained from matrix SH. The remaining values are both 1.0.
   *            1.0: H[1,1] and H[2,2] are obtained from matrix SH. H[1,2] is 1.0. H[2,1] is -1.0.
   *           P[1] Contains SH[1,1].
   *           P[2] Contains SH[2,1].
   *           P[3] Contains SH[1,2].
   *           P[4] Contains SH[2,2].
   */
  def cblas_srotmg(d1: Ptr[CFloat],
                   d2: Ptr[CFloat],
                   b1: Ptr[CFloat],
                   b2: CFloat,
                   P: Ptr[CFloat]): Unit = extern

  /**
   * Applies a Givens rotation matrix to a pair of vectors.
   */
  def cblas_srot(N: CInt,
                 X: Ptr[CFloat],
                 incX: CInt,
                 Y: Ptr[CFloat],
                 incY: CInt,
                 c: CFloat,
                 s: CFloat): Unit = extern

  /**
   * Applies a modified Givens transformation (single precision).
   */
  def cblas_srotm(N: CInt,
                  X: Ptr[CFloat],
                  incX: CInt,
                  Y: Ptr[CFloat],
                  incY: CInt,
                  P: Ptr[CFloat]): Unit = extern

  /**
   * Constructs a Givens rotation matrix.
   */
  def cblas_drotg(a: Ptr[CDouble],
                  b: Ptr[CDouble],
                  c: Ptr[CDouble],
                  s: Ptr[CDouble]): Unit = extern

  /**
   * Generates a modified Givens rotation matrix.
   */
  def cblas_drotmg(d1: Ptr[CDouble],
                   d2: Ptr[CDouble],
                   b1: Ptr[CDouble],
                   b2: CDouble,
                   P: Ptr[CDouble]): Unit = extern

  /**
   * Applies a Givens rotation matrix to a pair of vectors.
   */
  def cblas_drot(N: CInt,
                 X: Ptr[CDouble],
                 incX: CInt,
                 Y: Ptr[CDouble],
                 incY: CInt,
                 c: CDouble,
                 s: CDouble): Unit = extern

  /**
   * Applies a modified Givens transformation (single precision).
   */
  def cblas_drotm(N: CInt,
                  X: Ptr[CDouble],
                  incX: CInt,
                  Y: Ptr[CDouble],
                  incY: CInt,
                  P: Ptr[CDouble]): Unit = extern

  /*
   * Routines with S D C Z CS and ZD prefixes
   */

  /**
   * Multiplies each element of a vector by a constant (single-precision).
   */
  def cblas_sscal(N: CInt, alpha: CFloat, X: Ptr[CFloat], incX: CInt): Unit =
    extern

  /**
   * Multiplies each element of a vector by a constant (double-precision).
   */
  def cblas_dscal(N: CInt, alpha: CDouble, X: Ptr[CDouble], incX: CInt): Unit =
    extern

  /**
   * Multiplies each element of a vector by a constant (single-precision complex).
   */
  def cblas_cscal(N: CInt,
                  alpha: Ptr[CFloatComplex],
                  X: Ptr[CFloatComplex],
                  incX: CInt): Unit = extern

  /**
   * Multiplies each element of a vector by a constant (double-precision complex).
   */
  def cblas_zscal(N: CInt,
                  alpha: Ptr[CDoubleComplex],
                  X: Ptr[CDoubleComplex],
                  incX: CInt): Unit = extern

  /**
   * Multiplies each element of a vector by a constant (single-precision complex).
   */
  def cblas_csscal(N: CInt,
                   alpha: CFloat,
                   X: Ptr[CFloatComplex],
                   incX: CInt): Unit = extern

  /**
   * Multiplies each element of a vector by a constant (double-precision complex).
   */
  def cblas_zdscal(N: CInt,
                   alpha: CDouble,
                   X: Ptr[CDoubleComplex],
                   incX: CInt): Unit = extern
  /*
   * ===========================================================================
   * Prototypes for level 2 BLAS
   * ===========================================================================
   */

  /*
   * Routines with standard 4 prefixes (S, D, C, Z)
   */

  def cblas_sgemv(order: CBLAS_ORDER,
                  TransA: CBLAS_TRANSPOSE,
                  M: CInt,
                  N: CInt,
                  alpha: CFloat,
                  A: Ptr[CFloat],
                  lda: CInt,
                  X: Ptr[CFloat],
                  incX: CInt,
                  beta: CFloat,
                  Y: Ptr[CFloat],
                  incY: CInt): Unit = extern

  def cblas_sgbmv(order: CBLAS_ORDER,
                  TransA: CBLAS_TRANSPOSE,
                  M: CInt,
                  N: CInt,
                  KL: CInt,
                  KU: CInt,
                  alpha: CFloat,
                  A: Ptr[CFloat],
                  lda: CInt,
                  X: Ptr[CFloat],
                  incX: CInt,
                  beta: CFloat,
                  Y: Ptr[CFloat],
                  incY: CInt): Unit = extern

  def cblas_strmv(order: CBLAS_ORDER,
                  Uplo: CBLAS_UPLO,
                  TransA: CBLAS_TRANSPOSE,
                  Diag: CBLAS_DIAG,
                  N: CInt,
                  A: Ptr[CFloat],
                  lda: CInt,
                  X: Ptr[CFloat],
                  incX: CInt): Unit = extern

  def cblas_stbmv(order: CBLAS_ORDER,
                  Uplo: CBLAS_UPLO,
                  TransA: CBLAS_TRANSPOSE,
                  Diag: CBLAS_DIAG,
                  N: CInt,
                  K: CInt,
                  A: Ptr[CFloat],
                  lda: CInt,
                  X: Ptr[CFloat],
                  incX: CInt): Unit = extern

  def cblas_stpmv(order: CBLAS_ORDER,
                  Uplo: CBLAS_UPLO,
                  TransA: CBLAS_TRANSPOSE,
                  Diag: CBLAS_DIAG,
                  N: CInt,
                  Ap: Ptr[CFloat],
                  X: Ptr[CFloat],
                  incX: CInt): Unit = extern

  def cblas_strsv(order: CBLAS_ORDER,
                  Uplo: CBLAS_UPLO,
                  TransA: CBLAS_TRANSPOSE,
                  Diag: CBLAS_DIAG,
                  N: CInt,
                  A: Ptr[CFloat],
                  lda: CInt,
                  X: Ptr[CFloat],
                  incX: CInt): Unit = extern

  def cblas_stbsv(order: CBLAS_ORDER,
                  Uplo: CBLAS_UPLO,
                  TransA: CBLAS_TRANSPOSE,
                  Diag: CBLAS_DIAG,
                  N: CInt,
                  K: CInt,
                  A: Ptr[CFloat],
                  lda: CInt,
                  X: Ptr[CFloat],
                  incX: CInt): Unit = extern

  def cblas_stpsv(order: CBLAS_ORDER,
                  Uplo: CBLAS_UPLO,
                  TransA: CBLAS_TRANSPOSE,
                  Diag: CBLAS_DIAG,
                  N: CInt,
                  Ap: Ptr[CFloat],
                  X: Ptr[CFloat],
                  incX: CInt): Unit = extern

  def cblas_dgemv(order: CBLAS_ORDER,
                  TransA: CBLAS_TRANSPOSE,
                  M: CInt,
                  N: CInt,
                  alpha: CDouble,
                  A: Ptr[CDouble],
                  lda: CInt,
                  X: Ptr[CDouble],
                  incX: CInt,
                  beta: CDouble,
                  Y: Ptr[CDouble],
                  incY: CInt): Unit = extern

  def cblas_dgbmv(order: CBLAS_ORDER,
                  TransA: CBLAS_TRANSPOSE,
                  M: CInt,
                  N: CInt,
                  KL: CInt,
                  KU: CInt,
                  alpha: CDouble,
                  A: Ptr[CDouble],
                  lda: CInt,
                  X: Ptr[CDouble],
                  incX: CInt,
                  beta: CDouble,
                  Y: Ptr[CDouble],
                  incY: CInt): Unit = extern

  def cblas_dtrmv(order: CBLAS_ORDER,
                  Uplo: CBLAS_UPLO,
                  TransA: CBLAS_TRANSPOSE,
                  Diag: CBLAS_DIAG,
                  N: CInt,
                  A: Ptr[CDouble],
                  lda: CInt,
                  X: Ptr[CDouble],
                  incX: CInt): Unit = extern

  def cblas_dtbmv(order: CBLAS_ORDER,
                  Uplo: CBLAS_UPLO,
                  TransA: CBLAS_TRANSPOSE,
                  Diag: CBLAS_DIAG,
                  N: CInt,
                  K: CInt,
                  A: Ptr[CDouble],
                  lda: CInt,
                  X: Ptr[CDouble],
                  incX: CInt): Unit = extern

  def cblas_dtpmv(order: CBLAS_ORDER,
                  Uplo: CBLAS_UPLO,
                  TransA: CBLAS_TRANSPOSE,
                  Diag: CBLAS_DIAG,
                  N: CInt,
                  Ap: Ptr[CDouble],
                  X: Ptr[CDouble],
                  incX: CInt): Unit = extern

  def cblas_dtrsv(order: CBLAS_ORDER,
                  Uplo: CBLAS_UPLO,
                  TransA: CBLAS_TRANSPOSE,
                  Diag: CBLAS_DIAG,
                  N: CInt,
                  A: Ptr[CDouble],
                  lda: CInt,
                  X: Ptr[CDouble],
                  incX: CInt): Unit = extern

  def cblas_dtbsv(order: CBLAS_ORDER,
                  Uplo: CBLAS_UPLO,
                  TransA: CBLAS_TRANSPOSE,
                  Diag: CBLAS_DIAG,
                  N: CInt,
                  K: CInt,
                  A: Ptr[CDouble],
                  lda: CInt,
                  X: Ptr[CDouble],
                  incX: CInt): Unit = extern

  def cblas_dtpsv(order: CBLAS_ORDER,
                  Uplo: CBLAS_UPLO,
                  TransA: CBLAS_TRANSPOSE,
                  Diag: CBLAS_DIAG,
                  N: CInt,
                  Ap: Ptr[CDouble],
                  X: Ptr[CDouble],
                  incX: CInt): Unit = extern

  def cblas_cgemv(order: CBLAS_ORDER,
                  TransA: CBLAS_TRANSPOSE,
                  M: CInt,
                  N: CInt,
                  alpha: Ptr[CFloatComplex],
                  A: Ptr[CFloatComplex],
                  lda: CInt,
                  X: Ptr[CFloatComplex],
                  incX: CInt,
                  beta: Ptr[CFloatComplex],
                  Y: Ptr[CFloatComplex],
                  incY: CInt): Unit = extern

  def cblas_cgbmv(order: CBLAS_ORDER,
                  TransA: CBLAS_TRANSPOSE,
                  M: CInt,
                  N: CInt,
                  KL: CInt,
                  KU: CInt,
                  alpha: Ptr[CFloatComplex],
                  A: Ptr[CFloatComplex],
                  lda: CInt,
                  X: Ptr[CFloatComplex],
                  incX: CInt,
                  beta: Ptr[CFloatComplex],
                  Y: Ptr[CFloatComplex],
                  incY: CInt): Unit = extern

  def cblas_ctrmv(order: CBLAS_ORDER,
                  Uplo: CBLAS_UPLO,
                  TransA: CBLAS_TRANSPOSE,
                  Diag: CBLAS_DIAG,
                  N: CInt,
                  A: Ptr[CFloatComplex],
                  lda: CInt,
                  X: Ptr[CFloatComplex],
                  incX: CInt): Unit = extern

  def cblas_ctbmv(order: CBLAS_ORDER,
                  Uplo: CBLAS_UPLO,
                  TransA: CBLAS_TRANSPOSE,
                  Diag: CBLAS_DIAG,
                  N: CInt,
                  K: CInt,
                  A: Ptr[CFloatComplex],
                  lda: CInt,
                  X: Ptr[CFloatComplex],
                  incX: CInt): Unit = extern

  def cblas_ctpmv(order: CBLAS_ORDER,
                  Uplo: CBLAS_UPLO,
                  TransA: CBLAS_TRANSPOSE,
                  Diag: CBLAS_DIAG,
                  N: CInt,
                  Ap: Ptr[CFloatComplex],
                  X: Ptr[CFloatComplex],
                  incX: CInt): Unit = extern

  def cblas_ctrsv(order: CBLAS_ORDER,
                  Uplo: CBLAS_UPLO,
                  TransA: CBLAS_TRANSPOSE,
                  Diag: CBLAS_DIAG,
                  N: CInt,
                  A: Ptr[CFloatComplex],
                  lda: CInt,
                  X: Ptr[CFloatComplex],
                  incX: CInt): Unit = extern

  def cblas_ctbsv(order: CBLAS_ORDER,
                  Uplo: CBLAS_UPLO,
                  TransA: CBLAS_TRANSPOSE,
                  Diag: CBLAS_DIAG,
                  N: CInt,
                  K: CInt,
                  A: Ptr[CFloatComplex],
                  lda: CInt,
                  X: Ptr[CFloatComplex],
                  incX: CInt): Unit = extern

  def cblas_ctpsv(order: CBLAS_ORDER,
                  Uplo: CBLAS_UPLO,
                  TransA: CBLAS_TRANSPOSE,
                  Diag: CBLAS_DIAG,
                  N: CInt,
                  Ap: Ptr[CFloatComplex],
                  X: Ptr[CFloatComplex],
                  incX: CInt): Unit = extern

  def cblas_zgemv(order: CBLAS_ORDER,
                  TransA: CBLAS_TRANSPOSE,
                  M: CInt,
                  N: CInt,
                  alpha: Ptr[CDoubleComplex],
                  A: Ptr[CDoubleComplex],
                  lda: CInt,
                  X: Ptr[CDoubleComplex],
                  incX: CInt,
                  beta: Ptr[CDoubleComplex],
                  Y: Ptr[CDoubleComplex],
                  incY: CInt): Unit = extern

  def cblas_zgbmv(order: CBLAS_ORDER,
                  TransA: CBLAS_TRANSPOSE,
                  M: CInt,
                  N: CInt,
                  KL: CInt,
                  KU: CInt,
                  alpha: Ptr[CDoubleComplex],
                  A: Ptr[CDoubleComplex],
                  lda: CInt,
                  X: Ptr[CDoubleComplex],
                  incX: CInt,
                  beta: Ptr[CDoubleComplex],
                  Y: Ptr[CDoubleComplex],
                  incY: CInt): Unit = extern

  def cblas_ztrmv(order: CBLAS_ORDER,
                  Uplo: CBLAS_UPLO,
                  TransA: CBLAS_TRANSPOSE,
                  Diag: CBLAS_DIAG,
                  N: CInt,
                  A: Ptr[CDoubleComplex],
                  lda: CInt,
                  X: Ptr[CDoubleComplex],
                  incX: CInt): Unit = extern

  def cblas_ztbmv(order: CBLAS_ORDER,
                  Uplo: CBLAS_UPLO,
                  TransA: CBLAS_TRANSPOSE,
                  Diag: CBLAS_DIAG,
                  N: CInt,
                  K: CInt,
                  A: Ptr[CDoubleComplex],
                  lda: CInt,
                  X: Ptr[CDoubleComplex],
                  incX: CInt): Unit = extern

  def cblas_ztpmv(order: CBLAS_ORDER,
                  Uplo: CBLAS_UPLO,
                  TransA: CBLAS_TRANSPOSE,
                  Diag: CBLAS_DIAG,
                  N: CInt,
                  Ap: Ptr[CDoubleComplex],
                  X: Ptr[CDoubleComplex],
                  incX: CInt): Unit = extern

  def cblas_ztrsv(order: CBLAS_ORDER,
                  Uplo: CBLAS_UPLO,
                  TransA: CBLAS_TRANSPOSE,
                  Diag: CBLAS_DIAG,
                  N: CInt,
                  A: Ptr[CDoubleComplex],
                  lda: CInt,
                  X: Ptr[CDoubleComplex],
                  incX: CInt): Unit = extern

  def cblas_ztbsv(order: CBLAS_ORDER,
                  Uplo: CBLAS_UPLO,
                  TransA: CBLAS_TRANSPOSE,
                  Diag: CBLAS_DIAG,
                  N: CInt,
                  K: CInt,
                  A: Ptr[CDoubleComplex],
                  lda: CInt,
                  X: Ptr[CDoubleComplex],
                  incX: CInt): Unit = extern

  def cblas_ztpsv(order: CBLAS_ORDER,
                  Uplo: CBLAS_UPLO,
                  TransA: CBLAS_TRANSPOSE,
                  Diag: CBLAS_DIAG,
                  N: CInt,
                  Ap: Ptr[CDoubleComplex],
                  X: Ptr[CDoubleComplex],
                  incX: CInt): Unit = extern
  /*
   * Routines with S and D prefixes only
   */

  def cblas_ssymv(order: CBLAS_ORDER,
                  Uplo: CBLAS_UPLO,
                  N: CInt,
                  alpha: CFloat,
                  A: Ptr[CFloat],
                  lda: CInt,
                  X: Ptr[CFloat],
                  incX: CInt,
                  beta: CFloat,
                  Y: Ptr[CFloat],
                  incY: CInt): Unit = extern

  def cblas_ssbmv(order: CBLAS_ORDER,
                  Uplo: CBLAS_UPLO,
                  N: CInt,
                  K: CInt,
                  alpha: CFloat,
                  A: Ptr[CFloat],
                  lda: CInt,
                  X: Ptr[CFloat],
                  incX: CInt,
                  beta: CFloat,
                  Y: Ptr[CFloat],
                  incY: CInt): Unit = extern

  def cblas_sspmv(order: CBLAS_ORDER,
                  Uplo: CBLAS_UPLO,
                  N: CInt,
                  alpha: CFloat,
                  Ap: Ptr[CFloat],
                  X: Ptr[CFloat],
                  incX: CInt,
                  beta: CFloat,
                  Y: Ptr[CFloat],
                  incY: CInt): Unit = extern

  def cblas_sger(order: CBLAS_ORDER,
                 M: CInt,
                 N: CInt,
                 alpha: CFloat,
                 X: Ptr[CFloat],
                 incX: CInt,
                 Y: Ptr[CFloat],
                 incY: CInt,
                 A: Ptr[CFloat],
                 lda: CInt): Unit = extern

  def cblas_ssyr(order: CBLAS_ORDER,
                 Uplo: CBLAS_UPLO,
                 N: CInt,
                 alpha: CFloat,
                 X: Ptr[CFloat],
                 incX: CInt,
                 A: Ptr[CFloat],
                 lda: CInt): Unit = extern

  def cblas_sspr(order: CBLAS_ORDER,
                 Uplo: CBLAS_UPLO,
                 N: CInt,
                 alpha: CFloat,
                 X: Ptr[CFloat],
                 incX: CInt,
                 Ap: Ptr[CFloat]): Unit = extern

  def cblas_ssyr2(order: CBLAS_ORDER,
                  Uplo: CBLAS_UPLO,
                  N: CInt,
                  alpha: CFloat,
                  X: Ptr[CFloat],
                  incX: CInt,
                  Y: Ptr[CFloat],
                  incY: CInt,
                  A: Ptr[CFloat],
                  lda: CInt): Unit = extern

  def cblas_sspr2(order: CBLAS_ORDER,
                  Uplo: CBLAS_UPLO,
                  N: CInt,
                  alpha: CFloat,
                  X: Ptr[CFloat],
                  incX: CInt,
                  Y: Ptr[CFloat],
                  incY: CInt,
                  A: Ptr[CFloat]): Unit = extern

  def cblas_dsymv(order: CBLAS_ORDER,
                  Uplo: CBLAS_UPLO,
                  N: CInt,
                  alpha: CDouble,
                  A: Ptr[CDouble],
                  lda: CInt,
                  X: Ptr[CDouble],
                  incX: CInt,
                  beta: CDouble,
                  Y: Ptr[CDouble],
                  incY: CInt): Unit = extern

  def cblas_dsbmv(order: CBLAS_ORDER,
                  Uplo: CBLAS_UPLO,
                  N: CInt,
                  K: CInt,
                  alpha: CDouble,
                  A: Ptr[CDouble],
                  lda: CInt,
                  X: Ptr[CDouble],
                  incX: CInt,
                  beta: CDouble,
                  Y: Ptr[CDouble],
                  incY: CInt): Unit = extern

  def cblas_dspmv(order: CBLAS_ORDER,
                  Uplo: CBLAS_UPLO,
                  N: CInt,
                  alpha: CDouble,
                  Ap: Ptr[CDouble],
                  X: Ptr[CDouble],
                  incX: CInt,
                  beta: CDouble,
                  Y: Ptr[CDouble],
                  incY: CInt): Unit = extern

  def cblas_dger(order: CBLAS_ORDER,
                 M: CInt,
                 N: CInt,
                 alpha: CDouble,
                 X: Ptr[CDouble],
                 incX: CInt,
                 Y: Ptr[CDouble],
                 incY: CInt,
                 A: Ptr[CDouble],
                 lda: CInt): Unit = extern

  def cblas_dsyr(order: CBLAS_ORDER,
                 Uplo: CBLAS_UPLO,
                 N: CInt,
                 alpha: CDouble,
                 X: Ptr[CDouble],
                 incX: CInt,
                 A: Ptr[CDouble],
                 lda: CInt): Unit = extern

  def cblas_dspr(order: CBLAS_ORDER,
                 Uplo: CBLAS_UPLO,
                 N: CInt,
                 alpha: CDouble,
                 X: Ptr[CDouble],
                 incX: CInt,
                 Ap: Ptr[CDouble]): Unit = extern

  def cblas_dsyr2(order: CBLAS_ORDER,
                  Uplo: CBLAS_UPLO,
                  N: CInt,
                  alpha: CDouble,
                  X: Ptr[CDouble],
                  incX: CInt,
                  Y: Ptr[CDouble],
                  incY: CInt,
                  A: Ptr[CDouble],
                  lda: CInt): Unit = extern

  def cblas_dspr2(order: CBLAS_ORDER,
                  Uplo: CBLAS_UPLO,
                  N: CInt,
                  alpha: CDouble,
                  X: Ptr[CDouble],
                  incX: CInt,
                  Y: Ptr[CDouble],
                  incY: CInt,
                  A: Ptr[CDouble]): Unit = extern

  /*
   * Routines with C and Z prefixes only
   */

  def cblas_chemv(order: CBLAS_ORDER,
                  Uplo: CBLAS_UPLO,
                  N: CInt,
                  alpha: Ptr[CFloatComplex],
                  A: Ptr[CFloatComplex],
                  lda: CInt,
                  X: Ptr[CFloatComplex],
                  incX: CInt,
                  beta: Ptr[CFloatComplex],
                  Y: Ptr[CFloatComplex],
                  incY: CInt): Unit = extern

  def cblas_chbmv(order: CBLAS_ORDER,
                  Uplo: CBLAS_UPLO,
                  N: CInt,
                  K: CInt,
                  alpha: Ptr[CFloatComplex],
                  A: Ptr[CFloatComplex],
                  lda: CInt,
                  X: Ptr[CFloatComplex],
                  incX: CInt,
                  beta: Ptr[CFloatComplex],
                  Y: Ptr[CFloatComplex],
                  incY: CInt): Unit = extern

  def cblas_chpmv(order: CBLAS_ORDER,
                  Uplo: CBLAS_UPLO,
                  N: CInt,
                  alpha: Ptr[CFloatComplex],
                  Ap: Ptr[CFloatComplex],
                  X: Ptr[CFloatComplex],
                  incX: CInt,
                  beta: Ptr[CFloatComplex],
                  Y: Ptr[CFloatComplex],
                  incY: CInt): Unit = extern

  def cblas_cgeru(order: CBLAS_ORDER,
                  M: CInt,
                  N: CInt,
                  alpha: Ptr[CFloatComplex],
                  X: Ptr[CFloatComplex],
                  incX: CInt,
                  Y: Ptr[CFloatComplex],
                  incY: CInt,
                  A: Ptr[CFloatComplex],
                  lda: CInt): Unit = extern

  def cblas_cgerc(order: CBLAS_ORDER,
                  M: CInt,
                  N: CInt,
                  alpha: Ptr[CFloatComplex],
                  X: Ptr[CFloatComplex],
                  incX: CInt,
                  Y: Ptr[CFloatComplex],
                  incY: CInt,
                  A: Ptr[CFloatComplex],
                  lda: CInt): Unit = extern

  def cblas_cher(order: CBLAS_ORDER,
                 Uplo: CBLAS_UPLO,
                 N: CInt,
                 alpha: CFloat,
                 X: Ptr[CFloatComplex],
                 incX: CInt,
                 A: Ptr[CFloatComplex],
                 lda: CInt): Unit = extern

  def cblas_chpr(order: CBLAS_ORDER,
                 Uplo: CBLAS_UPLO,
                 N: CInt,
                 alpha: CFloat,
                 X: Ptr[CFloatComplex],
                 incX: CInt,
                 A: Ptr[CFloatComplex]): Unit = extern

  def cblas_cher2(order: CBLAS_ORDER,
                  Uplo: CBLAS_UPLO,
                  N: CInt,
                  alpha: Ptr[CFloatComplex],
                  X: Ptr[CFloatComplex],
                  incX: CInt,
                  Y: Ptr[CFloatComplex],
                  incY: CInt,
                  A: Ptr[CFloatComplex],
                  lda: CInt): Unit = extern

  def cblas_chpr2(order: CBLAS_ORDER,
                  Uplo: CBLAS_UPLO,
                  N: CInt,
                  alpha: Ptr[CFloatComplex],
                  X: Ptr[CFloatComplex],
                  incX: CInt,
                  Y: Ptr[CFloatComplex],
                  incY: CInt,
                  Ap: Ptr[CFloatComplex]): Unit = extern

  def cblas_zhemv(order: CBLAS_ORDER,
                  Uplo: CBLAS_UPLO,
                  N: CInt,
                  alpha: Ptr[CDoubleComplex],
                  A: Ptr[CDoubleComplex],
                  lda: CInt,
                  X: Ptr[CDoubleComplex],
                  incX: CInt,
                  beta: Ptr[CDoubleComplex],
                  Y: Ptr[CDoubleComplex],
                  incY: CInt): Unit = extern

  def cblas_zhbmv(order: CBLAS_ORDER,
                  Uplo: CBLAS_UPLO,
                  N: CInt,
                  K: CInt,
                  alpha: Ptr[CDoubleComplex],
                  A: Ptr[CDoubleComplex],
                  lda: CInt,
                  X: Ptr[CDoubleComplex],
                  incX: CInt,
                  beta: Ptr[CDoubleComplex],
                  Y: Ptr[CDoubleComplex],
                  incY: CInt): Unit = extern

  def cblas_zhpmv(order: CBLAS_ORDER,
                  Uplo: CBLAS_UPLO,
                  N: CInt,
                  alpha: Ptr[CDoubleComplex],
                  Ap: Ptr[CDoubleComplex],
                  X: Ptr[CDoubleComplex],
                  incX: CInt,
                  beta: Ptr[CDoubleComplex],
                  Y: Ptr[CDoubleComplex],
                  incY: CInt): Unit = extern

  def cblas_zgeru(order: CBLAS_ORDER,
                  M: CInt,
                  N: CInt,
                  alpha: Ptr[CDoubleComplex],
                  X: Ptr[CDoubleComplex],
                  incX: CInt,
                  Y: Ptr[CDoubleComplex],
                  incY: CInt,
                  A: Ptr[CDoubleComplex],
                  lda: CInt): Unit = extern

  def cblas_zgerc(order: CBLAS_ORDER,
                  M: CInt,
                  N: CInt,
                  alpha: Ptr[CDoubleComplex],
                  X: Ptr[CDoubleComplex],
                  incX: CInt,
                  Y: Ptr[CDoubleComplex],
                  incY: CInt,
                  A: Ptr[CDoubleComplex],
                  lda: CInt): Unit = extern

  def cblas_zher(order: CBLAS_ORDER,
                 Uplo: CBLAS_UPLO,
                 N: CInt,
                 alpha: CDouble,
                 X: Ptr[CDoubleComplex],
                 incX: CInt,
                 A: Ptr[CDoubleComplex],
                 lda: CInt): Unit = extern

  def cblas_zhpr(order: CBLAS_ORDER,
                 Uplo: CBLAS_UPLO,
                 N: CInt,
                 alpha: CDouble,
                 X: Ptr[CDoubleComplex],
                 incX: CInt,
                 A: Ptr[CDoubleComplex]): Unit = extern

  def cblas_zher2(order: CBLAS_ORDER,
                  Uplo: CBLAS_UPLO,
                  N: CInt,
                  alpha: Ptr[CDoubleComplex],
                  X: Ptr[CDoubleComplex],
                  incX: CInt,
                  Y: Ptr[CDoubleComplex],
                  incY: CInt,
                  A: Ptr[CDoubleComplex],
                  lda: CInt): Unit = extern

  def cblas_zhpr2(order: CBLAS_ORDER,
                  Uplo: CBLAS_UPLO,
                  N: CInt,
                  alpha: Ptr[CDoubleComplex],
                  X: Ptr[CDoubleComplex],
                  incX: CInt,
                  Y: Ptr[CDoubleComplex],
                  incY: CInt,
                  Ap: Ptr[CDoubleComplex]): Unit = extern

  /*
   * ===========================================================================
   * Prototypes for level 3 BLAS
   * ===========================================================================
   */

  /*
   * Routines with standard 4 prefixes (S, D, C, Z)
   */

  def cblas_sgemm(Order: CBLAS_ORDER,
                  TransA: CBLAS_TRANSPOSE,
                  TransB: CBLAS_TRANSPOSE,
                  M: CInt,
                  N: CInt,
                  K: CInt,
                  alpha: CFloat,
                  A: Ptr[CFloat],
                  lda: CInt,
                  B: Ptr[CFloat],
                  ldb: CInt,
                  beta: CFloat,
                  C: Ptr[CFloat],
                  ldc: CInt): Unit = extern

  def cblas_ssymm(Order: CBLAS_ORDER,
                  Side: CBLAS_SIDE,
                  Uplo: CBLAS_UPLO,
                  M: CInt,
                  N: CInt,
                  alpha: CFloat,
                  A: Ptr[CFloat],
                  lda: CInt,
                  B: Ptr[CFloat],
                  ldb: CInt,
                  beta: CFloat,
                  C: Ptr[CFloat],
                  ldc: CInt): Unit = extern

  def cblas_ssyrk(Order: CBLAS_ORDER,
                  Uplo: CBLAS_UPLO,
                  Trans: CBLAS_TRANSPOSE,
                  N: CInt,
                  K: CInt,
                  alpha: CFloat,
                  A: Ptr[CFloat],
                  lda: CInt,
                  beta: CFloat,
                  C: Ptr[CFloat],
                  ldc: CInt): Unit = extern

  def cblas_ssyr2k(Order: CBLAS_ORDER,
                   Uplo: CBLAS_UPLO,
                   Trans: CBLAS_TRANSPOSE,
                   N: CInt,
                   K: CInt,
                   alpha: CFloat,
                   A: Ptr[CFloat],
                   lda: CInt,
                   B: Ptr[CFloat],
                   ldb: CInt,
                   beta: CFloat,
                   C: Ptr[CFloat],
                   ldc: CInt): Unit = extern

  def cblas_strmm(Order: CBLAS_ORDER,
                  Side: CBLAS_SIDE,
                  Uplo: CBLAS_UPLO,
                  TransA: CBLAS_TRANSPOSE,
                  Diag: CBLAS_DIAG,
                  M: CInt,
                  N: CInt,
                  alpha: CFloat,
                  A: Ptr[CFloat],
                  lda: CInt,
                  B: Ptr[CFloat],
                  ldb: CInt): Unit = extern

  def cblas_strsm(Order: CBLAS_ORDER,
                  Side: CBLAS_SIDE,
                  Uplo: CBLAS_UPLO,
                  TransA: CBLAS_TRANSPOSE,
                  Diag: CBLAS_DIAG,
                  M: CInt,
                  N: CInt,
                  alpha: CFloat,
                  A: Ptr[CFloat],
                  lda: CInt,
                  B: Ptr[CFloat],
                  ldb: CInt): Unit = extern

  def cblas_dgemm(Order: CBLAS_ORDER,
                  TransA: CBLAS_TRANSPOSE,
                  TransB: CBLAS_TRANSPOSE,
                  M: CInt,
                  N: CInt,
                  K: CInt,
                  alpha: CDouble,
                  A: Ptr[CDouble],
                  lda: CInt,
                  B: Ptr[CDouble],
                  ldb: CInt,
                  beta: CDouble,
                  C: Ptr[CDouble],
                  ldc: CInt): Unit = extern

  def cblas_dsymm(Order: CBLAS_ORDER,
                  Side: CBLAS_SIDE,
                  Uplo: CBLAS_UPLO,
                  M: CInt,
                  N: CInt,
                  alpha: CDouble,
                  A: Ptr[CDouble],
                  lda: CInt,
                  B: Ptr[CDouble],
                  ldb: CInt,
                  beta: CDouble,
                  C: Ptr[CDouble],
                  ldc: CInt): Unit = extern

  def cblas_dsyrk(Order: CBLAS_ORDER,
                  Uplo: CBLAS_UPLO,
                  Trans: CBLAS_TRANSPOSE,
                  N: CInt,
                  K: CInt,
                  alpha: CDouble,
                  A: Ptr[CDouble],
                  lda: CInt,
                  beta: CDouble,
                  C: Ptr[CDouble],
                  ldc: CInt): Unit = extern

  def cblas_dsyr2k(Order: CBLAS_ORDER,
                   Uplo: CBLAS_UPLO,
                   Trans: CBLAS_TRANSPOSE,
                   N: CInt,
                   K: CInt,
                   alpha: CDouble,
                   A: Ptr[CDouble],
                   lda: CInt,
                   B: Ptr[CDouble],
                   ldb: CInt,
                   beta: CDouble,
                   C: Ptr[CDouble],
                   ldc: CInt): Unit = extern

  def cblas_dtrmm(Order: CBLAS_ORDER,
                  Side: CBLAS_SIDE,
                  Uplo: CBLAS_UPLO,
                  TransA: CBLAS_TRANSPOSE,
                  Diag: CBLAS_DIAG,
                  M: CInt,
                  N: CInt,
                  alpha: CDouble,
                  A: Ptr[CDouble],
                  lda: CInt,
                  B: Ptr[CDouble],
                  ldb: CInt): Unit = extern

  def cblas_dtrsm(Order: CBLAS_ORDER,
                  Side: CBLAS_SIDE,
                  Uplo: CBLAS_UPLO,
                  TransA: CBLAS_TRANSPOSE,
                  Diag: CBLAS_DIAG,
                  M: CInt,
                  N: CInt,
                  alpha: CDouble,
                  A: Ptr[CDouble],
                  lda: CInt,
                  B: Ptr[CDouble],
                  ldb: CInt): Unit = extern

  def cblas_cgemm(Order: CBLAS_ORDER,
                  TransA: CBLAS_TRANSPOSE,
                  TransB: CBLAS_TRANSPOSE,
                  M: CInt,
                  N: CInt,
                  K: CInt,
                  alpha: Ptr[CFloatComplex],
                  A: Ptr[CFloatComplex],
                  lda: CInt,
                  B: Ptr[CFloatComplex],
                  ldb: CInt,
                  beta: Ptr[CFloatComplex],
                  C: Ptr[CFloatComplex],
                  ldc: CInt): Unit = extern

  def cblas_csymm(Order: CBLAS_ORDER,
                  Side: CBLAS_SIDE,
                  Uplo: CBLAS_UPLO,
                  M: CInt,
                  N: CInt,
                  alpha: Ptr[CFloatComplex],
                  A: Ptr[CFloatComplex],
                  lda: CInt,
                  B: Ptr[CFloatComplex],
                  ldb: CInt,
                  beta: Ptr[CFloatComplex],
                  C: Ptr[CFloatComplex],
                  ldc: CInt): Unit = extern

  def cblas_csyrk(Order: CBLAS_ORDER,
                  Uplo: CBLAS_UPLO,
                  Trans: CBLAS_TRANSPOSE,
                  N: CInt,
                  K: CInt,
                  alpha: Ptr[CFloatComplex],
                  A: Ptr[CFloatComplex],
                  lda: CInt,
                  beta: Ptr[CFloatComplex],
                  C: Ptr[CFloatComplex],
                  ldc: CInt): Unit = extern

  def cblas_csyr2k(Order: CBLAS_ORDER,
                   Uplo: CBLAS_UPLO,
                   Trans: CBLAS_TRANSPOSE,
                   N: CInt,
                   K: CInt,
                   alpha: Ptr[CFloatComplex],
                   A: Ptr[CFloatComplex],
                   lda: CInt,
                   B: Ptr[CFloatComplex],
                   ldb: CInt,
                   beta: Ptr[CFloatComplex],
                   C: Ptr[CFloatComplex],
                   ldc: CInt): Unit = extern

  def cblas_ctrmm(Order: CBLAS_ORDER,
                  Side: CBLAS_SIDE,
                  Uplo: CBLAS_UPLO,
                  TransA: CBLAS_TRANSPOSE,
                  Diag: CBLAS_DIAG,
                  M: CInt,
                  N: CInt,
                  alpha: Ptr[CFloatComplex],
                  A: Ptr[CFloatComplex],
                  lda: CInt,
                  B: Ptr[CFloatComplex],
                  ldb: CInt): Unit = extern

  def cblas_ctrsm(Order: CBLAS_ORDER,
                  Side: CBLAS_SIDE,
                  Uplo: CBLAS_UPLO,
                  TransA: CBLAS_TRANSPOSE,
                  Diag: CBLAS_DIAG,
                  M: CInt,
                  N: CInt,
                  alpha: Ptr[CFloatComplex],
                  A: Ptr[CFloatComplex],
                  lda: CInt,
                  B: Ptr[CFloatComplex],
                  ldb: CInt): Unit = extern

  def cblas_zgemm(Order: CBLAS_ORDER,
                  TransA: CBLAS_TRANSPOSE,
                  TransB: CBLAS_TRANSPOSE,
                  M: CInt,
                  N: CInt,
                  K: CInt,
                  alpha: Ptr[CDoubleComplex],
                  A: Ptr[CDoubleComplex],
                  lda: CInt,
                  B: Ptr[CDoubleComplex],
                  ldb: CInt,
                  beta: Ptr[CDoubleComplex],
                  C: Ptr[CDoubleComplex],
                  ldc: CInt): Unit = extern

  def cblas_zsymm(Order: CBLAS_ORDER,
                  Side: CBLAS_SIDE,
                  Uplo: CBLAS_UPLO,
                  M: CInt,
                  N: CInt,
                  alpha: Ptr[CDoubleComplex],
                  A: Ptr[CDoubleComplex],
                  lda: CInt,
                  B: Ptr[CDoubleComplex],
                  ldb: CInt,
                  beta: Ptr[CDoubleComplex],
                  C: Ptr[CDoubleComplex],
                  ldc: CInt): Unit = extern

  def cblas_zsyrk(Order: CBLAS_ORDER,
                  Uplo: CBLAS_UPLO,
                  Trans: CBLAS_TRANSPOSE,
                  N: CInt,
                  K: CInt,
                  alpha: Ptr[CDoubleComplex],
                  A: Ptr[CDoubleComplex],
                  lda: CInt,
                  beta: Ptr[CDoubleComplex],
                  C: Ptr[CDoubleComplex],
                  ldc: CInt): Unit = extern

  def cblas_zsyr2k(Order: CBLAS_ORDER,
                   Uplo: CBLAS_UPLO,
                   Trans: CBLAS_TRANSPOSE,
                   N: CInt,
                   K: CInt,
                   alpha: Ptr[CDoubleComplex],
                   A: Ptr[CDoubleComplex],
                   lda: CInt,
                   B: Ptr[CDoubleComplex],
                   ldb: CInt,
                   beta: Ptr[CDoubleComplex],
                   C: Ptr[CDoubleComplex],
                   ldc: CInt): Unit = extern

  def cblas_ztrmm(Order: CBLAS_ORDER,
                  Side: CBLAS_SIDE,
                  Uplo: CBLAS_UPLO,
                  TransA: CBLAS_TRANSPOSE,
                  Diag: CBLAS_DIAG,
                  M: CInt,
                  N: CInt,
                  alpha: Ptr[CDoubleComplex],
                  A: Ptr[CDoubleComplex],
                  lda: CInt,
                  B: Ptr[CDoubleComplex],
                  ldb: CInt): Unit = extern

  def cblas_ztrsm(Order: CBLAS_ORDER,
                  Side: CBLAS_SIDE,
                  Uplo: CBLAS_UPLO,
                  TransA: CBLAS_TRANSPOSE,
                  Diag: CBLAS_DIAG,
                  M: CInt,
                  N: CInt,
                  alpha: Ptr[CDoubleComplex],
                  A: Ptr[CDoubleComplex],
                  lda: CInt,
                  B: Ptr[CDoubleComplex],
                  ldb: CInt): Unit = extern

  /*
   * Routines with prefixes C and Z only
   */

  def cblas_chemm(Order: CBLAS_ORDER,
                  Side: CBLAS_SIDE,
                  Uplo: CBLAS_UPLO,
                  M: CInt,
                  N: CInt,
                  alpha: Ptr[CFloatComplex],
                  A: Ptr[CFloatComplex],
                  lda: CInt,
                  B: Ptr[CFloatComplex],
                  ldb: CInt,
                  beta: Ptr[CFloatComplex],
                  C: Ptr[CFloatComplex],
                  ldc: CInt): Unit = extern

  def cblas_cherk(Order: CBLAS_ORDER,
                  Uplo: CBLAS_UPLO,
                  Trans: CBLAS_TRANSPOSE,
                  N: CInt,
                  K: CInt,
                  alpha: CFloat,
                  A: Ptr[CFloatComplex],
                  lda: CInt,
                  beta: CFloat,
                  C: Ptr[CFloatComplex],
                  ldc: CInt): Unit = extern

  def cblas_cher2k(Order: CBLAS_ORDER,
                   Uplo: CBLAS_UPLO,
                   Trans: CBLAS_TRANSPOSE,
                   N: CInt,
                   K: CInt,
                   alpha: Ptr[CFloatComplex],
                   A: Ptr[CFloatComplex],
                   lda: CInt,
                   B: Ptr[CFloatComplex],
                   ldb: CInt,
                   beta: CFloat,
                   C: Ptr[CFloatComplex],
                   ldc: CInt): Unit = extern

  def cblas_zhemm(Order: CBLAS_ORDER,
                  Side: CBLAS_SIDE,
                  Uplo: CBLAS_UPLO,
                  M: CInt,
                  N: CInt,
                  alpha: Ptr[CDoubleComplex],
                  A: Ptr[CDoubleComplex],
                  lda: CInt,
                  B: Ptr[CDoubleComplex],
                  ldb: CInt,
                  beta: Ptr[CDoubleComplex],
                  C: Ptr[CDoubleComplex],
                  ldc: CInt): Unit = extern

  def cblas_zherk(Order: CBLAS_ORDER,
                  Uplo: CBLAS_UPLO,
                  Trans: CBLAS_TRANSPOSE,
                  N: CInt,
                  K: CInt,
                  alpha: CDouble,
                  A: Ptr[CDoubleComplex],
                  lda: CInt,
                  beta: CDouble,
                  C: Ptr[CDoubleComplex],
                  ldc: CInt): Unit = extern

  def cblas_zher2k(Order: CBLAS_ORDER,
                   Uplo: CBLAS_UPLO,
                   Trans: CBLAS_TRANSPOSE,
                   N: CInt,
                   K: CInt,
                   alpha: Ptr[CDoubleComplex],
                   A: Ptr[CDoubleComplex],
                   lda: CInt,
                   B: Ptr[CDoubleComplex],
                   ldb: CInt,
                   beta: CDouble,
                   C: Ptr[CDoubleComplex],
                   ldc: CInt): Unit = extern

  /**
   * The error handler for the LAPACK routines.
   * It is called by an LAPACK routine if an input parameter has an
   * invalid value.  A message is printed and execution stops.
   */
  def cblas_xerbla(p: CInt,
                   rout: CString,
                   form: CString,
                   varArgs: CVararg*): Unit = extern
}
