@echo off
echo Initializing Git repository...
git init
git add .
git commit -m "Initial commit - RealmCore"
git branch -M main
echo Done. Now add your remote:
echo git remote add origin YOUR_GITHUB_URL
echo git push -u origin main
pause
