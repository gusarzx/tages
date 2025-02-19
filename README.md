****
### **Проект тестирования TAGES**  
*Совместимость проекта:* **JDK 21**
****

##### **Запуск по тегам**
###### 1. Для локального запуска необходимо в config.json указать путь до драйвера и выставить grid false
###### 2. Для удаленного запуска необходимо в config.json grid true и указать url
* Запуск всех регрессионных тестов 
  `mvn clean test -Dgroups=regress`
* Запуск тестов "Переход между вкладками"
  `mvn clean test -Dgroups=navigation`
* Запуск тестов "Проверка содержимого в блоках"
  `mvn clean test -Dgroups=content`
* Запуск тестов "Ссылки на сторонний ресурс"
  `mvn clean test -Dgroups=strangerLink`
* Запуск тестов "Ссылки внутри нашего вэб сервиса"
  `mvn clean test -Dgroups=ourLink`
* Запуск тестов "Валидация полей для отправки запроса"
  `mvn clean test -Dgroups=validationSend`
* Запуск тестов из класса "CheckElementOnMainPageTest"
  `mvn clean test -Dtest=CheckElementOnMainPageTest`
  ****
