### Tests and linter status:
[![Actions Status](https://github.com/sergi-Jr/1221-systems/actions/workflows/main.yml/badge.svg)](https://github.com/sergi-Jr/1221-systems/actions)

## Description
Это тестовое задание на позицию Intern Java developer в 1221Системс

## Technologies and system requirements
**JDK 21**  
**Gradle 8.5**  
**Potgres 14.6**  
**make cli**

## How to use
API использует 2 профиля - дефолтный указан dev в yml-конфиге, он коннектится к H2-mem-db.  
Чтобы использовать prod-профиль и коннектится к Postgres:  
- Укажите prod как SPRING_PROFILE_ACTIVE переменную окружения  
- Укажите необходимые для jdbc данные для подключения к postgres:  
- - JDBC_DATABASE_URL  
- - USERNAME
- - PASSWORD

Например, вот так  
- JDBC_DATABASE_URL: jdbc:postgresql://localhost:5432/mydatabase  
- USERNAME: myuser  
- PASSWORD: mypassword

Соответственно, в БД должно быть тоже самое.

Весь API можно увидеть с помощью swagger. После запуска API переходите на [my-swagger-api](http://localhost:8080/swagger-ui/index.html)

Подготовка Gradle Wrapper:

```./gradlew wrapper --gradle-version 8.5```

Сборка проекта:

```./gradlew build```

Команда выполнит очистку и полную сборку проекта.

```./gradlew clean```

Тестирование проекта:  

```./gradlew test```


  
