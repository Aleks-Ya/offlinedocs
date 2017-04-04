package ru.yaal.offlinedocs.impl.execution.operation.unpack;

import lombok.Getter;
import lombok.SneakyThrows;
import org.codehaus.plexus.archiver.tar.TarGZipUnArchiver;
import org.codehaus.plexus.archiver.zip.ZipUnArchiver;
import org.codehaus.plexus.logging.console.ConsoleLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.yaal.offlinedocs.impl.execution.EmptyExecuteParams;
import ru.yaal.offlinedocs.impl.execution.EmptyResult;
import ru.yaal.offlinedocs.impl.execution.operation.AbstractOp;

import java.io.File;

/**
 * @author Yablokov Aleksey
 */
@Component
@Scope("prototype")
public class UnpackZipOp extends AbstractOp<UnpackZipOp.InitParams, EmptyExecuteParams, EmptyResult> {

    private final Logger LOG = LoggerFactory.getLogger(UnpackZipOp.class);

    public UnpackZipOp(InitParams initParams) {
        super(initParams);
    }

    @Override
    @SneakyThrows
    public EmptyResult execute(EmptyExecuteParams executeParams) {
        File srcFile = getInitParams().getSrcFile();
        File destFile = getInitParams().getDestDir();
        LOG.info("Unpacking {} to {}", srcFile, destFile);

        final ZipUnArchiver ua = new ZipUnArchiver();
        ua.setSourceFile(srcFile);
        ua.enableLogging(new ConsoleLogger(org.codehaus.plexus.logging.Logger.LEVEL_DEBUG, "console_logger"));
        ua.setDestDirectory(destFile);
        ua.extract();

        LOG.debug("Unpacked: {} to {}", srcFile, destFile);
        return EmptyResult.instance;
    }

    @Getter
    public static class InitParams implements ru.yaal.offlinedocs.api.execution.InitParams {
        private final File srcFile;
        private final File destDir;

        public InitParams(File srcFile, File destDir) {
            this.srcFile = srcFile;
            this.destDir = destDir;
        }
    }
}
