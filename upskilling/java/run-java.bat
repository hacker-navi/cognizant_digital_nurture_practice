@echo off
setlocal
pushd "%~dp0"

if "%~1"=="" (
    echo Usage: run-java.bat ClassName [args...]
    echo Example: run-java.bat ArrayListExample
    popd
    exit /b 1
)

java %*
popd
