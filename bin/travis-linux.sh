#!/usr/bin/env bash
# Code from Scala Native

# Enable strict mode and fail the script on non-zero exit code,
# unresolved variable or pipe failure.
set -euo pipefail
IFS=$'\n\t'

# Remove libunwind pre-bundled with clang
sudo find /usr -name "*libunwind*" -delete

# Install re2
# Starting from Ubuntu 16.04 LTS, it'll be available as http://packages.ubuntu.com/xenial/libre2-dev
sudo apt-get install -y make
export CXX=clang++-3.9
git clone https://code.googlesource.com/re2
pushd re2
git checkout 2017-03-01
make -j4 test
sudo make install prefix=/usr
make testinstall prefix=/usr
popd