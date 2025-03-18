setup:
	./gradlew wrapper --gradle-version 8.5
	./gradlew build

clean:
	./gradlew clean

build:
	./gradlew clean build

reload-classes:
	./gradlew -t classes

install:
	./gradlew installDist

lint:
	./gradlew checkstyleMain checkstyleTest

test:
	./gradlew test

report:
	./gradlew jacocoTestReport

check-java-deps:
	./gradlew dependencyUpdates -Drevision=release

.PHONY: build