package ze.duado.compress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ze.duado.compress.service.Compress;
import ze.duado.compress.service.IOUtil;

@Component
public class CompressManager {

    private final IOUtil ioUtil;

    private final IOProperties ioProperties;

    private final Compress lz4;

    private final Compress zstd;

    private static Logger LOG = LoggerFactory.getLogger(CompressApplication.class);

    @Autowired
    public CompressManager(IOUtil ioUtil, @Qualifier("lz4") Compress lz4, @Qualifier("zstd") Compress zstd, IOProperties ioProperties) {
        this.ioUtil = ioUtil;
        this.lz4 = lz4;
        this.zstd = zstd;
        this.ioProperties = ioProperties;
    }

    private void runTest(String inputParam, String outputParam, Compress compressor) {
        LOG.info("############## "+ compressor.getName() +" ##############");
        byte[] input = read(inputParam);
        double start = System.nanoTime();
        byte[] output = compressor.compress(input);
        double finish = System.nanoTime();
        LOG.info("input-size:    " + input.length);
        LOG.info("output-size:   " + output.length);
        LOG.info(compressor.getName() + " duração de compressão: " + (finish - start)/1000000 + " ms");
        start = System.nanoTime();
        byte[] restored = compressor.decompress(output, input.length);
        finish = System.nanoTime();
        LOG.info("output-size:   " + output.length);
        LOG.info("restored-size: " + restored.length);
        LOG.info(compressor.getName() + " duração de decompressão: " + (finish - start)/1000000 + " ms");
        write(output, outputParam.concat("." + compressor.getName()));
        write(restored, outputParam.concat("." + compressor.getName()).concat(".restored"));
    }

    public void lz4(String inputParam, String outputParam) {
        runTest(inputParam, outputParam, lz4);
    }

    public void zstd(String inputParam, String outputParam) {
        runTest(inputParam, outputParam, zstd);
    }

    public void all(String inputParam, String outputParam) {
        LOG.info("---------------------------------");
        double start = System.nanoTime();
        lz4(inputParam, outputParam);
        zstd(inputParam, outputParam);
        double finish = System.nanoTime();
        LOG.info("---------------------------------");
        LOG.info("Duração total: " + (finish - start)/1000000 + " ms");
    }

    public void fullTest() {
        LOG.info("STARTING");
        System.out.println();
        LOG.info("Arquivo com conteudo zero");
        all(ioProperties.getNullFile(), ioProperties.getNullFile());
        System.out.println();
        LOG.info("Arquivo com conteudo random");
        all(ioProperties.getRandomFile(), ioProperties.getRandomFile());
        System.out.println();
        LOG.info("Arquivo com conteudo random seguido de zeros");
        all(ioProperties.getSemiRandomFile(), ioProperties.getSemiRandomFile());
        System.out.println();
    }

    private byte[] read(String inputParam) {
        byte[] input;
        if(inputParam == null || inputParam.isEmpty()) {
            input = ioUtil.read();
        } else {
            input = ioUtil.read(inputParam);
        }
        return  input;
    }

    private void write(byte[] output, String outputParam) {
        if(outputParam == null || outputParam.isEmpty()) {
            ioUtil.write(output);
        } else {
            ioUtil.write(output, outputParam);
        }
    }

    public void full() {

    }
}
