SUMMARY = "SocketCAN over Ethernet tunnel using UDP to transfer CAN frames between two machines"
SECTION = "socketcan"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"
PR = "r0"

## Please, use cannelloni version 1.0.0
## https://github.com/mguentner/cannelloni/commit/0bd7e27db35bdef361226882ae04205504f7b2f4

## The very next one produces g++ compiler errors
## https://github.com/mguentner/cannelloni/commit/d01dd1dc745914d129b1f4da2074e282253246af

## SRCREV = "${AUTOREV}"
SRCREV = "0bd7e27db35bdef361226882ae04205504f7b2f4"
SRC_URI = "git://github.com/mguentner/cannelloni.git;protocol=https"

S = "${WORKDIR}/git"

inherit pkgconfig cmake

EXTRA_OECMAKE += "-DCMAKE_BUILD_TYPE=Release"

## cannelloni-dev-1.0 is registered as shlib provider for libcannelloni-common.so.0
SOLIBS = ".so"
FILES_SOLIBSDEV = ""

do_install() {
	install -d ${D}${libdir}
	install -m 0755 libcannelloni-common.so ${D}${libdir}
}
