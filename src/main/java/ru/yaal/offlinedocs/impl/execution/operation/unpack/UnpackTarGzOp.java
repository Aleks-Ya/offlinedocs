package ru.yaal.offlinedocs.impl.execution.operation.unpack;

import lombok.Getter;
import lombok.SneakyThrows;
import org.codehaus.plexus.archiver.tar.TarGZipUnArchiver;
import org.codehaus.plexus.components.io.fileselectors.FileSelector;
import org.codehaus.plexus.logging.console.ConsoleLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.yaal.offlinedocs.impl.execution.EmptyExecParams;
import ru.yaal.offlinedocs.impl.execution.EmptyResult;
import ru.yaal.offlinedocs.impl.execution.operation.AbstractOp;

import java.io.File;

/**
 * @author Yablokov Aleksey
 */
@Component
@Scope("prototype")
public class UnpackTarGzOp extends AbstractOp<UnpackTarGzOp.InitParams, EmptyExecParams, EmptyResult> {

    private final Logger LOG = LoggerFactory.getLogger(UnpackTarGzOp.class);

    public UnpackTarGzOp(UnpackTarGzOp.InitParams initParams) {
        super(initParams);
    }

    @Override
    @SneakyThrows
    public EmptyResult execute(EmptyExecParams execParams) {
        File srcFile = getInitParams().getSrcFile();
        File destDir = getInitParams().getDestDir();
        //noinspection ResultOfMethodCallIgnored
        destDir.mkdirs();
        LOG.info("Unpacking {} to {}", srcFile, destDir);

        final TarGZipUnArchiver ua = new TarGZipUnArchiver();
        ua.setSourceFile(srcFile);
        ua.enableLogging(new ConsoleLogger(org.codehaus.plexus.logging.Logger.LEVEL_DEBUG, "console_logger"));
        ua.setDestDirectory(destDir);
        ua.setFileSelectors(getInitParams().getFileSelectors());
        ua.extract();

        LOG.debug("Unpacked: {} to {}", srcFile, destDir);
        return EmptyResult.instance;
    }

    @Getter
    public static class InitParams implements ru.yaal.offlinedocs.api.execution.InitParams {
        private final File srcFile;
        private final File destDir;
        private final FileSelector[] fileSelectors;

        public InitParams(File srcFile, File destDir, FileSelector[] fileSelectors) {
            this.srcFile = srcFile;
            this.destDir = destDir;
            this.fileSelectors = fileSelectors;
        }
    }
}
