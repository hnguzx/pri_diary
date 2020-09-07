package per.guzx.pri_diary.core;


import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Condition;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * 基于通用MyBatis Mapper插件的Service接口的实现
 */
public abstract class AbstractService<T> implements Service<T> {

    @Autowired
    protected MyBatisMapper<T> myBatisMapper;

    private Class<T> modelClass;    // 当前泛型真实类型的Class

    public AbstractService() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        modelClass = (Class<T>) pt.getActualTypeArguments()[0];
    }

    public void save(T model) {
        myBatisMapper.insertSelective(model);
    }

    public void save(List<T> models) {
        myBatisMapper.insertList(models);
    }

    public void deleteById(Integer id) {
        myBatisMapper.deleteByPrimaryKey(id);
    }

    public void deleteByIds(String ids) {
        myBatisMapper.deleteByIds(ids);
    }

    public void update(T model) {
        myBatisMapper.updateByPrimaryKeySelective(model);
    }

    public T findById(Integer id) {
        return myBatisMapper.selectByPrimaryKey(id);
    }

    @Override
    public T findBy(String fieldName, Object value) throws TooManyResultsException {
        try {
            T model = modelClass.newInstance();
            Field field = modelClass.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(model, value);
            return myBatisMapper.selectOne(model);
        } catch (ReflectiveOperationException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List<T> findByIds(String ids) {
        return myBatisMapper.selectByIds(ids);
    }

    public List<T> findByCondition(Condition condition) {
        return myBatisMapper.selectByCondition(condition);
    }

    public List<T> findAll() {
        return myBatisMapper.selectAll();
    }
}
