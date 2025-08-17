# Proba99

Базовый Java проект на Gradle для экспериментов и обучения.

## Структура проекта

```
proba99/
├── src/
│   ├── main/java/com/example/     # Исходный код
│   └── test/java/com/example/     # Тесты
├── gradle/wrapper/                # Gradle Wrapper файлы
├── build.gradle                   # Конфигурация сборки
├── settings.gradle                # Настройки проекта
├── gradlew                        # Gradle Wrapper (Unix/Mac)
├── gradlew.bat                    # Gradle Wrapper (Windows)
└── .gitignore                     # Игнорируемые файлы
```

## Технологии

- **Java 17+**
- **Gradle 8.10.2**
- **JUnit 5** для тестирования
- **SLF4J + Logback** для логирования

## Быстрый старт

### Сборка проекта
```bash
./gradlew build
```

### Запуск приложения
```bash
./gradlew run
```

### Запуск тестов
```bash
./gradlew test
```

### Создание JAR файла
```bash
./gradlew jar
```

## Требования

- Java 17 или выше
- Интернет соединение для скачивания зависимостей (при первом запуске)

## Полезные команды Gradle

```bash
# Очистка build директории
./gradlew clean

# Полная пересборка
./gradlew clean build

# Показать зависимости
./gradlew dependencies

# Показать все задачи
./gradlew tasks
```