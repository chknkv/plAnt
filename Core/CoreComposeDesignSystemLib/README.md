### Модуль Дизайн Системы

Данный модуль отвечает за дизайн систему в приложении. Весь функционал реализован на [Jetpack Compose](https://developer.android.com/jetpack/compose). Все модули, которые взаимодействуют с пользователем, должны использовать UI-элементы данного модуля.

> Дизайн система — набор компонентов, правил, предписаний и инструментов для разработки качественного интерфейса приложения.

В дизайн системе реализовано большое количество UI-элементов, которые необходимы для качественной и единой разработки, а именно:

- Theme (Preview + Main)
- AlertDialog
- Blueberry
- BottomNavigationBar
- ChipCard
- ChipCardGroup
- DualBlueberryHorizontal
- DualBlueberryVertical
- ProgressCircle
- SnackbarCard
- TextStyles
- TextFieldCard

---

**Theme (Preview + Main)** <br/>
Любое современное приложение должно быть оптимизировано для раз устройств и тем (темная и светлая). Данный модуль несет ответственность за темизацию всего приложения. 
Существует набор токенов, переопределяя которые, меняется цвет элемента.

Также появилась необходимость реализовать preview-тему, которая бы использовалась для предпоказа элемента (используется только для разработки).

**AlertDialog** <br/>
`AlertDialog` - это кастомное всплывающee окно, которое используются для отображения важных сообщений или запросов пользователю. Каждый AlertDialog состоит из заголовка, подзаголовка, одной или двух кнопок действия (определяется при создании), изображения и кнопки закрытия. Пример изображен ниже только в светлой теме.

<img src="https://github.com/coder-chekunkov/plAnt/blob/develop/Core/CoreComposeDesignSystemLib/examples/alert_dialog_preview.png" alt="AlertDialog" width="250"/>

**Blueberry** <br/>
`Blueberry` - это нестандартное название кнопок, которые помогают взаимодействовать с пользователем. Имеется четыре доступных темы: стандарт, предупреждение, текст-кнопка и отключенное состояние. Все типы изображены на рисунке ниже в двух темах (светлая и темная).

<img src="https://github.com/coder-chekunkov/plAnt/blob/develop/Core/CoreComposeDesignSystemLib/examples/blueberry_preview.png" alt="Blueberry" width="500"/>

**BottomNavigationBar** <br/>
`BottomNavigationBar` - это навигационная панель, которая позволяет производить запуск экранов. Навигационная панель "прибита" к низу экрана. Примеры изображены на рисунке ниже в двух темах (светлая и темная).

<img src="https://github.com/coder-chekunkov/plAnt/blob/develop/Core/CoreComposeDesignSystemLib/examples/bottom_navigation_bar_preview.png" alt="BottomNavigationBar" width="500"/>

**ChipCard** <br/>
`ChipCard` - это карточка-чипса, которая используется для выбора каких-то данных. Карточка чипса состоит только из текста и может принимать три состояния - выбранно, невыбранно и предупреждение. Все типы изображены на рисунке ниже в двух темах (светлая и темная).

<img src="https://github.com/coder-chekunkov/plAnt/blob/develop/Core/CoreComposeDesignSystemLib/examples/chipcard_preview.png" alt="ChipCard" width="500"/>

**ChipCardGroup** <br/>
`ChipCardGroup` - это набор карточек-чипсов, которые используются для выбора каких-то данных в наборе. Набор может состоять из n-ого количества карточек-чипсов и работать как RadioButton (одиночный выбор). Пример изображен на gif ниже только в светлой теме.

<img src="https://github.com/coder-chekunkov/plAnt/blob/develop/Core/CoreComposeDesignSystemLib/examples/chipcard_group_preview.gif" alt="ChipCardGroup" width="250"/>

**DualBlueberryHorizontal** <br/>
`DualBlueberryHorizontal` - это набор двух кнопок Blueberry, которые расположены горизонтально. Для каждой кнопки можно настроить свой стиль и поведение. Примеры изображены на рисунке ниже в двух темах (светлая и темная).

<img src="https://github.com/coder-chekunkov/plAnt/blob/develop/Core/CoreComposeDesignSystemLib/examples/dual_blueberry_horizontal_preview.png" alt="DualBlueberryHorizontal" width="500"/>

**DualBlueberryVertical** <br/>
`DualBlueberryHorizontal` - это набор двух кнопок Blueberry, которые расположены вертикально. Для каждой кнопки можно настроить свой стиль и поведение. Примеры изображены на рисунке ниже в двух темах (светлая и темная).

<img src="https://github.com/coder-chekunkov/plAnt/blob/develop/Core/CoreComposeDesignSystemLib/examples/dual_blueberry_vertical_preview.png" alt="DualBlueberryVertical" width="500"/>

**ProgressCircle** <br/>
`ProgressCircle` - это доработанный ProgressBar, который вращается бесконечно. Следует использовать для загрузки каких-либо элементов или отображение статического состояния. Есть возможность добавить текст пояснения. Пример изображен на gif ниже только в темной теме.

<img src="https://github.com/coder-chekunkov/plAnt/blob/develop/Core/CoreComposeDesignSystemLib/examples/progress_bar_circle_preview.gif" alt="ProgressCircle" width="250"/>

**SnackbarCard** <br/>
`SnackbarCard` - это кастомное всплывающee окно с сообщением внизу экрана. Данный компонент состоит из иконки вначале элемента, текста и кнопки закрытия. Элемент способен только уведомлять о каком-то событии. Пример изображен ниже только в светлой теме.

<img src="https://github.com/coder-chekunkov/plAnt/blob/develop/Core/CoreComposeDesignSystemLib/examples/snackbar_preview.png" alt="AlertDialog" width="250"/>


**TextStyles** <br/>
Для отображения пользователю какой-либо информации используются текстовые поля. Модуль дизайн системы поддерживает разные типы текста, которые могут сообщать критическую информацию, быть заголовками и т.д. Названия и внешний вид каждого из текста изображен на рисунке ниже в двух темах (светлая и темная). 

<img src="https://github.com/coder-chekunkov/plAnt/blob/develop/Core/CoreComposeDesignSystemLib/examples/text_styles_preview.png" alt="TextStyles" width="500"/>

**TextFieldCard** <br/>
Для ввода какого-либо текста используются поля ввода. Модуль дизайн системы поддерживает два типа полей ввода: стандартный и предупреждающий. Каждое поле ввода может быть настроенно - видимость подзаголовка, максимальное количество строк, тип клавиатуры и т.д. Примеры изображены на рисунке ниже в двух темах (светлая и темная).

<img src="https://github.com/coder-chekunkov/plAnt/blob/develop/Core/CoreComposeDesignSystemLib/examples/text_field_card_preview.png" alt="TextFieldCard" width="500"/>
