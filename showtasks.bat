call runcrud
if "%ERRORLEVEL%" == "0" goto Chrome
echo.
echo Error during calling runcrud.bat - breaking work
goto fail

:Chrome
start chrome --new-window http://localhost:8080/crud/v1/task/getTasks
if "%ERRORLEVEL%" == "0" goto end
echo Cannot open "http://localhost:8080/crud/v1/task/getTasks"
goto fail

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished