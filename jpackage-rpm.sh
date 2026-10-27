#!/bin/sh

mvn clean package -B

version=$(mvn help:evaluate -Dexpression=project.version -q -DforceStdout)

rm -f target/mines-$version.jar
rm -rf target/archive-tmp   
rm -rf target/classes
rm -rf target/generated-sources
rm -rf target/generated-test-sources
rm -rf target/maven-archiver
rm -rf target/maven-status
rm -rf target/test-classes

if [ "$CI" = "true" ]; then
    JPACKAGE_CMD="jpackage"
else
    # Ruta en tu máquina local
    JPACKAGE_CMD="$HOME/java/jdk-21.0.2/bin/jpackage"
fi

"$JPACKAGE_CMD" --input target/ \
    --name "I-SW Mines" \
    --vendor "I-SW - I. Software" \
    --main-jar mines-$version-jar-with-dependencies.jar \
    --main-class inaki.sw.mines.Mines \
    --app-version $version \
    --icon src/main/resources/inaki/sw/mines/icon/isw-mines-96.png \
    --description "The old minesweeper game, for Linux and Windows." \
    --linux-package-name isw-mines \
    --linux-menu-group Utility \
    --linux-rpm-license-type MIT \
    --type rpm \
    --dest target