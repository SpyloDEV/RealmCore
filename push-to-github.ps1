param(
    [string]$RepoUrl = ""
)

if ([string]::IsNullOrWhiteSpace($RepoUrl)) {
    $RepoUrl = Read-Host "Enter your GitHub repository URL"
}

git remote remove origin 2>$null
git remote add origin $RepoUrl
git branch -M main
git push -u origin main
