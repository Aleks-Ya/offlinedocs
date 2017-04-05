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
 * TODO find/create matcher for verifying directory structure
 *
 * @author Yablokov Aleksey
 */
public class UnpackZipOpTest extends TestBase {
    @Test
    public void execute() throws IOException {
        File srcFile = new File(getClass().getResource("my.zip").getFile());
        File destDir = Files.createTempDirectory(getClass().getSimpleName() + "_").toFile();
        destDir.deleteOnExit();

        UnpackZipOp.InitParams params = new UnpackZipOp.InitParams(srcFile, destDir);
        Operation<UnpackZipOp.InitParams, EmptyExecuteParams, EmptyResult> op =
                executionFactory.getNewOperation(UnpackZipOp.class, params);
        op.execute(EmptyExecuteParams.instance);
        assertThat(destDir.list(), Matchers.arrayContaining("my_zip"));
    }

}