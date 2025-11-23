# Run script for ExpenseTracker (PowerShell)
# Requires JDK 21 installed and either JAVA_HOME set or java on PATH.

if ($env:JAVA_HOME) {
    $java = Join-Path $env:JAVA_HOME "bin\java.exe"
} else {
    $java = "java"
}

try {
    & $java -version > $null 2>&1
} catch {
    Write-Error "java not found. Please install JDK 21 and set JAVA_HOME or add java to PATH."
    exit 1
}

# Ensure classes exist
if (-not (Test-Path .\out)) {
    Write-Error "Compiled classes not found. Run .\build.ps1 first."
    exit 1
}

$cp = "out;lib\gson-2.10.jar"
& $java -cp $cp main.ExpenseTrackerApp
