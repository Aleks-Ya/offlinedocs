package ru.yaal.offlinedocs.impl.execution.id;

import org.springframework.stereotype.Component;
import ru.yaal.offlinedocs.api.execution.id.IdFactory;
import ru.yaal.offlinedocs.api.execution.job.Job;
import ru.yaal.offlinedocs.api.execution.job.JobId;
import ru.yaal.offlinedocs.api.execution.operation.OpId;
import ru.yaal.offlinedocs.api.execution.operation.Operation;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO Make IdFactoryImpl thread safety
 *
 * @author Yablokov Aleksey
 */
@Component
class IdFactoryImpl implements IdFactory {
    private Map<Class<? extends Job>, Integer> nextJobIds = new HashMap<>();
    private Map<JobId, Map<Class<? extends Operation>, Integer>> nextOpIds = new HashMap<>();

    @Override
    public JobId getJobId(Class<? extends Job> jobClass) {
        Integer nextId = nextJobIds.get(jobClass);
        if (nextId == null) {
            nextId = 0;
        }
        JobId jobId = new JobIdImpl(jobClass.getSimpleName() + "-" + nextId);
        nextJobIds.put(jobClass, ++nextId);
        return jobId;
    }

    @Override
    public OpId getOpId(JobId jobId, Class<? extends Operation> opClass) {
        Map<Class<? extends Operation>, Integer> nextIdMap = nextOpIds.computeIfAbsent(jobId, k -> new HashMap<>());
        Integer nextId = nextIdMap.get(opClass);
        if (nextId == null) {
            nextId = 0;
        }
        nextIdMap.put(opClass, nextId + 1);
        return new OpIdImpl(jobId, opClass.getSimpleName() + "-" + nextId);
    }
}
