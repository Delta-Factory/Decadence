package decadence.api.database;

import java.util.Date;

public class TypeHandler {

	public static Object getTypedObject(Object object, ObjectType type) {
		return switch (type) {
			case INTEGER 	-> (Integer) object;
			case BOOLEAN 	-> (boolean) object;
			case STRING  	-> (String)  object;
			case DOUBLE	 	-> (double)  object;
			case FLOAT   	-> (float)   object;
			case LONG    	-> (long)    object;
			case DATE    	-> (Date)    object;
		};
	}

	public static ObjectType getTypeOfObject(Object object) {
		return switch (object.getClass().getSimpleName()) {
			case "Integer", "int" 		-> ObjectType.INTEGER;
			case "Boolean", "boolean" 	-> ObjectType.BOOLEAN;
			case "Double", 	"double" 	-> ObjectType.DOUBLE;
			case "Float", 	"float"		-> ObjectType.FLOAT;
			case "Long", 	"long" 		-> ObjectType.LONG;
			case "Date" 				-> ObjectType.DATE;
			default 					-> ObjectType.STRING;
		};
	}

}
