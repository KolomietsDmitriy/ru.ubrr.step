.DEFAULT_GOAL := build-run

build-run: build run

run:
	java -jar target/ru.ubrr.step-1.0-SNAPSHOT-jar-with-dependencies.jar

clean:
	rd /s/q target

build:
	mvnw clean package

update:
	mvnw versions:update-properties