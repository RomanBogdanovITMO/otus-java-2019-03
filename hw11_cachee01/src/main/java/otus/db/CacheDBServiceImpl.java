package otus.db;

import lombok.AllArgsConstructor;
import otus.cache.CacheEngine;
import otus.dataset.UserDataSet;

import java.util.Objects;
import java.util.logging.Logger;

@AllArgsConstructor
public class CacheDBServiceImpl implements DBService {

    static Logger logger = Logger.getLogger(CacheDBServiceImpl.class.getName());

    private final CacheEngine<Long, UserDataSet> cache;

    @Override
    public void create(final UserDataSet dataSet) {
        logger.info("execute method create: ");

        final DBServiceHiber serviceHiber = new DBServiceHiber();

        serviceHiber.create(dataSet);
        cache.put(dataSet.getId(), dataSet);

        serviceHiber.create(dataSet);
        cache.put(dataSet.getId(), dataSet);
    }

    @Override
    public UserDataSet load(final long id) {
        logger.info("execute method load: ");

        final DBServiceHiber serviceHiber = new DBServiceHiber();
        UserDataSet dataSet = cache.get(id);

        if (Objects.isNull(dataSet)) {

            dataSet = serviceHiber.load(id);

            if (Objects.nonNull(dataSet))

                cache.put(dataSet.getId(), dataSet);
        }
        return serviceHiber.load(id);
    }


}
