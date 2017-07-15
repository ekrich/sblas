; ModuleID = 'test.c'
source_filename = "test.c"
target datalayout = "e-m:o-i64:64-f80:128-n8:16:32:64-S128"
target triple = "x86_64-apple-macosx10.12.0"

%struct.foo = type { float, float }
%struct.bar = type { float, float }

@f = common global { float, float } zeroinitializer, align 4
@d = common global { double, double } zeroinitializer, align 8
@l = common global { x86_fp80, x86_fp80 } zeroinitializer, align 16
@fc = common global { float, float } zeroinitializer, align 4
@dc = common global { double, double } zeroinitializer, align 8
@lc = common global { x86_fp80, x86_fp80 } zeroinitializer, align 16
@ff = common global %struct.foo zeroinitializer, align 4
@bb = common global %struct.bar zeroinitializer, align 4
@farr = common global [2 x float] zeroinitializer, align 4

!llvm.module.flags = !{!0}
!llvm.ident = !{!1}

!0 = !{i32 1, !"PIC Level", i32 2}
!1 = !{!"Apple LLVM version 8.1.0 (clang-802.0.42)"}
