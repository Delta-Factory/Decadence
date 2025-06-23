package delta.cion.server.plugins.utils;

import java.io.File;
import java.util.ArrayList;

public abstract class PluginsData {

	public abstract ArrayList<Plugin> getPLUGINS();
	public abstract void setPLUGINS(ArrayList<Plugin> plugins);
	public abstract void cleanPLUGINS();

	public abstract PluginState getPluginStatus(Plugin plugin);
	public abstract void setPluginsStatus(Plugin plugin, PluginState state);

	public abstract File getPluginFile(Plugin plugin);
	public abstract void setPluginFile(Plugin plugin, File file);


}

/** Старт сервера
 * 		Старт плагинов
 * 			Чек всех плагинов и добавление в лист
 *	 			Старт по одному
 * 					Плагин Файл
 * 					Мейн класс
 * 					Обновление статуса (Плагин Статус)
 * 				Вывос стартовых и ошибок
 * 		Отключение плагинов
 * 				Получение из списка плагинов и сравнение статуса
 * 				Включен - Отключение
 * 					Мейн класс
 * 					Удаление из листов   <--
 * 						Список плагинов  <--
 * 						Мапа статусов	 <--
 * 				Нет - Удаление из листов ^^^
 *
 */
