#!/usr/bin/env bash
# Lanzador para Linux / macOS: comprueba Java, arranca la app y abre el navegador.
set -e
cd "$(dirname "$0")"

URL="http://localhost:8080"

echo ""
echo "  Balanced Scorecard - Southwest 2025"
echo "  -----------------------------------"
echo ""

if ! command -v java >/dev/null 2>&1; then
  echo "  [X] No se encontro Java. Instala el JDK 21 y vuelve a intentarlo."
  echo "      macOS:  brew install --cask temurin@21"
  echo "      Linux:  sudo apt install openjdk-21-jdk"
  exit 1
fi

MAJOR=$(java -version 2>&1 | sed -n 's/.*version "\([0-9][0-9]*\).*/\1/p' | head -1)
MAJOR=${MAJOR:-0}
if [ "$MAJOR" -ge 21 ]; then
  echo "  [OK] Java $MAJOR detectado."
else
  echo "  [!]  Tu Java es la version $MAJOR; el proyecto necesita la 21."
  echo "      Si tienes otro JDK 21 instalado, Gradle lo usara automaticamente."
  echo ""
fi

if [ -n "$(command -v curl)" ] && curl -sf "$URL" >/dev/null 2>&1; then
  echo "  [OK] La aplicacion ya esta en marcha."
  if command -v xdg-open >/dev/null 2>&1; then xdg-open "$URL" >/dev/null 2>&1 & elif command -v open >/dev/null 2>&1; then open "$URL"; fi
  exit 0
fi

# Abrir el navegador cuando el servidor responda
(
  i=0
  while [ "$i" -lt 600 ]; do
    if curl -sf "$URL" >/dev/null 2>&1; then
      if command -v xdg-open >/dev/null 2>&1; then xdg-open "$URL" >/dev/null 2>&1
      elif command -v open >/dev/null 2>&1; then open "$URL"
      fi
      break
    fi
    i=$((i + 1))
    sleep 1
  done
) >/dev/null 2>&1 &

echo ""
echo "  Iniciando la aplicacion..."
echo "  La primera vez descarga Gradle y las dependencias (varios minutos)."
echo "  Deja esta terminal abierta mientras uses la app."
echo ""

exec ./gradlew bootRun --console=plain
