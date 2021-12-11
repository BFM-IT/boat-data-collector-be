@echo off
echo ============== Setting JAVA_HOME ==============
set JAVA_HOME=C:\Program Files\AdoptOpenJDK\jdk-11.0.10.9-hotspot
echo OK
echo ============== setting PATH ==============
set PATH=C:\Program Files\AdoptOpenJDK\jdk-11.0.10.9-hotspot\bin;%PATH%
echo OK
echo ============== Display java version ==============
java -version
echo ============== Building project... (clean; package) ==============
call mvnw.cmd -DskipTests clean package
echo ============== Copying jar to server ==============
pscp D:\boat-data-collector-be\target\boat-data-collector-be-*.jar fm_srv_for_deploy:/app/boat-data-collector-be
echo ============== Running docker deploy ==============
plink -no-antispoof fm_srv_for_deploy  /app/boat-data-collector-be/deploy.sh
exit