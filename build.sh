#!/bin/sh
mkpkg PKGBUILD
repoman -a target/zzw.blink.demo-*.tar.gz --repo 11.239.142.113:18080
repoman -a target/zzw.blink.demo-*.tar.gz --repo isearch-software-sh.vip.alidc.net:8080