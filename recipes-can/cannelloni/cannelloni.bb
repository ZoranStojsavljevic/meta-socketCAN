SUMMARY = "SocketCAN over Ethernet tunnel using UDP to transfer CAN frames between two machines"
SECTION = "socketcan"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"
PR = "r0"

<<<<<<< HEAD
DEPENDS = "${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'systemd', '', d)}"

## SRCREV = "${AUTOREV}"
SRCREV = "0fb6880b719b8acf2b4210b264b7140135e4be8a"
SRC_URI = "git://github.com/mguentner/cannelloni \
           file://launch_cannelloni.sh \
           file://launch_cannelloni.service"
=======
SRCREV = "${AUTOREV}"
SRC_URI = "git://github.com/mguentner/cannelloni.git;protocol=https"
>>>>>>> Finalizing meta-socketcan recipes SRCREV => $AUTOREV

S = "${WORKDIR}/git"

inherit pkgconfig cmake

EXTRA_OECMAKE += "-DCMAKE_BUILD_TYPE=Release"

INSANE_SKIP_${PN} = "ldflags"
INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"
SOLIBS = ".so"
FILES_SOLIBSDEV = ""

do_install() {
	install -d ${D}${bindir}
	install -m 0755 cannelloni ${D}${bindir}

	## ERROR: QA Issue: package cannelloni contains bad RPATH
	## quick fix is in a do_install or do_install_append do
	chrpath -d ${D}${bindir}/cannelloni

	install -d ${D}${libdir}
	install -m 0755 ${WORKDIR}/build/libcannelloni-common.so ${D}${libdir}
}
