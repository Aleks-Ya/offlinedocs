package ru.yaal.offlinedocs.impl.execution.id;

import org.springframework.stereotype.Component;
import ru.yaal.offlinedocs.api.execution.id.IdFactory;
import ru.yaal.offlinedocs.api.execution.job.Job;
import ru.yaal.offlinedocs.api.execution.job.JobId;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO Make IdFactoryImpl thread safety
 *
 * @author Yablokov Aleksey
 */
@Component
public class IdFactoryImpl implements IdFactory {
    private Map<Class<? extends Job>, Integer> nextIds = new HashMap<>();

    @Override
    public JobId getJobId(Class<? extends Job> jobClass) {
        Integer nextId = nextIds.get(jobClass);
        if (nextId == null) {
            nextId = 0;
        }
        JobId jobId = new JobIdImpl(jobClass.getSimpleName() + "-" + nextId);
        nextIds.put(jobClass, ++nextId);
        return jobId;
    }
}
