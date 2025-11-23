# Build script for ExpenseTracker (PowerShell)
# Requires JDK 17 installed and either JAVA_HOME set or javac on PATH.

# Resolve javac
if ($env:JAVA_HOME) {
    $javac = Join-Path $env:JAVA_HOME "bin\javac.exe"
} else {
    $javac = "javac"
}

try {
    & $javac -version > $null 2>&1
} catch {
    Write-Error "javac not found. Please install JDK 21 and set JAVA_HOME or add javac to PATH."
    exit 1
}

# Create output directory
New-Item -ItemType Directory -Force -Path .\out | Out-Null

# Collect .java files
$files = Get-ChildItem -Recurse -Filter *.java | ForEach-Object { $_.FullName }
if ($files.Count -eq 0) {
    Write-Error "No Java source files found."
    exit 1
}

# Compile with --release 17 and include gson jar on classpath
$cp = "lib\gson-2.10.jar"
& $javac --release 17 -cp $cp -d out $files

if ($LASTEXITCODE -eq 0) {
    Write-Host "Build succeeded. Classes written to .\out"
} else {
    Write-Error "Build failed with exit code $LASTEXITCODE"
    exit $LASTEXITCODE
}
