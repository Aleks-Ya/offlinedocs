package ru.yaal.offlinedocs.impl.execution.operation.unpack;

import org.junit.Test;
import ru.yaal.offlinedocs.impl.TestBase;
import ru.yaal.offlinedocs.impl.execution.param.EmptyExecParams;
import ru.yaal.offlinedocs.impl.execution.id.JustId;

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
        execFactory.getNewOperation(JustId.jobId, UnpackGzOp.class, params).execute(EmptyExecParams.instance);
        String content = Files.readAllLines(destFile.toPath(), Charset.forName("windows-1251")).stream().collect(Collectors.joining());
        assertThat(content, equalTo("file_content"));
    }

}