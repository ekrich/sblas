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
   * Computes the dot product of two single-precision vectors plus
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
   * Computes the double-precision dot product of a pair of single-precision vectors.
   */
  def cblas_dsdot(N: CInt,
                  X: Ptr[CFloat],
                  incX: CInt,
                  Y: Ptr[CFloat],
                  incY: CInt): CDouble = extern

  /**
   * Computes the dot product of two vectors (single-precision).
   */
  def cblas_sdot(N: CInt,
                 X: Ptr[CFloat],
                 incX: CInt,
                 Y: Ptr[CFloat],
                 incY: CInt): CFloat = extern

  /**
   * Computes the dot product of two vectors (double-precision).
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
   * Computes the dot product of two single-precision complex vectors.
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
   * Calculates the dot product of the complex conjugate of a single-precision
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
   * Computes the dot product of two double-precision complex vectors.
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
   * Calculates the dot product of the complex conjugate of a double-precision
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
   * Computes the L2 norm (Euclidian length) of a vector (single-precision).
   */
  def cblas_snrm2(N: CInt, X: Ptr[CFloat], incX: CInt): CFloat = extern

  /**
   * Computes the sum of the absolute values of elements in a vector (single-precision).
   */
  def cblas_sasum(N: CInt, X: Ptr[CFloat], incX: CInt): CFloat = extern

  /**
   * Computes the L2 norm (Euclidian length) of a vector (double-precision).
   */
  def cblas_dnrm2(N: CInt, X: Ptr[CDouble], incX: CInt): CDouble = extern

  /**
   * Computes the sum of the absolute values of elements in a vector (double-precision).
   */
  def cblas_dasum(N: CInt, X: Ptr[CDouble], incX: CInt): CDouble = extern

  /**
   * Computes the unitary norm of a vector (single-precision complex).
   */
  def cblas_scnrm2(N: CInt, X: Ptr[CFloatComplex], incX: CInt): CFloat = extern

  /**
   * Computes the sum of the absolute values of real and imaginary parts
   * of elements in a vector (single-precision complex).
   */
  def cblas_scasum(N: CInt, X: Ptr[CFloatComplex], incX: CInt): CFloat = extern

  /**
   * Computes the unitary norm of a vector (double-precision complex).
   */
  def cblas_dznrm2(N: CInt, X: Ptr[CDoubleComplex], incX: CInt): CDouble =
    extern

  /**
   * Computes the sum of the absolute values of real and imaginary parts
   * of elements in a vector (double-precision complex).
   */
  def cblas_dzasum(N: CInt, X: Ptr[CDoubleComplex], incX: CInt): CDouble =
    extern

  /*
   * Functions having standard 4 prefixes (S D C Z)
   */

  /**
   * Returns the index of the element with the largest absolute value
   * in a vector (single-precision).
   */
  def cblas_isamax(N: CInt, X: Ptr[CFloat], incX: CInt): CblasIndex = extern

  /**
   * Returns the index of the element with the largest absolute value
   * in a vector (double-precision).
   */
  def cblas_idamax(N: CInt, X: Ptr[CDouble], incX: CInt): CblasIndex = extern

  /**
   * Returns the index of the element with the largest absolute value
   * in a vector (single-precision complex).
   */
  def cblas_icamax(N: CInt, X: Ptr[CFloatComplex], incX: CInt): CblasIndex =
    extern

  /**
   * Returns the index of the element with the largest absolute value
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
   * Computes a constant times a vector plus a vector (single-precision).
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
   * Computes a constant times a vector plus a vector (single-precision complex).
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
   * Computes a constant times a vector plus a vector (double-precision complex).
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
}

/**
 * Used for level 2 and 3 BLAS
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
// Removing as I go

///*
// * ===========================================================================
// * Prototypes for level 2 BLAS
// * ===========================================================================
// */
//
///*
// * Routines with standard 4 prefixes (S, D, C, Z)
// */
//void cblas_sgemv(const enum CBLAS_ORDER order,
//const enum CBLAS_TRANSPOSE TransA, const int M, const int N,
//const float alpha, const float *A, const int lda,
//const float *X, const int incX, const float beta,
//float *Y, const int incY);
//void cblas_sgbmv(const enum CBLAS_ORDER order,
//const enum CBLAS_TRANSPOSE TransA, const int M, const int N,
//const int KL, const int KU, const float alpha,
//const float *A, const int lda, const float *X,
//const int incX, const float beta, float *Y, const int incY);
//void cblas_strmv(const enum CBLAS_ORDER order, const enum CBLAS_UPLO Uplo,
//const enum CBLAS_TRANSPOSE TransA, const enum CBLAS_DIAG Diag,
//const int N, const float *A, const int lda,
//float *X, const int incX);
//void cblas_stbmv(const enum CBLAS_ORDER order, const enum CBLAS_UPLO Uplo,
//const enum CBLAS_TRANSPOSE TransA, const enum CBLAS_DIAG Diag,
//const int N, const int K, const float *A, const int lda,
//float *X, const int incX);
//void cblas_stpmv(const enum CBLAS_ORDER order, const enum CBLAS_UPLO Uplo,
//const enum CBLAS_TRANSPOSE TransA, const enum CBLAS_DIAG Diag,
//const int N, const float *Ap, float *X, const int incX);
//void cblas_strsv(const enum CBLAS_ORDER order, const enum CBLAS_UPLO Uplo,
//const enum CBLAS_TRANSPOSE TransA, const enum CBLAS_DIAG Diag,
//const int N, const float *A, const int lda, float *X,
//const int incX);
//void cblas_stbsv(const enum CBLAS_ORDER order, const enum CBLAS_UPLO Uplo,
//const enum CBLAS_TRANSPOSE TransA, const enum CBLAS_DIAG Diag,
//const int N, const int K, const float *A, const int lda,
//float *X, const int incX);
//void cblas_stpsv(const enum CBLAS_ORDER order, const enum CBLAS_UPLO Uplo,
//const enum CBLAS_TRANSPOSE TransA, const enum CBLAS_DIAG Diag,
//const int N, const float *Ap, float *X, const int incX);
//
//void cblas_dgemv(const enum CBLAS_ORDER order,
//const enum CBLAS_TRANSPOSE TransA, const int M, const int N,
//const double alpha, const double *A, const int lda,
//const double *X, const int incX, const double beta,
//double *Y, const int incY);
//void cblas_dgbmv(const enum CBLAS_ORDER order,
//const enum CBLAS_TRANSPOSE TransA, const int M, const int N,
//const int KL, const int KU, const double alpha,
//const double *A, const int lda, const double *X,
//const int incX, const double beta, double *Y, const int incY);
//void cblas_dtrmv(const enum CBLAS_ORDER order, const enum CBLAS_UPLO Uplo,
//const enum CBLAS_TRANSPOSE TransA, const enum CBLAS_DIAG Diag,
//const int N, const double *A, const int lda,
//double *X, const int incX);
//void cblas_dtbmv(const enum CBLAS_ORDER order, const enum CBLAS_UPLO Uplo,
//const enum CBLAS_TRANSPOSE TransA, const enum CBLAS_DIAG Diag,
//const int N, const int K, const double *A, const int lda,
//double *X, const int incX);
//void cblas_dtpmv(const enum CBLAS_ORDER order, const enum CBLAS_UPLO Uplo,
//const enum CBLAS_TRANSPOSE TransA, const enum CBLAS_DIAG Diag,
//const int N, const double *Ap, double *X, const int incX);
//void cblas_dtrsv(const enum CBLAS_ORDER order, const enum CBLAS_UPLO Uplo,
//const enum CBLAS_TRANSPOSE TransA, const enum CBLAS_DIAG Diag,
//const int N, const double *A, const int lda, double *X,
//const int incX);
//void cblas_dtbsv(const enum CBLAS_ORDER order, const enum CBLAS_UPLO Uplo,
//const enum CBLAS_TRANSPOSE TransA, const enum CBLAS_DIAG Diag,
//const int N, const int K, const double *A, const int lda,
//double *X, const int incX);
//void cblas_dtpsv(const enum CBLAS_ORDER order, const enum CBLAS_UPLO Uplo,
//const enum CBLAS_TRANSPOSE TransA, const enum CBLAS_DIAG Diag,
//const int N, const double *Ap, double *X, const int incX);
//
//void cblas_cgemv(const enum CBLAS_ORDER order,
//const enum CBLAS_TRANSPOSE TransA, const int M, const int N,
//const void *alpha, const void *A, const int lda,
//const void *X, const int incX, const void *beta,
//void *Y, const int incY);
//void cblas_cgbmv(const enum CBLAS_ORDER order,
//const enum CBLAS_TRANSPOSE TransA, const int M, const int N,
//const int KL, const int KU, const void *alpha,
//const void *A, const int lda, const void *X,
//const int incX, const void *beta, void *Y, const int incY);
//void cblas_ctrmv(const enum CBLAS_ORDER order, const enum CBLAS_UPLO Uplo,
//const enum CBLAS_TRANSPOSE TransA, const enum CBLAS_DIAG Diag,
//const int N, const void *A, const int lda,
//void *X, const int incX);
//void cblas_ctbmv(const enum CBLAS_ORDER order, const enum CBLAS_UPLO Uplo,
//const enum CBLAS_TRANSPOSE TransA, const enum CBLAS_DIAG Diag,
//const int N, const int K, const void *A, const int lda,
//void *X, const int incX);
//void cblas_ctpmv(const enum CBLAS_ORDER order, const enum CBLAS_UPLO Uplo,
//const enum CBLAS_TRANSPOSE TransA, const enum CBLAS_DIAG Diag,
//const int N, const void *Ap, void *X, const int incX);
//void cblas_ctrsv(const enum CBLAS_ORDER order, const enum CBLAS_UPLO Uplo,
//const enum CBLAS_TRANSPOSE TransA, const enum CBLAS_DIAG Diag,
//const int N, const void *A, const int lda, void *X,
//const int incX);
//void cblas_ctbsv(const enum CBLAS_ORDER order, const enum CBLAS_UPLO Uplo,
//const enum CBLAS_TRANSPOSE TransA, const enum CBLAS_DIAG Diag,
//const int N, const int K, const void *A, const int lda,
//void *X, const int incX);
//void cblas_ctpsv(const enum CBLAS_ORDER order, const enum CBLAS_UPLO Uplo,
//const enum CBLAS_TRANSPOSE TransA, const enum CBLAS_DIAG Diag,
//const int N, const void *Ap, void *X, const int incX);
//
//void cblas_zgemv(const enum CBLAS_ORDER order,
//const enum CBLAS_TRANSPOSE TransA, const int M, const int N,
//const void *alpha, const void *A, const int lda,
//const void *X, const int incX, const void *beta,
//void *Y, const int incY);
//void cblas_zgbmv(const enum CBLAS_ORDER order,
//const enum CBLAS_TRANSPOSE TransA, const int M, const int N,
//const int KL, const int KU, const void *alpha,
//const void *A, const int lda, const void *X,
//const int incX, const void *beta, void *Y, const int incY);
//void cblas_ztrmv(const enum CBLAS_ORDER order, const enum CBLAS_UPLO Uplo,
//const enum CBLAS_TRANSPOSE TransA, const enum CBLAS_DIAG Diag,
//const int N, const void *A, const int lda,
//void *X, const int incX);
//void cblas_ztbmv(const enum CBLAS_ORDER order, const enum CBLAS_UPLO Uplo,
//const enum CBLAS_TRANSPOSE TransA, const enum CBLAS_DIAG Diag,
//const int N, const int K, const void *A, const int lda,
//void *X, const int incX);
//void cblas_ztpmv(const enum CBLAS_ORDER order, const enum CBLAS_UPLO Uplo,
//const enum CBLAS_TRANSPOSE TransA, const enum CBLAS_DIAG Diag,
//const int N, const void *Ap, void *X, const int incX);
//void cblas_ztrsv(const enum CBLAS_ORDER order, const enum CBLAS_UPLO Uplo,
//const enum CBLAS_TRANSPOSE TransA, const enum CBLAS_DIAG Diag,
//const int N, const void *A, const int lda, void *X,
//const int incX);
//void cblas_ztbsv(const enum CBLAS_ORDER order, const enum CBLAS_UPLO Uplo,
//const enum CBLAS_TRANSPOSE TransA, const enum CBLAS_DIAG Diag,
//const int N, const int K, const void *A, const int lda,
//void *X, const int incX);
//void cblas_ztpsv(const enum CBLAS_ORDER order, const enum CBLAS_UPLO Uplo,
//const enum CBLAS_TRANSPOSE TransA, const enum CBLAS_DIAG Diag,
//const int N, const void *Ap, void *X, const int incX);
//
//
///*
// * Routines with S and D prefixes only
// */
//void cblas_ssymv(const enum CBLAS_ORDER order, const enum CBLAS_UPLO Uplo,
//const int N, const float alpha, const float *A,
//const int lda, const float *X, const int incX,
//const float beta, float *Y, const int incY);
//void cblas_ssbmv(const enum CBLAS_ORDER order, const enum CBLAS_UPLO Uplo,
//const int N, const int K, const float alpha, const float *A,
//const int lda, const float *X, const int incX,
//const float beta, float *Y, const int incY);
//void cblas_sspmv(const enum CBLAS_ORDER order, const enum CBLAS_UPLO Uplo,
//const int N, const float alpha, const float *Ap,
//const float *X, const int incX,
//const float beta, float *Y, const int incY);
//void cblas_sger(const enum CBLAS_ORDER order, const int M, const int N,
//const float alpha, const float *X, const int incX,
//const float *Y, const int incY, float *A, const int lda);
//void cblas_ssyr(const enum CBLAS_ORDER order, const enum CBLAS_UPLO Uplo,
//const int N, const float alpha, const float *X,
//const int incX, float *A, const int lda);
//void cblas_sspr(const enum CBLAS_ORDER order, const enum CBLAS_UPLO Uplo,
//const int N, const float alpha, const float *X,
//const int incX, float *Ap);
//void cblas_ssyr2(const enum CBLAS_ORDER order, const enum CBLAS_UPLO Uplo,
//const int N, const float alpha, const float *X,
//const int incX, const float *Y, const int incY, float *A,
//const int lda);
//void cblas_sspr2(const enum CBLAS_ORDER order, const enum CBLAS_UPLO Uplo,
//const int N, const float alpha, const float *X,
//const int incX, const float *Y, const int incY, float *A);
//
//void cblas_dsymv(const enum CBLAS_ORDER order, const enum CBLAS_UPLO Uplo,
//const int N, const double alpha, const double *A,
//const int lda, const double *X, const int incX,
//const double beta, double *Y, const int incY);
//void cblas_dsbmv(const enum CBLAS_ORDER order, const enum CBLAS_UPLO Uplo,
//const int N, const int K, const double alpha, const double *A,
//const int lda, const double *X, const int incX,
//const double beta, double *Y, const int incY);
//void cblas_dspmv(const enum CBLAS_ORDER order, const enum CBLAS_UPLO Uplo,
//const int N, const double alpha, const double *Ap,
//const double *X, const int incX,
//const double beta, double *Y, const int incY);
//void cblas_dger(const enum CBLAS_ORDER order, const int M, const int N,
//const double alpha, const double *X, const int incX,
//const double *Y, const int incY, double *A, const int lda);
//void cblas_dsyr(const enum CBLAS_ORDER order, const enum CBLAS_UPLO Uplo,
//const int N, const double alpha, const double *X,
//const int incX, double *A, const int lda);
//void cblas_dspr(const enum CBLAS_ORDER order, const enum CBLAS_UPLO Uplo,
//const int N, const double alpha, const double *X,
//const int incX, double *Ap);
//void cblas_dsyr2(const enum CBLAS_ORDER order, const enum CBLAS_UPLO Uplo,
//const int N, const double alpha, const double *X,
//const int incX, const double *Y, const int incY, double *A,
//const int lda);
//void cblas_dspr2(const enum CBLAS_ORDER order, const enum CBLAS_UPLO Uplo,
//const int N, const double alpha, const double *X,
//const int incX, const double *Y, const int incY, double *A);
//
//
///*
// * Routines with C and Z prefixes only
// */
//void cblas_chemv(const enum CBLAS_ORDER order, const enum CBLAS_UPLO Uplo,
//const int N, const void *alpha, const void *A,
//const int lda, const void *X, const int incX,
//const void *beta, void *Y, const int incY);
//void cblas_chbmv(const enum CBLAS_ORDER order, const enum CBLAS_UPLO Uplo,
//const int N, const int K, const void *alpha, const void *A,
//const int lda, const void *X, const int incX,
//const void *beta, void *Y, const int incY);
//void cblas_chpmv(const enum CBLAS_ORDER order, const enum CBLAS_UPLO Uplo,
//const int N, const void *alpha, const void *Ap,
//const void *X, const int incX,
//const void *beta, void *Y, const int incY);
//void cblas_cgeru(const enum CBLAS_ORDER order, const int M, const int N,
//const void *alpha, const void *X, const int incX,
//const void *Y, const int incY, void *A, const int lda);
//void cblas_cgerc(const enum CBLAS_ORDER order, const int M, const int N,
//const void *alpha, const void *X, const int incX,
//const void *Y, const int incY, void *A, const int lda);
//void cblas_cher(const enum CBLAS_ORDER order, const enum CBLAS_UPLO Uplo,
//const int N, const float alpha, const void *X, const int incX,
//void *A, const int lda);
//void cblas_chpr(const enum CBLAS_ORDER order, const enum CBLAS_UPLO Uplo,
//const int N, const float alpha, const void *X,
//const int incX, void *A);
//void cblas_cher2(const enum CBLAS_ORDER order, const enum CBLAS_UPLO Uplo, const int N,
//const void *alpha, const void *X, const int incX,
//const void *Y, const int incY, void *A, const int lda);
//void cblas_chpr2(const enum CBLAS_ORDER order, const enum CBLAS_UPLO Uplo, const int N,
//const void *alpha, const void *X, const int incX,
//const void *Y, const int incY, void *Ap);
//
//void cblas_zhemv(const enum CBLAS_ORDER order, const enum CBLAS_UPLO Uplo,
//const int N, const void *alpha, const void *A,
//const int lda, const void *X, const int incX,
//const void *beta, void *Y, const int incY);
//void cblas_zhbmv(const enum CBLAS_ORDER order, const enum CBLAS_UPLO Uplo,
//const int N, const int K, const void *alpha, const void *A,
//const int lda, const void *X, const int incX,
//const void *beta, void *Y, const int incY);
//void cblas_zhpmv(const enum CBLAS_ORDER order, const enum CBLAS_UPLO Uplo,
//const int N, const void *alpha, const void *Ap,
//const void *X, const int incX,
//const void *beta, void *Y, const int incY);
//void cblas_zgeru(const enum CBLAS_ORDER order, const int M, const int N,
//const void *alpha, const void *X, const int incX,
//const void *Y, const int incY, void *A, const int lda);
//void cblas_zgerc(const enum CBLAS_ORDER order, const int M, const int N,
//const void *alpha, const void *X, const int incX,
//const void *Y, const int incY, void *A, const int lda);
//void cblas_zher(const enum CBLAS_ORDER order, const enum CBLAS_UPLO Uplo,
//const int N, const double alpha, const void *X, const int incX,
//void *A, const int lda);
//void cblas_zhpr(const enum CBLAS_ORDER order, const enum CBLAS_UPLO Uplo,
//const int N, const double alpha, const void *X,
//const int incX, void *A);
//void cblas_zher2(const enum CBLAS_ORDER order, const enum CBLAS_UPLO Uplo, const int N,
//const void *alpha, const void *X, const int incX,
//const void *Y, const int incY, void *A, const int lda);
//void cblas_zhpr2(const enum CBLAS_ORDER order, const enum CBLAS_UPLO Uplo, const int N,
//const void *alpha, const void *X, const int incX,
//const void *Y, const int incY, void *Ap);
//
///*
// * ===========================================================================
// * Prototypes for level 3 BLAS
// * ===========================================================================
// */
//
///*
// * Routines with standard 4 prefixes (S, D, C, Z)
// */
//void cblas_sgemm(const enum CBLAS_ORDER Order, const enum CBLAS_TRANSPOSE TransA,
//const enum CBLAS_TRANSPOSE TransB, const int M, const int N,
//const int K, const float alpha, const float *A,
//const int lda, const float *B, const int ldb,
//const float beta, float *C, const int ldc);
//void cblas_ssymm(const enum CBLAS_ORDER Order, const enum CBLAS_SIDE Side,
//const enum CBLAS_UPLO Uplo, const int M, const int N,
//const float alpha, const float *A, const int lda,
//const float *B, const int ldb, const float beta,
//float *C, const int ldc);
//void cblas_ssyrk(const enum CBLAS_ORDER Order, const enum CBLAS_UPLO Uplo,
//const enum CBLAS_TRANSPOSE Trans, const int N, const int K,
//const float alpha, const float *A, const int lda,
//const float beta, float *C, const int ldc);
//void cblas_ssyr2k(const enum CBLAS_ORDER Order, const enum CBLAS_UPLO Uplo,
//const enum CBLAS_TRANSPOSE Trans, const int N, const int K,
//const float alpha, const float *A, const int lda,
//const float *B, const int ldb, const float beta,
//float *C, const int ldc);
//void cblas_strmm(const enum CBLAS_ORDER Order, const enum CBLAS_SIDE Side,
//const enum CBLAS_UPLO Uplo, const enum CBLAS_TRANSPOSE TransA,
//const enum CBLAS_DIAG Diag, const int M, const int N,
//const float alpha, const float *A, const int lda,
//float *B, const int ldb);
//void cblas_strsm(const enum CBLAS_ORDER Order, const enum CBLAS_SIDE Side,
//const enum CBLAS_UPLO Uplo, const enum CBLAS_TRANSPOSE TransA,
//const enum CBLAS_DIAG Diag, const int M, const int N,
//const float alpha, const float *A, const int lda,
//float *B, const int ldb);
//
//void cblas_dgemm(const enum CBLAS_ORDER Order, const enum CBLAS_TRANSPOSE TransA,
//const enum CBLAS_TRANSPOSE TransB, const int M, const int N,
//const int K, const double alpha, const double *A,
//const int lda, const double *B, const int ldb,
//const double beta, double *C, const int ldc);
//void cblas_dsymm(const enum CBLAS_ORDER Order, const enum CBLAS_SIDE Side,
//const enum CBLAS_UPLO Uplo, const int M, const int N,
//const double alpha, const double *A, const int lda,
//const double *B, const int ldb, const double beta,
//double *C, const int ldc);
//void cblas_dsyrk(const enum CBLAS_ORDER Order, const enum CBLAS_UPLO Uplo,
//const enum CBLAS_TRANSPOSE Trans, const int N, const int K,
//const double alpha, const double *A, const int lda,
//const double beta, double *C, const int ldc);
//void cblas_dsyr2k(const enum CBLAS_ORDER Order, const enum CBLAS_UPLO Uplo,
//const enum CBLAS_TRANSPOSE Trans, const int N, const int K,
//const double alpha, const double *A, const int lda,
//const double *B, const int ldb, const double beta,
//double *C, const int ldc);
//void cblas_dtrmm(const enum CBLAS_ORDER Order, const enum CBLAS_SIDE Side,
//const enum CBLAS_UPLO Uplo, const enum CBLAS_TRANSPOSE TransA,
//const enum CBLAS_DIAG Diag, const int M, const int N,
//const double alpha, const double *A, const int lda,
//double *B, const int ldb);
//void cblas_dtrsm(const enum CBLAS_ORDER Order, const enum CBLAS_SIDE Side,
//const enum CBLAS_UPLO Uplo, const enum CBLAS_TRANSPOSE TransA,
//const enum CBLAS_DIAG Diag, const int M, const int N,
//const double alpha, const double *A, const int lda,
//double *B, const int ldb);
//
//void cblas_cgemm(const enum CBLAS_ORDER Order, const enum CBLAS_TRANSPOSE TransA,
//const enum CBLAS_TRANSPOSE TransB, const int M, const int N,
//const int K, const void *alpha, const void *A,
//const int lda, const void *B, const int ldb,
//const void *beta, void *C, const int ldc);
//void cblas_csymm(const enum CBLAS_ORDER Order, const enum CBLAS_SIDE Side,
//const enum CBLAS_UPLO Uplo, const int M, const int N,
//const void *alpha, const void *A, const int lda,
//const void *B, const int ldb, const void *beta,
//void *C, const int ldc);
//void cblas_csyrk(const enum CBLAS_ORDER Order, const enum CBLAS_UPLO Uplo,
//const enum CBLAS_TRANSPOSE Trans, const int N, const int K,
//const void *alpha, const void *A, const int lda,
//const void *beta, void *C, const int ldc);
//void cblas_csyr2k(const enum CBLAS_ORDER Order, const enum CBLAS_UPLO Uplo,
//const enum CBLAS_TRANSPOSE Trans, const int N, const int K,
//const void *alpha, const void *A, const int lda,
//const void *B, const int ldb, const void *beta,
//void *C, const int ldc);
//void cblas_ctrmm(const enum CBLAS_ORDER Order, const enum CBLAS_SIDE Side,
//const enum CBLAS_UPLO Uplo, const enum CBLAS_TRANSPOSE TransA,
//const enum CBLAS_DIAG Diag, const int M, const int N,
//const void *alpha, const void *A, const int lda,
//void *B, const int ldb);
//void cblas_ctrsm(const enum CBLAS_ORDER Order, const enum CBLAS_SIDE Side,
//const enum CBLAS_UPLO Uplo, const enum CBLAS_TRANSPOSE TransA,
//const enum CBLAS_DIAG Diag, const int M, const int N,
//const void *alpha, const void *A, const int lda,
//void *B, const int ldb);
//
//void cblas_zgemm(const enum CBLAS_ORDER Order, const enum CBLAS_TRANSPOSE TransA,
//const enum CBLAS_TRANSPOSE TransB, const int M, const int N,
//const int K, const void *alpha, const void *A,
//const int lda, const void *B, const int ldb,
//const void *beta, void *C, const int ldc);
//void cblas_zsymm(const enum CBLAS_ORDER Order, const enum CBLAS_SIDE Side,
//const enum CBLAS_UPLO Uplo, const int M, const int N,
//const void *alpha, const void *A, const int lda,
//const void *B, const int ldb, const void *beta,
//void *C, const int ldc);
//void cblas_zsyrk(const enum CBLAS_ORDER Order, const enum CBLAS_UPLO Uplo,
//const enum CBLAS_TRANSPOSE Trans, const int N, const int K,
//const void *alpha, const void *A, const int lda,
//const void *beta, void *C, const int ldc);
//void cblas_zsyr2k(const enum CBLAS_ORDER Order, const enum CBLAS_UPLO Uplo,
//const enum CBLAS_TRANSPOSE Trans, const int N, const int K,
//const void *alpha, const void *A, const int lda,
//const void *B, const int ldb, const void *beta,
//void *C, const int ldc);
//void cblas_ztrmm(const enum CBLAS_ORDER Order, const enum CBLAS_SIDE Side,
//const enum CBLAS_UPLO Uplo, const enum CBLAS_TRANSPOSE TransA,
//const enum CBLAS_DIAG Diag, const int M, const int N,
//const void *alpha, const void *A, const int lda,
//void *B, const int ldb);
//void cblas_ztrsm(const enum CBLAS_ORDER Order, const enum CBLAS_SIDE Side,
//const enum CBLAS_UPLO Uplo, const enum CBLAS_TRANSPOSE TransA,
//const enum CBLAS_DIAG Diag, const int M, const int N,
//const void *alpha, const void *A, const int lda,
//void *B, const int ldb);
//
//
///*
// * Routines with prefixes C and Z only
// */
//void cblas_chemm(const enum CBLAS_ORDER Order, const enum CBLAS_SIDE Side,
//const enum CBLAS_UPLO Uplo, const int M, const int N,
//const void *alpha, const void *A, const int lda,
//const void *B, const int ldb, const void *beta,
//void *C, const int ldc);
//void cblas_cherk(const enum CBLAS_ORDER Order, const enum CBLAS_UPLO Uplo,
//const enum CBLAS_TRANSPOSE Trans, const int N, const int K,
//const float alpha, const void *A, const int lda,
//const float beta, void *C, const int ldc);
//void cblas_cher2k(const enum CBLAS_ORDER Order, const enum CBLAS_UPLO Uplo,
//const enum CBLAS_TRANSPOSE Trans, const int N, const int K,
//const void *alpha, const void *A, const int lda,
//const void *B, const int ldb, const float beta,
//void *C, const int ldc);
//
//void cblas_zhemm(const enum CBLAS_ORDER Order, const enum CBLAS_SIDE Side,
//const enum CBLAS_UPLO Uplo, const int M, const int N,
//const void *alpha, const void *A, const int lda,
//const void *B, const int ldb, const void *beta,
//void *C, const int ldc);
//void cblas_zherk(const enum CBLAS_ORDER Order, const enum CBLAS_UPLO Uplo,
//const enum CBLAS_TRANSPOSE Trans, const int N, const int K,
//const double alpha, const void *A, const int lda,
//const double beta, void *C, const int ldc);
//void cblas_zher2k(const enum CBLAS_ORDER Order, const enum CBLAS_UPLO Uplo,
//const enum CBLAS_TRANSPOSE Trans, const int N, const int K,
//const void *alpha, const void *A, const int lda,
//const void *B, const int ldb, const double beta,
//void *C, const int ldc);
//
//void cblas_xerbla(int p, const char *rout, const char *form, ...);
//
//#ifdef __cplusplus
//}
//#endif
//#endif
