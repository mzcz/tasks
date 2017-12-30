call runcrud.bat
if "%ERRORLEVEL%" == "0" goto startBrowser
echo.
echo script runcrud.bat has errors - breaking work
goto fail

:startBrowser
start chrome http://localhost:8080/crud/v1/task/getTasks
goto end

:fail
echo.
echo There were errors

:end
echo.
echo Application started...