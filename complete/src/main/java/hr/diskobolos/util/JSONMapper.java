package hr.diskobolos.util;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * The class that is used for recursive mapping of objects in JSONObject.
 *
 * @author Tomislav Čavka
 *
 */
@Component
public class JSONMapper {

    private static final Logger logger = LoggerFactory.getLogger(JSONMapper.class);

    public JSONObject getJSONObject(final Object object) throws JSONException {
        return getJSONObject(object, null, null);
    }

    public JSONArray getJSONArray(final Object object) throws JSONException {
        return getJSONArray(object, null, null);
    }

    /**
     * Main method for recursive mapping of java objects to JSONObject
     *
     * @param object Object that needs to be mapped
     * @param fields List of field names of the root object separated with
     * comma. Fields that are not in the list will not be mapped.
     * @param mappingLevel Depth that will be reached with recursive calls
     * during the mapping process
     * @return
     * @throws JSONException
     */
    public JSONObject getJSONObject(final Object object, final String fields, final Integer mappingLevel) throws JSONException {
        if (object == null) {
            return new JSONObject();
        }

        Object resObj = getJSONObjectInner(object, fields, mappingLevel);
        if (resObj instanceof JSONObject) {
            return (JSONObject) resObj;
        }
        return new JSONObject(resObj);
    }

    public JSONArray getJSONArray(final Object object, final String fields, final Integer mappingLevel) throws JSONException {
        Object retObj = getJSONObjectInner(object, fields, mappingLevel);

        if (retObj instanceof JSONArray) {
            return (JSONArray) retObj;
        }
        return new JSONArray();
    }

    private Object getJSONObjectInner(final Object object, final String fields, final Integer mappingLevel) throws JSONException {
        int level = 6;// default depth (4)
        if (mappingLevel != null && mappingLevel > 0) {
            level = mappingLevel + 2;
        }

        if (object == null) {
            return null;
        }

        String[] farray = null;
        if (fields != null) {
            farray = fields.split(",");
        }

        return mapObject2JSON(object, farray, level);
    }

    private Object mapObject2JSON(final Object object, final String[] fields, final int level) throws JSONException {
        if (object == null) {
            return null;
        }

        if (level <= 0) {
            return null;
        }

        if (object instanceof JSONObject || object instanceof JSONArray) {
            return object;
        }

        int mappingLevel = -1;
        if (level > -1) {
            mappingLevel = level - 1;
        }

        if (object.getClass().equals(java.sql.Timestamp.class) || object.getClass().equals(java.sql.Date.class)) {
            return new SimpleDateFormat("yyyy-MM-dd").format(object);
        }

        if (object.getClass().isArray() || object instanceof Collection) {
            return mapObject2JSONArray(object, fields, mappingLevel);
        } else if (object instanceof Map) {
            JSONObject jsonSubobject = new JSONObject();
            for (Object key : ((Map) object).keySet()) {
                // recursive call
                Object resobj = mapObject2JSON(((Map) object).get(key), fields, mappingLevel);
                jsonSubobject.put(key.toString(), resobj);
            }
            return jsonSubobject;
        } else if (isWrapperType(object.getClass())) {
            return object;
        }
        JSONObject jsonResult = new JSONObject();

        if (fields != null) {
            ArrayList<String> mappedFields = new ArrayList<String>();
            try {
                for (String fieldName : fields) {
                    fieldName = fieldName.trim();
                    if (fieldName.length() == 0) {
                        continue;
                    }
                    int ind = fieldName.indexOf('.');
                    String[] subfields = new String[0];
                    if (ind > -1) {
                        String field = fieldName.substring(0, ind);
                        if (mappedFields.contains(field)) {
                            continue;
                        }
                        ArrayList<String> sublist = new ArrayList<String>();
                        // passing by through all the specified subfields of current field
                        for (String fname : fields) {
                            if (fname.startsWith(field + ".")) {
                                String subfield = fname.substring(field.length() + 1).trim();
                                if (subfield.length() == 0) {
                                    continue;
                                }
                                sublist.add(subfield);
                            }
                        }
                        subfields = sublist.toArray(subfields);
                        fieldName = field;
                    } else {
                        subfields = null;
                    }

                    if (mappedFields.contains(fieldName)) {
                        continue;
                    }

                    try {
                        Object fieldValue = PropertyUtils.getProperty(object, fieldName);
                        mapSubobject(jsonResult, fieldName, fieldValue, mappingLevel, subfields);
                        mappedFields.add(fieldName);
                    } catch (NoSuchMethodException noe) {
                        continue;
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        continue;
                    }
                }
            } finally {
                mappedFields.clear();
            }
        } else {
            try {
                Map<String, Object> descriptors = PropertyUtils.describe(object);
                for (String fieldName : descriptors.keySet()) {
                    if (fieldName.equals("class")) {
                        continue;
                    }
                    Object fieldValue = descriptors.get(fieldName);
                    mapSubobject(jsonResult, fieldName, fieldValue, mappingLevel, null);
                }
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                logger.error("JSONMapper: Exception while mapping " + object.getClass(), e);
            }
        }
        return jsonResult;
    }

    /**
     * Method that is used for determination is object that needs to be mapped a
     * collection (Collection, Array, Map)
     *
     * @param jsonResult
     * @param fieldName
     * @param fieldValue
     * @param mappingLevel
     * @param subfields
     */
    private void mapSubobject(JSONObject jsonResult, String fieldName, Object fieldValue, int mappingLevel,
            String[] subfields) {
        if (fieldValue == null) {
            return;
        }
        try {
            if (fieldValue.getClass().isArray() || fieldValue instanceof Collection) {
                if (mappingLevel < 1) {
                    return;
                }
                jsonResult.put(fieldName, mapObject2JSONArray(fieldValue, subfields, mappingLevel));
            } else if (fieldValue instanceof Map) {
                if (mappingLevel < 1) {
                    return;
                }
                JSONObject jsonSubobject = new JSONObject();
                for (Object key : ((Map) fieldValue).keySet()) {
                    // recursive call
                    jsonSubobject.put(key.toString(),
                            mapObject2JSON(((Map) fieldValue).get(key), subfields, mappingLevel));
                }
                jsonResult.put(fieldName, jsonSubobject);
            } else if (isWrapperType(fieldValue.getClass())) {
                if (fieldValue.getClass().equals(java.sql.Timestamp.class)
                        || fieldValue.getClass().equals(java.sql.Date.class)) {
                    jsonResult.put(fieldName, new SimpleDateFormat("yyyy-MM-dd").format(fieldValue));
                } else {
                    jsonResult.put(fieldName, fieldValue);
                }
            } else {
                jsonResult.put(fieldName, mapObject2JSON(fieldValue, subfields, mappingLevel));
            }
        } catch (Exception e) {
            logger.error("JSONMapper: Exception while mapping subobject " + fieldName, e);
        }
    }

    private JSONArray mapObject2JSONArray(Object object, String[] fields, int mappingLevel) throws JSONException {
        if (object == null) {
            return null;
        }
        if (mappingLevel <= 0) {
            return null;
        }

        JSONArray array = new JSONArray();

        if (object instanceof Collection) {
            for (Object subObject : ((Collection) object)) {
                Object obj = mapObject2JSON(subObject, fields, mappingLevel);
                array.put(obj);
            }
        } else if (object.getClass().isArray()) {
            int length = Array.getLength(object);
            for (int i = 0; i < length; i++) {
                Object subObject = Array.get(object, i);
                array.put(mapObject2JSON(subObject, fields, mappingLevel - 1));
            }
        }

        return array;
    }

    private static final Set<Class<?>> WRAPPER_TYPES = getWrapperTypes();

    public static boolean isWrapperType(Class<?> clazz) {
        return clazz.isEnum() || WRAPPER_TYPES.contains(clazz);
    }

    private static Set<Class<?>> getWrapperTypes() {
        Set<Class<?>> ret = new HashSet<Class<?>>();
        ret.add(String.class);
        ret.add(java.sql.Timestamp.class);
        ret.add(Boolean.class);
        ret.add(Character.class);
        ret.add(BigDecimal.class);
        ret.add(java.util.Date.class);
        ret.add(Byte.class);
        ret.add(Short.class);
        ret.add(Integer.class);
        ret.add(Long.class);
        ret.add(Float.class);
        ret.add(Double.class);
        ret.add(Void.class);
        return ret;
    }
}
