<h2 align="center">Ваш первый плагин</h2>
	<p align="center">
		Вообще тут будут описаны лишь основы разработки под это ядро, тк большая часть смешна с разработкой на либе Minestom (На которой это ядро и написано).
	</p>
<h3 align="center">О Topaz-API</h3>
	<p align="center">
		Такс.. Пока самой апишки нет на maven репозиториях - Вам придется либо скопировать её в проект, либо самостоятельно сбилдить.
		<br>
		Для билда - Можно прописать:
		"./gradlew :Decadence-API:publishToMavenLocal"
		<br><br>
		Строка, требуемая для импорта в проект будет выведена во время билда.
		<br>
		Будет что-то типо:
		"decadence.api:topaz_api:0.0.0-DEV"
		<br>
		<img src="../../../img/docs/ApiImportStringSample.png" alt="Строка импорта api">
		<br>
		Скриншот устарел,
		<br>
		`delta.cion` было заменено на `decadence` во всех пакетах
	</p>
