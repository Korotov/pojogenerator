package by.mvn;

import by.mvn.annotations.GenerateField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *
 */
public class PojoGenerator {
    public static Logger logger = LogManager.getLogger(PojoGenerator.class);

    public static  <T> T randomPojo(Class<T> clazz){
        T resultEntity = null;

        try {
            resultEntity = clazz.newInstance();
        } catch (InstantiationException e) {
            logger.error("New exception:" + e);
        } catch (IllegalAccessException e) {
            logger.error("New exception:" + e);
        }

        Field[] fields = clazz.getDeclaredFields();
        for(Field field : fields) {
            if (field.isAnnotationPresent(GenerateField.class)) {
                GenerateField annotation = field.getAnnotation(GenerateField.class);
                String setterName = getSetterNameByField(field);
                try {
                    Method method = clazz.getDeclaredMethod(setterName, field.getType());
                    String fieldValue = getStringByGeneratorType(annotation.type());
                    if (field.getType().isAssignableFrom(String.class)) {
                        method.invoke(resultEntity, fieldValue);
                    }
                    if (field.getType().equals(Integer.TYPE)) {

                    }

                } catch (NoSuchMethodException e) {
                    logger.error("Setter:" + setterName + " not found:" + e);
                } catch (InvocationTargetException e) {
                    logger.error("New exception:" + e);
                } catch (IllegalAccessException e) {
                    logger.error("New exception:" + e);
                }

            }
        }
        return resultEntity;
    }

    private static String getSetterNameByField(Field field){
        char[] fieldName = field.getName().trim().toCharArray();
        fieldName[0] = Character.toUpperCase(fieldName[0]);
        return "set"+new String(fieldName);

    }
    private static String getStringByGeneratorType(GeneratorType type) throws ClassCastException{
        switch (type){
            case FIRSTNAME:
                return Generate.randomFirstName();
            case LASTNAME:
                return Generate.randomSecondName();
            case CITY:
                return Generate.randomCity();
            case COUNTRY:
                return Generate.randomCountry();
            case EMAIL:
                return Generate.randomEmail();
            case STREET:
                return Generate.randomStreet();
            case PASSWORD:
                return "123";
            case INTVALUE:
                throw new ClassCastException();
            case READABLEWORD:
                return Generate.readableWord();
            default:
                throw new ClassCastException();
        }
    }
}
