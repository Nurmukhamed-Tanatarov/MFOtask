# MFO Project
# Описание

#### Проект MFO предназначен для управления финансовыми транзакциями. Он предоставляет REST API для выполнения операций с транзакциями, таких как создание, обновление, удаление и получение информации о транзакциях. Проект включает в себя следующие компоненты:

Сервис транзакций: Управляет операциями с транзакциями.
Контроллеры: Обрабатывают запросы от клиентов.
Модели данных: Определяют структуру данных транзакций.
Структура проекта
src/main/java/com/micro/pe/mfo

Контроллер: Контроллеры для обработки запросов.
Сервис: Логика обработки транзакций.
Модель: Модели данных для транзакций.
Репозиторий: Интерфейсы для работы с базой данных.

## Настройка проекта
Клонируйте репозиторий
#### git clone https://github.com/Nurmukhamed-Tanatarov/MFOtask.git
Перейдите в директорию проекта
##### cd MFOtask
Соберите проект

##### mvn clean install

Запустите приложение

##### mvn spring-boot:run
### Использование API
##### Создание транзакции
POST /api/transactions

###### Тело запроса:

{
"amount": 100.0,
"description": "Payment for services",
"date": "2024-08-08"
}

##### Получение транзакции по ID
GET /api/transactions/{id}

###### Ответ:

{
"id": 1,
"amount": 100.0,
"description": "Payment for services",
"date": "2024-08-08"
}

##### Обновление транзакции
PUT /api/transactions/{id}

###### Тело запроса:

{
"amount": 150.0,
"description": "Updated payment description",
"date": "2024-08-09"
}
###### Ответ:

{
"id": 1,
"amount": 150.0,
"description": "Updated payment description",
"date": "2024-08-09"
}

##### Удаление транзакции
DELETE /api/transactions/{id}

###### Ответ:
{
"message": "Transaction deleted successfully"
}

#### Тестирование
Запустите тесты

mvn test

## Ссылки
### GitHub репозиторий проекта

#### https://github.com/Nurmukhamed-Tanatarov/MFOtask.git
