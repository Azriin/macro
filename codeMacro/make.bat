@echo off
dir src
echo =================================
set /p file="file to compile : "
echo %file%
bcc32 "src/%file%" 
del *.obj *.tds
move "*.exe" "bin"