# NTM Extended Updated

[Русский](#русский) • [English](#english)

---

## Русский

**NTM Extended Updated** – неофициальный обновляемый форк **HBM Nuclear Tech Mod - 1.12.2 Extended Edition** для **Minecraft Forge 1.12.2**.

Цель этого форка – продолжать поддержку версии **1.12.2**, исправлять критические ошибки, улучшать стабильность в больших модпаках и аккуратно добавлять патчи, не ломая основную механику оригинального мода.

> **Важно:** это неофициальный форк. Проект не является официальным продолжением от оригинальных авторов и не заменяет оригинальный NTM Extended.

### Что планируется

В дальнейшем этот форк будет развиваться в направлении стабильности и удобства для сборок Minecraft 1.12.2:

- исправление крашей и критических ошибок;
- оптимизация опасных механик, которые могут вызывать зависания или падения мира;
- улучшение совместимости с крупными Forge 1.12.2-модпаками;
- аккуратные performance-oriented патчи;
- добавление конфигов для спорных/опасных механик;
- улучшение диагностики проблемных блоков и чанков;
- сохранение общей идеи и атмосферы HBM/NTM.

### Уже добавлено / изменено в этом форке

- Исправлена проблема рекурсивного срабатывания горючего и взрывоопасного газа.
- Добавлена защита от бесконечной цепочки `gas -> fire/explosion -> neighbour update -> gas`.
- Добавлен лимитер газовых реакций за один tick.
- Уменьшены каскадные обновления соседних блоков при движении и удалении газа.
- Добавлены дополнительные проверки состояния блока перед движением и воспламенением газа.
- Улучшена стабильность для больших сборок Forge 1.12.2.

### Статус проекта

Проект находится в активной доработке как **updated fork** под Minecraft **1.12.2**.

Основной фокус:

- стабильность;
- совместимость;
- исправление крашей;
- удобство для модпаков;
- сохранение оригинального gameplay-ядра NTM/HBM.

### Установка

1. Установите **Minecraft 1.12.2**.
2. Установите **Minecraft Forge 14.23.5.2859+**.
3. Скачайте `.jar` из раздела **Releases**.
4. Переместите `.jar` в папку:

```txt
.minecraft/mods
```

1. Запустите игру.

### Сборка из исходников

Для сборки рекомендуется использовать **Java 8 / JDK 8**.

```bat
.\gradlew.bat clean build
```

После завершения сборки готовый `.jar` будет находиться в:

```txt
build/libs
```

### Для модпаков

Этот форк ориентирован на использование в крупных Forge 1.12.2-сборках.  
Если вы используете мод в своём модпаке, рекомендуется:

- тестировать новый мир после обновления;
- делать резервные копии перед заменой `.jar`;
- не держать одновременно оригинальный NTM Extended и этот форк в папке `mods`;
- проверять crash-reports при переходе между версиями.

### Авторство и происхождение

Этот проект основан на **HBM Nuclear Tech Mod - 1.12.2 Extended Edition**.

Цепочка происхождения оригинального проекта:

- оригинальный мод: [HBMTheBobcat](https://github.com/HbmMods/Hbm-s-Nuclear-Tech-GIT);
- порт на 1.12.2: [Drilon200](https://github.com/Drillgon200/Hbm-s-Nuclear-Tech-GIT);
- форк: [TheOriginalGolem](https://github.com/TheOriginalGolem/Hbm-s-Nuclear-Tech-GIT);
- Extended Edition: [Alliterate](https://github.com/Alcatergit/Hbm-s-Nuclear-Tech-GIT).

Этот репозиторий является неофициальным обновляемым форком с дополнительными исправлениями и патчами.

### Лицензия

Проект сохраняет лицензию оригинального репозитория.

См. файлы:

```txt
LICENSE
LICENSE.LESSER
```

---

## English

**NTM Extended Updated** is an unofficial-updated fork of **HBM Nuclear Tech Mod - 1.12.2 Extended Edition** for **Minecraft Forge 1.12.2**.

The goal of this fork is to continue maintaining the **1.12.2** version, fix critical crashes, improve stability in large modpacks, and add careful patches without breaking the core gameplay of the original mod.

> **Important:** this is an unofficial fork. This project is not an official continuation by the original maintainers and does not replace the original NTM Extended project.

### Planned development

This fork will continue to focus on Minecraft **1.12.2** stability and modpack usability:

- fixing crashes and critical bugs;
- optimizing dangerous mechanics that may freeze or crash worlds;
- improving compatibility with large Forge 1.12.2 modpacks;
- adding careful performance-oriented patches;
- adding configuration options for risky mechanics;
- improving diagnostics for problematic blocks and chunks;
- preserving the original HBM/NTM atmosphere and gameplay direction.

### Added / changed in this fork

- Fixed recursive flammable/explosive gas combustion crash.
- Added protection against infinite `gas -> fire/explosion -> neighbour update -> gas` chains.
- Added a per-tick gas combustion limiter.
- Reduced neighbor-update cascades during gas movement and removal.
- Added additional block-state checks before gas movement and combustion.
- Improved stability for large Forge 1.12.2 modpacks.

### Project status

This project is maintained as an **updated fork** for Minecraft **1.12.2**.

Main focus:

- stability;
- compatibility;
- crash fixes;
- modpack usability;
- preserving the original NTM/HBM gameplay core.

### Installation

1. Install **Minecraft 1.12.2**.
2. Install **Minecraft Forge 14.23.5.2859+**.
3. Download the `.jar` file from **Releases**.
4. Put the `.jar` into:

```txt
.minecraft/mods
```

1. Launch the game.

### Building from source

Java 8 / JDK 8 is recommended.

```bat
.\gradlew.bat clean build
```

After the build finishes, the output `.jar` will be located in:

```txt
build/libs
```

### For modpacks

This fork is intended for large Forge 1.12.2 modpacks.  
If you use it in a modpack, it is recommended to:

- test a new world after updating;
- make backups before replacing the `.jar`;
- never keep both original NTM Extended and this fork in the `mods` folder at the same time;
- check crash reports when migrating between versions.

### Credits and origin

This project is based on **HBM Nuclear Tech Mod - 1.12.2 Extended Edition**.

Original project lineage:

- original mod: [HBMTheBobcat](https://github.com/HbmMods/Hbm-s-Nuclear-Tech-GIT);
- 1.12.2 port: [Drilon200](https://github.com/Drillgon200/Hbm-s-Nuclear-Tech-GIT);
- fork: [TheOriginalGolem](https://github.com/TheOriginalGolem/Hbm-s-Nuclear-Tech-GIT);
- Extended Edition: [Alliterate](https://github.com/Alcatergit/Hbm-s-Nuclear-Tech-GIT).

This repository is an unofficial-updated fork with additional fixes and patches.

### License

This project keeps the license of the original repository.

See:

```txt
LICENSE
LICENSE.LESSER
```
