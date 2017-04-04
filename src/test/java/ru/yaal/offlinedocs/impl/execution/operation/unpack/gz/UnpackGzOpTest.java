package ru.yaal.offlinedocs.impl.execution.operation.unpack.gz;

import org.junit.Test;
import ru.yaal.offlinedocs.api.execution.operation.Operation;
import ru.yaal.offlinedocs.impl.TestBase;
import ru.yaal.offlinedocs.impl.execution.EmptyExecuteParams;
import ru.yaal.offlinedocs.impl.execution.EmptyResult;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author Yablokov Aleksey
 */
public class UnpackGzOpTest extends TestBase {
    @Test
    public void execute() throws IOException {
        File srcFile = new File(getClass().getResource("content.txt.gz").getFile());
        File destFile = File.createTempFile(getClass().getSimpleName() + "_", ".tmp");
        destFile.deleteOnExit();
        UnpackGzOp.InitParams params = new UnpackGzOp.InitParams(srcFile, destFile);
        Operation<UnpackGzOp.InitParams, EmptyExecuteParams, EmptyResult> op = executionFactory.getNewOperation(UnpackGzOp.class, params);
        op.execute(EmptyExecuteParams.instance);
        String content = Files.readAllLines(destFile.toPath(), Charset.forName("windows-1251")).stream().collect(Collectors.joining());
        assertThat(content, equalTo("file_content"));
    }

}