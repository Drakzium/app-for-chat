@ECHO OFF
@REM Minimal launcher: prefer Android Studio Gradle sync if wrapper JAR is missing.
SETDIR=%~dp0
IF EXIST "%DIR%gradle\wrapper\gradle-wrapper.jar" (
  WHERE java >NUL 2>&1
  IF ERRORLEVEL 1 (
    ECHO Java not found. Install JDK 17 or open the project in Android Studio.
    EXIT /B 1
  )
  java -jar "%DIR%gradle\wrapper\gradle-wrapper.jar" %*
) ELSE (
  ECHO gradle-wrapper.jar missing.
  ECHO Open this project in Android Studio once to generate the Gradle wrapper, then retry.
  EXIT /B 1
)
