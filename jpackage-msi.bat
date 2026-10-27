@echo off
setlocal

for /f "tokens=3 delims=<>" %%a in ('findstr /i "<version>" pom.xml') do (
    set version=%%a
    goto :found
)
:found

if "%version%"=="" (
    set /p version=Version: 
)

call mvn clean package -B

del /q target\mines-%version%.jar
rmdir /s /q target\archive-tmp
rmdir /s /q target\classes
rmdir /s /q target\generated-sources
rmdir /s /q target\generated-test-sources
rmdir /s /q target\maven-archiver
rmdir /s /q target\maven-status
rmdir /s /q target\test-classes

jpackage --input target/ ^
    --name "I-SW Mines" ^
    --vendor "I-SW - I. Software" ^
    --main-jar mines-%version%-jar-with-dependencies.jar ^
    --main-class inaki.sw.mines.Mines ^
    --app-version %version% ^
    --icon src\main\resources\inaki\sw\mines\icon\isw-mines-96.ico ^
    --description "The old minesweeper game, for Linux and Windows." ^
	--win-dir-chooser ^
	--win-menu ^
	--win-menu-group I-SW ^
	--win-shortcut ^
	--win-shortcut-prompt ^
    --type msi ^
    --dest target

if "%CI%"=="" pause
endlocal