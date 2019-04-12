/*
 * Run to produced test.ll file:
 * clang -S -emit-llvm test.c
*/
#include <complex.h>
complex float f;
complex double d;
complex long double l;

float complex fc;
double complex dc;
long double complex  lc;

typedef struct foo {
    float re;
    float im;
}foo;

foo ff;

struct bar {
    float re;
    float im;
};

struct bar bb;

float farr[2];