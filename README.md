<h3>Project simulation</h3>

<b>1. Создать JavaFX приложение в окне которого есть:</b><br>
<li>поле для ввода текста
<li>кнопка - search. При нажатии очищаются предыдущие результаты поиска(если есть) 
и загружаются новые в асинхронном потоке.
<br><br>Программа должна использовать YouTube API для отображения результатов поиска 
видео.

<b>В результатах должно содержаться:</b>
<li>Название видео
<li>Название канала
<li>Дата публикации
<li>Кнопка - View. При нажатии на которую воспроизводиться видео в окне программы.

<h3>Task2</h3>
В результатах поиска добавляется 5-ый элемент - изображение из видео (thumbnails)
Во время загрузки изображений программа не должна зависать, что значит - загрузка 
должна выполняется НЕ в главном потоке программы.

<h3>Task 3</h3>
Добавить в приложение кнопку - advanced. <br>Находиться она должна справа от кнопки search. 
<br>После нажатия на эту кнопку на экране должны отобразиться дополнительные поля:
maxResults. <br>Максимальное кол-во результатов в выдаче.
Кол-во дней. Если видео было опубликовано раньше чем Х дней назад, 
значит его не надо отображать в поиске.

<h3>Task 4</h3>
В результатах поиска поле - Название канала должно быть кликабельным.
<br>При нажатии на него, в программе пропадает отображение результатов поиска. 
<b>Вместо них отображается информация канала на весь экран приложения.</b>
<br><br>Элементе экрана Канала:
<ul>
<li>Поле для ввода в поиск (как в задании 1)
<li>Кнопка - search (как в задании 1)
<li>Кнопка - advanced (как в задании 3)
<li>Аватарка канала
<li>Название канала
<li>Описание канала
</ul>
<br>Список из 10 последних загруженных видео. В списке каждое видео состоит из:
<ul>
<li>Название видео
<li>Название канала
<li>Дата публикации
<li>Кнопка - View. При нажатии на которую воспроизводиться видео в окне программы.
</ul>