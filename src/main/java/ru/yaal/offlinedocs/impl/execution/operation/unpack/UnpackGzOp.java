package ru.yaal.offlinedocs.impl.execution.operation.unpack;

import lombok.Getter;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.yaal.offlinedocs.impl.execution.EmptyExecParams;
import ru.yaal.offlinedocs.impl.execution.EmptyResult;
import ru.yaal.offlinedocs.impl.execution.operation.AbstractOp;
import ru.yaal.offlinedocs.impl.execution.operation.download.DownloadHelper;

import java.io.*;
import java.util.zip.GZIPInputStream;

/**
 * TODO extract archive operations to separated bean for performance testing and tuning
 *
 * @author Yablokov Aleksey
 */
@Component
@Scope("prototype")
public class UnpackGzOp extends AbstractOp<UnpackGzOp.InitParams, EmptyExecParams, EmptyResult> {

    private final Logger LOG = LoggerFactory.getLogger(UnpackGzOp.class);

    public UnpackGzOp(InitParams initParams) {
        super(initParams);
    }

    @Override
    @SneakyThrows
    public EmptyResult execute(EmptyExecParams execParams) {
        File srcFile = getInitParams().getSrcFile();
        File destFile = getInitParams().getDestFile();
        LOG.info("Unpacking {} to {}", srcFile, destFile);
        InputStream is = new GZIPInputStream(new FileInputStream(srcFile));
        OutputStream os = new FileOutputStream(destFile);
        long copied = DownloadHelper.copyInputStreamToOutputStream(is, os, 5_000_000);
        os.close();
        is.close();
        LOG.debug("Unpacked {} bytes: {} to {}", copied, srcFile, destFile);
        return EmptyResult.instance;
    }

    @Getter
    public static class InitParams implements ru.yaal.offlinedocs.api.execution.InitParams {
        private final File srcFile;
        private final File destFile;

        public InitParams(File srcFile, File destFile) {
            this.srcFile = srcFile;
            this.destFile = destFile;
        }
    }
}
