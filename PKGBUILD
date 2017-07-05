#the package name
%NAME%
zzw.blink.demo

#the package version
%VERSION%
0.0.1

#the package description
%DESC%
flink.demo for blink from zzw

#files copy to target dir
%COPY_FILES%


%BUILD_FUNC%
cd $SVN_ROOT
mvn clean package -Pbuild-jar -Dmaven.test.skip
mkdir -p $PKG_ROOT/{bin,log,lib,etc}
cp -r $SVN_ROOT/bin/*   $PKG_ROOT/bin/
cp -r $SVN_ROOT/target/lib/*   $PKG_ROOT/lib/
cp $SVN_ROOT/target/*.jar $PKG_ROOT/