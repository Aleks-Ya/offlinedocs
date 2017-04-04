package ru.yaal.offlinedocs.impl.execution.operation.unpack;

import org.hamcrest.Matchers;
import org.junit.Test;
import ru.yaal.offlinedocs.api.execution.operation.Operation;
import ru.yaal.offlinedocs.impl.TestBase;
import ru.yaal.offlinedocs.impl.execution.EmptyExecuteParams;
import ru.yaal.offlinedocs.impl.execution.EmptyResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.Assert.assertThat;

/**
 * @author Yablokov Aleksey
 */
public class UnpackTarGzOpTest extends TestBase {
    @Test
    public void execute() throws IOException {
        File srcFile = new File(getClass().getResource("my.tar.gz").getFile());
        File destDir = Files.createTempDirectory(getClass().getSimpleName() + "_").toFile();
        destDir.deleteOnExit();
        UnpackTarGzOp.InitParams params = new UnpackTarGzOp.InitParams(srcFile, destDir);
        Operation<UnpackTarGzOp.InitParams, EmptyExecuteParams, EmptyResult> op = executionFactory.getNewOperation(UnpackTarGzOp.class, params);
        op.execute(EmptyExecuteParams.instance);
        assertThat(destDir.list(), Matchers.arrayContaining("mytar"));
    }

}