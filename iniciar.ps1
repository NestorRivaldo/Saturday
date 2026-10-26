param(
    [switch]$NoBrowser
)

$ErrorActionPreference = 'Stop'
[Console]::OutputEncoding = [System.Text.Encoding]::UTF8

$root = Split-Path -Parent $MyInvocation.MyCommand.Path
$url  = 'http://localhost:8080'

function Write-Ok($t)   { Write-Host "  [OK] $t" -ForegroundColor Green }
function Write-Warn($t) { Write-Host "  [!]  $t" -ForegroundColor Yellow }
function Write-Err($t)  { Write-Host "  [X]  $t" -ForegroundColor Red }

Write-Host ''
Write-Host '  Balanced Scorecard - Southwest 2025' -ForegroundColor Cyan
Write-Host '  -----------------------------------' -ForegroundColor DarkGray
Write-Host ''

# ---------------------------------------------------------------------
# 1. Comprobar Java
# ---------------------------------------------------------------------
$javaExe = $null
$java = Get-Command java -ErrorAction SilentlyContinue
if ($java) {
    $javaExe = $java.Source
} elseif ($env:JAVA_HOME -and (Test-Path (Join-Path $env:JAVA_HOME 'bin\java.exe'))) {
    $javaExe = Join-Path $env:JAVA_HOME 'bin\java.exe'
}

if (-not $javaExe) {
    Write-Err 'No se encontro Java. Instala el JDK 21 y vuelve a intentarlo:'
    Write-Host '       winget install Microsoft.OpenJDK.21' -ForegroundColor White
    Write-Host ''
    exit 1
}

# java -version escribe en stderr; cmd lo redirige a stdout para que PowerShell
# no lo trate como error terminante (NativeCommandError).
$versionText = (& cmd.exe /c "`"$javaExe`" -version 2>&1" | Out-String)
$major = 0
if ($versionText -match 'version "(\d+)') { $major = [int]$Matches[1] }

if ($major -ge 21) {
    Write-Ok "Java $major detectado."
} else {
    Write-Warn "Tu Java del PATH es la version $major; el proyecto necesita la 21."
    Write-Host '       Si tienes otro JDK 21 instalado, Gradle lo usara automaticamente.' -ForegroundColor DarkGray
    Write-Host '       Si no:  winget install Microsoft.OpenJDK.21' -ForegroundColor White
    Write-Host ''
}

# ---------------------------------------------------------------------
# 2. Si ya esta corriendo, solo abrir el navegador
# ---------------------------------------------------------------------
$listening = Get-NetTCPConnection -LocalPort 8080 -State Listen -ErrorAction SilentlyContinue
if ($listening) {
    $alive = $false
    try { $alive = (Invoke-WebRequest -Uri $url -UseBasicParsing -TimeoutSec 3).StatusCode -eq 200 } catch { $alive = $false }

    if ($alive) {
        Write-Ok 'La aplicacion ya esta en marcha.'
        if (-not $NoBrowser) { Start-Process $url }
        exit 0
    }

    Write-Err 'El puerto 8080 esta ocupado por otro programa.'
    Write-Host '       Cierralo o arranca en otro puerto:' -ForegroundColor White
    Write-Host '       gradlew.bat bootRun --args="--server.port=8081"' -ForegroundColor DarkGray
    Write-Host ''
    exit 1
}

# ---------------------------------------------------------------------
# 3. Abrir el navegador en cuanto el servidor responda
# ---------------------------------------------------------------------
if (-not $NoBrowser) {
    Start-Job -ScriptBlock {
        param($u)
        for ($i = 0; $i -lt 600; $i++) {
            try {
                if ((Invoke-WebRequest -Uri $u -UseBasicParsing -TimeoutSec 2).StatusCode -eq 200) {
                    Start-Process $u
                    return
                }
            } catch { }
            Start-Sleep -Seconds 1
        }
    } -ArgumentList $url | Out-Null

    Write-Ok 'El navegador se abrira solo cuando termine de arrancar.'
}

Write-Host ''
Write-Host '  Iniciando la aplicacion...' -ForegroundColor Cyan
Write-Host '  La primera vez descarga Gradle y las dependencias (varios minutos).' -ForegroundColor DarkGray
Write-Host '  Deja esta ventana abierta mientras uses la app.' -ForegroundColor DarkGray
Write-Host ''

Push-Location $root
try {
    & (Join-Path $root 'gradlew.bat') bootRun --console=plain
} finally {
    Pop-Location
}
