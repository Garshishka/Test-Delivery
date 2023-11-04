# Test-Delivery
Стартовый экран мобильного приложения по доставке еды. Тестовое задание от компании Hammer-systems.

Основной необходимый рабочий функционал - получение по любому подходящему api информации и еде на доставку в основном скролл-окне приложения. Коллапсируемые рекламные баннеры при скролле основного окна вниз.

## Состав UI
Экран состоит из [основной Activity с верхним и нижним меню](https://github.com/Garshishka/Test-Delivery/blob/master/app/src/main/java/ru/garshishka/testdelivery/MainActivity.kt) ([xml верстка](https://github.com/Garshishka/Test-Delivery/blob/master/app/src/main/res/layout/activity_main.xml)) и одним [Fragment внутри с содержанием страницы "Меню"](https://github.com/Garshishka/Test-Delivery/blob/master/app/src/main/java/ru/garshishka/testdelivery/ui/main/MainFragment.kt) ([xml верстка]( https://github.com/Garshishka/Test-Delivery/blob/master/app/src/main/res/layout/fragment_main.xml))

Для отображения списка еды и рекламных баннеров используется Recycler View. [Xml верстка еды](https://github.com/Garshishka/Test-Delivery/blob/master/app/src/main/res/layout/layout_food_item.xml), [Xml верстка баннера](https://github.com/Garshishka/Test-Delivery/blob/master/app/src/main/res/layout/layout_banner_picture.xml). Посредством Adapter'ов и ViewHolder'ов они добавляются на экран фрагмента ([еда](https://github.com/Garshishka/Test-Delivery/blob/master/app/src/main/java/ru/garshishka/testdelivery/ui/viewholder/FoodItemViewHolder.kt), [баннеры](https://github.com/Garshishka/Test-Delivery/blob/master/app/src/main/java/ru/garshishka/testdelivery/ui/viewholder/BannerPictureViewHolder.kt)).

## Получение информации

Для получения информации о доступной к доставке еде использовался сервис создания случайных данных для web-api [mockAPI](https://mockapi.io/). GET вызов на https://6543f4f201b5e279de21322b.mockapi.io/food/product выдает Json файл, который содержит:
1. Случайные слова в качестве наименования еды
2. Случайные слова описания
3. Случайную цифру для цены
4. Ссылку на картинку с сайта случайных фото [loremflick](https://loremflickr.com)
5. Номер по порядку для идентификации

## Отображение полученной информации

Запрос отправляется при помощи Retrofit через [сервис](https://github.com/Garshishka/Test-Delivery/blob/master/app/src/main/java/ru/garshishka/testdelivery/webapi/ApiService.kt). 

Полученные данные сохраняются в базу данных на основе ROOM в [репозитории](https://github.com/Garshishka/Test-Delivery/blob/master/app/src/main/java/ru/garshishka/testdelivery/repository/FoodRepositoryImpl.kt) и оттуда передаются в LiveData. 

[ViewModel](https://github.com/Garshishka/Test-Delivery/blob/master/app/src/main/java/ru/garshishka/testdelivery/ui/main/FoodViewModel.kt), присоединенная к фрагменту, отслеживает эту LiveData и заполняет ей адаптер основного списка. 

База данных используется для ситуации отсутствия интернета - при неудачном запросе на экране будет отображен последний удачный результат.

## DI

Для Dependency Injection в проекте используется Dagger. [Модули](https://github.com/Garshishka/Test-Delivery/tree/master/app/src/main/java/ru/garshishka/testdelivery/di) 
